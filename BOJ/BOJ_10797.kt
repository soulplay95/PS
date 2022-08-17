package boj

fun main() {
    val day = readln().toInt()
    println(readln().toCharArray().map { Character.getNumericValue(it) }.filter { it == day }.size)
}