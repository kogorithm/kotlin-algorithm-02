package `4week`.acw


import kotlin.math.pow

//시계방향은 오른쪽shift 반시계는 왼쪽 shift
const val RIGHT = 5
const val LEFT = 1

class `acw톱니바퀴` {

    var wheel = Array(4) { 0 }
    var visit = Array(4) { false }
    var turn = arrayListOf<Pair<Int, Int>>()


    private fun addTurn(w: Int, turnD: Int) {
        //dfs로 들어가면서 회전해야할 것들 turn에 추가해주기


        val wheelNowRight = if (wheel[w] and (1 shl RIGHT) != 0) 1 else 0
        val wheelNowLeft = if (wheel[w] and (1 shl LEFT) != 0) 1 else 0

        if (w - 1 >= 0) {
            val leftWheelsRight = if (wheel[w - 1] and (1 shl RIGHT) != 0) 1 else 0
            if (wheelNowLeft != leftWheelsRight && !visit[w - 1]) {
                visit[w - 1] = true
                turn.add(Pair(w - 1, turnD * -1))
                addTurn(w - 1, turnD * -1)
            }
        }

        if (w + 1 < 4) {
            val rightWheelsLeft = if (wheel[w + 1] and (1 shl LEFT) != 0) 1 else 0
            if (wheelNowRight != rightWheelsLeft && !visit[w + 1]) {
                visit[w + 1] = true
                turn.add(Pair(w + 1, turnD * -1))
                addTurn(w + 1, turnD * -1)
            }
        }


    }

    private fun turnWheel() {

        while (turn.isNotEmpty()) {
            val (w, d) = turn.removeFirst()

            if (d == -1) {
                //반시계
                val last = wheel[w] and (1 shl 7)

                wheel[w] = (wheel[w] shl 1) and 255
                if (last != 0) {
                    wheel[w]++
                }

            } else {
                //시계
                val first = wheel[w] and 1

                wheel[w] = wheel[w] shr 1

                if (first != 0) {
                    wheel[w] += 1 shl 7
                }
            }


        }

    }


    fun solution() {
        repeat(4) {
            var input = readln()
            var m = 128
            var num = 0
            for (i in input) {
                num += (i.digitToInt() * m)
                m /= 2
            }//숫자로 바꿔서 저장
            wheel[it] = num

        }

        val turnCnt = readln().toInt()
        repeat(turnCnt) {
            val (w, d) = readln().split(" ").map { it.toInt() }
            turn.add(Pair(w - 1, d))
            visit[w - 1] = true


            addTurn(w - 1, d)//회전해야할 것들 turn List에 추가해주기
            turnWheel()// turn List에서 빼서 회전시켜주기
            visit = Array(4) { false } // visit은 다시 사용해야함으로 다시 false로 초기화
        }


        val answer = wheel.withIndex().sumOf { (idx, value) ->
            if (value and (1 shl 7) != 0) {
                (2.0).pow(idx).toInt()
            } else {
                0
            }
        }


        println(answer)


    }
}

fun main() {
    val sol = `acw톱니바퀴`()
    sol.solution()
}
