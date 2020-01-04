package org.firstinspires.ftc.teamcode.utils

import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern

class CommonOptimizations : CommonOptimizationsI {
    override fun optimizeSleepCalls(unOptimized: List<String>): List<String> {
        val optimized = LinkedList<String>()
        removeDuplicateSleepCalls(pureList(reIntegrateSleepCalls(unOptimized.size, getSleepCalls(unOptimized), removeDuplicateCallBlocks(getNormalCallBlocks(unOptimized))))).forEach(Consumer { statementIndexPair: StatementIndexPair -> optimized.add(statementIndexPair.statement) })
        return optimized
    }

    override fun optimizeByLine(unOptimized: List<String>, optimizable: Pattern): List<String> {
        return unOptimized
    }

    private fun getSleepCalls(lines: List<String>): List<StatementIndexPair> {
        val sleepCalls: MutableList<StatementIndexPair> = LinkedList()
        var line: String
        for (i in lines.indices) {
            line = lines[i]
            if (line.contains(SLEEP)) sleepCalls.add(StatementIndexPair(line, i))
        }
        return sleepCalls
    }

    private fun getNormalCallBlocks(lines: List<String>): List<StatementIndexPair> {
        val normalCallBlocks: MutableList<StatementIndexPair> = LinkedList()
        var line: String
        for (i in lines.indices) {
            line = lines[i]
            if (!line.contains(SLEEP)) normalCallBlocks.add(StatementIndexPair(line, i))
        }
        return normalCallBlocks
    }

    private fun removeDuplicateCallBlocks(lines: List<StatementIndexPair>): List<StatementIndexPair> {
        val optimizedCallBlocks: MutableList<StatementIndexPair> = LinkedList()
        val blocks: MutableList<List<StatementIndexPair>> = LinkedList()
        val blockSize = getBlockSize(lines)
        run {
            var i = 0
            while (i < lines.size) {
                val currentBlock: MutableList<StatementIndexPair> = LinkedList()
                var j = 0
                while (i + j < lines.size && j < blockSize) {
                    currentBlock.add(lines[i + j])
                    j++
                }
                blocks.add(currentBlock)
                i += blockSize
            }
        }
        var i = 0
        while (i + 1 < blocks.size) {
            if (blocksAreEqual(blocks[i], blocks[i + 1])) {
                blocks.remove(blocks[i + 1])
                i--
            }
            i++
        }
        blocks.forEach(Consumer { c: List<StatementIndexPair> -> optimizedCallBlocks.addAll(c) })
        return optimizedCallBlocks
    }

    private fun reIntegrateSleepCalls(originalSize: Int, sleepCalls: List<StatementIndexPair>, otherLines: List<StatementIndexPair>): List<StatementIndexPair> {
        val reIntegratedCalls = arrayOfNulls<StatementIndexPair>(originalSize)
        sleepCalls.forEach(Consumer { pair: StatementIndexPair -> reIntegratedCalls[pair.index] = pair })
        otherLines.forEach(Consumer { pair: StatementIndexPair -> reIntegratedCalls[pair.index] = pair })
        return LinkedList<StatementIndexPair>(listOf(*reIntegratedCalls))
    }

    private fun pureList(statementIndexPairs: List<StatementIndexPair>): List<StatementIndexPair> {
        val pureList: MutableList<StatementIndexPair> = LinkedList()
        statementIndexPairs.forEach(Consumer { pair: StatementIndexPair? -> if (pair != null) pureList.add(pair) })
        return pureList
    }

    private fun removeDuplicateSleepCalls(pureList: List<StatementIndexPair>): List<StatementIndexPair> {
        val optimizedList: MutableList<StatementIndexPair> = LinkedList(pureList)
        var i = 0
        while (i + 1 < optimizedList.size) {
            if (mergeSleepCalls(optimizedList[i], optimizedList[i + 1]) != null) {
                optimizedList[i].statement = mergeSleepCalls(optimizedList[i], optimizedList[i + 1])!!.statement
                optimizedList.removeAt(i + 1)
                i--
            }
            i++
        }
        return optimizedList
    }

    private fun getBlockSize(lines: List<StatementIndexPair>): Int {
        var blockSize = 1
        while (blockSize < lines.size && !lines[0].equalsIgnoreIndexAndStatementArg(lines[blockSize])) blockSize++
        return blockSize
    }

    private fun blocksAreEqual(block1: List<StatementIndexPair>, block2: List<StatementIndexPair>): Boolean {
        if (block1.size != block2.size) return false
        for (i in block1.indices) {
            if (block1[i].statement != block2[i].statement) return false
        }
        return true
    }

    private fun mergeSleepCalls(pair1: StatementIndexPair, pair2: StatementIndexPair): StatementIndexPair? {
        return if (getSleepTimeMs(pair1.statement) > 0 && getSleepTimeMs(pair2.statement) > 0) {
            StatementIndexPair("sleep(" + (getSleepTimeMs(pair1.statement) + getSleepTimeMs(pair2.statement)) + ");", pair1.index)
        } else null
    }

    private fun getSleepTimeMs(line: String?): Int {
        if (line != null) {
            if (line.contains(SLEEP) && line.contains(OPEN_PARENTHESIS) && line.contains(CLOSE_PARENTHESIS)) {
                val startIndex = line.indexOf(OPEN_PARENTHESIS) + 1
                val endIndex = line.indexOf(CLOSE_PARENTHESIS) - 1
                return if (startIndex != endIndex) {
                    line.substring(startIndex, endIndex + 1).toInt()
                } else line[startIndex].toString().toInt()
            }
        }
        return -1
    }

    companion object {
        private const val SLEEP = "sleep"
        private const val OPEN_PARENTHESIS = "("
        private const val CLOSE_PARENTHESIS = ")"
    }
}