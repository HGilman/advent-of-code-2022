fun main() {
    val testInput = readInput("Day01_test")
    check(Day.part1(testInput) == 1)

    val input = readInput("Day01")

    println(Day.part1(input))
    println(Day.part2(input))
}

object Day {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }
}