fun main(){
    val N=readln().toInt()
    var a=readln().split(" ").map { it.toInt() }
    var b=readln().split(" ").map{it.toInt()}
    var answer=0
    a=a.sorted()
    b=b.sortedByDescending { it }

    for(i in 0 until N){
        answer+=a[i]*b[i]
    }

    println(answer)
}