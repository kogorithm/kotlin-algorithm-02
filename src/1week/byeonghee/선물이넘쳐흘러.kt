package `1week`.byeonghee

import java.io.*
import java.lang.Integer.max
import java.lang.Integer.min

/**
 * 접근방법: 윈도우?
 * BxB 공장에서 컨베이어 벨트인 (3B - 2) 칸을 일차원 배열로 가정
 * 컨베이어 배열의 인덱스마다 인접한 스태프의 아이디를 저장
 * 선물이 이동하지 않고 컨베이어 배열이 한 칸씩 전진하며 대응하는 인덱스의 선물을 확인한다.
 */

class `선물이 넘쳐흘러_소병희` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val OFF_BELT = 0
            const val ON_BELT = 1

            const val FREE = 0
            const val WORK = 1
        }

        private val br = BufferedReader(InputStreamReader(System.`in`))

        private var B = 0
        private var N = 0
        private var M = 0

        private val around = listOf(
            Pair(1, 0),
            Pair(0, 1),
            Pair(-1, 0)
        )


        data class Staff(val r: Int, val c: Int, val cost: Int, var state: Int = FREE, var tWork: Int = 0)
        private val staffs = mutableListOf<Staff>()
        private var presents = IntArray(0)
        private var belts = mutableListOf<MutableList<Int>>()
        private var factory = listOf<IntArray>()
        private var doneCount = 0

        private fun readInts() = br.readLine().split(" ").map{ it.toInt() }

        fun solution():Unit = with(br) {
            readInts().run {
                B = this[0]
                factory = List(B){ IntArray(B) { -1 } }
                belts = MutableList(3 * B - 2) { mutableListOf() }

                N = this[1]

                M = this[2]
                presents = IntArray(M)
            }

            var (fr, fc) = listOf(0, 0)
            repeat(3 * B - 2) { idx ->
                factory[fr][fc] = idx
                if (fr == B - 1) fc--
                else if (fc == B - 1) fr++
                else fc++
            }

            repeat(N) { i ->
                readInts().run {
                    staffs.add(Staff(this[0], this[1], this[2]))

                    with(staffs[i]) {
                        for((dr, dc) in around) {
                            if (r + dr >= 0 && c + dc >= 0) {
                                val idx = factory[r + dr][c + dc]
                                if (idx >= 0) {
                                    belts[idx].add(i)
                                }
                            }
                        }
                    }
                }
            }

            for(t in 0 until M + belts.lastIndex) {
                if (t < M) presents[t] = ON_BELT

                for(b in min(t, belts.lastIndex) downTo max(0, t - presents.lastIndex)) {
                    if(presents[t - b] == ON_BELT && belts[b].isNotEmpty()) {
                        belts[b].first().also { staff_id ->
                            staffs[staff_id].run {
                                if (state == FREE) {
                                    state = WORK
                                    tWork = cost
                                    presents[t - b] = OFF_BELT
                                    doneCount++
                                }
                            }
                        }
                    }
                }

                for(i in 0 until N) {
                    if (staffs[i].state == FREE) continue

                    staffs[i].run {
                        tWork--
                        if(tWork == 0) state = FREE
                    }
                }
            }

            println(doneCount)
        }
    }
}

fun main() {
    `선물이 넘쳐흘러_소병희`.getSolution().solution()
}