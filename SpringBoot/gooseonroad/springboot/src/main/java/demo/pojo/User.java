package demo.pojo;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class User {
    private Long id;
    private String userName;
    private String note;
}
