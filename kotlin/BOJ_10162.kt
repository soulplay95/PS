val times = arrayOf(300, 60, 10)
val answer = arrayOf(0, 0, 0)

// 그리디
fun main() {
    var t = readln().toInt()

    if (t % 10 != 0) {
        println(-1)
        return
    }

    times.forEachIndexed { index, time ->
        answer[index] = t / time
        t %= time
    }

    println(answer.joinToString(" "))
}