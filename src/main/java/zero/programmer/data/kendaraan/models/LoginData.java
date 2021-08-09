package zero.programmer.data.kendaraan.models;

import javax.validation.constraints.NotEmpty;

public class LoginData {

    @NotEmpty(message = "Username tidak boleh kosong")
    String username;

    @NotEmpty(message = "Password tidak boleh kosong")
    String password;

    public LoginData() {
    }

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
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
    
}
