package day5

import readTextGroups
import java.util.*

fun main() {
    val testInput: List<String> = readTextGroups("day5/Day05_test")
    check(Day.part1(getStacks(testInput[0]), getProcedure(testInput[1])) == "CMZ")

    val input: List<String> = readTextGroups("day5/Day05")
    println(Day.part1(getStacks(input[0]), getProcedure(input[1])))
    println(Day.part2(getStacks(input[0]), getProcedure(input[1])))
}

fun getStacks(firstPartInput: String): List<Stack<Char>> {
    val firstPartData: List<String> = firstPartInput.split("\n")

    // don't take last string
    val stackAmount = firstPartData
        .last()
        .last { it.isDigit() }
        .digitToInt()

    val stackData = firstPartData.subList(0, firstPartData.size - 1)

    val stacks = List<Stack<Char>>(stackAmount) {
        Stack()
    }

    // go from bottom to top
    stackData
        .reversed()
        .forEach { sd ->
            for (i in 0 until stackAmount) {
                val symbolIndex = (1 + i * 4)

                if (symbolIndex < sd.length) {
                    val symbol = sd[symbolIndex]
                    if (!symbol.isWhitespace()) {
                        stacks[i].push(symbol)
                    }
                }
            }
        }

    return stacks
}

fun getProcedure(secondPartInput: String): List<Triple<Int, Int, Int>> {
    return secondPartInput
        .split("\n")
        .map {
            val parts = it.split(' ')
            Triple(parts[1].toInt(), parts[3].toInt() - 1, parts[5].toInt() - 1)
        }
}

object Day {

    private fun getResult(stacks: List<Stack<Char>>): String {
        return stacks.map { s ->
            s.peek()
        }.fold("") { acc, c ->
            acc + c
        }
    }

    fun part1(stacks: List<Stack<Char>>, procedure: List<Triple<Int, Int, Int>>): String {

        procedure.forEach { p ->
            val (amount, from, to) = p
            val fromStack = stacks[from]
            val toStack = stacks[to]

            for (i in 0 until amount) {
                if (fromStack.isNotEmpty()) {
                    toStack.push(fromStack.pop())
                }
            }
        }

        return getResult(stacks)
    }

    fun part2(stacks: List<Stack<Char>>, procedure: List<Triple<Int, Int, Int>>): String {

        procedure.forEach { p ->
            val (amount, from, to) = p
            val fromStack = stacks[from]
            val toStack = stacks[to]

            val popArray = mutableListOf<Char>()

            for (i in 0 until amount) {
                if (fromStack.isNotEmpty()) {
                    popArray.add(fromStack.pop())
                }
            }

            for (pop in popArray.reversed()) {
                toStack.push(pop)
            }
        }
        return getResult(stacks)
    }
}
