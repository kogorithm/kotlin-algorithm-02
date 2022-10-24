package `4week`.acw


import java.util.*

class `acw지름길`{
    lateinit var distance:Array<Int>
    var roads= arrayListOf<Triple<Int,Int,Int>>()

    fun solution(){
        val (K,D)=readln().split(" ").map{it.toInt()}
        distance=Array(10001){it}

        var now=0
        var nowCost=0

        for(i in 1..K){
            val (s,e,c)=readln().split(" ").map{it.toInt()}

            if(e>D){
                continue
            }

            if(c>=(e-s)){
                continue
            }

            roads.add(Triple(s,e,c))
        }

        while(roads.isNotEmpty()){
            var now=roads.first()
            var cost=now.second-now.first-now.third
            var nowRange=now.first..now.second
            val arr= LinkedList<Triple<Int,Int,Int>>()

            for(i in 1 until roads.size){
                val (s,e,c)=roads[i]
                if(s in nowRange || e in nowRange){
                    arr.add(Triple(s,e,c))
                }
            }
            arr.add(now)
            for(i in 0 until arr.size){
                var costNow=arr[i].second-arr[i].first-arr[i].third
                for(j in i+1 until arr.size){

                    if(arr[i].second<=arr[j].first || arr[i].first>=arr[j].second){
                        costNow+=(arr[j].second-arr[j].first-arr[j].third)
                    }
                }
                if(costNow>cost){
                    cost=costNow
                }

            }//겹치는 것들 중 구성할 수 있는 최대 값 구성
            distance[D]-=cost


            roads.removeAll(arr)


        }




        println(distance[D])


    }
}
fun main(){
    val sol =`acw지름길`()
    sol.solution()
}
