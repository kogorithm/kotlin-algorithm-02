package `2week`.byeonghee

/**
 * 접근 방식: dp + 비트마스킹으로 도전
 * 한계: dp를 뭔지도 잘 모르고 그냥 한 것 같습니다 큐ㅠㅠ
 */

import java.io.*

class  `소병희_외판원 순회 2` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        private val br = BufferedReader(InputStreamReader(System.`in`))

        private var n = 0
        private var complete = 0
        private var adjacency = Array(0){ IntArray(0) }
        private val dp = Array(1 shl 10){ IntArray(10) { -1 } }

        data class Travel(val city: Int, val route: Int)

        private fun getBit(city: Int) : Int {
            return 1 shl city
        }

        private fun getBeforeList(route: Int) : List<Travel> {
            val list = mutableListOf<Travel>()
            for(i in 1 until n) {
                getBit(i).run {
                    if ((route and this) != 0) {
                        list.add(Travel(i, route xor this))
                    }
                }
            }
            return list
        }

        private fun dfs(route: Int, last: Int) : Int {
            if (route == 0) return adjacency[0][last]
            if (dp[route][last] > -1) return dp[route][last]

            var minLocalCost = Int.MAX_VALUE

            getBeforeList(route).forEach { before ->
                if (dp[before.route][before.city] == -1) {
                    dp[before.route][before.city] = dfs(before.route, before.city)
                }

                if (dp[before.route][before.city] > 0 && adjacency[before.city][last] > 0) {
                    minLocalCost = Integer.min(
                        minLocalCost,
                        dp[before.route][before.city] + adjacency[before.city][last]
                    )
                }
            }

            minLocalCost = minLocalCost.let{ if (it == Int.MAX_VALUE) 0 else it }
            return minLocalCost
        }

        fun solution() {
            n = br.readLine().toInt()
            complete = (1 shl n) - 1

            adjacency = Array(n) { br.readLine().split(" ").map{ it.toInt() }.toIntArray() }

            for(i in 0 until n) {
                dp[0][i] = adjacency[0][i]
            }

            println(dfs(complete - 1, 0))
        }
    }
}

fun main() {
    `소병희_외판원 순회 2`.getSolution().solution()
}