package zero.programmer.data.kendaraan.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrow_vehicle")
public class BorrowVehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBorrow;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vehicle vehicle;
    
    @ManyToOne
    private Driver driver;
    
    @Column(name = "borrow_type")
    private String borrowType;

    @Column(name = "current_date_borrow", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date currentDate;

    private String necessity;

    @Column(name = "borrow_date")
    private Date borrowDate;
    
    @Column(name = "return_date")
    private Date returnDate;
    
    private String destination;

    public BorrowVehicle() {
    }

    public BorrowVehicle(Integer idBorrow, User user, Vehicle vehicle, Driver driver, String borrowType,
            Date currentDate, String necessity, Date borrowDate, Date returnDate, String destination) {
        this.idBorrow = idBorrow;
        this.user = user;
        this.vehicle = vehicle;
        this.driver = driver;
        this.borrowType = borrowType;
        this.currentDate = currentDate;
        this.necessity = necessity;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.destination = destination;
    }

    public Integer getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(Integer idBorrow) {
        this.idBorrow = idBorrow;
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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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

    
    

}
