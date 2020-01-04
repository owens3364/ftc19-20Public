package org.firstinspires.ftc.teamcode.utils

class StatementIndexPair(var statement: String, var index: Int) {
    override fun equals(other: Any?): Boolean {
        return other != null && other is StatementIndexPair && other.index == index && other.statement == statement
    }

    fun equalsIgnoreIndexAndStatementArg(comparison: StatementIndexPair?): Boolean {
        return comparison != null && comparison.statement.split("\\(").toTypedArray()[0] == statement.split("\\(").toTypedArray()[0]
    }

    override fun hashCode(): Int {
        var result = statement.hashCode()
        result = 31 * result + index
        return result
    }

}