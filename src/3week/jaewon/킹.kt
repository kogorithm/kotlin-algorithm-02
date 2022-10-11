import kotlin.math.hypot


class JaeWonKing{
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        data class Pos( val x : Int, val y : Int )

        val pos : Map<Char,Pos> =mapOf(
            'R' to Pos(1,0),
            'L' to Pos(-1,0),
            'B' to  Pos(0,-1),
            'T' to Pos(0,1))

        fun solution(){
            var (kingPos, rockPos, n) = readln().split(" ").map {it}

            repeat(n.toInt()){
                val move = readln().toString()
                var tKing = kingPos
                var tRock = rockPos
                for (i in move){
                    tKing = "${tKing[0]+(pos[i]!!.x)}"+"${tKing[1]+(pos[i]!!.y)}"
                }

                if (tKing[0] in 'A'..'H' && tKing[1] in '1'..'8'){
                    //킹이 밖으로 나가지 않았을 경우
                    if (tKing == rockPos){ // 킹이 움직이는 곳에 돌이 있는 경우
                        for (i in move){
                            tRock = "${tRock[0]+pos[i]!!.x}"+"${tRock[1]+pos[i]!!.y}"
                        }
                        if (tRock[0] in 'A'..'H' && tRock[1] in '1'..'8'){ // 돌이 밖으로 나가지 않을 경우
                            kingPos = tKing
                            rockPos = tRock
                        }
                    }else {
                        kingPos = tKing
                    }
                }
            }

            println(kingPos)
            println(rockPos)
        }

    }
}

fun main(){
    JaeWonKing.getSolution().solution()
}

