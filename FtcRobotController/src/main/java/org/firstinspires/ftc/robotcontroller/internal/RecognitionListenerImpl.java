package org.firstinspires.ftc.robotcontroller.internal;

import android.util.Log;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;

public class RecognitionListenerImpl implements RecognitionListener {
    @Override
    public void onBeginningOfSpeech() {}

    @Override
    public void onEndOfSpeech() {
        FtcRobotControllerActivity.soloInstance().recognizer.stop();
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        Log.d("ONPAR", "ONPAR");
        if (hypothesis != null) {
            Log.d("AHHH", hypothesis.getHypstr());
            if (hypothesis.getHypstr().equals(FtcRobotControllerActivity.WAKEUP_STR)) {
                FtcRobotControllerActivity.soloInstance().recognizer.stop();
            }
        }
    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        Log.d("ONRES", "ONRES");
        if (hypothesis != null) {
            Log.d("BHHH", hypothesis.getHypstr());
            OpModeManagerImpl.getOpModeManagerOfActivity(FtcRobotControllerActivity.soloInstance()).initActiveOpMode("HEYCANARY");
            OpModeManagerImpl.getOpModeManagerOfActivity(FtcRobotControllerActivity.soloInstance()).startActiveOpMode();
            FtcRobotControllerActivity.soloInstance().listening = false;
            return;
        }
        FtcRobotControllerActivity.soloInstance().recognizer.startListening(FtcRobotControllerActivity.WAKEUP_STR);
    }

    @Override
    public void onError(Exception e) {}

    @Override
    public void onTimeout() {}
}
