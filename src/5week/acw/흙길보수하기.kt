package `5week`.acw


import java.util.PriorityQueue


class `acw흙길보수하기` {
    fun solution() {
        val (N, L) = readln().split(" ").map { it.toInt() }
        var bridgeEnd = 1
        var answer = 0
        val puddle = PriorityQueue<Pair<Int, Int>> { a, b ->
            a.first - b.first
        }

        repeat(N) {
            val (s, e) = readln().split(" ").map { it.toInt() }
            puddle.add(Pair(s, e))
        }

        while (puddle.isNotEmpty()) {
            var (s, e) = puddle.poll()
            if (bridgeEnd > s) {
                s = bridgeEnd
            }//현재 판자보다 웅덩이의 시작점이 빠를경우 시작점을 판자끝부분으로 바꿔주기
            while (s < e) {
                //시작점부터 end까지 cover할 수 있도록 판자사용하기
                s += L
                answer++
            }
            bridgeEnd = s
            //판자 end부분 update
        }

        println(answer)


    }
}

fun main() {
    val sol = `acw흙길보수하기`()
    sol.solution()
}
