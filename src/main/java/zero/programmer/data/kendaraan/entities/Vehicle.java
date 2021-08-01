package zero.programmer.data.kendaraan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable{

    @Id
    @Column(name = "registration_number", length = 30)
    private String registrationNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String merk;

    @Column(name = "chassis_number", unique = true)
    private String chassisNumber;

    @Column(name = "manchine_number", unique = true)
    private String machineNumber;

    @Column(name = "police_number", unique = true)
    private String policeNumber;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "acquisition_value")
    private Long acquisitionValue;

    @Column(name = "location")
    private String location;

    @Column(name = "condition_vehicle")
    private String condition;

    @Column(name = "is_borrow")
    private Boolean isBorrow;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber, String name, String merk, String chassisNumber, String machineNumber,
            String policeNumber, Date purchaseDate, Long acquisitionValue, String location, String condition,
            Boolean isBorrow) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.merk = merk;
        this.chassisNumber = chassisNumber;
        this.machineNumber = machineNumber;
        this.policeNumber = policeNumber;
        this.purchaseDate = purchaseDate;
        this.acquisitionValue = acquisitionValue;
        this.location = location;
        this.condition = condition;
        this.isBorrow = isBorrow;
    }

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

    public Boolean getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(Boolean isBorrow) {
        this.isBorrow = isBorrow;
    }

    



    
}
