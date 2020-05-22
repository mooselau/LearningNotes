package annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@JsonSerializable
@Getter
@Setter
@AllArgsConstructor
public class Person {
    @JsonElement
    private String name;
    @JsonElement(key = "person_age")
    private int age;
    private String address;

    @Init
    private void initName() {
        this.name = this.name.toUpperCase();
    }
}
