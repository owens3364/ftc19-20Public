package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.regex.Pattern

interface CommonOptimizationsI {
    fun optimizeSleepCalls(unOptimized: List<String>): List<String>
    fun optimizeByLine(unOptimized: List<String>, optimizable: Pattern): List<String>
}