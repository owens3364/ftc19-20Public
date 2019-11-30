package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Function

interface RecorderI {
    fun record(recordable: Recordable)
    fun optimize(optimizingFunction: Function<LinkedList<String>?, LinkedList<String>?>)
    val statements: LinkedList<String>?
    fun getStatementsSeparatedBy(separator: String): String
}