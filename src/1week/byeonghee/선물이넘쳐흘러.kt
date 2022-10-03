package `1week`.byeonghee

import java.io.*
import java.lang.Integer.max

/**
 * 접근방법: 윈도우?
 * BxB 공장에서 컨베이어 벨트인 (3B - 2) 칸을 일차원 배열로 가정
 * 컨베이어 배열의 인덱스마다 인접한 스태프의 아이디를 저장
 * 선물이 이동하지 않고 컨베이어 배열이 한 칸씩 전진하며 대응하는 인덱스의 선물을 확인한다.
 */

const val OFF_BELT = 0 // 안 올라왔거나, 포장 중이거나, 포장이 끝났거나
const val ON_BELT = 1

const val FREE = 0
const val WORK = 1

val br = BufferedReader(InputStreamReader(System.`in`))

var B = 0
var N = 0
var M = 0

val around = listOf(
    Pair(1, -1),
    Pair(1, 0),
    Pair(1, 1),
    Pair(0, 1),
    Pair(-1, 1),
    Pair(-1, 0),
    Pair(-1, -1)
)


data class Staff(val r: Int, val c: Int, val t: Int, var state: Int = FREE, var tWork: Int = 0)
val staffs = mutableListOf<Staff>()
var presents = IntArray(0)
var belts = mutableListOf<MutableList<Int>>()
var factory = listOf<IntArray>()
var doneCount = 0

fun readInts() = br.readLine().split(" ").map{ it.toInt() }

fun main():Unit = with(br) {
    readInts().run {
        B = this[0]
        factory = List(B){ IntArray(B) { -1 } }
        belts = MutableList(3 * B - 2) { mutableListOf() }

        N = this[1]

        M = this[2]
        presents = IntArray(M)
    }

    var (r, c) = listOf(0, 0)
    repeat(3 * B - 2) { idx ->
        factory[r][c] = idx
        if (r == B - 1) c--
        else if (c == B - 1) r++
        else c++
    }

    repeat(N) {
        readInts().run {
            staffs.add(Staff(this[0], this[1], this[2]))
        }
    }
    staffs.sortBy { it.r * B + it.c }

    repeat(N) { i ->
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

    for(t in 0 until M) {
        presents[t] = ON_BELT

        for(b in 0..t) {
            if(presents[t - b] == ON_BELT) {
                belts[b].forEach { staff_id ->
                    staffs[staff_id].run {
                        if (state == FREE) {
                            state = WORK
                            tWork = t + 1
                            presents[t - b] = OFF_BELT
                            doneCount++
                            return@forEach
                        }
                    }
                }
            }
        }

        for(i in 0 until N) {
            if (staffs[i].state == FREE) continue

            staffs[i].run {
                if(--tWork == 0) state = FREE
            }
        }
    }

    println(doneCount)
}