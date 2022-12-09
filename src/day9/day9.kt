package day9

import lib.Point
import readInput
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

fun main() {
    val day = 9
    val testInput = readInput("day$day/testInput")
    check(part1(testInput) == 13)

    val input = readInput("day$day/input")
    println(part1(input))
    println(part2(input))
}

fun getMoves(input: List<String>): List<Pair<Char, Int>> {
    return input.map {
        val (dir, moveSize) = it.split(" ")
        Pair(dir.first(), moveSize.toInt())
    }
}

fun part1(input: List<String>): Int {
    return followHead(2, getMoves(input))
}

fun part2(input: List<String>): Int {
    return followHead(10, getMoves(input))
}

fun Point.updateHead(dir: Char): Point {
    return when (dir) {
        'R' -> toRight()
        'L' -> toLeft()
        'U' -> higher()
        'D' -> lower()
        else -> error("Invalid direction")
    }
}

fun Point.followIfBeyond(f: Point, distance: Double = 1.5): Point? {
    return if (this.distanceTo(f) < distance) {
        null
    } else Point(
        x + sign(f.x - x.toDouble()).toInt() * min(abs(f.x - x), 1),
        y + sign(f.y - y.toDouble()).toInt() * min(abs(f.y - y), 1)
    )
}

fun followHead(ropeSize: Int, moves: List<Pair<Char, Int>>): Int {

    val rope = MutableList(ropeSize) { Point(0, 0) }
    val tailPoints = HashSet<Point>().apply {
        add(rope.last())
    }

    for ((dir, steps) in moves) {
        repeat(steps) {
            rope[0] = rope[0].updateHead(dir)

            for (i in 1 until ropeSize) {

                rope[i].followIfBeyond(rope[i - 1])?.let {
                    rope[i] = it
                    if (i == ropeSize - 1) {
                        tailPoints.add(rope.last())
                    }
                } ?: break

            }
        }
    }
    return tailPoints.count()
}