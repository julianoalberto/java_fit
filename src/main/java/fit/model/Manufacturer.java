package fit.model;

public enum Manufacturer {
    GARMIN(com.garmin.fit.Manufacturer.GARMIN);

    private Integer value;

    private Manufacturer(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}