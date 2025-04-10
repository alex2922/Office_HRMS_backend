package com.SaharaAmussmentPark.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class EmployeeDto {
//	private int eId;
//	private String employeeName;
//	private String employeeId;
//	private int attendanceCode;
//	private String gender;
//	private String employeeStatus;
//	private String designation;
//	private String department;
//	private String dateOfJoining;
//	private String dateOfLiving;
//	private String contactNumber;
//	private String email;
//	private String ifscCode;
//	private String dateOfBirth;
//	private String aadharNumber;
//	private String panNumber;
//	private String accountNumber;
//	private double costtoCompany;
//	private double employeeSalary;
//	private String bankName;
//	private String companyName;
//	private double diduction;
//	private String employeeImage;
//	private String address;
//	private int uId;
	
	private int eId;

    @NotBlank(message = "Employee Name cannot be empty")
    private String employeeName;

    @NotBlank(message = "Employee ID cannot be empty")
    private String employeeId;

    @Min(value = 1, message = "Attendance Code must be greater than 0")
    private int attendanceCode;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Employee Status is required")
    private String employeeStatus;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Date of Joining is required")
    private String dateOfJoining;

    private String dateOfLiving;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid contact number")
    private String contactNumber;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code")
    private String ifscCode;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;

    @Pattern(regexp = "\\d{12}", message = "Aadhar number must be 12 digits")
    private String aadharNumber;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number")
    private String panNumber;

    @Pattern(regexp = "\\d{9,18}", message = "Account number must be 9-18 digits")
    private String accountNumber;

    @Positive(message = "CTC must be greater than 0")
    private double costtoCompany;

    @Positive(message = "Salary must be greater than 0")
    private double employeeSalary;

    @NotBlank(message = "Bank Name is required")
    private String bankName;

    @NotBlank(message = "Company Name is required")
    private String companyName;

    private double diduction;

    private String employeeImage;
    private String address;
    private int uanNo;


}
