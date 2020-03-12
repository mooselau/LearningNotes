package demo.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private Integer age;
    private Long salary;
    private String address;
    private String jobTitle;
    private Long companyId;
}
