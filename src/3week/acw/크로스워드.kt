package `3week`.acw
import java.util.PriorityQueue


class 크로스워드{
    lateinit var puzzle: Array<String>
    var row=0
    var column=0

    private val direction = listOf(
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, 0),
        Pair(-1, 0)
    )
    data class Pos(val y: Int,val x: Int)

    var words = PriorityQueue<String>()

    fun isIsolate(y:Int,x:Int):Boolean{
        for(i in 0 until 4){
            val nPos= Pos(y+direction[i].first,x+direction[i].second)

            if(nPos.y<0 || nPos.x<0 || nPos.y>=column ||nPos.x>=row){
                continue
            }

            if(puzzle[nPos.y][nPos.x] !in 'a'..'z'){
                return false
            }
        }

        return true
    }

    fun solution() {
        readln().split(" ").let{ (c,r) ->
            row=r.toInt()
            column=c.toInt()
        }
        puzzle = Array(column){ readln() }


        repeat(column){
            puzzle[it].split("#").map {
                if(it.length>1){
                    words.add(it)
                }
            }

        }//가로

        repeat(row){
            var word=""
            for(i in 0 until column){
                word+=puzzle[i][it]
            }
            word.split("#").map {
                if(it.length>1){
                    words.add(it)
                }
            }
        }//세로

        for(i in 0 until column){
            for(j in 0 until row){
                isIsolate(i,j)
            }
        }


        println(words.first())

    }
}
fun main(){
    val sol = 크로스워드()
    sol.solution()
}

