package org.firstinspires.ftc.teamcode.utils

class ObjectTriplet<A, B, C>(val a: A, val b: B, val c: C) {

    fun setA(a: A): ObjectTriplet<A, B, C> {
        return ObjectTriplet(a, b, c)
    }

    fun setB(b: B): ObjectTriplet<A, B, C> {
        return ObjectTriplet(a, b, c)
    }

    fun setC(c: C): ObjectTriplet<A, B, C> {
        return ObjectTriplet(a, b, c)
    }

}