lateinit var train:Array<Int>



fun main(){
    val (N,M)= readln().split(" ").map { it.toInt() }
    train=Array(N){0}

    repeat(M){
        val command= readln().split(" ").map { it.toInt() }
        val c=command[0]
        val t=command[1]
        when(c){
            1->{
                //추가
                train[t-1]=train[t-1] or (1 shl (command[2]-1))
            }
            2->{
                //삭제
                train[t-1]=train[t-1] and (1 shl (command[2]-1)).inv()
            }
            3->{
                //한칸 뒤로
                //기차의 좌석 개수는 20개 1111 1111 1111 1111 1111 = 1048575 , 1 0000 0000 0000 0000 0000 = 1048576

                train[t-1]=train[t-1] shl 1
                if(train[t-1]>1048575){
                    train[t-1]-=1048576
                }


            }
            4->{
                // 한칸 앞으로
                train[t-1]=train[t-1] shr 1
            }

        }

    }

    println(train.distinct().size)

}