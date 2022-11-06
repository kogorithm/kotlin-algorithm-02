/**
 *
 * 0. 현재 물고기 정보 저장.
 * 1. 물고기가 이동할 수 있는 경우
 *    모든 물고기는 한칸씩 이동
 *    상어가 있는 칸 ( shark 로 확인 ) , 물고기의 냄새가 있는 칸 ( smell로 확인 ) , 격자의 범위는 x
 *      -> 안되면 45도씩 이동해서 갈 수 있는지 확인
 *
 *
 * 2. 상어가 움직이는 경우
 *    1,2,3,4 중에 3개를 뽑고 중복순열을 만들어서 오름차순으로 정렬하기
 *    해당 값을 반복하면서
 *      좌표를 이동
 *          불가능하면 -> 이동x
 *          물고기가 있다면 -> 물고기 제외 count 추가
 *    물고기 제외 count max()
 *    max인 값의 좌표로 이동후 물고기 냄새 표시하기.
 *
 *
 * 3. smell에서 time이 2인 얘들 삭제
 * 4. 기존의 fishse 그대로 적용... 오마갓...
 *
 *
 */

data class FISH (val x : Int, val y : Int, val arrow : Int)
data class SMELL (val x : Int, val y : Int, var time : Int)

val fishPos = mapOf<Int, Pair<Int,Int>>(
    0 to Pair(0,0),
    1 to Pair(0,-1),
    2 to Pair(-1,-1),
    3 to Pair(-1,0),
    4 to Pair(-1,1),
    5 to Pair(0,1),
    6 to Pair(1,1),
    7 to Pair(1,0),
    8 to Pair(1,-1)
)

val sharkPos = mapOf(
    1 to Pair(-1,0),
    3 to Pair(1,0),
    2 to Pair(0,-1),
    4 to Pair(0,1)
)

var sharkX = 0
var sharkY = 0
var fishes = mutableListOf<FISH>()
val smell = mutableListOf<SMELL>()
val temp = mutableListOf<FISH>()
val moveSharkList = mutableListOf<String>()


fun main(){
    val (m,s) = readln().split(" ").map { it.toInt() }
    repeat(m){
        val fi = readln().split(" ").map { it.toInt() }
        fishes.add( FISH(fi[0],fi[1],fi[2]))
    }

    val shark = readln().split(" ").map { it.toInt() }
    sharkX = shark[0]
    sharkY = shark[1]
    makeMoveShark(arrayOf(1,2,3,4), Array(3){0},0,3)
    moveSharkList.sortDescending()

    repeat(s){
        smell.forEach{ smell -> smell.time ++}
        temp.clear()
        temp.addAll(fishes)

        //물고기 이동
        fishes.forEachIndexed { idx, fish ->
            var (x,y,arrow) = fish
            if (checkFish(x,y,arrow)){
                x += fishPos[arrow]!!.first
                y += fishPos[arrow]!!.second
                fishes[idx] =  FISH(x,y,arrow)
            }else if(!checkFish(x,y,arrow)){
                // 이동할 수 없는 경우
                var i  = 0
                while (i < 8){
                    arrow = if( arrow == 1) 8 else { arrow-1 } // 45도 반시계로 돌리기
                    if(checkFish(x,y,arrow)){
                        x += fishPos[arrow]!!.first
                        y += fishPos[arrow]!!.second
                        fishes[idx] = (FISH(x,y,arrow))
                        break
                    }else {
                        i++
                    }
                }
            }
        }

        //상어의 우선순위 이동pos 찾기
        var removeMax = 0
        var removePos = "444"
        moveSharkList.forEach sharkRepeat@{ pos ->
            var count = 0
            var tempMax = 0
            var tempSharkX = sharkX
            var tempSharkY = sharkY
            val tempFishList = mutableListOf<FISH>()
            tempFishList.clear()
            tempFishList.addAll(fishes)
            repeat(3){
                tempSharkX += sharkPos[(pos[it]).digitToInt()]!!.first
                tempSharkY += sharkPos[(pos[it]).digitToInt()]!!.second
                if (tempSharkX <= 4 && tempSharkY <=4){
                    count++
                    tempFishList.forEachIndexed {idx , fish ->
                        if (tempSharkX == fish.x && tempSharkY == fish.y){
                            // 상어자리에 물고기가 있다면
                            tempFishList[idx] = FISH(0,0,0)
                            tempMax++
                        }
                    }
                }
            }

            if (count == 3 && tempMax >= removeMax){
                removeMax = tempMax
                removePos = pos
                if (removeMax == 3){ return@sharkRepeat }
            }
        }

        removeFish(removePos) // 3번 이동( 물고기 제외 & 상어 좌표 이동 )
        smell.filter { it.time == 2 }.forEach { smell.remove(it) } // 두번 전에 생긴 물고기 냄새 지우기
        temp.forEach { fishes.add(it) }
    }

    println(fishes.size)
}

fun checkFish(x : Int, y : Int, arrow : Int) : Boolean{
    val newX = x + fishPos[arrow]!!.first
    val newY = y + fishPos[arrow]!!.second
    return !((newX == sharkX && newY == sharkY) || (smell.any{ smell -> smell.x == newX && smell.y == newY}) || newX >4 || newY >4)
}

fun makeMoveShark(arr : Array<Int>, output : Array<Int>, depth : Int, r : Int){
    if(depth == r){
        moveSharkList.add(output.joinToString(""))
        println()
        return
    }
    for (i in arr.indices){
        output[depth] = arr[i]
        makeMoveShark(arr,output,depth+1, r)
    }
}

fun removeFish(moveShark : String){
    moveShark.forEach {
        sharkX += sharkPos[it.digitToInt()]!!.first
        sharkY += sharkPos[it.digitToInt()]!!.second
        fishes.forEachIndexed {idx, fish ->
            if (sharkX == fish.x && sharkY == fish.y){
                // 상어자리에 물고기가 있다면
                fishes[idx] = FISH(0,0,0)
                smell.add(SMELL(fish.x,fish.y,0))
            }
        }
    }
    fishes = fishes.filterNot { it.x == 0 && it.y ==0 && it.arrow == 0 }.toMutableList()
}