lateinit var arr:Array<Array<Int>>
var input= Array(2){0}
var answer=0

val dx= arrayOf(0,-1,1,0,1,1,-1,-1)
val dy= arrayOf(-1,0,0,1,1,-1,1,-1)


fun dfs(y:Int,x:Int){

    arr[y][x]=0

    for(i in 0 until 8){
        val ny=y+dy[i]
        val nx=x+dx[i]

        if(ny<0||ny>=input[0]||nx<0||nx>=input[1]){
            continue
        }

        if(arr[ny][nx]!=1){
            continue
        }

        dfs(ny,nx)
    }


}

fun main(args: Array<String>) {
    input= readLine()!!.split(" ").map{it.toInt()}.toTypedArray()


    arr=Array(input[0]){Array(input[1]){0}}

    for(i in 0 until input[0]){
        arr[i]= readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    }

    for(i in 0 until input[0]){
        for(j in 0 until input[1]){
            if(arr[i][j]==1){
                dfs(i,j)
                answer++
            }
        }
    }



    println(answer)


}