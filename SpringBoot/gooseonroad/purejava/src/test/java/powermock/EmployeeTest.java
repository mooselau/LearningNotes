package powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {EmployeeService.class, Employee.class})
public class EmployeeTest {

    @InjectMocks
    private EmployeeService service;

    @Test
    public void verifyMethodInvokationTest()
    {
        PowerMockito.mockStatic(Employee.class);
        when(Employee.getThreshold()).thenReturn(10);
        int ret = service.getThreshold();
        assertTrue(ret == 10);
    }

}
