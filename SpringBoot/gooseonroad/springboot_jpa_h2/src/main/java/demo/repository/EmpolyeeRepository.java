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

    List<Employee> findAllByCompanyId(Long companyId);

    // native query will be executed directly in DB, will not append annotation like @Where
    @Query(value = " SELECT * FROM Employee WHERE company_id = ?1 ", nativeQuery = true)
    List<Employee> findAllEmployeeWithNativeQuery(Long companyId);

    List<Employee> findAllByName(String name);

    Employee findByNameAndAgeAndJobTitle(String name, int age, String jobTitle);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.time_updated = ?4, e.jobTitle = ?1, e.salary = ?2 WHERE e.id = ?3")
    int setJobTitleAndSalary(String jobTitle, Long salary, Long employeeId, Long time);

    // this is manually soft delete api
    @Transactional
    @Modifying
    @Query(" UPDATE Employee e SET e.is_deleted = true, e.time_deleted = ?1 WHERE id = ?2 " )
    int delete(Long currentTime, Long id);
}
