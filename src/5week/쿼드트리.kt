package `5week`

class 전현수_쿼드트리 {

    /**
     * <문제>
     * [쿼드트리](https://www.acmicpc.net/problem/1992)
     *
     * 분할정복?
     *
     * 단계
     * - 현재 영역이 0, 1 단일로만 구성되어있는지 확인해보기
     *      - 단일로만 구성되어있다면
     *          - 0, 1로 압축하기
     *      - 4등분 하기
     *          - 각 등분 별로 단계 처음부터 반복
     * -
     */
    fun solution() {

        val size = readln().toInt()
        val imageData = Array<String>(size) { readln() }
        divideAndConquer(imageData.toList()).apply {
            println(this)
        }

    }

    fun divideAndConquer(imageData: List<String>): String {

        // 단일 숫자로만 구성되었는지 확인하기
        var isSingle = true
        val firElement = imageData.first().first()
        imageData.forEach { string ->
            if (string.any { it != firElement }) isSingle = false
        }

        // 단일이라면
        if (isSingle) {
            return "$firElement"
            // 단일이 아니라면, 4분할 후 각각 재귀
        } else {

            val size = imageData.size
            val halfSize = size / 2

            // 좌상 우상 좌하 우하
            val firstArea = imageData.subList(0, halfSize).map {
                it.substring(0, halfSize)
            }
            val secondArea = imageData.subList(0, halfSize).map {
                it.substring(halfSize, size)
            }
            val thirdArea = imageData.subList(halfSize, size).map {
                it.substring(0, halfSize)
            }
            val fourthArea = imageData.subList(halfSize, size).map {
                it.substring(halfSize, size)
            }

            val leftTop = divideAndConquer(firstArea)
            val rightTop = divideAndConquer(secondArea)
            val leftBottom = divideAndConquer(thirdArea)
            val rightBottom = divideAndConquer(fourthArea)

            return "($leftTop$rightTop$leftBottom$rightBottom)"
        }

    }

}