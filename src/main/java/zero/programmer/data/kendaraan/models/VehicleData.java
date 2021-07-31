package zero.programmer.data.kendaraan.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class VehicleData {

    @NotEmpty(message = "Nomor registrasi kendaraan tidak boleh kosong")
    private String registrationNumber;

    @NotEmpty(message = "Nama kendaraan tidak boleh kosong")
    private String name;

    @NotEmpty(message = "Merk kendaraan tidak boleh kosong")
    private String merk;
    
    private String chassisNumber;
    
    private String machineNumber;
    
    private String policeNumber;

    private Date purchaseDate;

    private Long acquisitionValue;

    @NotEmpty(message = "Lokasi kendaraan tidak boleh kosong")
    private String location;

    @NotEmpty(message = "Kondisi kendaraan tidak boleh kosong")
    private String condition;
    
    private String borrowStatus;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getAcquisitionValue() {
        return acquisitionValue;
    }

    public void setAcquisitionValue(Long acquisitionValue) {
        this.acquisitionValue = acquisitionValue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    
}
