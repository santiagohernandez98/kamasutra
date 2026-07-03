package com.example.data

data class KamaPosition(
    val id: String,
    val name: String,
    val sanskritName: String,
    val category: String,
    val difficulty: String, // "Fácil", "Intermedio", "Avanzado"
    val intimacy: Int, // 1 to 5
    val flexibility: Int, // 1 to 5
    val strength: Int, // 1 to 5
    val description: String,
    val steps: List<String>,
    val benefits: List<String>,
    val tips: String
)

object KamaPositionProvider {
    val positions = listOf(
        KamaPosition(
            id = "misionero",
            name = "El Misionero",
            sanskritName = "Purushayita",
            category = "Acostado",
            difficulty = "Fácil",
            intimacy = 5,
            flexibility = 2,
            strength = 2,
            description = "Una de las posiciones más tradicionales e íntimas. Facilita una conexión visual y emocional profunda al mantener a la pareja frente a frente con contacto total de la piel.",
            steps = listOf(
                "Un miembro de la pareja se recuesta cómodamente de espaldas (boca arriba) sobre el colchón, relajando las piernas.",
                "El otro miembro se coloca encima de forma estable, apoyando su peso sobre sus propios codos o manos a los lados.",
                "Mantengan la mirada fija en el otro, sincronizando la respiración profunda para establecer un ritmo coordinado.",
                "Realicen movimientos lentos y controlados, adaptando la inclinación según la comodidad de ambos."
            ),
            benefits = listOf(
                "Fomenta el contacto visual constante y la conexión de besos.",
                "Es de muy bajo impacto físico, permitiendo relajarse por completo.",
                "Facilita caricias suaves en el rostro, hombros y torso."
            ),
            tips = "Colocar una almohada pequeña o cojín debajo de la cadera de la persona que está acostada mejora drásticamente el ángulo de inclinación y reduce cualquier tensión lumbar."
        ),
        KamaPosition(
            id = "cuchara",
            name = "La Cuchara",
            sanskritName = "Sushupti",
            category = "Acostado",
            difficulty = "Fácil",
            intimacy = 5,
            flexibility = 1,
            strength = 1,
            description = "Perfecta para momentos de fatiga o relajación profunda. Ambos se acuestan de lado en la misma dirección, logrando un contacto cálido sin exigir esfuerzo físico.",
            steps = listOf(
                "Ambos miembros de la pareja se recuestan de lado en posición fetal ligera, mirando en la misma dirección.",
                "La persona de atrás se acerca y abraza suavemente la cintura y el torso de la persona que está al frente.",
                "La persona de adelante flexiona ligeramente las rodillas para dar mayor comodidad y espacio de acople.",
                "El ritmo es naturalmente lento y pausado, ideal para caricias prolongadas en la espalda, hombros y cabello."
            ),
            benefits = listOf(
                "Ofrece el mínimo esfuerzo muscular y articular de todo el catálogo.",
                "Favorece un contacto cálido de piel con piel por toda la espalda.",
                "Es excelente para prolongar el tiempo de conexión íntima sin cansancio."
            ),
            tips = "Para mayor comodidad física en las piernas, coloquen una almohada pequeña entre las rodillas de ambos. Esto alinea la columna y previene calambres."
        ),
        KamaPosition(
            id = "loto",
            name = "El Loto",
            sanskritName = "Padmasana-style",
            category = "Sentado",
            difficulty = "Intermedio",
            intimacy = 5,
            flexibility = 4,
            strength = 3,
            description = "Una posición de alta trascendencia espiritual y meditativa. Requiere cierta flexibilidad de cadera y fomenta un abrazo corporal total, ideal para respiraciones unísonas.",
            steps = listOf(
                "Un miembro de la pareja se sienta en el colchón con las piernas cruzadas en posición de loto o sastre.",
                "El otro miembro se sienta con suavidad sobre su regazo, rodeando la cintura del compañero con ambas piernas.",
                "Ambos se abrazan fuertemente rodeando el cuello y los hombros del otro, aproximando sus rostros al máximo.",
                "El movimiento se genera mediante un balanceo sutil y rítmico de la pelvis, guiado por respiraciones profundas."
            ),
            benefits = listOf(
                "Cercanía y abrazo corporal total a nivel de pecho y hombros.",
                "Permite una respiración sincronizada que induce un estado meditativo conjunto.",
                "Fomenta besos, susurros y caricias directas de espalda."
            ),
            tips = "Quien sirve de base puede apoyar su espalda contra una cabecera de cama o pared acolchada con cojines para evitar la fatiga en los músculos de la columna."
        ),
        KamaPosition(
            id = "arco",
            name = "El Arco",
            sanskritName = "Dhanurasana-style",
            category = "Acostado",
            difficulty = "Avanzado",
            intimacy = 3,
            flexibility = 5,
            strength = 4,
            description = "Una postura que combina dinamismo físico y un estiramiento reconfortante. Quien se acuesta eleva las caderas para lograr una alineación arqueada de gran estímulo.",
            steps = listOf(
                "La persona receptiva se recuesta boca arriba, flexionando las rodillas y apoyando las plantas de los pies firmemente.",
                "Eleva la pelvis y la espalda baja arqueándose, pudiendo sostener sus propios tobillos o talones con las manos para estabilidad.",
                "La pareja activa se arrodilla frente a sus caderas, proporcionando soporte y manteniendo el equilibrio de la postura.",
                "Mantengan movimientos rítmicos y firmes, prestando atención a la comodidad lumbar."
            ),
            benefits = listOf(
                "Excelente estiramiento y flexibilización para la cadera y la columna.",
                "Fortalece significativamente las piernas, glúteos y el core abdominal.",
                "Crea un ángulo y alineación geométrica sumamente dinámicos."
            ),
            tips = "No fuercen la columna si sienten molestias. Pueden colocar una almohada o bloque firme de yoga debajo del sacro para descansar el arco sin perder la elevación geométrica."
        ),
        KamaPosition(
            id = "mariposa",
            name = "La Unión de la Mariposa",
            sanskritName = "Chitrakarini",
            category = "Acostado",
            difficulty = "Intermedio",
            intimacy = 4,
            flexibility = 3,
            strength = 3,
            description = "Una posición elegante y cómoda efectuada en el borde de la cama, que reduce la fatiga física y facilita caricias completas utilizando la gravedad a favor.",
            steps = listOf(
                "Un miembro se acuesta boca arriba con la pelvis muy cerca del borde del colchón.",
                "Flexiona las rodillas aproximando las plantas de los pies entre sí, dejando caer las rodillas hacia afuera en forma de alas.",
                "El otro miembro se coloca de rodillas o de pie justo en el borde de la cama frente a su pareja.",
                "Sostengan los muslos o tobillos para regular la comodidad de la apertura y realicen movimientos fluidos."
            ),
            benefits = listOf(
                "Libera a la persona acostada de soportar el peso de su pareja.",
                "Permite un excelente ángulo visual y de gesticulación para caricias.",
                "Facilita una apertura relajada y natural de las caderas."
            ),
            tips = "Colocar una toalla suave enrollada o un cojín plano bajo la rabadilla ayuda a proteger el área del borde rígido del colchón y estabiliza el movimiento."
        ),
        KamaPosition(
            id = "puente_oro",
            name = "El Puente de Oro",
            sanskritName = "Setubandhasana",
            category = "Acostado",
            difficulty = "Avanzado",
            intimacy = 3,
            flexibility = 4,
            strength = 5,
            description = "Un desafío atlético que requiere fuerza en el core y glúteos. Logra un equilibrio suspendido que intensifica el control sobre la profundidad de los movimientos.",
            steps = listOf(
                "La persona activa se arrodilla, levantando y sosteniendo las piernas de su pareja sobre sus hombros o caderas.",
                "La persona acostada eleva activamente el torso de la cama, apoyándose únicamente en hombros, cuello y brazos.",
                "Sostengan la elevación contrayendo el abdomen y los músculos del glúteo para crear un puente suspendido.",
                "El ritmo se controla mutuamente aplicando la fuerza de piernas y cadera de manera sincronizada."
            ),
            benefits = listOf(
                "Excelente ejercicio isométrico para fortalecer piernas, abdomen y espalda baja.",
                "Otorga un control de profundidad absoluto para ambas partes.",
                "Estimula el sentido del equilibrio y la complicidad corporal activa."
            ),
            tips = "Hagan breves descansos bajando la pelvis al colchón cada pocos minutos para evitar calambres musculares y disfrutar de una sesión prolongada sin sobrecarga."
        ),
        KamaPosition(
            id = "estrella",
            name = "La Estrella",
            sanskritName = "Taraka",
            category = "Acostado",
            difficulty = "Fácil",
            intimacy = 4,
            flexibility = 2,
            strength = 2,
            description = "Una variante sumamente relajante y holgada de la posición clásica. Las piernas se extienden libremente formando una estrella que reduce el cansancio de las rodillas.",
            steps = listOf(
                "Un miembro de la pareja se recuesta de espaldas abriendo brazos y piernas holgadamente en forma de estrella.",
                "El otro miembro se ubica encima, pero cruzándose en un ángulo diagonal para repartir el peso cómodamente.",
                "Entrelacen las piernas de forma suave y sin presión, permitiendo descansar los muslos sobre el colchón.",
                "Los movimientos son laterales y ondulatorios, reduciendo el impacto y enfocándose en la proximidad de los rostros."
            ),
            benefits = listOf(
                "Evita la fatiga mecánica en rodillas y codos.",
                "Ofrece un soporte completo para la columna de ambas personas.",
                "Fomenta besos prolongados en el cuello, hombros y clavícula."
            ),
            tips = "Esta posición es genial para transiciones relajadas o para momentos finales en los que desean priorizar el contacto de piel y la respiración pausada."
        ),
        KamaPosition(
            id = "gondola",
            name = "La Góndola",
            sanskritName = "Nauka",
            category = "Sentado",
            difficulty = "Intermedio",
            intimacy = 4,
            flexibility = 3,
            strength = 4,
            description = "Ambos miembros se sientan cara a cara inclinándose recíprocamente hacia atrás. Utilizan la tensión de sus brazos para crear un balanceo armonioso.",
            steps = listOf(
                "Ambos se sientan frente a frente doblando las rodillas en un ángulo cómodo sobre el colchón.",
                "El compañero activo desliza sus piernas por fuera de las piernas del compañero sentado frente a él.",
                "Sosténganse con firmeza por los antebrazos o las muñecas e inclínense ligeramente hacia atrás para tensionar la cuerda.",
                "Generen un balanceo basculante rítmico impulsando alternadamente los hombros hacia adelante y atrás."
            ),
            benefits = listOf(
                "Ejercita de forma simultánea brazos, hombros y los músculos erectores de la columna.",
                "Crea un vaivén simétrico muy divertido y visualmente envolvente.",
                "Mantiene las miradas alineadas de manera constante durante todo el proceso."
            ),
            tips = "Apoyen las plantas de los pies firmemente sobre el colchón o una manta antideslizante para evitar que el vaivén las desplace desestabilizando el equilibrio."
        ),
        KamaPosition(
            id = "balanza",
            name = "La Balanza",
            sanskritName = "Tolasana-style",
            category = "De Pie",
            difficulty = "Avanzado",
            intimacy = 3,
            flexibility = 3,
            strength = 5,
            description = "Un encuentro de pie que constituye el mayor desafío de fuerza del catálogo. Uno de los miembros carga en vilo al otro, experimentando una ingravidez estimulante.",
            steps = listOf(
                "El compañero que carga se coloca de pie con los pies bien apoyados a la distancia de los hombros y rodillas ligeramente flexionadas.",
                "El otro compañero salta y se abraza rodeando con sus piernas la cadera del compañero y sujetándose firme del cuello.",
                "El compañero de pie sostiene con fuerza los muslos y glúteos del compañero cargado para asegurar la estabilidad del peso.",
                "Realicen un balanceo vertical controlado basándose en flexiones rítmicas de las rodillas (estilo sentadilla ligera)."
            ),
            benefits = listOf(
                "Intenso entrenamiento cardiovascular y muscular para todo el cuerpo de ambos.",
                "Brinda una sensación estimulante de ingravidez, juego y entrega absoluta.",
                "Excelente para romper la rutina física de las posiciones horizontales tradicionales."
            ),
            tips = "Para reducir sustancialmente la fuerza requerida para elevar a la pareja, inicien la posición desde un sitio elevado como el borde de una mesa, cómoda o encimera alta."
        ),
        KamaPosition(
            id = "triangulo",
            name = "El Triángulo",
            sanskritName = "Trikonasana-style",
            category = "De Pie",
            difficulty = "Intermedio",
            intimacy = 3,
            flexibility = 4,
            strength = 3,
            description = "Un encuentro de gran comodidad para la columna. Un miembro se inclina hacia adelante apoyando los brazos sobre un mueble estable, minimizando la carga en espalda y rodillas.",
            steps = listOf(
                "Un compañero se para frente a una cama, mesa de noche o silla pesada inclinando su torso al frente y apoyando sus manos.",
                "Mantenga las piernas separadas para estabilidad y la espalda estirada, cuidando la alineación de su columna.",
                "El compañero activo se posiciona de pie detrás, apoyando suavemente sus manos sobre la cadera del compañero inclinado.",
                "Desarrollen un movimiento rítmico y de vaivén aprovechando la firmeza que ofrece el soporte frontal."
            ),
            benefits = listOf(
                "Libera de cualquier presión o peso a la espalda baja de ambos miembros.",
                "Es una de las posturas más ergonómicas si existen dolores crónicos en articulaciones superiores.",
                "Brinda un soporte extremadamente seguro y relajado para sostener el ritmo."
            ),
            tips = "Asegúrense absolutamente de que el mueble de apoyo sea pesado, estable y no se deslice sobre el suelo. Pueden apuntalarlo contra la pared para seguridad total."
        ),
        KamaPosition(
            id = "venus",
            name = "La Fusión de Venus",
            sanskritName = "Kama-Venus",
            category = "Lésbico",
            difficulty = "Fácil",
            intimacy = 5,
            flexibility = 2,
            strength = 2,
            description = "Una posición altamente íntima y sensorial diseñada para la estimulación mutua cara a cara. Ideal para mantener un contacto visual continuo, respiración sincronizada y caricias por todo el cuerpo.",
            steps = listOf(
                "Ambas compañeras se recuestan de lado frente a frente, entrelazando suavemente sus piernas para un contacto cercano.",
                "Sincronicen la respiración para relajarse, acercando el pecho y el abdomen para maximizar el calor de la piel.",
                "Utilicen las manos libres para acariciar el cabello, los hombros y el rostro de la compañera.",
                "Realicen movimientos sutiles de vaivén de la pelvis, disfrutando de la fricción mutua de manera lenta y placentera."
            ),
            benefits = listOf(
                "Contacto visual cara a cara ininterrumpido.",
                "Excelente para masajes y caricias espontáneas durante la intimidad.",
                "Esfuerzo físico mínimo, permitiendo una experiencia de larga duración."
            ),
            tips = "Pueden colocar un cojín bajo la cadera inferior de una de las compañeras para lograr una alineación pélvica perfecta y un ajuste óptimo de fricción."
        ),
        KamaPosition(
            id = "mariposa_lesbica",
            name = "La Mariposa de Safos",
            sanskritName = "Tribas-Mariposa",
            category = "Lésbico",
            difficulty = "Intermedio",
            intimacy = 5,
            flexibility = 4,
            strength = 3,
            description = "Una postura que utiliza la gravedad y la apertura de caderas para una conexión profunda. Una de las compañeras descansa de espaldas mientras la otra se coloca encima de forma perpendicular, maximizando el contacto íntimo.",
            steps = listOf(
                "Una compañera se recuesta boca arriba con la pelvis cerca del borde de la cama o sobre cojines, flexionando las rodillas hacia afuera.",
                "La otra compañera se arrodilla o se recuesta de forma transversal encima, permitiendo que sus áreas pélvicas se alineen perfectamente.",
                "Sosténganse mutuamente de los hombros o de las manos para mantener la estabilidad del acople.",
                "Generen movimientos de balanceo rítmico y circular con la pelvis, ajustando la velocidad según el deseo."
            ),
            benefits = listOf(
                "Perfecto acople anatómico que intensifica la estimulación clitoriana.",
                "Libera de tensión la espalda de la compañera que se encuentra en la base.",
                "Fomenta una sensación envolvente y un contacto físico de gran intensidad."
            ),
            tips = "Usar un cojín de cuña bajo la espalda baja de la persona que está acostada facilita la elevación y reduce el esfuerzo necesario de la persona que está arriba."
        ),
        KamaPosition(
            id = "arco_anal",
            name = "El Templo Posterior",
            sanskritName = "Paschima-Anala",
            category = "Anal",
            difficulty = "Avanzado",
            intimacy = 4,
            flexibility = 4,
            strength = 4,
            description = "Una postura de alta sensibilidad y control. Quien recibe se arrodilla inclinando el pecho hacia el colchón (posición de cuatro apoyos), facilitando la penetración anal con un ángulo cómodo, seguro y controlable.",
            steps = listOf(
                "La persona receptiva se coloca de rodillas y manos en el colchón (cuatro puntos de apoyo), bajando gradualmente el pecho para relajar la espalda.",
                "Apliquen abundante lubricante de base acuosa de forma pausada y sensual en la zona anal.",
                "La persona activa se arrodilla detrás de forma estable, sujetando con suavidad las caderas de su compañero o compañera.",
                "La introducción debe ser extremadamente lenta, esperando a que los músculos anales se adapten mediante respiraciones profundas antes de iniciar el vaivén."
            ),
            benefits = listOf(
                "Ofrece un ángulo de penetración anal natural que reduce la resistencia muscular.",
                "Permite al miembro activo controlar con precisión la dirección y profundidad.",
                "Facilita que ambos puedan acariciar las zonas erógenas anteriores simultáneamente."
            ),
            tips = "La comunicación verbal es CRÍTICA aquí. Si sientes molestia, detén el movimiento y respira hondo. El uso de lubricante de alta calidad nunca debe ser escatimado."
        ),
        KamaPosition(
            id = "cuchara_anal",
            name = "La Cuchara Íntima",
            sanskritName = "Sushupti-Anala",
            category = "Anal",
            difficulty = "Fácil",
            intimacy = 5,
            flexibility = 1,
            strength = 1,
            description = "La postura más recomendada para iniciarse en el sexo anal debido a su naturaleza pasiva y relajante. El contacto lateral disminuye la tensión muscular y permite una relajación óptima del esfínter.",
            steps = listOf(
                "Ambos miembros de la pareja se recuestan de lado mirando hacia la misma dirección, acoplándose como cucharas.",
                "La persona receptiva flexiona las rodillas hacia el pecho de manera cómoda para abrir y relajar la zona posterior.",
                "Apliquen lubricación abundante y realicen caricias suaves de calentamiento en el torso y la espalda.",
                "La persona de atrás realiza una aproximación lenta y gradual, manteniendo un ritmo suave y constante sin presiones apresuradas."
            ),
            benefits = listOf(
                "Esfuerzo muscular nulo, lo cual ayuda directamente a la relajación física y anal.",
                "Fomenta una sensación cálida de cobijo, ideal para vencer la ansiedad.",
                "Permite que la persona de adelante acaricie libremente su propio clítoris o cuerpo."
            ),
            tips = "Avanzar milímetro a milímetro y no apresurar el ritmo. Si se siente tensión, la persona de adelante puede dar indicaciones para detenerse o ralentizar hasta que vuelva el placer."
        )
    )
}
