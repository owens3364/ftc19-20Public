package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Function

interface RecordingOptimizerI {
    fun getOptimizerFunctionFor(recordingType: RecordingType?): Function<LinkedList<String?>, LinkedList<String>?>?
    fun setOptimizerFunctionFor(recordingType: RecordingType?, optimizerFunction: Function<LinkedList<String?>, LinkedList<String>?>): RecordingOptimizerI
}