package `3week`.acw

const val king=3
const val stone=2
class 킹{
    lateinit var input:List<String>
    var chessMap=Array(8){Array(8){0} }

    val direction= hashMapOf<String,Pair<Int,Int>>(
        Pair("R",Pair(0,1)),
        Pair("L",Pair(0,-1)),
        Pair("B",Pair(1,0)),
        Pair("T",Pair(-1,0)),
        Pair("RT",Pair(-1,1)),
        Pair("LT",Pair(-1,-1)),
        Pair("RB",Pair(1,1)),
        Pair("LB",Pair(1,-1))
    )


    fun validate(y:Int,x:Int):Boolean{
        return !(y<0 || x<0 || y>7 || x>7)
    }

    fun solution(){
        input=readln().split(" ")

        var kingX=input[0][0]-'A'
        var kingY=8-input[0][1].digitToInt()

        var stoneX=input[1][0]-'A'
        var stoneY=8-input[1][1].digitToInt()

        val N=input[2].toInt()

        chessMap[kingY][kingX]=king
        //king

        chessMap[stoneY][stoneX]=stone
        //stone

        for( i in 0 until N){
            val mv=readln()

            val ny=kingY+direction[mv]!!.first
            val nx=kingX+direction[mv]!!.second
            if(!validate(ny,nx)){
                continue
            }//유효성 check

            if(chessMap[ny][nx]==stone){
                val nsy=stoneY+direction[mv]!!.first
                val nsx=stoneX+direction[mv]!!.second

                if(!validate(nsy,nsx)){
                    continue
                }//돌이 자리를 비켜주지 못할 때
                else{
                    chessMap[stoneY][stoneX]=0
                    chessMap[kingY][kingX]=0

                    chessMap[nsy][nsx]=stone
                    chessMap[ny][nx]=king

                    kingY=ny
                    kingX=nx

                    stoneY=nsy
                    stoneX=nsx
                    //이동시켜주기

                    continue
                }

            }//가는 자리에 돌이 있을 때


            chessMap[kingY][kingX]=0
            chessMap[ny][nx]=king
            kingY=ny
            kingX=nx
            //아무문제 없으면 king만 이동

        }



        println("${(kingX+'A'.toInt()).toChar()}${8-kingY}\n${(stoneX+'A'.toInt()).toChar()}${8-stoneY}")

    }
}


fun main(){
    val sol = 킹()
    sol.solution()
}