package demo.controller;

import demo.base.JustResponse;
import demo.dto.CompanyDTO;
import demo.dto.EmployeeDTO;
import demo.entity.Company;
import demo.entity.Employee;
import demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/employee")
    public JustResponse addEmployee(@RequestBody EmployeeDTO dto) {
        Long id = demoService.createEmployee(dto.getName(), dto.getAge(), dto.getAddress(), dto.getCompanyId(),
                dto.getJobTitle(), dto.getSalary());
        return new JustResponse(id);
    }

    @PutMapping("/employee/{id}")
    public JustResponse promoteEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        String jobTitle = dto.getJobTitle();
        Long salary = dto.getSalary();
        Long employeeId = demoService.promoteEmployee(jobTitle, salary, id);
        return new JustResponse(employeeId);
    }

    @PutMapping("/employee/{id}/address")
    public JustResponse updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        String address = dto.getAddress();
        Long employeeId = demoService.updateEmployeeAddress(address, id);
        return new JustResponse(employeeId);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        demoService.deleteEmployee(id);
        return ;
    }

    @GetMapping("/employee/{id}")
    public JustResponse getEmployee(@PathVariable Long id) {
        Employee employee = demoService.getEmployee(id);
        return new JustResponse<Employee>(employee);
    }

    @GetMapping("/employee/")
    public JustResponse getEmployee(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age,
                                    @RequestParam(value = "jobTitle") String jobTitle) {
        Employee employee = demoService.getEmployee(name, age, jobTitle);
        return new JustResponse<Employee>(employee);
    }

    @PostMapping("/company")
    public JustResponse addCompany(@RequestBody CompanyDTO dto) {
        Long id = demoService.createCompany(dto.getCompanyName(), dto.getCompanyAddress());
        return new JustResponse(id);
    }

    @GetMapping("/company/{id}")
    public JustResponse getCompany(@PathVariable Long id) {
        Company company = demoService.getCompany(id);
        return new JustResponse<Company>(company);
    }

    public void updateCompany() {

    }

    public void getCompany() {

    }

}
