package `2week`.byeonghee

/**
 * 접근방식: dfs
 * 문제를 잘 읽자
 * 반례모음 : https://www.acmicpc.net/board/view/97339
 */

import java.io.*

class `소병희_단지 번호 붙이기` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val BLANK = '0'
            const val APART = '1'
        }

        private val br = BufferedReader(InputStreamReader(System.`in`))

        lateinit var apartMap: Array<CharArray>

        var n = 0
        var complexCount = 0
        /** ▽▽ 이걸 IntArray(200)으로 임의로 줬었는데 아무래도 200개를 넘어갔던 것 같다 **/
        private val complexList = mutableListOf<Int>()
        private val around = listOf(
                Pair(-1, 0),
                Pair(0, -1),
                Pair(0, 1),
                Pair(1, 0)
            )

        fun solution() {
            n = br.readLine().toInt()
            apartMap = Array(n) { br.readLine().toCharArray() }

            for(i in 0 until n) for(j in 0 until n) {
                if (apartMap[i][j] == BLANK) continue

                complexList.add(0)
                dfs(i, j)
                complexCount++
            }

            println(complexCount)
            println(complexList.sorted().joinToString("\n"))
        }

        private fun dfs(r: Int, c: Int) {
            apartMap[r][c] = BLANK
            complexList[complexCount]++

            for((dr, dc) in around) {
                if ((r + dr) in 0 until n
                    && (c + dc) in 0 until n
                    && apartMap[r + dr][c + dc] == APART
                ) {
                    dfs(r + dr, c + dc)
                }
            }
        }
    }
}

fun main() {
    `소병희_단지 번호 붙이기`.getSolution().solution()
}