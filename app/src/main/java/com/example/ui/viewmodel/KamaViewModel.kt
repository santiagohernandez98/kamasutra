package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PositionRepository
import com.example.data.repository.UserKamaPosition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

// Helper data class to aggregate filters and avoid Kotlin combine vararg type-erasure issues
data class PositionFilters(
    val query: String,
    val difficulty: String?,
    val category: String?,
    val onlyFavs: Boolean,
    val onlyCompleted: Boolean
)

class KamaViewModel(private val repository: PositionRepository) : ViewModel() {

    // Filter states
    val searchQuery = MutableStateFlow("")
    val selectedDifficulty = MutableStateFlow<String?>(null) // "Fácil", "Intermedio", "Avanzado"
    val selectedCategory = MutableStateFlow<String?>(null) // "Acostado", "Sentado", "De Pie"
    val showOnlyFavorites = MutableStateFlow(false)
    val showOnlyCompleted = MutableStateFlow(false)

    // Data streams
    val allPositions: StateFlow<List<UserKamaPosition>> = repository.allUserPositions
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Intermediate stream combining exactly 5 filter states (within standard compile limits)
    private val filtersFlow: StateFlow<PositionFilters> = combine(
        searchQuery,
        selectedDifficulty,
        selectedCategory,
        showOnlyFavorites,
        showOnlyCompleted
    ) { query, difficulty, category, onlyFavs, onlyCompleted ->
        PositionFilters(query, difficulty, category, onlyFavs, onlyCompleted)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PositionFilters("", null, null, false, false)
    )

    // Filtered positions based on active search/filters
    val filteredPositions: StateFlow<List<UserKamaPosition>> = combine(
        allPositions,
        filtersFlow
    ) { positions, filters ->
        positions.filter { item ->
            val matchesQuery = filters.query.isEmpty() || 
                item.position.name.contains(filters.query, ignoreCase = true) || 
                item.position.sanskritName.contains(filters.query, ignoreCase = true) ||
                item.position.description.contains(filters.query, ignoreCase = true)

            val matchesDifficulty = filters.difficulty == null || item.position.difficulty == filters.difficulty
            val matchesCategory = filters.category == null || item.position.category == filters.category
            val matchesFav = !filters.onlyFavs || item.isFavorite
            val matchesCompleted = !filters.onlyCompleted || item.status == "COMPLETED"

            matchesQuery && matchesDifficulty && matchesCategory && matchesFav && matchesCompleted
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Recommended position of the day (changes daily using date-of-year seed)
    val recommendedPosition: StateFlow<UserKamaPosition?> = allPositions.mapStateFlow { positions ->
        if (positions.isEmpty()) return@mapStateFlow null
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val index = dayOfYear % positions.size
        positions[index]
    }

    // Helper to map StateFlow easily
    private fun <T, R> StateFlow<T>.mapStateFlow(transform: (T) -> R): StateFlow<R> {
        val initial = transform(this.value)
        val flow = this.map { transform(it) }
        return flow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initial)
    }

    // Actions
    fun toggleFavorite(positionId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(positionId)
        }
    }

    fun updateStatus(positionId: String, status: String) {
        viewModelScope.launch {
            repository.updateStatus(positionId, status)
        }
    }

    fun saveNotesAndRating(positionId: String, notes: String, rating: Int) {
        viewModelScope.launch {
            repository.saveNotesAndRating(positionId, notes, rating)
        }
    }

    fun clearAllFilters() {
        searchQuery.value = ""
        selectedDifficulty.value = null
        selectedCategory.value = null
        showOnlyFavorites.value = false
        showOnlyCompleted.value = false
    }
}

// Factory to instantiate the ViewModel with constructor arguments
class KamaViewModelFactory(private val repository: PositionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KamaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KamaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
