fun main(){
    val (n,m) = readln().split(" ").map {it.toInt()}
    val trains = Array(n+1){BooleanArray(21){false}}
    val answer = mutableListOf<BooleanArray>()
    repeat(m){
        val input = readln().split(" ").map { it.toInt() }
        when(input[0]){
            1 ->{ trains[input[1]][input[2]] = true }
            2 ->{ trains[input[1]][input[2]] = false}
            3 ->{
                if(trains[input[1]][20]){
                    trains[input[1]][20] = false
                }
                for (i in 19 downTo 1){
                    if(trains[input[1]][i]){
                        trains[input[1]][i+1] = true
                        trains[input[1]][i] = false
                    }
                }
            }
            4 ->{
                if(trains[input[1]][1]){
                    trains[input[1]][1] = false
                }
                for (i in 2.. 20){
                    if(trains[input[1]][i]){
                        trains[input[1]][i-1] = true
                        trains[input[1]][i] = false
                    }
                }
            }
        }

    }

    for (i in 1..n){
        if (answer.count { it.contentEquals(trains[i]) } == 0){
            answer.add(trains[i])
        }
    }

    println(answer.size)
}