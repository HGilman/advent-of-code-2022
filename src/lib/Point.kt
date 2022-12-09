package lib

import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {

    override fun toString(): String {
        return "x: $x, y: $y"
    }

    fun toRight() = Point(x + 1, y)

    fun toLeft() = Point(x - 1, y)

    fun higher() = Point(x, y + 1)

    fun lower() = Point(x, y - 1)

    fun distanceTo(p: Point): Double {
        return distance(this, p)
    }

    companion object {
        fun distance(p1: Point, p2: Point): Double {
            return sqrt((p2.x.toDouble() - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
        }
    }
}