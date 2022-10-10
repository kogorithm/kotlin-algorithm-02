package `1week`.acw

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.hypot

class 선물이넘쳐흘러 {

    lateinit var timeArr: Array<Int>
    lateinit var map: Array<Array<Int>>
    var answer = 0


    var working = mutableListOf<Triple<Int, Int, Int>>()

    fun putNewPresent(B: Int, numOfM: Int) {
        val tmp1 = map[0][B - 1]

        for (i in B - 1 downTo 1) {
            map[0][i] = map[0][i - 1]
        }


        val tmp2 = map[B - 1][B - 1]
        for (i in B - 1 downTo 2) {
            map[i][B - 1] = map[i - 1][B - 1]
        }
        map[1][B - 1] = tmp1


        for (i in 0..B - 3) {
            map[B - 1][i] = map[B - 1][i + 1]
        }
        map[B - 1][B - 2] = tmp2

        //컨베이어 벨트 한칸 씩 밀기


        if (numOfM > 0) {
            map[0][0] = 1
            // 새 선물 벨트 위에 올리기
        } else {
            map[0][0] = 3
        }


    }

    fun assignPerson(y: Int, x: Int, B: Int) {
        val dx = arrayOf(0, 1, -1, 0)
        val dy = arrayOf(1, 0, 0, -1)


        var ny = 0;
        var nx = 0
        for (i in 0 until 4) {
            ny = y + dy[i]
            nx = x + dx[i]

            if (ny < 0 || ny > B - 1 || nx < 0 || nx > B - 1 || ny == 0 || nx == B - 1 || ny == B - 1) {
                continue
            }//유효성 검사


            if (map[ny][nx] > 0) {//사람을 찾았을 경우
                working.add(Triple(ny, nx, map[ny][nx]))
                map[ny][nx] = -map[ny][nx]

                map[y][x] = 3
                answer++
                break
            }


        }
    }

    fun solution() {
        val (B, N, M) = readLine()!!.split(" ").map { it.toInt() }
        map = Array(B) { Array(B) { 0 } }
        timeArr = Array(N) { 0 }

        for (i in 0 until B) {
            for (j in 0 until B) {
                if (i == 0 || j == B - 1 || i == B - 1) {
                    map[i][j] = 3
                    //3 - 컨베이어벨트
                }
            }
        }

        for (i in 0 until N) {
            val (y, x, t) = readLine()!!.split(" ").map { it.toInt() }
            map[y][x] = t

        }

        // input end

        val endTime = M * ((B - 1) * 3 - 2)
        var numOfM = M

        for (i in 1..endTime) {
            //선물 올리기
            putNewPresent(B, numOfM)
            numOfM--

            //일하는 사람 시간 깎기
            val num = working.size
            for (i in 0 until num) {
                val tmp = working.first()
                working.removeFirst()

                var time = tmp.third
                val y = tmp.first;
                val x = tmp.second

                time--
                if (time == 0) {
                    map[y][x] = -map[y][x]
                    continue
                }

                working.add(Triple(y, x, time))
            }


            // 사람 배정하기

            for (i in 0 until B) {
                if (map[B - 1][i] != 3) {
                    assignPerson(B - 1, i, B)
                }
            }

            for (i in B - 1 downTo 0) {
                if (map[i][B - 1] != 3) {
                    assignPerson(i, B - 1, B)
                }
            }

            for (i in B - 1 downTo 0) {
                if (map[0][i] != 3) {
                    assignPerson(0, i, B)
                }
            }


        }

        println(answer)


    }
}
fun main(){
    val solClass=선물이넘쳐흘러()
    solClass.solution()
}

