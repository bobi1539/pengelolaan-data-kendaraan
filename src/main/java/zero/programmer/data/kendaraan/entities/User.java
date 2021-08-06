package zero.programmer.data.kendaraan.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import zero.programmer.data.kendaraan.utils.RoleIdEnum;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "employee_number", nullable = false)
    private String employeeNumber;
    
    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "work_unit", nullable = false)
    private String workUnit;

    @Column(name = "role_id", nullable = false)
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
