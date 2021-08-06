package zero.programmer.data.kendaraan.models;

import javax.validation.constraints.NotEmpty;

import zero.programmer.data.kendaraan.utils.RoleIdEnum;

public class UserData {
    
    @NotEmpty(message = "Username tidak boleh kosong")
    private String username;

    @NotEmpty(message = "Password tidak boleh kosong")
    private String password;
    
    @NotEmpty(message = "Nama lengkap tidak boleh kosong")
    private String fullName;
    
    @NotEmpty(message = "Nomor pokok karyawan tidak boleh kosong")
    private String employeeNumber;
    
    @NotEmpty(message = "Jabatan tidak boleh kosong")
    private String position;
    
    @NotEmpty(message = "Unit kerja tidak boleh kosong")
    private String workUnit;
    
    private RoleIdEnum roleId;

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

