package `5week`.acw


const val WEST = 0
const val SOUTH = 1
const val EAST = 2
const val NORTH = 3

class `acw미로만들기` {
    var map = Array(101) { Array(101) { "#" } }

    fun solution() {
        readln()
        val mv = readln()
        var directionNow = SOUTH

        var ny = 50
        var nx = 50

        map[ny][nx] = "."

        for (i in mv) {
            when (i) {
                'R' -> {
                    directionNow--
                    if (directionNow == -1) {
                        directionNow = NORTH
                    }

                }

                'L' -> {
                    directionNow = (directionNow + 1) % 4
                }

                'F' -> {
                    when (directionNow) {
                        WEST -> {
                            nx--
                        }

                        EAST -> {
                            nx++
                        }

                        SOUTH -> {
                            ny++
                        }

                        NORTH -> {
                            ny--
                        }
                    }
                    map[ny][nx] = "."
                }
            }
        }

        var maxX = 50
        var maxY = 50
        var minX = 50
        var minY = 50

        for (i in 0..100) {
            for (j in 0..100) {
                if (map[i][j] == ".") {
                    maxX = maxX.coerceAtLeast(j)
                    maxY = maxY.coerceAtLeast(i)
                    minX = minX.coerceAtMost(j)
                    minY = minY.coerceAtMost(i)
                }
            }
        }

        for (i in minY..maxY) {
            for (j in minX..maxX) {
                print(map[i][j])
            }
            println()
        }


    }
}

fun main() {
    val sol = `acw미로만들기`()
    sol.solution()
}
