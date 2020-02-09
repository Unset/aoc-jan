package nl.janvanrosmalen.human.lists

import nl.janvanrosmalen.human.natural.Natural

operator fun <E>List<E>.get(index : Natural) : E = get(index.long.toInt())
