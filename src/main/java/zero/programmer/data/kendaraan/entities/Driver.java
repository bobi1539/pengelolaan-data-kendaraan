package zero.programmer.data.kendaraan.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "drivers")
public class Driver {

    @NotEmpty(message = "Id driver tidak boleh kosong")
    @Id
    @Column(name = "id_driver", length = 30)
    private String idDriver;

    @NotEmpty(message = "Nama lengkap tidak boleh kosong")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotEmpty(message = "No handphone tidak boleh kosong")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_on_duty")
    private Boolean isOnDuty;

    public Driver() {
    }

    public Driver(String idDriver, String fullName, String phoneNumber, String address, Boolean isOnDuty) {
        this.idDriver = idDriver;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isOnDuty = isOnDuty;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsOnDuty() {
        return isOnDuty;
    }

    public void setIsOnDuty(Boolean isOnDuty) {
        this.isOnDuty = isOnDuty;
    }

}
