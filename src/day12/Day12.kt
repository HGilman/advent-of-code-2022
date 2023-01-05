package day12

import lib.Point2D
import readInput


fun main() {
    val day = 12
//    val testInput = readInput("day$day/testInput")
//    check(part1(testInput) == 31)

    val input = readInput("day$day/input")
    println(part1(input))
//    println(part2(input))
}

fun getHeight(c: Char): Int {
    return when (c) {
        'S' -> getHeight('a')
        'E' -> getHeight('z')
        else -> c - 'a'
    }
}

fun part1(input: List<String>): Int {

    val height = input.size
    val width = input[0].toCharArray().size

    var sX: Int = 0
    var sY: Int = 0

    var eX: Int = 0
    var eY: Int = 0

    val map = List<List<Int>>(height) { y ->
        List<Int>(width) { x ->
            val c = input[y][x]

            if (c == 'S') {
                sX = x
                sY = y
            } else if (c == 'E') {
                eX = x
                eY = y
            }
            getHeight(c)
        }
    }

    fun isPointOnMap(p: Point2D): Boolean {
        return (p.x in (0 until width)) && (p.y in (0 until height))
    }

    fun dfsA(point: Point2D, destination: Point2D, path: LinkedHashMap<Point2D, Char>) {

        if (point == destination) {
            return
        }

        // try to go four different directions
        val left = point.toLeft() to '<'
        val right = point.toRight() to '>'
        val up = point.higher() to 'v'
        val low = point.lower() to '^'

        // heuristics : find point which is nearest to destination, and also possible to go

        val directions = listOf(left, right, up, low)

        val preferedDirection = directions.mapIndexed { i, dir ->

            if (isPointOnMap(dir.first)) {
                val currentHeight = map[point.y][point.x]
                val directionHeight = map[dir.first.y][dir.first.x]
                val isPossibleDir = (directionHeight - currentHeight <= 1) && !path.contains(dir.first)
                Pair(dir, isPossibleDir)
            } else {
                null
            }
        }.filterNotNull().filter { it.second }.minByOrNull { it.first.first.distanceTo(destination) }?.first ?: return

        path[point] = preferedDirection.second

        dfsA(preferedDirection.first, destination, path)
    }

    val path = linkedMapOf<Point2D, Char>()
    dfsA(Point2D(sX, sY), Point2D(eX, eY), path)

    for (y in 0 until height) {
        for (x in 0 until width) {
            val h = map[y][x].toString()
            val s = (if (h.length == 1) "$h " else h) + " "
            print(s)
        }
        println()
    }

    println()

    for (y in 0 until height) {
        for (x in 0 until width) {
            val c = path[Point2D(x, y)] ?: "."
            print("$c ")
        }
        println()
    }

    println(path.size)
    return path.size
}

fun part2(input: List<String>): Int {
    return input.size
}