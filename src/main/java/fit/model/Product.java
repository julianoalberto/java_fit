package fit.model;

public enum Product {
    GARMIN_EDGE_500(com.garmin.fit.GarminProduct.EDGE500);

    private Integer value;

    private Product(Integer value) {
        this.value = value;
    }
    
    public Integer getValue() {
        return this.value;
    }
}