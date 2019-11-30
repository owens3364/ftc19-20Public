package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Function

class Recorder(private val byEncoder: Boolean) : RecorderI {
    override var statements: LinkedList<String>? = null
        private set
    private var timeLastUpdated: Long = 0
    override fun record(recordable: Recordable) {
        statements = LinkedList()
        recordable.setOnStateChangedCallback(Runnable {
            if (System.currentTimeMillis() - timeLastUpdated != 0L && timeLastUpdated != 0L) {
                statements!!.add("sleep(" + (System.currentTimeMillis() - timeLastUpdated) + ");")
            }
            timeLastUpdated = System.currentTimeMillis()
            statements!!.addAll(recordable.getStatements(byEncoder))
        })
    }

    override fun optimize(optimizingFunction: Function<LinkedList<String>?, LinkedList<String>?>) {
        statements = optimizingFunction.apply(statements)
    }

    override fun getStatementsSeparatedBy(separator: String): String {
        val sb = StringBuilder()
        for (statement in statements!!) {
            sb.append(statement)
            sb.append(separator)
        }
        sb.delete(sb.length - (separator.length + 1), sb.length - 1)
        return sb.toString()
    }

}