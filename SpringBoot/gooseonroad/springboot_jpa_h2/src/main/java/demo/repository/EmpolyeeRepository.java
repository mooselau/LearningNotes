package demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import demo.entity.Employee;

@Repository
public interface EmpolyeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByCompanyId(Long companyId);

    List<Employee> findAllByName(String name);

    // native query will be executed directly in DB, will not trigger annotations like @Where, @Prexxx or @Postxxx
    @Query(value = " SELECT * FROM Employee WHERE company_id = ?1 ", nativeQuery = true)
    List<Employee> findAllEmployeeWithNativeQuery(Long companyId);

    // JPQL query with JOIN
    @Query(value = " SELECT e FROM Employee e INNER JOIN Company c ON c.id = e.company WHERE c.id = ?1 ")
    List<Employee> findAllEmployeeWithJPQL(Long companyId);

    // find with multiple fields
    Employee findByNameAndAgeAndJobTitle(String name, int age, String jobTitle);

    // (By) Order By field At DESC/ASC to fetch entries with order, e.g.: findAllByOrderByxxxDesc,
    // findByAgeOrderByxxxAsc.
    List<Employee> findAllByCompanyIdOrderByTimeCreatedDesc(Long companyId);

    // multiple set in one update
    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.timeUpdated = ?4, e.jobTitle = ?1, e.salary = ?2 WHERE e.id = ?3")
    int setJobTitleAndSalary(String jobTitle, Long salary, Long employeeId, Long time);

    // manually soft delete api
    @Transactional
    @Modifying
    @Query(" UPDATE Employee e SET e.isDeleted = true, e.timeDeleted = ?1 WHERE id = ?2 ")
    int delete(Long currentTime, Long id);

    // count by usage
    int countByCompanyIdAndAge(long companyId, int age);
}
