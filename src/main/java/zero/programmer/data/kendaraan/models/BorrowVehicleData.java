package zero.programmer.data.kendaraan.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.entities.Driver;

public class BorrowVehicleData {

    private User user;

    private Vehicle vehicle;

    private Driver driver;

    @NotEmpty(message = "Jenis peminjaman tidak boleh kosong")
    private String borrowType;

    @NotEmpty(message = "Keperluan tidak boleh kosong")
    private String necessity;

    private Date borrowDate;

    private Date returnDate;

    @NotEmpty(message = "Tempat tujuan tidak boleh kosong")
    private String destination;

    private Boolean borrowStatus;

    public BorrowVehicleData() {
    }

    public BorrowVehicleData(User user, Vehicle vehicle, Driver driver, String borrowType, String necessity,
            Date borrowDate, Date returnDate, String destination, Boolean borrowStatus) {
        this.user = user;
        this.vehicle = vehicle;
        this.driver = driver;
        this.borrowType = borrowType;
        this.necessity = necessity;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.destination = destination;
        this.borrowStatus = borrowStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public String getNecessity() {
        return necessity;
    }

    public void setNecessity(String necessity) {
        this.necessity = necessity;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Boolean borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}
