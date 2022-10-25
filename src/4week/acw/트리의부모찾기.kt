package `4week`.acw

class `acw트리의부모찾기` {
    lateinit var treeMap: Array<MutableList<Int>>
    lateinit var parent: Array<Int>
    var N = 0

    fun dfs(p: Int) {
        //p로 전달받은 노드는 부모란 뜻이니까 연결된 노드들은 자식으로 처리해주기
        //dfs로 이어가면서 같은 처리 해주기
        for (child in treeMap[p]) {
            parent[child] = p
            treeMap[child].remove(p)
            dfs(child)
        }
    }


    fun solution() {
        N = readln().toInt()
        treeMap = Array(N + 1) { mutableListOf() }
        parent = Array(N + 1) { 0 }

        for (i in 1 until N) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            treeMap[a].add(b)
            treeMap[b].add(a)
            //일단 입력받을 경우 두 node에 대해 상호 연결하기
        }

        dfs(1)
        for (i in 2..N) {
            println(parent[i])
        }

    }
}

fun main() {
    val sol = `acw트리의부모찾기`()
    sol.solution()
}
