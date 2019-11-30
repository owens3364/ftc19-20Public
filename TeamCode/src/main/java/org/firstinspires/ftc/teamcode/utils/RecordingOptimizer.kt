package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Function

class RecordingOptimizer : RecordingOptimizerI {
    private var optimizerFunctionForPosition = Function<LinkedList<String?>, LinkedList<String>?> { null }
    private var optimizerFunctionForPower = Function<LinkedList<String?>, LinkedList<String>?> { null }
    override fun getOptimizerFunctionFor(recordingType: RecordingType?): Function<LinkedList<String?>, LinkedList<String>?>? {
        return when (recordingType) {
            RecordingType.POSITION -> optimizerFunctionForPosition
            RecordingType.POWER -> optimizerFunctionForPower
            else -> null
        }
    }

    override fun setOptimizerFunctionFor(recordingType: RecordingType?, optimizerFunction: Function<LinkedList<String?>, LinkedList<String>?>): RecordingOptimizerI {
        when (recordingType) {
            RecordingType.POSITION -> {
                optimizerFunctionForPosition = optimizerFunction
                optimizerFunctionForPower = optimizerFunction
            }
            RecordingType.POWER -> optimizerFunctionForPower = optimizerFunction
        }
        return this
    }
}