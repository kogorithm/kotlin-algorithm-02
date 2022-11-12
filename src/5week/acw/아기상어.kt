package `5week`.acw


class `acw아기상어` {
    lateinit var m: Array<Array<Int>>
    lateinit var visits: Array<Array<Boolean>>


    var q = arrayListOf<Triple<Int, Int, Int>>()

    val dx = arrayOf(0, -1, 1, 0)
    val dy = arrayOf(-1, 0, 0, 1)
    var answer = 0
    var sizeOfShark = 2
    var fishEaten = 0
    var foods = 0

    fun solution() {
        var x = 0;
        var y = 0//상어의 위치
        val N = readln().toInt()
        m = Array(N) { Array(N) { 0 } }
        visits = Array(N) { Array(N) { false } }

        for (i in 0 until N) {
            m[i] = readln().split(" ").map { it.toInt() }.toTypedArray()
        }

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (m[i][j] == 9) {
                    y = i
                    x = j
                } else if (m[i][j] > 0) {
                    foods++
                }
            }
        }//상어 위치와 음식 개수 세기

        q.add(Triple(y, x, 0))
        visits[y][x] = true
        m[y][x] = 0

        var nx = 0;
        var ny = 0;
        var cost = 0

        var ex = 100;
        var ey = 100;
        var costExpected = 100
        //상어가 먹을 물고기의 위치를 임시 저장할 변수

        while (foods > 0) {
            if (q.isEmpty()) {
                //자기보다 몸집이 큰 물고기때문에 bfs도중 q가 비어버릴때
                if (ey != 100) {
                    //만약 q가 비었는데 비기 전 먹을 수 있는 물고기의 위치가 있다면 먹고나서 상어의 사이즈가 커지면서 bfs를 더 돌릴가능성이 생기므로 먹고 다시 bfs를 돌려준다.
                    y = ey
                    x = ex
                    cost = costExpected


                    m[y][x] = 0
                    foods--
                    fishEaten++

                    if (fishEaten == sizeOfShark) {
                        sizeOfShark++
                        fishEaten = 0
                        //상어 size up
                    }

                    q = arrayListOf()
                    q.add(Triple(y, x, 0))

                    visits = Array(N) { Array(N) { false } }
                    visits[y][x] = true

                    answer += (cost)

                    ey = 100
                    ex = 100
                    costExpected = 100
                } else {
                    break
                }
            } else {
                y = q.first().first
                x = q.first().second
                cost = q.first().third
                q.removeFirst()

                if (ey != 100 && costExpected != cost) {
                    //bfs depth가 바뀌었는데 ey에 값이 기록되어있을 경우에는 먹을 수 있는 물고기가 있다는 뜻이므로 먹어준다.
                    //그리고 먹어준 자리부터 다시 탐색을 시작한다.
                    y = ey
                    x = ex
                    cost = costExpected


                    m[y][x] = 0
                    foods--
                    fishEaten++

                    if (fishEaten == sizeOfShark) {
                        sizeOfShark++
                        fishEaten = 0
                        //상어 size up
                    }

                    q = arrayListOf()

                    visits = Array(N) { Array(N) { false } }
                    visits[y][x] = true

                    answer += (cost)
                    cost = 0

                    ey = 100
                    ex = 100
                    costExpected = 100
                    //bfs다시 돌리기 위해서 초기값 다시 set
                }

                if (m[y][x] in 1 until sizeOfShark) {
                    //먹을 수 있는 물고기라면 일단 그 물고기의 위치와 cost를 ey ex costExpected에 기록해둔다.
                    if (y < ey) {
                        //가장 위/왼쪽에 있는 물고기를 먹으라 했으므로
                        ey = y
                        ex = x
                        costExpected = cost
                    } else if (y == ey && x < ex) {
                        ey = y
                        ex = x
                        costExpected = cost
                    }

                }

                for (i in 0 until 4) {
                    nx = x + dx[i]
                    ny = y + dy[i]

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue
                    }
                    if (visits[ny][nx]) {
                        continue
                    }

                    if (m[ny][nx] > sizeOfShark) {
                        continue
                    } else if (m[ny][nx] <= sizeOfShark || m[ny][nx] >= 0) {
                        q.add(Triple(ny, nx, cost + 1))
                        visits[ny][nx] = true
                    }


                }


            }


        }



        println(answer)


    }
}

fun main() {
    val sol = `acw아기상어`()
    sol.solution()
}