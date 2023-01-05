package day24

import lib.DecreaseYDirection
import lib.IncreaseYDirection
import lib.LeftDirection
import lib.Point2D
import lib.RightDirection
import lib.Vector2D
import lib.directionsClockwise
import readInput


data class Node(val pos: Point2D, val childs: MutableList<Node> = mutableListOf())


fun main() {
    val day = 24
    val testInput = readInput("day$day/testInput")
//    check(part1(testInput) == 18)

    val input = readInput("day$day/input")
    println(part1(input))
//    println(part2(input))
}

val dirToSymbol = mapOf(
    RightDirection to '>',
    LeftDirection to '<',
    IncreaseYDirection to 'v',
    DecreaseYDirection to '^'
)

fun part1(input: List<String>): Int {

    val h = input.size
    val w = input[0].length

    val start = Point2D(1, 0)
    val destination = Point2D(w - 2, h - 1)

    var winds = mutableMapOf<Point2D, MutableSet<Vector2D>>()

    (0 until h).forEach { y ->
        (0 until w).forEach { x ->
            val c = input[y][x]
            val p = Point2D(x, y)

            when (c) {

                '>' -> {
                    winds[p] = mutableSetOf(RightDirection)
                }

                '<' -> {
                    winds[p] = mutableSetOf(LeftDirection)
                }

                'v' -> {
                    winds[p] = mutableSetOf(IncreaseYDirection)
                }

                '^' -> {
                    winds[p] = mutableSetOf(DecreaseYDirection)
                }
            }
        }
    }

    fun updateWindsPosition() {
        val newWinds = mutableMapOf<Point2D, MutableSet<Vector2D>>()

        for (wind in winds) {

            for (dir: Vector2D in wind.value) {
                val nextPosition = wind.key + dir
                var x = nextPosition.x
                var y = nextPosition.y

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
                val newPosition = Point2D(x, y)

                newWinds.putIfAbsent(newPosition, mutableSetOf())
                newWinds[newPosition]?.add(dir)
            }

        }
        winds = newWinds
    }

    var minute = 0

    var childs = mutableSetOf<Point2D>()
    childs.add(start)

    var notFound = true

    while (notFound) {

        updateWindsPosition()

        val newChilds = mutableSetOf<Point2D>()

        for (childNode in childs) {

            if (childNode == destination) {
                notFound = false
            }

            var foundNear = false

            directionsClockwise.forEach { dir ->
                val p = childNode + dir

                if (p == destination) {
                    notFound = false
                }

                if (((p.x in 1 until w - 1) && (p.y in 1 until h - 1)) || p == start || p == destination) {

                    val windsContains = winds.containsKey(p)

                    if (!windsContains) {
                        newChilds.add(p)
                        foundNear = true
                    }
                }
            }

            if (!foundNear) {
                if (!winds.containsKey(childNode)) {
                    newChilds.add(childNode)
                }
            }
        }

        childs = newChilds

        minute++
        print("Minute: $minute, childCount: ${childs.size}")

        if (childs.isNotEmpty()) {
            print(", nearest dist: ${childs.minByOrNull { it.manhattanDistanceTo(destination) }!!.manhattanDistanceTo(destination)}")
        }
        println()
    }

    return minute
}



fun part2(input: List<String>): Int {
    return input.size
}