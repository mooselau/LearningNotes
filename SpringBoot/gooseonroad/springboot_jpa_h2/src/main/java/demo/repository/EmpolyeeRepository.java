package demo.repository;

import demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmpolyeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByName(String name);

    Employee findByNameAndAgeAndJobTitle(String name, int age, String jobTitle);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.time_updated = ?4, e.jobTitle = ?1, e.salary = ?2 WHERE e.id = ?3")
    int setJobTitleAndSalary(String jobTitle, Long salary, Long employeeId, Long time);
}
