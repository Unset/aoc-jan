package nl.janvanrosmalen.human.lists

import nl.janvanrosmalen.human.natural.NaturalComparable

operator fun <E>List<E>.get(index : NaturalComparable) : E = get(index.long.toInt())
