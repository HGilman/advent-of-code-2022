package day1

import readTextGroups

fun main() {
    fun part1(input: List<String>): Int {

        return input
            .asSequence()
            .map { it -> it.split("\n").sumOf { it.toInt() } }
            .max()
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it -> it.split("\n").sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readTextGroups("Day01")

    println(part1(input))
    println(part2(input))
}