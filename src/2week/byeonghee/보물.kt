package `2week`.byeonghee

/**
 * @접근방법: 하나는 오름차, 하나는 내림차로 정렬
 */

import java.io.*

class `소병희_보물` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        private val br = BufferedReader(InputStreamReader(System.`in`))

        private var N = 0
        private var A = listOf<Int>()
        private var B = listOf<Int>()
        var answer = 0

        fun solution() {
            N = br.readLine().toInt()
            A = br.readLine().split(" ").map{ it.toInt() }.sorted()
            B = br.readLine().split(" ").map{ it.toInt() }.sortedDescending()

            for(i in 0 until N) { answer += A[i] * B[i] }
            println(answer)
        }
    }
}

fun main() {
    `소병희_보물`.getSolution().solution()
}