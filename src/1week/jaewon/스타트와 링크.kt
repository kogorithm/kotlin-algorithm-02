import kotlin.math.abs
import kotlin.math.min
import kotlin.system.exitProcess

class `스타트와 링크` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {

        val ability : MutableList<List<Int>> = mutableListOf()
        var min = 1000
        fun solution(){
            val n = readln().toInt()
            val temp = MutableList(n){0}
            val answer = mutableListOf<List<Int>>()
            var visited = Array<Boolean>(n, {false});
            repeat(n){x ->
                temp[x] = x+1
                val list = readln().split(" ").map { it.toInt() }
                ability.add(list)
            }

            fun getAbility(){
                var team1 = 0
                var team2 = 0

                for(i in 0 until n-1) {
                    for(j in i+1 until n) {

                        if(visited[i] && visited[j]) {
                            team1 += ability[i][j] + ability[j][i];
                        }
                        else if( !visited[i] && !visited[j] ) {
                            team2 += ability[i][j] + ability[j][i];
                        }
                    }
                }
                min = min(min, abs(team1-team2))
                if(min == 0) {
                    println(min)
                    exitProcess(0)
                }
            }

            fun dfs(index : Int, depth : Int){
                if(depth == n/2){
                    getAbility()
                    return;
                }
                for (i in index until n){
                    if(visited[i]) continue;
                    visited[i] = true;
                    dfs(i+1, depth + 1);
                    visited[i] = false;
                }
            }


            dfs(0,0)
            println(min)
        }

    }
}

fun main() {
    `스타트와 링크`.getSolution().solution()
}



