package org.firstinspires.ftc.teamcode.utils

class ObjectPair<A, B>(val a: A, val b: B) {

    fun setA(a: A): ObjectPair<A, B> {
        return ObjectPair(a, b)
    }

    fun setB(b: B): ObjectPair<A, B> {
        return ObjectPair(a, b)
    }

}