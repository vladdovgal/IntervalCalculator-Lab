package com.example.intervalcalculator.utils

import android.util.Log
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.max

class IntervalUtils {

    fun add(interval1: Interval, interval2: Interval): Interval =
        interval1.sum(interval2)

    fun subtract(interval1: Interval, interval2: Interval): Interval =
        interval1.difference(interval2)

    fun multiply(interval1: Interval, interval2: Interval): Interval =
        interval1.product(interval2)

    fun divide(interval1: Interval, interval2: Interval): Interval =
        interval1.product(Pair(1 / interval2.first, 1 / interval2.second))

    fun calcWidth(interval: Interval): Double = (interval.second - interval.first).round(7)

    fun calcMid(interval: Interval): Double = (0.5 * (interval.first + interval.second)).round(7)

    fun calcAbsValue(interval: Interval): Double = max(abs(interval.first), interval.second)

    fun calcRadius(interval: Interval): Double = (calcWidth(interval) / 2).round(7)

    private fun multiplyByScalar(interval: Interval, scalar: Double): Interval =
        if (scalar >= 0)
            Pair(interval.first * scalar, interval.second * scalar)
        else
            Pair(interval.second * scalar, interval.first * scalar)

    private fun hasZero(interval: Interval): Boolean = interval.first <= 0 && interval.second >= 0

    private fun findCoefs(
        coef: String,
        a: Interval,
        b: Interval,
        c: Interval,
        d: Interval
    ): Interval {
        var r1 = 0.0
        var r2 = 0.0
        when (coef) {
            "c" -> {
                r2 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(b, c.first), multiplyByScalar(multiply(a, d), -1.0))
                    ), c.first
                ).maxValue()
                r1 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(b, c.second), multiplyByScalar(multiply(a, d), -1.0))
                    ), c.second
                ).minValue()
            }
            "b" -> {
                r2 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(c, b.first), multiplyByScalar(multiply(a, d), -1.0))
                    ), b.first
                ).maxValue()
                r1 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(c, b.second), multiplyByScalar(multiply(a, d), -1.0))
                    ), b.second
                ).minValue()
            }
            "a" -> {
                r2 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(d, a.first), multiplyByScalar(multiply(b, c), -1.0))
                    ), a.first
                ).maxValue()
                r1 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(d, a.second), multiplyByScalar(multiply(b, c), -1.0))
                    ), a.second
                ).minValue()
            }
            "d" -> {
                r2 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(a, d.first), multiplyByScalar(multiply(b, c), -1.0))
                    ), d.first
                ).maxValue()
                r1 = multiplyByScalar(
                    divide(
                        Pair(1.0, 1.0),
                        add(multiplyByScalar(a, d.second), multiplyByScalar(multiply(b, c), -1.0))
                    ), d.second
                ).minValue()
            }
        }
        return Pair(r1, r2)
    }

    private fun invertMatrix(matrix: Matrix): Matrix {
        val a = matrix[0][0]
        val b = matrix[0][1]
        val c = matrix[1][0]
        val d = matrix[1][1]
        val aResult: Interval
        val bResult: Interval
        val cResult: Interval
        val dResult: Interval
        var coef: Interval
        aResult = if (hasZero(a)) {
            findCoefs("a", a, b, c, d)
        } else {
            divide(Pair(1.0, 1.0), add(d, multiplyByScalar(divide(multiply(b, c), a), -1.0)))
        }
        if (hasZero(b)) {
            bResult = findCoefs("b", a, b, c, d)
        } else {
            coef = add(multiply(b, c), multiplyByScalar(multiply(a, d), -1.0))
            bResult = divide(b, coef)
        }
        if (hasZero(c)) {
            cResult = findCoefs("c", a, b, c, d)
        } else {
            coef = add(multiply(b, c), multiplyByScalar(multiply(a, d), -1.0))
            cResult = divide(c, coef)
        }
        dResult = if (hasZero(d)) {
            findCoefs("d", a, b, c, d)
        } else {
            divide(Pair(1.0, 1.0), add(a, multiplyByScalar(divide(multiply(b, c), d), -1.0)))
        }
        return listOf(
            listOf(dResult, bResult),
            listOf(cResult, aResult)
        )
    }

    fun findXMatrix(
        a: Interval,
        b: Interval,
        c: Interval,
        d: Interval,
        b1: Interval,
        b2: Interval
    ): Matrix {
        val matrixA = listOf(
            listOf(a, b),
            listOf(c, d)
        )
        val matrixB = listOf(b1, b2)

        if (!check(a) && !check(b) && !check(c) && !check(d) && !check(b1) && !check(b2)) {
            Log.d("myLogs", "Check intervals once more")
        } else {
            Log.d("myLogs", "Intervals are proper")
        }

        hasZero(add(multiply(a, d), multiplyByScalar(multiply(d, c), -1.0)))
        val invA = invertMatrix(matrixA)
        Log.d("myLogs", "Inverted matrix: ")
        invA.forEach {
            it.forEach { it1 -> Log.d("myLogs", "[$it1]") }
        }
        val result = listOf(
            listOf(add(multiply(invA[0][0], matrixB[0]), multiply(invA[0][1], matrixB[1]))),
            listOf(add(multiply(invA[1][0], matrixB[0]), multiply(invA[1][1], matrixB[1]))),
        )
        Log.d("myLogs", "Result-----------")
        result.forEach {
            Log.d("myLogs", "Line--")
            it.forEach { it1 -> Log.d("myLogs", "[$it1]") }
        }
        return result
    }

    private fun check(interval: Interval): Boolean {
        return interval.first <= interval.second
    }
}

typealias Interval = Pair<Double, Double>

typealias Matrix = List<List<Interval>>

/** Cartesian product
 * ["a0","a1"].cartesianProduct( ["b0", "b1"] )  --> [(a0, b0), (a0, b1), (a1, b0), (a1, b1)]
 */
private fun <T, S> List<S>.cartesianProduct(other: List<T>) = this.flatMap {
    List(other.size) { i -> Pair(it, other[i]) }
}

private fun Pair<Double, Double>.sum(otherPair: Pair<Double, Double>) = Pair(
    (this.first + otherPair.first).round(7), (this.second + otherPair.second).round(7)
)

private fun Pair<Double, Double>.difference(otherPair: Pair<Double, Double>) = Pair(
    (this.first - otherPair.second).round(7), (this.second - otherPair.first).round(7)
)

private fun Pair<Double, Double>.product(otherPair: Pair<Double, Double>): Pair<Double, Double> {
    val listLeft = listOf(this.first, this.second)
    val listRight = listOf(otherPair.first, otherPair.second)
    val combinationsProduct = listLeft.cartesianProduct(listRight).map { it.first * it.second }
    return Pair(
        (combinationsProduct.minByOrNull { it } ?: 0.0).round(7),
        (combinationsProduct.maxByOrNull { it } ?: 0.0).round(7)
    )
}

fun Double.round(decimals: Int = 2): Double {
    val numFormat = NumberFormat.getInstance()
    return numFormat.parse("%.${decimals}f".format(this)).toDouble()
}


private fun Pair<Double, Double>.values() = listOf(this.first, this.second)

private fun Pair<Double, Double>.minValue(): Double = (this.values().minByOrNull { it } ?: 0.0)

private fun Pair<Double, Double>.maxValue(): Double = (this.values().maxByOrNull { it } ?: 0.0)
