package org.firstinspires.ftc.teamcode.utils

interface AutonomousFileGeneratorI {
    fun writeFile(contents: String?, path: String?, extension: String?): AutonomousFileGeneratorI
}