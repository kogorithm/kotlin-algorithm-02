package `4week`.acw

class `acw근손실`{
    lateinit var exerciseKits:List<Int>
    lateinit var visit:Array<Boolean>
    var cnt=0



    fun makeComb(arr:MutableList<Int>,power:Int,day:Int,N:Int,K:Int){
        var powerNow=power
        if(arr.isNotEmpty()){
            powerNow=power+arr.last()-K
        }

        if(powerNow<500)
        {
            return
        }

        if(day==N){
            cnt++
            return
        }

        for(i in 0 until N){
            if(visit[i]){
                continue
            }

            arr.add(exerciseKits[i])
            visit[i]=true
            makeComb(arr,powerNow,day+1,N,K)
            arr.remove(exerciseKits[i])
            visit[i]=false
        }
    }


    fun solution(){
        val (N,K)=readln().split(" ").map { it.toInt() }
        exerciseKits=readln().split(" ").map { it.toInt() }
        visit=Array(N){false}
        makeComb(mutableListOf(),500,0,N,K)

        println(cnt)

    }
}
fun main() {
    val sol = `acw근손실`()
    sol.solution()
}

