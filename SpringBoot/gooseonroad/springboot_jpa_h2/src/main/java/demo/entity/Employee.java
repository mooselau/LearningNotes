package demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SQLDelete(sql = " UPDATE employee SET is_deleted = true WHERE id = ? AND version = ? ")
@Where(clause = " is_deleted = false ")
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
