package org.firstinspires.ftc.teamcode.utils

class StatementIndexPair : StatementIndexPairI {
    override var index: Int = 0
        private set
    override var statement: String = ""
        private set
    override var isRemovable: Boolean = false
        private set

    constructor(index: Int, statement: String) {
        this.index = index
        this.statement = statement
    }

    constructor(index: Int, statement: String, isRemovable: Boolean) {
        this.index = index
        this.statement = statement
        this.isRemovable = isRemovable
    }

    override fun setIndex(index: Int): StatementIndexPairI {
        this.index = index
        return this
    }

    override fun setStatement(statement: String): StatementIndexPairI {
        this.statement = statement
        return this
    }

    override fun setIsRemovable(isRemovable: Boolean): StatementIndexPairI {
        this.isRemovable = isRemovable
        return this
    }

}