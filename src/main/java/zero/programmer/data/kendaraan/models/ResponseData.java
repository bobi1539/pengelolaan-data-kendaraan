package zero.programmer.data.kendaraan.models;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
    
    private Integer code;

    private String status;

    private List<String> messages = new ArrayList<>();

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    

}
