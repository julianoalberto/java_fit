package fit.mts;

public enum Zone {
    N1_GREEN(135),
    N1_RED(140),
    N1_BLACK(145),

    N2_GREEN(145),
    N2_RED(150),
    N2_BLACK(155),

    N3_GREEN(155),
    N3_RED(160),
    N3_BLACK(165),

    N4_GREEN(165),
    N4_RED(170),
    N4_BLACK(175),

    N5_GREEN(175),
    N5_RED(180),
    N5_BLACK(185),

    N6_GREEN(185),
    N6_RED(190),
    N6_BLACK(195);

    private final static int RANGE = 3;
    public final int heartRate;
    public final int heartRateMin;
    public final int heartRateMax;

    Zone(int heartRate) {
        this.heartRate = heartRate;
        this.heartRateMin = heartRate - RANGE;
        this.heartRateMax = heartRate + RANGE;
    }
}