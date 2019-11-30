package org.firstinspires.ftc.teamcode.utils

import java.util.*

interface Recordable {
    fun getStatements(byEncoder: Boolean): LinkedList<String>
    fun setOnStateChangedCallback(callback: Runnable?)
}