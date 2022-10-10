package `2week`.acw

import kotlin.math.absoluteValue



const val MAX=1000000
class 리모컨{
    val btn=Array(10){false}
    val availableBtn= arrayListOf<Int>()


    fun possible(num:Int):Boolean{
        var flag=true
        var n=num
        var now=0

        do{
            now=n%10
            n/=10
            if(!availableBtn.contains(now)){
                flag=false
                break
            }
        }while(n>0)

        return flag
    }//버튼들로 채널 만들 수 있는지 check

    fun Int.magnitude():Int{
        var count=0
        var num=this
        do{
            num/=10
            count++
        }while(num>0)

        return count
    }//자릿수 구하기


    fun findNum(N:Int):Int{
        var answer=MAX
        var num=N


        //down
        while(num>=0){
            if(possible(num)){
                answer=(N-num)+num.magnitude()
                break
            }
            num--
        }


        num=N

        //up
        while(num<MAX){
            if(possible(num)){
                answer=answer.coerceAtMost((num-N)+num.magnitude())
                break
            }
            num++
        }


        return answer
    }


    fun solution(){
        val N=readln().toInt()
        val numOfBreak=readln().toInt()
        if(numOfBreak!=0){
            readln().split(" ").map{
                btn[it.toInt()]=true
            }
        }

        for(i in 0 until 10){
            if(!btn[i])availableBtn.add(i)
        }

        var answer=findNum(N)

        if(availableBtn.isEmpty()){
            println((N-100).absoluteValue)
        }else{
            println(answer.coerceAtMost((N-100).absoluteValue))

        }


    }

}
fun main() {
    val sol = 리모컨()
    sol.solution()
}

