package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern

// startIndex is the index of the first occurrence of what's being optimized (eg a sleep call)
// frequency is the amount of times in which there will be one occurrence of what's being optimized
// For instance, a frequency of 6 means there will be one occurrence every six statements
class CommonOptimizations : CommonOptimizationsI {
    override fun optimizeSleepCalls(unOptimized: LinkedList<String>, frequency: Int, startIndex: Int): LinkedList<String> {
        var statements = unOptimized.toTypedArray()
        val indices = getCodeIndices(frequency)
        var originalLength = statements.size
        var noOpsPerformed = false
        while (!noOpsPerformed) { // Iterate through calls to sleep(int millis)
            var i = startIndex
            while (i < statements.size) {
                // Look for duplicate code after sleep(int millis) calls
                var optimizable = true
                var j = 1
                while (j < indices.size && i + j + frequency < statements.size && optimizable) {
                    if (statements[i + j] != statements[i + j + frequency]) {
                        optimizable = false
                    }
                    j++
                }
                // If there's duplicate blocks of code, turn them into whitespace
                if (optimizable) {
                    eliminateStatementAtIndices(statements, *getAbsoluteIndices(i + frequency, *indices))
                }
                i += frequency
            }
            statements = eliminateWhitespace(statements)
            statements = eliminateDuplicateSleepCalls(statements)
            noOpsPerformed = statements.size == originalLength
            originalLength = statements.size
        }
        return LinkedList(listOf(*statements))
    }

    override fun optimizeStateChanges(unOptimized: LinkedList<String?>?, optimizable: Pattern?, firstStatic: String?, lastStatic: String?): LinkedList<String?>? {
        return null
    }

    override fun optimizeByLine(unOptimized: LinkedList<String>, optimizable: Pattern): LinkedList<String> {
        val statements = unOptimized.toTypedArray()
        val optimizables = ArrayList<StatementIndexPairI>()
        // Find all lines that match the optimizable pattern
        for (i in statements.indices) {
            if (optimizable.matcher(statements[i]).matches()) {
                optimizables.add(StatementIndexPair(i, statements[i]))
            }
        }
        // If a line and the one that follows it are duplicates, mark it as removable
        var i = 0
        while (i + 1 < optimizables.size) {
            if (optimizables[i] == optimizables[i + 1]) {
                optimizables[i].setIsRemovable(true)
            }
            i++
        }
        // Remove the lines in the statements array that are marked as removable in the optimizables list
        optimizables.forEach(Consumer { statementIndexPair: StatementIndexPairI ->
            if (statementIndexPair.isRemovable) {
                statements[statementIndexPair.index] = ELIMINATED_STATEMENT
            }
        })
        return LinkedList(listOf(*statements))
    }

    // Passing 8 will return 1, 2, 3, 4, 5, 6, 7
    private fun getCodeIndices(frequency: Int): IntArray {
        val indices = IntArray(frequency - 1)
        for (i in 0 until frequency - 1) {
            indices[i] = i + 1
        }
        return indices
    }

    // Passing 0, 1, 2, 3 will return 1, 2, 3
// Passing 0, 2, 3, 4 will return 2, 3, 4
// Passing 1, 2, 3, 4 will return 3, 4, 5
    private fun getAbsoluteIndices(base: Int, vararg indices: Int): IntArray {
        val absIndices = IntArray(indices.size)
        for (i in absIndices.indices) {
            absIndices[i] = base + indices[i]
        }
        return absIndices
    }

    private fun eliminateStatementAtIndices(statements: Array<String>, vararg indices: Int) {
        for (index in indices) {
            if (index < statements.size) {
                statements[index] = ELIMINATED_STATEMENT
            }
        }
    }

    private fun eliminateWhitespace(arr: Array<String>): Array<String> {
        val whitespaceFree = LinkedList<String>()
        for (statement in arr) {
            if (statement != ELIMINATED_STATEMENT) {
                whitespaceFree.add(statement)
            }
        }
        return whitespaceFree.toTypedArray()
    }

    private fun eliminateDuplicateSleepCalls(arr: Array<String>): Array<String> {
        var newArr = arr
        var noOperationsPerformed = false
        while (!noOperationsPerformed) {
            noOperationsPerformed = true
            var i = 0
            while (i + 1 < newArr.size) {
                if (Pattern.compile(SLEEP_CALL_REGEXP).matcher(newArr[i]).matches() && Pattern.compile(SLEEP_CALL_REGEXP).matcher(newArr[i + 1]).matches()) {
                    newArr[i] = combineSleepCalls(newArr[i], newArr[i + 1])
                    newArr[i + 1] = ELIMINATED_STATEMENT
                    noOperationsPerformed = false
                }
                i++
            }
            newArr = eliminateWhitespace(newArr)
        }
        return newArr
    }

    private fun combineSleepCalls(firstCall: String, secondCall: String): String {
        val endOfFirstCall = firstCall.split(OPEN_PARENTHESES_REGEXP).toTypedArray()[1]
        val endOfSecondCall = secondCall.split(OPEN_PARENTHESES_REGEXP).toTypedArray()[1]
        val numOfFirstCall = endOfFirstCall.split(CLOSING_PARENTHESES_REGEXP).toTypedArray()[0]
        val numOfSecondCall = endOfSecondCall.split(CLOSING_PARENTHESES_REGEXP).toTypedArray()[0]
        return SLEEP_CALL_START + (numOfFirstCall.toInt() + numOfSecondCall.toInt()) + SLEEP_CALL_END
    } /*

    private LinkedList<StatementIndexPairI> getMatches(String[] statements, Pattern pattern) {

    }
     */

    companion object {
        private const val OPEN_PARENTHESES_REGEXP = "\\("
        private const val CLOSING_PARENTHESES_REGEXP = "\\)"
        private const val SLEEP_CALL_START = "sleep("
        private const val SLEEP_CALL_END = ");"
        private const val SLEEP_CALL_REGEXP = "sleep\\([0-9]+\\);"
        private const val ELIMINATED_STATEMENT = ""
    }
}