package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.regex.Pattern

interface CommonOptimizationsI {
    fun optimizeSleepCalls(unOptimized: LinkedList<String>, frequency: Int, startIndex: Int): LinkedList<String>
    fun optimizeStateChanges(unOptimized: LinkedList<String?>?, optimizable: Pattern?, firstStatic: String?, lastStatic: String?): LinkedList<String?>?
    fun optimizeByLine(unOptimized: LinkedList<String>, optimizable: Pattern): LinkedList<String>
}