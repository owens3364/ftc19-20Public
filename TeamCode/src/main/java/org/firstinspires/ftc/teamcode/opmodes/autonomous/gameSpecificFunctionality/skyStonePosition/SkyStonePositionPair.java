package org.firstinspires.ftc.teamcode.opmodes.autonomous.gameSpecificFunctionality.skyStonePosition;

public class SkyStonePositionPair {
    private final SkyStonePosition pos1;
    private final SkyStonePosition pos2;

    public SkyStonePositionPair(SkyStonePosition pos1, SkyStonePosition pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public SkyStonePosition getPos1() {
        return pos1;
    }

    public SkyStonePosition getPos2() {
        return pos2;
    }
}
