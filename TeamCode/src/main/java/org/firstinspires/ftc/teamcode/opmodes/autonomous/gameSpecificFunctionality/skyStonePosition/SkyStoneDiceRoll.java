package org.firstinspires.ftc.teamcode.opmodes.autonomous.gameSpecificFunctionality.skyStonePosition;

public enum SkyStoneDiceRoll {
    ONE, TWO, THREE, FOUR, FIVE, SIX;

    public boolean isEquivalentTo(SkyStoneDiceRoll pos) {
        if (pos != null) {
            switch (this) {
                case ONE:
                    return pos == FOUR || this == pos;
                case TWO:
                    return pos == FIVE || this == pos;
                case THREE:
                    return pos == SIX  || this == pos;
            }
        }
        return false;
    }

    public SkyStonePositionPair getSkyStonePositions() {
        switch (this) {
            case ONE  :
            case FOUR :
                return new SkyStonePositionPair(SkyStonePosition.ONE, SkyStonePosition.FOUR);
            case TWO  :
            case FIVE :
                return new SkyStonePositionPair(SkyStonePosition.TWO, SkyStonePosition.FIVE);
            case THREE:
            case SIX  :
                return new SkyStonePositionPair(SkyStonePosition.THREE, SkyStonePosition.SIX);
        }
        return null;
    }
}
