package `2week`.byeonghee

/**
 * 접근방식: 비트마스킹, distinct()
 * 각 열차를 20개의 비트를 사용하는 정수로 표현하고
 * 모든 명령이 끝난 후 열차를 모아놓은 IntArray에 distinct().size로 은하수를 건너는 개수를 구한다.
 */

import java.io.*

class `소병희_기차가 어둠을 헤치고 은하수를` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val GET_ON = 1
            const val GET_OFF = 2
            const val MV_BACK = 3
            const val MV_FORE = 4

            const val masking = (1 shl 20) - 1
        }

        val br = BufferedReader(InputStreamReader(System.`in`))

        var comm = 0
        var tr = 0
        var ps = 0

        fun solution() {
            val (N, M) = br.readLine().split(" ").map{ it.toInt() }
            val trains = IntArray(N)

            repeat(M) {
                br.readLine().split(" ").map{ it.toInt() }.run {
                    comm = component1()
                    tr = component2()
                    if (size > 2) ps = component3()
                }

                trains[tr - 1] = trains[tr - 1].let {
                    when(comm) {
                        GET_ON -> it or (1 shl (ps - 1))
                        GET_OFF -> it and (masking - (1 shl (ps - 1)))
                        MV_BACK -> (it * 2) and masking
                        MV_FORE -> it / 2
                        else -> it
                    }
                }
            }

            println(trains.distinct().size)
        }
    }
}

fun main() {
    `소병희_기차가 어둠을 헤치고 은하수를`.getSolution().solution()
}