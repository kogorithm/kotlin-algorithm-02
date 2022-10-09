val q= arrayListOf<Pair<Int,Int>>()
var N=0
lateinit var map:Array<Array<Int>>
lateinit var visit:Array<Array<Boolean>>
val dx=arrayOf(0,1,-1,0)
val dy=arrayOf(1,0,0,-1)

fun bfs():Int{
    var count=0

    while(!q.isEmpty()){
        val (y,x)=q.removeFirst()
        count++
        map[y][x]=0

        for(i in 0 until 4){
            val ny=y+dy[i]
            val nx=x+dx[i]

            if(ny<0||ny>N-1||nx>N-1||nx<0){
                continue
            }
            if(map[ny][nx]==0 || visit[ny][nx]){
                continue
            }

            q.add(Pair(ny,nx))
            visit[ny][nx]=true
        }
    }

    return count

}


fun main(){
    val arr= arrayListOf<Int>()
    N=readln().toInt()
    map=Array(N){Array(N){0}}
    visit=Array(N){Array(N){false} }

    repeat(N){
        map[it]=readln().chunked(1).map { it.toInt() }.toTypedArray()
    }

    for(i in 0 until N){
        for(j in 0 until N){
            if(map[i][j]!=0){
                q.add(Pair(i,j))
                visit[i][j]=true
                arr.add(bfs())
            }
        }
    }

    arr.sort()
    val countOfComplex=arr.size
    println(countOfComplex)
    for(i in arr){
        println(i)
    }

}