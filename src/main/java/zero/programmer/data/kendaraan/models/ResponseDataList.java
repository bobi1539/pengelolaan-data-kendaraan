package zero.programmer.data.kendaraan.models;

import java.util.ArrayList;
import java.util.List;

public class ResponseDataList<T> {
    
    private Integer code;

    private String status;

    private List<String> messages = new ArrayList<>();

    private List<T> data;

    public ResponseDataList() {
    }

    public ResponseDataList(Integer code, String status, List<String> messages, List<T> data) {
        this.code = code;
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
