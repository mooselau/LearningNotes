package demo.controller;

import demo.base.JustResponse;
import demo.dto.CompanyDTO;
import demo.dto.EmployeeDTO;
import demo.entity.Company;
import demo.entity.Employee;
import demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    public void updateEmployee() {

    }

    @GetMapping("/employee/{id}")
    public JustResponse getEmployee(@PathVariable Long id) {
        Employee employee = demoService.getEmployee(id);
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
