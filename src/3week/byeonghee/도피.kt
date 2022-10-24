package `3week`.byeonghee

import java.io.*

class `소병희_도피` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val ONEPROB = 1000000000000000000L
        }

        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0

        var dp = Array(0) { LongArray(0) }
        var moves = Array(0) { LongArray(0) }
        var ans = 0L
        var cityList = mutableListOf<Int>()

        fun getDp(day: Int, dst: Int) {
            if (dp[day][dst] > -1) return

            var prob = 0L
            for(from in 0 until n) {
                if(dp[day-1][from] < 0) getDp(day-1, from)
                prob += dp[day-1][from] * moves[from][dst]
            }
            dp[day][dst] = prob
        }

        fun solution() {

            val (n_tmp, m) = br.readLine().split(" ").map{ it.toInt() }
            n = n_tmp

            dp = Array(9) { LongArray(n){ -1 } }
            moves = Array(n) { LongArray(n) }

            repeat(m) {
                br.readLine().split(" ").map{ it.toInt() }.let {
                    moves[it[0]][it[1]] = it[2].toLong()
                }
            }

            dp[0].fill(0)
            moves[0].forEachIndexed { i, v -> dp[0][i] = v }

            for(e in 0 until n) {
                getDp(8, e)
                if (ans < dp[8][e]) {
                    ans = dp[8][e]
                    cityList = mutableListOf(e)
                }
                else if (ans == dp[8][e]) {
                    cityList.add(e)
                }
            }

            println(cityList.joinToString(" "))
            if (ans == ONEPROB) println("1." + "0".repeat(18))
            else println(String.format("0.%18d", ans))
        }
    }
}

fun main() {
    `소병희_도피`.getSolution().solution()
}