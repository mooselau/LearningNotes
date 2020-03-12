package demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee extends baseEntity {
    private String name;
    private Integer age;
    private Long salary;
    private String address;
    private String jobTitle;

    /* When showing relation with other entity:
     * a. simply use one field like:
     *    private Long companyId; // simple map to id field
     * b. use joinColumn & ManyToOne this kinda annotations like below.
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
