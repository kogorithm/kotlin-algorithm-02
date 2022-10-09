fun main(){
    val N=readln().toInt()
    var a=readln().split(" ").map { it.toInt() }.toMutableList()
    var b=readln().split(" ").map{it.toInt()}.toMutableList()
    var answer=0
    a.sort()
    b.sortByDescending { it }

    for(i in 0 until N){
        answer+=a[i]*b[i]
    }

    println(answer)
}