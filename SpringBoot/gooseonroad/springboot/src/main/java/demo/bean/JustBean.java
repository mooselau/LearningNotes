package demo.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JustBean {
    private String name;
    public JustBean(String name) {
        this.name = name;
        p("===> Just Bean with name: " + name + " has been initialized!");
    }

    private void p(String msg) {
        System.out.println(msg);
    }
}
