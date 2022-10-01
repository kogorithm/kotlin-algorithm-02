lateinit var input:Array<Int>
lateinit var sushi:Array<Int>

private val N by lazy{
    input[0]
}
private val d by lazy{
    input[1]
}
private val k by lazy{
    input[2]
}
private val c by lazy{
    input[3]
}

fun countSushi(firstSushi:Int):Int{
    var answer=0
    var arr= arrayListOf<Int>()
    var first=firstSushi
    var now=first

    while(first<N){

        while(arr.size<k){
            arr.add(sushi[now])
            now=(now+1)%N
        }

        var numOfSushi=arr.distinct().size
        if(!arr.contains(c)){
            numOfSushi+=1
        }
        if(answer<numOfSushi){
            answer=numOfSushi
        }

        arr.removeFirst()
        first++

    }


    return answer

}

val br=System.`in`.bufferedReader()
fun main()=with(System.out.bufferedWriter()){
    var answer=0
    input= br.readLine().split(" ").map { it.toInt() }.toTypedArray()
    sushi=Array(N){0}

    for ( i in 0 until N){
        val e= br.readLine().toInt()
        sushi[i]=e
    }
    answer=countSushi(0)


    write("$answer")
    close()



}