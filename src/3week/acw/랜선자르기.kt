package `3week`.acw
import java.util.PriorityQueue

class 랜선자르기 {
    var answer = 0L
    lateinit var test : PriorityQueue<Long>

    private fun binarySearch(start: Long, end: Long, targetNum: Int) {

        if (start > end) {
            return
        }
        val now = ((start + end) / 2)
        val count = test.sumOf { it / now }

        if (count < targetNum) {
            binarySearch(start, now - 1, targetNum)
            //작을 땐 더 작은 수로 계산해보기
        } else {
            answer = answer.coerceAtLeast(now)
            binarySearch(now + 1, end, targetNum)
            //크면 더 큰 수 찾아보기
        }

    }

    fun solution() {
        val (K, N) = readln().split(" ").map { it.toInt() }

        test=PriorityQueue<Long>().apply{
            addAll(Array(K){ readln().toLong() })
        }

        binarySearch(1, test.last(), N)

        println(answer)


    }
}

fun main() {
    val sol = 랜선자르기()
    sol.solution()
}


