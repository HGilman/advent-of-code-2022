package day24

import lib.DecreaseYDirection
import lib.IncreaseYDirection
import lib.LeftDirection
import lib.Point2D
import lib.RightDirection
import lib.Vector2D
import lib.directionsClockwise
import readInput

fun main() {
    val day = 24
    val testInput = readInput("day$day/testInput")
    check(part1(testInput) == 18)

    val input = readInput("day$day/input")
    println(part1(input))
//    println(part2(input))
}

fun part1(input: List<String>): Int {

    val h = input.size
    val w = input[0].length

    val start = Point2D(1, 0)
    val destination = Point2D(w - 2, h - 1)

    val winds = mutableListOf<Pair<Point2D, Vector2D>>()

    (0 until h).forEach { y ->
        (0 until w).forEach { x ->
            val c = input[y][x]
            val p = Point2D(x, y)
            when (c) {

                '>' -> winds.add(p to RightDirection)

                '<' -> winds.add(p to LeftDirection)

                'v' -> winds.add(p to IncreaseYDirection)

                '^' -> winds.add(p to DecreaseYDirection)
                else -> {}

            }
        }
    }

    fun updateWindsPosition() {
        for (i in winds.indices) {
            val wind = winds[i]
            val newPosition = wind.first + wind.second
            var x = newPosition.x
            var y = newPosition.y

            if (x < 1) {
                x = (w - 2)
            }

            if (x > w - 2) {
                x = 1
            }

            if (y < 1) {
                y = (h - 2)
            }

            if (y > h - 2) {
                y = 1
            }
            winds[i] = Point2D(x, y) to wind.second
        }
    }

    var minute = 0
    val childs = mutableSetOf<Point2D>()
    childs.add(start)
    var notFound = true

    while (notFound) {
        updateWindsPosition()

        val windsPositions = winds.map { it.first }.toSet()

        val newChilds = mutableSetOf<Point2D>()

        for (childNode in childs) {

            directionsClockwise.forEach { dir ->
                val p = childNode + dir

                if (p == destination) {
                    notFound = false
                }

                if (((p.x in 1 until w - 1) && (p.y in 1 until h - 1)) || p == start) {

                    if (!windsPositions.contains(p)) {
                        newChilds.add(p)
                    }
                }
            }
            if (!windsPositions.contains(childNode)) {
                newChilds.add(childNode)
            }
        }
        childs.clear()
        childs.addAll(newChilds)

        minute++
        println("Minute: $minute, childCount: ${childs.size}")
    }
    return minute
}


fun part2(input: List<String>): Int {
    return input.size
}