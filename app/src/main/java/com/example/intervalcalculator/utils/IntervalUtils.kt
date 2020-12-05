package com.example.intervalcalculator.utils

class IntervalUtils {

    fun add(interval1: Interval, interval2: Interval): Interval {
        return Pair(
            interval1.first + interval2.first,
            interval1.second + interval2.second
        )
    }

    fun subtract(interval1: Interval, interval2: Interval): Interval {
        return Pair(
            interval1.first - interval2.first,
            interval1.second - interval2.second
        )
    }

}

typealias Interval = Pair<Double, Double>