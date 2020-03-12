package demo.service;

import demo.entity.Company;
import demo.entity.Employee;
import demo.repository.CompanyRepository;
import demo.repository.EmpolyeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private EmpolyeeRepository empolyeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Long createCompany(String companyName, String companyAddress) {
        Company company = new Company();
        company.setName(companyName);
        company.setAddress(companyAddress);
        companyRepository.save(company);
        return company.getId();
    }

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

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public Employee getEmployee(Long employeeId) {
        return empolyeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getEmployee(String employeeName) {
//        return empolyeeRepository.findAllByEmployeeName(employeeName);
        return empolyeeRepository.findAllByName(employeeName);
    }

    public Employee getEmployee(String employeeName, int age, String jobTitle) {
        return empolyeeRepository.findByNameAndAgeAndJobTitle(employeeName, age, jobTitle);
    }
}
