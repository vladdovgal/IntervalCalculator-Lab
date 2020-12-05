package com.example.intervalcalculator.utils

class IntervalUtils {

    fun add(interval1: Interval, interval2: Interval): Interval =
        interval1.sum(interval2)

    fun subtract(interval1: Interval, interval2: Interval): Interval =
        interval1.difference(interval2)

    fun multiply(interval1: Interval, interval2: Interval): Interval =
        interval1.product(interval2)

    fun divide(interval1: Interval, interval2: Interval): Interval =
        interval1.product(Pair(1 / interval2.first, 1 / interval2.second))
}

typealias Interval = Pair<Double, Double>

/** Cartesian product
 * ["a0","a1"].cartesianProduct( ["b0", "b1"] )  --> [(a0, b0), (a0, b1), (a1, b0), (a1, b1)]
 */
private fun <T, S> List<S>.cartesianProduct(other: List<T>) = this.flatMap {
    List(other.size) { i -> Pair(it, other[i]) }
}

private fun Pair<Double, Double>.sum(otherPair: Pair<Double, Double>) = Pair(
    this.first + otherPair.first, this.second + otherPair.second
)

private fun Pair<Double, Double>.difference(otherPair: Pair<Double, Double>) = Pair(
    this.first - otherPair.second, this.second - otherPair.first
)

private fun Pair<Double, Double>.product(otherPair: Pair<Double, Double>): Pair<Double, Double> {
    val listLeft = listOf(this.first, this.second)
    val listRight = listOf(otherPair.first, otherPair.second)
    val combinationsProduct = listLeft.cartesianProduct(listRight).map { it.first * it.second }
    return Pair(
        combinationsProduct.minByOrNull { it } ?: 0.0,
        combinationsProduct.maxByOrNull { it } ?: 0.0
    )
}
