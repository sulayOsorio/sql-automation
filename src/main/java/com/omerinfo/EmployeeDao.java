package com.omerinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles data access operations related to employees.
 * by OmerAKBEN
 */
public class EmployeeDao {

    // SQL query to get the full names of all employees.
    private static final String GET_FULL_NAMES_SQL = "SELECT concat(first_name, ' ', last_name) AS Full_Name from employees;";

    // SQL query to get the full names and birth years of employees born between 1960 and 1961.
    private static final String GET_EMPLOYEES_BETWEEN_1960_AND_1961_SQL = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, DATE_FORMAT(birth_date, '%Y') AS BD FROM employees WHERE DATE_FORMAT(birth_date, '%Y') BETWEEN 1960 AND 1961;";
    // SQL query select salary from salaries where salary < 100000;
    private static final String GET_SALARY_SQL ="SELECT salary FROM salaries WHERE salary < 100000;";
    //SQL query SELECT * FROM salaries WHERE salary 100000 and date_format(to_date,"%Y")>1999;
    private static final String GET_SALARY_AND_DATE_FORMAT_SQL= "SELECT salary FROM salaries WHERE salary < 100000 AND DATE_FORMAT(to_date,'%Y')>1999;";
    //SQL query SELECT * FROM employees WHERE first_name > 'z%'and gender = 'M';
    private static final String GET_EMPLOYEES_WHERE_FIRST_NAME_GREATER_THAN_Z_AND_GENDER_EQUAL_M_SQL="SELECT * FROM employees WHERE first_name > 'z%' AND gender = 'M';";

    /**
     * Retrieves the full names of all employees from the database.
     *
     * @return A list of employee full names.
     * @throws SQLException If there is an issue executing the SQL query.
     */
    public List<String> getEmployeeFullNames() throws SQLException {
        List<String> employeeFullNames = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FULL_NAMES_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    employeeFullNames.add(resultSet.getString("Full_Name"));
                }
            }
        }
        return employeeFullNames;
    }

    /**
     * Retrieves employees born between 1960 and 1961, including their full names and birth years.
     *
     * @return A list of maps containing the full name and birth year of each employee.
     * @throws SQLException If there is an issue executing the SQL query.
     */
    public List<Map<String, String>> getEmployeesBornBetween1960And1961() throws SQLException {
        List<Map<String, String>> employees = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES_BETWEEN_1960_AND_1961_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, String> employee = new HashMap<>();
                    employee.put("full_name", resultSet.getString("full_name"));
                    employee.put("BD", resultSet.getString("BD"));
                    employees.add(employee);
                }
            }
        }
        return employees;
    }
    public List<Map<String,String>> getSalaryGreaterThan100000() throws SQLException {
        List<Map<String,String>> salaries = new ArrayList<>();
        try (Connection connection =MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SALARY_SQL)){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {

                    Map<String,String> salary = new HashMap<>();
                    salary.put("salary",resultSet.getString("salary"));
                    salaries.add(salary);
                   // System.out.println(resultSet.getString("salary"));

                }            }

        }
        return salaries;
    }
    public List<Map<String,String>> getSalaryGreaterThan100000AndDateFormate()throws SQLException {
        List<Map<String,String>> salaries = new ArrayList<>();
        try(Connection connection = MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SALARY_AND_DATE_FORMAT_SQL)){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    Map<String,String>salary= new HashMap<>();
                    salary.put("salary",resultSet.getString("salary"));
                    salaries.add(salary);
                }
            }

        }
        return salaries;
    }
    public List<Map<String,String>> getEmployeesWhereFirstNameGreaterThanZAndGenderEqualM() throws SQLException {
        List<Map<String,String>> employees = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES_WHERE_FIRST_NAME_GREATER_THAN_Z_AND_GENDER_EQUAL_M_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Map<String,String> employee = new HashMap<>();
                    employee.put("first_name",resultSet.getString("first_name"));
                    employee.put("gender",resultSet.getString("gender"));
                    employees.add(employee);
                }
            }
        }
        return employees;
    }
}
