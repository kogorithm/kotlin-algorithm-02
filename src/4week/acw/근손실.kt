package `4week`.acw

class `acw근손실` {
    lateinit var exerciseKits: List<Int>
    lateinit var visit: Array<Boolean>
    var cnt = 0


    fun makePermutation(arr: MutableList<Int>, power: Int, day: Int, N: Int, K: Int) {
        //순열구성하기
        val powerNow = power + (arr.takeIf { it.isNotEmpty() }?.let { it.last() - K } ?: 0)

        if (powerNow < 500) {
            return
        }

        if (day == N) {
            cnt++
            return
        }

        for (i in 0 until N) {
            if (visit[i]) {
                continue
            }

            arr.add(exerciseKits[i])
            visit[i] = true
            makePermutation(arr, powerNow, day + 1, N, K)
            arr.remove(exerciseKits[i])
            visit[i] = false
        }
    }


    fun solution() {
        val (N, K) = readln().split(" ").map { it.toInt() }
        exerciseKits = readln().split(" ").map { it.toInt() }
        visit = Array(N) { false }
        makePermutation(mutableListOf(), 500, 0, N, K)

        println(cnt)

    }
}

fun main() {
    val sol = `acw근손실`()
    sol.solution()
}