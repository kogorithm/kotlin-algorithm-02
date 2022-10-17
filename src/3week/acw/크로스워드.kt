package `3week`.acw

import java.util.PriorityQueue

class 크로스워드{
    lateinit var puzzle: Array<String>

    lateinit var size: List<Int>

    val direction = listOf<Pair<Int, Int>>(
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, 0),
        Pair(-1, 0)
    )

    var words = PriorityQueue<String>()

    fun isIsolate(y:Int,x:Int):Boolean{
        for(i in 0 until 4){
            val ny=y+direction[i].first
            val nx=x+direction[i].second

            if(ny<0 || nx<0 || ny>=size[0] ||nx>=size[1]){
                continue
            }

            if(puzzle[ny][nx] !in 'a'..'z'){
                return false
            }
        }

        return true
    }

    fun solution() {

        size = readln().split(" ").map { it.toInt() }

        puzzle = Array(size[0]){ readln() }

        //visit = Array(size[0]) { Array(size[1]) { false } }

        repeat(size[0]){
            puzzle[it].split("#").map {
                if(it.length>1){
                    words.add(it)
                }
            }

        }//가로

        repeat(size[1]){
            var word=""
            for(i in 0 until size[0]){
                word+=puzzle[i][it]
            }
            word.split("#").map {
                if(it.length>1){
                    words.add(it)
                }
            }
        }//세로

        for(i in 0 until size[0]){
            for(j in 0 until size[1]){
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
