package zero.programmer.data.kendaraan.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import zero.programmer.data.kendaraan.utils.RoleIdEnum;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "username", length = 30, nullable = false)
    @NotEmpty(message = "Username tidak boleh kosong")
    private String username;

    @Column(name = "password_hash", nullable = false)
    @NotEmpty(message = "Password tidak boleh kosong")
    private String password;

    @Column(name = "full_name", nullable = false)
    @NotEmpty(message = "Nama lengkap tidak boleh kosong")
    private String fullName;
    
    @Column(name = "employee_number", nullable = false)
    @NotEmpty(message = "Nomor pokok karyawan tidak boleh kosong")
    private String employeeNumber;
    
    @Column(name = "position", nullable = false)
    @NotEmpty(message = "Jabatan tidak boleh kosong")
    private String position;

    @Column(name = "work_unit", nullable = false)
    @NotEmpty(message = "Unit kerja tidak boleh kosong")
    private String workUnit;

    @Column(name = "role_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleIdEnum roleId;

    public User() {
    }

    public User(String username, String password, String fullName, String employeeNumber, String position,
            String workUnit, RoleIdEnum roleId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.employeeNumber = employeeNumber;
        this.position = position;
        this.workUnit = workUnit;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public RoleIdEnum getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleIdEnum roleId) {
        this.roleId = roleId;
    }

    
}
