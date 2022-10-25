package `4week`.acw


import java.util.PriorityQueue

class `acw회의실배정` {
    val conferance = PriorityQueue<Pair<Int, Int>>(
        Comparator { a, b ->
            if (a.second - b.second == 0) {
                a.first - b.first
            } else {
                a.second - b.second
            }
        }

    )
// 종료시간이 빠른것을 기준으로 정렬한다.
// 종료시간이 같을 경우 시작시간이 더 빠른것을 위주로 정렬해야한다. -> 시작시간과 종료시간이 같은 회의가 있을 수 있기 때문
// ex) 5,6 / 6,6 이 있는데 6,6이 먼저 뽑힐 경우 56 /66이 모두 사용될 수 있음에도 불구하고 56은 선택되지 못한다.

    fun solution() {
        val N = readln().toInt()
        var answer = 0
        var lastConferance = Pair(0, 0)

        repeat(N) {
            val (s, e) = readln().split(" ").map { it.toInt() }
            conferance.add(Pair(s, e))

        }
        lastConferance = conferance.poll()
        answer++


        while (conferance.isNotEmpty()) {
            val nowConferance = conferance.poll()
            if (nowConferance.first < lastConferance.second) {
                continue
            } else {
                lastConferance = nowConferance
                answer++
            }

        }
        println(answer)


    }

}

fun main() {
    val sol = `acw회의실배정`()
    sol.solution()
}
