package day11

import java.math.BigInteger

fun main() {
//    part1Test()
//    partTwo()

//    part1Test()
    part2Test()
}


fun part1Test() {

    //testing

    val m1 = Monkey(
        listOf(79, 98),
        { it * 19 },
        23,
        {
            if (it) 2 else 3
        })

    val m2 = Monkey(
        listOf(54, 65, 75, 74),
        { it + 6 },
        19,
        {
            if (it) 2 else 0
        })

    val m3 = Monkey(
        listOf(79, 60, 97),
        { it * it },
        13,
        {
            if (it) 1 else 3
        })

    val m4 = Monkey(
        listOf(74),
        { it + 3 },
        17,
        {
            if (it) 0 else 1
        })

    val monkeys = listOf(m1, m2, m3, m4)

    for (r in 0 until 20) {
        for (m in monkeys) {
            m.round(monkeys, r)
        }
    }

    println(m1.items.joinToString(" ") { it.toString() })
    println(m2.items.joinToString(" ") { it.toString() })
    println(m3.items.joinToString(" ") { it.toString() })
    println(m4.items.joinToString(" ") { it.toString() })

    val mostActive = monkeys.map { it.processedAmount }.sorted().takeLast(2)
    println(mostActive[0] * mostActive[1])

}

fun part2Test() {

    //testing
    val m1 = Monkey(
        listOf(79, 98),
        {
            it * 19
        },
        23,
        {
            if (it) 2 else 3
        },
        false
    )

    val m2 = Monkey(
        listOf(54, 65, 75, 74),
        { it + 6 },
        19,
        {
            if (it) 2 else 0
        },
        false,
        isAddOp = true
    )

    val m3 = Monkey(
        listOf(79, 60, 97),
        {
         it * it
        },
        13,
        {
            if (it) 1 else 3
        },
        false
    )

    val m4 = Monkey(
        listOf(74),
        { it + 3 },
        17,
        {
            if (it) 0 else 1
        },
        false,
        isAddOp = true
    )

    val monkeys = listOf(m1, m2, m3, m4)

    for (r in 0 until 10000) {
        for (m in monkeys) {
            m.round(monkeys, r)
        }
        println()
    }

    println(m1.items.joinToString(" ") { it.toString() })
    println(m2.items.joinToString(" ") { it.toString() })
    println(m3.items.joinToString(" ") { it.toString() })
    println(m4.items.joinToString(" ") { it.toString() })

    println(m1.processedAmount)
    println(m2.processedAmount)
    println(m3.processedAmount)
    println(m4.processedAmount)

    val mostActive = monkeys.map { it.processedAmount }.sorted().takeLast(2)
    println(mostActive[0] * mostActive[1])
}


//fun partOne() {
//
//    //testing
//
//    val m1 = Monkey(
//        listOf(66, 79),
//        { it * 11 },
//        7,
//        {
//            if (it) 6 else 7
//        })
//
//    val m2 = Monkey(
//        listOf(84, 94, 94, 81, 98, 75),
//        { it * 17 },
//        13,
//        {
//            if (it) 5 else 2
//        })
//
//    val m3 = Monkey(
//        listOf(85, 79, 59, 64, 79, 95, 67),
//        { it + 8 },
//        5,
//        {
//            if (it) 4 else 5
//        })
//
//    val m4 = Monkey(
//        listOf(70),
//        { it + 3 },
//        19,
//        {
//            if (it) 6 else 0
//        })
//
//    val m5 = Monkey(
//        listOf(57, 69, 78, 78),
//        { it + 4 },
//        2,
//        {
//            if (it) 0 else 3
//        })
//
//    val m6 = Monkey(
//        listOf(65, 92, 60, 74, 72),
//        { it + 7 },
//        11,
//        {
//            if (it) 3 else 4
//        })
//
//    val m7 = Monkey(
//        listOf(77, 91, 91),
//        { it * it },
//        17,
//        {
//            if (it) 1 else 7
//        })
//
//    val m8 = Monkey(
//        listOf( 76, 58, 57, 55, 67, 77, 54, 99),
//        { it + 6 },
//        3,
//        {
//            if (it) 2 else 1
//        })
//
//    val monkeys = listOf(m1, m2, m3, m4, m5, m6, m7, m8)
//
//    for (r in 0 until 20) {
//        for (m in monkeys) {
//            m.round(monkeys)
//        }
//    }
//
////    println(m1.items.joinToString(" ") { it.toString() })
////    println(m2.items.joinToString(" ") { it.toString() })
////    println(m3.items.joinToString(" ") { it.toString() })
////    println(m4.items.joinToString(" ") { it.toString() })
//
//    val mostActive = monkeys.map { it.processedAmount }.sorted().takeLast(2)
//    println(mostActive[0] * mostActive[1])
//
//}

fun partTwo() {

    //testing

    val m1 = MonkeyBigInteger(
        listOf(66, 79),
        { it * BigInteger.valueOf(11) },
        7,
        {
            if (it) 6 else 7
        })

    val m2 = MonkeyBigInteger(
        listOf(84, 94, 94, 81, 98, 75),
        { it * BigInteger.valueOf(17) },
        13,
        {
            if (it) 5 else 2
        })

    val m3 = MonkeyBigInteger(
        listOf(85, 79, 59, 64, 79, 95, 67),
        { it + BigInteger.valueOf(8) },
        5,
        {
            if (it) 4 else 5
        })

    val m4 = MonkeyBigInteger(
        listOf(70),
        { it + BigInteger.valueOf(3) },
        19,
        {
            if (it) 6 else 0
        })

    val m5 = MonkeyBigInteger(
        listOf(57, 69, 78, 78),
        { it + BigInteger.valueOf(4) },
        2,
        {
            if (it) 0 else 3
        })

    val m6 = MonkeyBigInteger(
        listOf(65, 92, 60, 74, 72),
        { it + BigInteger.valueOf(7) },
        11,
        {
            if (it) 3 else 4
        })

    val m7 = MonkeyBigInteger(
        listOf(77, 91, 91),
        { it * it },
        17,
        {
            if (it) 1 else 7
        })

    val m8 = MonkeyBigInteger(
        listOf(76, 58, 57, 55, 67, 77, 54, 99),
        { it + BigInteger.valueOf(6) },
        3,
        {
            if (it) 2 else 1
        })

    val monkeys = listOf(m1, m2, m3, m4, m5, m6, m7, m8)

    for (r in 0 until 10000) {
        for (m in monkeys) {
            m.round(monkeys)
        }
    }

//    println(m1.items.joinToString(" ") { it.toString() })
//    println(m2.items.joinToString(" ") { it.toString() })
//    println(m3.items.joinToString(" ") { it.toString() })
//    println(m4.items.joinToString(" ") { it.toString() })

    val mostActive = monkeys.map { it.processedAmount }.sorted().takeLast(2)
    println(mostActive[0])
    println(mostActive[1])
    println(mostActive[0] * mostActive[1])

}

class Monkey(
    initItems: List<Long>,
    private val op: (Long) -> Long,
    private val divisibleBy: Long,
    private val throwTo: (Boolean) -> Int,
    private val shouldDevideByTree: Boolean = true,
    private val isAddOp: Boolean = false,
    val divisors: MutableList<Int> = mutableListOf()
) {
    private val id: Int

    init {
        globalId++
        id = globalId
    }

    companion object {
        var globalId: Int = 0
    }

    var processedAmount = 0L
        private set

    val items = ArrayDeque<Long>(initItems)

    fun receiveItem(item: Long) {
        items.addLast(item)
    }

    // processing all items in queue
    fun round(monkeys: List<Monkey>, r: Int) {

        println("Monkey: $id, round: $r")

        while (items.isNotEmpty()) {
            val item = items.removeFirst()
            var inspectedItem = op.invoke(item)

            if (shouldDevideByTree) {
                inspectedItem /= 3
            }

            val isDivisible = inspectedItem % divisibleBy == 0L

            while (inspectedItem % divisibleBy == 0L) {
                inspectedItem /= divisibleBy
            }

            if (!isDivisible) {
                inspectedItem %= divisibleBy
            }

            val monkeyNumber = throwTo.invoke(isDivisible)

            monkeys[monkeyNumber].receiveItem(inspectedItem)
            processedAmount++

            println(
                "item: $item, inspected item: $inspectedItem, " +
                        "divisibleBy: $divisibleBy, isDivisible: $isDivisible"
            )
        }
        println()
    }
}

class MonkeyBigInteger(
    initItems: List<Long>,
    private val op: (BigInteger) -> BigInteger,
    private val divisibleBy: Long,
    private val throwTo: (Boolean) -> Int
) {

    var processedAmount: BigInteger = BigInteger.ZERO
        private set

    val items = ArrayDeque<BigInteger>(initItems.map { BigInteger.valueOf(it) })

    fun receiveItem(item: BigInteger) {
        items.addLast(item)
    }

    // processing all items in queue
    fun round(monkeys: List<MonkeyBigInteger>) {
        while (items.isNotEmpty()) {
            val item = items.removeFirst()
            val inspectedItem = op.invoke(item)
            val monkeyNumber = throwTo.invoke(inspectedItem.mod(BigInteger.valueOf(divisibleBy)) == BigInteger.ZERO)
            monkeys[monkeyNumber].receiveItem(inspectedItem)
            processedAmount++
        }
    }
}

//val divisors: MutableMap<Long, List<Long>> = mutableMapOf()
//
fun getDivisors(n: Long): List<Long> {
    var cur = 1L
    val res = mutableListOf<Long>()
    res.add(cur)

    cur++
    var last = n
    while (cur * cur <= n) {

        if (last % cur == 0L) {
            last /= cur
            res.add(cur)
        } else {
            cur++
        }
    }
    if (n > 1) {
        res.add(n)
    }
    return res
}