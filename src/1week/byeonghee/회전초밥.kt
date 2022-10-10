package `1week`.byeonghee

import java.io.*
import java.lang.Integer.max

class `회전 초밥_소병희` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {

        val br = BufferedReader(InputStreamReader(System.`in`))

        val belt = mutableListOf<Int>()
        var possibleMaxKind = 0
        var maxKind = 0
        var curKind = 0

        fun solution(): Unit = with(br) {
            val (N, d, k, c) = readLine().split(" ").map{ it.toInt() }

            possibleMaxKind = k + 1

            repeat(N) {
                belt.add(readLine().trim().toInt())
            }
            belt.addAll(belt.subList(0, k))

            for(i in 0 until N) {
                curKind = belt.subList(i, i + k).toMutableSet().let {
                    it.add(c)
                    it.size
                }
                maxKind = max(maxKind, curKind)

                if (maxKind == possibleMaxKind) break
            }

            println(maxKind)
        }

    }
}

fun main() {
    `회전 초밥_소병희`.getSolution().solution()
}