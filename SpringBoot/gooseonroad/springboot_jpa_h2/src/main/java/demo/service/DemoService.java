package demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.entity.Company;
import demo.entity.Employee;
import demo.repository.CompanyRepository;
import demo.repository.EmpolyeeRepository;

@Service
public class DemoService {

    @Autowired
    private EmpolyeeRepository empolyeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    /********************************
                Company
     ******************************/
    public Long createCompany(String companyName, String companyAddress) {
        Company company = new Company();
        company.setName(companyName);
        company.setAddress(companyAddress);
        companyRepository.save(company);
        return company.getId();
    }

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    /********************************
                Employee
     ******************************/
    public Long createEmployee(String name, int age, String address, long companyId, String jobTitle, long salary) {
        Company company = companyRepository.findById(companyId).orElse(null);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setAddress(address);
        employee.setCompany(company);
        employee.setJobTitle(jobTitle);
        employee.setSalary(salary);
        empolyeeRepository.save(employee);
        return employee.getId();
    }

    public Employee getEmployee(Long employeeId) {
        return empolyeeRepository.findById(employeeId).orElse(null);
    }

    public Employee getEmployee(String employeeName, int age, String jobTitle) {
        return empolyeeRepository.findByNameAndAgeAndJobTitle(employeeName, age, jobTitle);
    }

    public int getEmployeesCount(int age, Long companyId) {
        int count = empolyeeRepository.countByCompanyIdAndAge(companyId, age);
        return count;
    }

    public List<Employee> getAllEmployee(Long companyId) {
        List<Employee> list = empolyeeRepository.findAllByCompanyId(companyId);
        return list;
    }

    public List<Employee> getAllEmployeeWithNativeQuery(Long companyId) {
        List<Employee> list = empolyeeRepository.findAllEmployeeWithNativeQuery(companyId);
        return list;
    }

    public List<Employee> getAllEmployeeWithJPQL(Long companyId) {
        List<Employee> list = empolyeeRepository.findAllEmployeeWithJPQL(companyId);
        return list;
    }

    public List<Employee> getAllEmployeeWithOrder(Long companyId) {
        List<Employee> list = empolyeeRepository.findAllByCompanyIdOrderByTimeCreatedDesc(companyId);
        return list;
    }

    public Long promoteEmployee(String jobTitle, Long salary, Long employeeId) {
        int rows = empolyeeRepository.setJobTitleAndSalary(jobTitle, salary, employeeId, System.currentTimeMillis());
        assert rows == 1;
        return employeeId;
    }

    @Transactional
    public Long updateEmployeeAddress(String address, Long employeeId) {
        Employee employee = empolyeeRepository.findById(employeeId).orElseThrow(RuntimeException::new);
        employee.setAddress(address);
        empolyeeRepository.save(employee);
        return employeeId;
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = empolyeeRepository.findById(employeeId).orElse(null);
        Long currentTimeMillis = System.currentTimeMillis();
        empolyeeRepository.delete(employee);
//        empolyeeRepository.delete(currentTimeMillis, employee.getId());
    }

}
