package backend.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
		@JsonProperty("id")
	 	private int id;
		@JsonProperty("employee_name")
	    private String employeeName;
		@JsonProperty("employee_salary")
	    private BigDecimal employeeSalary;
		@JsonProperty("employee_age")
	    private int employeeAge;
		@JsonProperty("profile_image")
	    private String profileImage;
		
		public Employee() {
		}

	    public Employee(int id, String employeeName, BigDecimal employeeSalary) {
	    	this.setId(id);
	    	this.setEmployeeName(employeeName);
	    	this.setEmployeeSalary(employeeSalary);
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getEmployeeName() {
	        return employeeName;
	    }

	    public void setEmployeeName(String employeeName) {
	        this.employeeName = employeeName;
	    }

	    public BigDecimal getEmployeeSalary() {
	        return employeeSalary;
	    }

	    public void setEmployeeSalary(BigDecimal employeeSalary) {
	        this.employeeSalary = employeeSalary;
	    }

	    public int getEmployeeAge() {
	        return employeeAge;
	    }

	    public void setEmployeeAge(int employeeAge) {
	        this.employeeAge = employeeAge;
	    }

	    public String getProfileImage() {
	        return profileImage;
	    }

	    public void setProfileImage(String profileImage) {
	        this.profileImage = profileImage;
	    }

}
