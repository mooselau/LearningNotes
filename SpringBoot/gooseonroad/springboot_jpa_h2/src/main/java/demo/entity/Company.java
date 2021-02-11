package demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SQLDelete(sql = " UPDATE company SET isDeleted = true WHERE id = ? AND version = ? ")
@Where(clause = " is_deleted = false ")
public class Company extends baseEntity {
    private String name;
    private String address;
}
