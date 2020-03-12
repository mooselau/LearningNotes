package demo.repository;

import demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpolyeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByName(String name);

    Employee findByNameAndAgeAndJobTitle(String name, int age, String jobTitle);
}
