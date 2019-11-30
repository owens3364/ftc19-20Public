package org.firstinspires.ftc.teamcode.utils

import android.icu.text.SimpleDateFormat
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity
import java.sql.Timestamp

class AutonomousFileGenerator : AutonomousFileGeneratorI {
    // path should begin with a slash and include slashes to separate multiple directories
// it does not need to end with a slash, nor should it
// name should NOT include any slashes- ONLY the file name
// The returned FileWriter will OVERWRITE file contents if they exist
    override fun writeFile(contents: String?, path: String?, extension: String?): AutonomousFileGeneratorI {
        FtcRobotControllerActivity.soloInstance().writeFile(contents, path, extension)
        return this
    }

    companion object {
        const val AUTONOMOUS_RECORDING_BY_ENCODER_DIR_NAME = "AutonomousDriveByEncoder"
        const val AUTONOMOUS_RECORDING_BY_POWER_DIR_NAME = "AutonomousDriveByPower"
        const val TEXT_FILE_EXTENSION = ".txt"
        private const val PREFERRED_DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss.SSS"
        @JvmStatic
        val simpleDateFormat: String
            get() = SimpleDateFormat(PREFERRED_DATE_FORMAT)
                    .format(Timestamp(System.currentTimeMillis()))
    }
}