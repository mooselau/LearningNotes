package demo.aop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 123L;

    @Value("1")
    private Long id;

    @Value("user_name_1")
    private String userName;

    @Value("note_1")
    private String note;

    public User() {
    }

    public User(Long id, String userName, String note) {
        this.id = id;
        this.userName = userName;
        this.note = note;
    }
}

