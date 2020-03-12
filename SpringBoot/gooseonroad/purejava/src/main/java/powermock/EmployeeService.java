package powermock;

public class EmployeeService
{
    public int getEmployeeCount() {
        throw new UnsupportedOperationException();
    }

    public void saveEmployee(Employee employee) {
        //return nothing
    }

    public int getThreshold() {
        System.out.println("Service is doing sth..");
        return Employee.getThreshold();
    }
}

