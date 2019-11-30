package org.firstinspires.ftc.teamcode.utils

interface StatementIndexPairI {
    fun setStatement(statement: String): StatementIndexPairI
    val statement: String
    fun setIndex(index: Int): StatementIndexPairI
    val index: Int
    fun setIsRemovable(isRemovable: Boolean): StatementIndexPairI
    val isRemovable: Boolean
}