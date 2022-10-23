package `4week`.byeonghee

import java.io.*
import java.util.PriorityQueue

class `소병희_회의실 배정` {

    data class Meeting(val s: Int, val e: Int)

    val br = BufferedReader(InputStreamReader(System.`in`))
    val meetings = PriorityQueue(Comparator<Meeting> { a, b -> if (a.e == b.e) a.s - b.s else a.e - b.e })

    fun solution() {
        val n = br.readLine().toInt()
        repeat(n) {
            br.readLine().split(" ").map{ it.toInt() }.run{
                meetings.add(Meeting(first(), last()))
            }
        }

        var answer = 0
        var curLastT = 0
        lateinit var nxt : Meeting

        while(meetings.isNotEmpty()) {
            nxt = meetings.poll()
            if (curLastT <= nxt.s) {
                curLastT = nxt.e
                answer++
            }
        }

        println(answer)
    }
}

fun main() {
    `소병희_회의실 배정`().solution()
}