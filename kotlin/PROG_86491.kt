package programmers.level1

import java.util.*

class PROG_86491 {

    fun solution(sizes: Array<IntArray>): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(Comparator { o1, o2 ->
            o2.first - o1.first
        })

        sizes.forEach {
            pq.offer(Pair(it[0], it[1]))
            pq.offer(Pair(it[1], it[0]))
        }

        var answer = pq.poll().first

        while (!pq.isEmpty()) {
            val cur = pq.poll()
            if (cur.second >= cur.first) {
                answer *= cur.first
                break
            }
        }

        return answer
    }

}