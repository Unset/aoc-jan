package util

import java.math.BigInteger

fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}

fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
fun gcd(a: BigInteger, b: BigInteger): BigInteger = if (b.signum() == 0) a else gcd(b, a % b)

//https://stackoverflow.com/questions/16336500/kotlin-ternary-conditional-operator
infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null