package `3week`.byeonghee

import java.io.*

class `소병희_단풍잎 이야기` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        var m = 0

        val games = mutableListOf<Int>()
        val useList = mutableSetOf<Int>()
        val pickList = mutableSetOf<Int>()
        var answer = 0

        fun getBit(n: Int) : Int {
            return 1 shl n
        }

        fun dfs(bm: Int, pick: Int) {
            if (pick == n) {
                pickList.add(bm)
                return
            }

            for(i in useList) {
                if (getBit(i) and bm > 0) continue
                dfs(bm + getBit(i), pick + 1)
            }
        }


        fun solution() {
            br.readLine().split(" ").map{ it.toInt() }.let {
                n = it[0]
                m = it[1]
            }

            repeat(m) {
                br.readLine().split(" ")
                    .fold(0) { acc, v ->
                        useList.add(v.toInt())
                        acc + getBit(v.toInt())
                    }
                    .let { games.add(it) }
            }

            dfs(0, 0)

            pickList.forEach { bm -> answer = Integer.max(answer, games.count { it and bm == it }) }

            println(answer)
        }
    }
}

fun main() {
    `소병희_단풍잎 이야기`.getSolution().solution()
}