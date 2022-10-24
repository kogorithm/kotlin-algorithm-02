package `4week`.acw

class `acw트리의부모찾기`{
    lateinit var treeMap:Array<MutableList<Int>>
    lateinit var parent:Array<Int>
    var N=0

    fun dfs(p:Int){
        for(child in treeMap[p]) {
            parent[child] = p
            treeMap[child].remove(p)
            dfs(child)
        }
    }



    fun solution(){
        N=readln().toInt()
        treeMap=Array(N+1){ mutableListOf() }
        parent=Array(N+1){0}

        for(i in 1 until N){
            val (a,b)=readln().split(" ").map { it.toInt() }
            treeMap[a].add(b)
            treeMap[b].add(a)
        }

        dfs(1)
        for(i in 2..N){
            println(parent[i])
        }

    }
}
fun main(){
    val sol=`acw트리의부모찾기`()
    sol.solution()
}
