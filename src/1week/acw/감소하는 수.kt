class 감소하는수{

    val br = System.`in`.bufferedReader()

    var answer: Long = -1
    var decreasingList = arrayListOf<Long>()


    fun findDecreasingNum(N: Long) {
        decreasingList.add(N)

        var last = N % 10
        if (last == 0L) {
            return
        }

        for (i in last - 1 downTo 0) {
            val next = N * 10 + i
            findDecreasingNum(next)
        }

    }


    fun solution() = with(System.out.bufferedWriter()) {

        val N = br.readLine().toLong()

        if (N < 11) {
            answer = N.toLong()
        } else if (N > 1022) {
            answer = -1
        } else {

            for (i in 0..9) {
                findDecreasingNum(i.toLong())
            }
            decreasingList.sort()
            answer = decreasingList[N.toInt()]
        }


        write("$answer")
        close()


    }

}
fun main(){
    val solClass=감소하는수()
    solClass.solution()
}
