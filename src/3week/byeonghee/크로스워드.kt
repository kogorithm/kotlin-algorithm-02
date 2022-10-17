package `3week`.byeonghee

/** 우선순위큐, split 함수 */

import java.io.*
import java.util.*

class `소병희_크로스워드` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var words = PriorityQueue<String> { a, b -> when {
            a > b -> 1
            a < b -> -1
            else -> 0
        }}

        fun solution() {
            val (row, col) = br.readLine().split(" ").map{ it.toInt()}
            val puzzles = Array(row) { br.readLine() }

            (0 until row).forEach {  r ->
                puzzles[r].split("#").filter{ it.length > 1 }.let {
                    words.addAll(it)
                }
            }

            (0 until col).forEach { c ->
                puzzles.map{ it[c] }.joinToString("").split("#").filter{ it.length > 1 }.let {
                    words.addAll(it)
                }
            }

            println(words.peek())
        }
    }
}

fun main() {
    `소병희_크로스워드`.getSolution().solution()
}