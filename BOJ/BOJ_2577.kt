package boj

class BOJ_2577 {

    fun main(args: Array<String>) {
        var result = 1
        val answer = Array<Int>(10) { 0 }

        repeat(3) {
            result *= readln().toInt()
        }

        result.toString().forEach { answer[it.digitToInt()]++ }
        println(answer.joinToString("\n"))
    }

}