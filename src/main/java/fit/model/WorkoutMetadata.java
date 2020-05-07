package fit.model;

import java.util.Date;

import com.garmin.fit.DateTime;
import com.garmin.fit.File;
import com.garmin.fit.FileIdMesg;

import fit.model.WorkoutMetadata;

/**
 * Encapsulates com.garmin.fit.FileIdMesg
 */
public class WorkoutMetadata {
    
    private FileIdMesg fileIdMesg;
    private Product product;
    private Manufacturer manufacturer;
    
    public WorkoutMetadata(Long serialNumber, Manufacturer manufacturer, Product product, Date timeCreated) {
        fileIdMesg = new FileIdMesg();
        fileIdMesg.setType(File.WORKOUT);
        setManufacturer(manufacturer);
        setProduct(product);
        setSerialnumber(serialNumber);
        setTimeCreated(timeCreated);
    }

    public void setTimeCreated(Date timeCreated) {
        getFileIdMesg().setTimeCreated(new DateTime(timeCreated));
    }

    public Date getTimeCreated() {
        return getFileIdMesg().getTimeCreated().getDate();
    }

    public void setProduct(Product product) {
        this.product = product;
        getFileIdMesg().setProduct(product.getValue());
    }

    public Product getProduct() {
        return this.product;
    }

    public FileIdMesg getFileIdMesg() {
        return fileIdMesg;
    }

    public void setSerialnumber(Long serial) {
        getFileIdMesg().setSerialNumber(serial);
    }

    public Long getSerialnumber(Long serial) {
        return getFileIdMesg().getSerialNumber();
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        getFileIdMesg().setManufacturer(manufacturer.getValue());
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }
}