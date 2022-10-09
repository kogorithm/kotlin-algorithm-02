val q= arrayListOf<Pair<Int,Int>>()
lateinit var size:List<Int>
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

            if(ny<0||ny>size[0]-1||nx>size[1]-1||nx<0){
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
    size=readln().split(" ").map { it.toInt() }
    map=Array(size[0]){Array(size[1]){0}}
    visit=Array(size[0]){Array(size[1]){false} }

    repeat(size[0]){
        map[it]=readln().split(" ").map { it.toInt() }.toTypedArray()
    }

    for(i in 0 until size[0]){
        for(j in 0 until size[1]){
            if(map[i][j]!=0){
                q.add(Pair(i,j))
                visit[i][j]=true
                arr.add(bfs())
            }
        }
    }


    val numOfPaint=arr.size
    println(numOfPaint)
    if(numOfPaint==0){
        println(0)
    }else{
        arr.sort()
        println(arr.last())
    }

}
