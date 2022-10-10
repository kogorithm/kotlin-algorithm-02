import kotlin.math.absoluteValue

class 스타트와링크 {

    val br = System.`in`.bufferedReader()

    lateinit var point: Array<Array<Int>>
    lateinit var team: Array<Int>
    lateinit var cb: Array<Boolean>
    var answer = 3000

    fun calcDiff(arr: ArrayList<List<Int>>): Int {
        val team1 = arr[0]
        val team2 = arr[1]
        var team1Score = 0
        var team2Score = 0

        val s = team1.size
        var ret = 0

        for (i in 0 until s) {
            for (j in i + 1 until s) {
                team1Score += (point[team1[i]][team1[j]] + point[team1[j]][team1[i]])
                team2Score += (point[team2[i]][team2[j]] + point[team2[j]][team2[i]])

            }
        }
        ret = (team1Score - team2Score).absoluteValue

        return ret
    }

    fun divideTeams(N: Int, start: Int, num: Int) {

        if (num == 0) {
            val teams = arrayListOf<List<Int>>()
            teams.addAll(listOf(team.filterIndexed { index, i -> cb[index] }))
            teams.addAll(listOf(team.filterIndexed { index, i -> !cb[index] }))
            val diff = calcDiff(teams)
            if (diff < answer) {
                answer = diff
            }
        } else {

            for (i in start until N) {
                cb[i] = true
                divideTeams(N, i + 1, num - 1)

                cb[i] = false
            }


        }


    }


    fun main() = with(System.out.bufferedWriter()) {

        val N = br.readLine().toInt()
        val halfN = N / 2
        point = Array(N) { Array(N) { 0 } }
        team = Array(N) { it }
        cb = Array(N) { false }
        for (i in 0 until N) {
            point[i] = br.readLine().split(" ").map { it.toInt() }.toTypedArray()
        }





        divideTeams(N, 0, halfN)
        write("$answer")
        close()


    }


}
