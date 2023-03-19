import com.omerinfo.EmployeeDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/*
* by OmerAKBEN
* */
public class EmployeeDaoTest {

    private final EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void testGetEmployeeFullNames() throws SQLException {
        List<String> employeeFullNames = employeeDao.getEmployeeFullNames();
        assertFalse(employeeFullNames.isEmpty(), "The employee full names list should not be empty");
    }


    @Test
    public void testGetEmployeesBornBetween1960And1961() throws SQLException {
        List<Map<String, String>> employees = employeeDao.getEmployeesBornBetween1960And1961();
        assertFalse(employees.isEmpty(), "The employee list should not be empty");

        for (Map<String, String> employee : employees) {
            String fullName = employee.get("full_name");
            String birthYear = employee.get("BD");

            assertFalse(fullName.isEmpty(), "Full name should not be empty");
            assertFalse(birthYear.isEmpty(), "Birth year should not be empty");

            int birthYearInt = Integer.parseInt(birthYear);
            assertTrue(birthYearInt >= 1960 && birthYearInt <= 1961, "Birth year should be between 1960 and 1961");
        }
    }

    @Test
    public void testGetSalaryGreaterThan100000() throws SQLException {
        List<Map<String,String>> salariesGreaterThan = employeeDao.getSalaryGreaterThan100000();
        assertFalse(salariesGreaterThan.isEmpty(),"The salary should not be empty");

        for(Map<String,String> salaries : salariesGreaterThan){
            String salary = salaries.get("salary");
            int salaryInt = Integer.parseInt(salary);
            assertFalse(salaryInt > 100000, "Salary should be greater than 100000");

        }
    }
}
