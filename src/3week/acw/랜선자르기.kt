package `3week`.acw

import java.util.PriorityQueue
class 랜선자르기{
    var answer=0L
    var test=PriorityQueue<Long>()
    fun binarySearch(start:Long,end:Long,targetNum:Int){

        if(start>end){
            return
        }

        var s:Long=start.toLong()
        var e:Long=end.toLong()
        var count=0L
        val now=((s+e)/2)
        for(i in test){
            count+=(i/now)

        }

        if(count<targetNum){
            binarySearch(s,now-1,targetNum)
            //작을 땐 더 작은 수로 계산해보기
        }else{
            answer=answer.coerceAtLeast(now)
            binarySearch(now+1,e,targetNum)
            //크면 더 큰 수 찾아보기
        }

    }

    fun solution(){
        val (K,N)= readln().split(" ").map{it.toInt()}

        repeat(K){
            val input= readln().toLong()
            test.add(input)
        }
        binarySearch(1,test.last(),N)

        println(answer)





    }
}
fun main() {
    val sol = 랜선자르기()
    sol.solution()
}


