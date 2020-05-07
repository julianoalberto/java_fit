package fit.model;

public enum Sport {
    CYCLING(com.garmin.fit.Sport.CYCLING);

    private com.garmin.fit.Sport value;

    private Sport(com.garmin.fit.Sport value) {
        this.value = value;
    }
    
    public com.garmin.fit.Sport getValue() {
        return this.value;
    }
}