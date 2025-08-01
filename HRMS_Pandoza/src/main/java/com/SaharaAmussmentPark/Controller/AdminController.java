package com.SaharaAmussmentPark.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Service.DepartmentService;
import com.SaharaAmussmentPark.Service.DesignationService;
import com.SaharaAmussmentPark.Service.EmployeeService;
import com.SaharaAmussmentPark.Service.IncreamentLetterService;
import com.SaharaAmussmentPark.Service.OfficialLetterService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.model.IncreamentLetter;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class AdminController {

	private final UserService userservice;
	private final DepartmentService departmentservice;
	private final DesignationService designationservice;
	private final EmployeeService employeeService;
	public final OfficialLetterService officialLetterservice;
	private final IncreamentLetterService increamentletterservice;

	@PostMapping("/RegisterUser")
	public ResponseEntity<Message<UserDto>> registerUser(@RequestBody UserDto user) {
		log.info("In UserController registerUser() with request: {}", user);
		Message<UserDto> message = userservice.registerUser(user);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/AddEmployee")
	public ResponseEntity<Message<EmployeeDto>> registerEmployee(@RequestBody EmployeeDto dto) {
		Message<EmployeeDto> message = employeeService.registerUser(dto);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());

		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/UpdateEmployee")
	public ResponseEntity<Message<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto dto) {

		Message<EmployeeDto> message = employeeService.updateEmployee(dto);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());

		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/GetAllEmployee")
	public ResponseEntity<List<Message<EmployeeDto>>> getAllEmployee() {
		log.info("In AdminController getAllUser()");
		List<Message<EmployeeDto>> message = employeeService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@GetMapping("/GetEmployee/{employeeId}")
	public ResponseEntity<Message<EmployeeDto>> getAllDesignation(@PathVariable String employeeId) {
		log.info("In AdminController get Employee By EmployeeID");
		Message<EmployeeDto> message = employeeService.getByemployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@GetMapping("/GetAllUser")
	public ResponseEntity<List<Message<UserDto>>> getAllUser() {
		log.info("In AdminController getAllUser()");
		List<Message<UserDto>> message = userservice.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PostMapping("/addDepartment")
	public ResponseEntity<Message<DepartmentDto>> AddDepartment(@RequestBody DepartmentDto request) {
		log.info("In usercontroller login() with request:", request);
		Message<DepartmentDto> message = departmentservice.AddDepartment(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@DeleteMapping("/deleteDepartment")
	public ResponseEntity<Message<DepartmentDto>> DeleteDepartment(@RequestParam("deptId") int deptId) {
		log.info("In usercontroller login() with request:", deptId);
		Message<DepartmentDto> message = departmentservice.DeleteDepartment(deptId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updateDepartment")
	public ResponseEntity<Message<DepartmentDto>> UpdateDepartment(@RequestBody DepartmentDto request) {
		log.info("In usercontroller login() with request:", request);
		Message<DepartmentDto> message = departmentservice.UpdateDepartment(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getDepartmentById")
	public ResponseEntity<Message<DepartmentDto>> GetDepartmentById(@RequestParam("deptId") int deptId) {
		log.info("In usercontroller login() with request:{}", deptId);
		Message<DepartmentDto> message = departmentservice.GetDepartmentById(deptId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Message<DepartmentDto>>> GetAllDepartments() {
		List<Message<DepartmentDto>> message = departmentservice.GetAllDepartment();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PostMapping("/addDesignation")
	public ResponseEntity<Message<DesignationDto>> AddDesignation(@RequestBody DesignationDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<DesignationDto> message = designationservice.AddDesignation(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@DeleteMapping("/deleteDesignation")
	public ResponseEntity<Message<DesignationDto>> DeleteDesignation(@RequestParam("did") int did) {
		log.info("In usercontroller login() with request:{}", did);
		Message<DesignationDto> message = designationservice.DeleteDesignation(did);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updateDesignation")
	public ResponseEntity<Message<DesignationDto>> UpdateDesignation(@RequestBody DesignationDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<DesignationDto> message = designationservice.UpdateDesignation(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getDesignationById")
	public ResponseEntity<Message<DesignationDto>> GetDesignation(@RequestParam("did") int did) {
		log.info("In usercontroller login() with request:{}", did);
		Message<DesignationDto> message = designationservice.GetDesignationById(did);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getAllDesignations")
	public ResponseEntity<List<Message<DesignationDto>>> GetAllDesignations() {
		List<Message<DesignationDto>> message = designationservice.GetAllDesignation();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<Message<UserDto>> updateUser(@RequestBody UserDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<UserDto> message = userservice.updateUser(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getUserById/{uId}")
	public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
		log.info("In usercontroller login() with request:{}", uId);
		Message<userdetailsResponseDto> message = userservice.getUserById(uId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@DeleteMapping("/deleteUser/{uId}")
	public ResponseEntity<Message<UserDto>> deleteUser(@PathVariable int uId) {
		log.info("In usercontroller login() with request:{}", uId);
		Message<UserDto> message = userservice.deleteUser(uId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/addOfficialLetter")
	public ResponseEntity<Message<OfficialLetterDto>> AddOfficialLetter(@RequestBody OfficialLetterDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<OfficialLetterDto> message = officialLetterservice.AddOfficialLetter(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@PutMapping("/updateOfficialLetter")
	public ResponseEntity<Message<OfficialLetterDto>> UpdateOfficialLetter(@RequestBody OfficialLetterDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<OfficialLetterDto> message = officialLetterservice.UpdateOfficialLetter(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getOfficialLetterById")
	public ResponseEntity<Message<OfficialLetterDto>> GetOfficialLetter(@RequestParam("oId") int oId) {
		log.info("In usercontroller login() with request:{}", oId);
		Message<OfficialLetterDto> message = officialLetterservice.GetOfficialLetterById(oId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@PostMapping("/EditAccess")
	public ResponseEntity<Message<EmployeeDto>> AddOfficialLetterType(@RequestParam("eid") int eid) {
		log.info("In AdminController login() with request:{}", eid);
		Message<EmployeeDto> message = employeeService.ApproveEdit(eid);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@GetMapping("/getAllOfficialLetters")
	public ResponseEntity<List<Message<OfficialLetterDto>>> getAllOfficialLetters() {
		log.info("In AdminController getAllOfficialLetters()");

		List<Message<OfficialLetterDto>> messages = officialLetterservice.GetAllOfficialLetter();

		// Determine appropriate HTTP status
		HttpStatus httpStatus = HttpStatus.OK;
		if (!messages.isEmpty()) {
			HttpStatus firstStatus = messages.get(0).getStatus();
			httpStatus = (firstStatus != null) ? firstStatus : HttpStatus.OK;
		} else {
			httpStatus = HttpStatus.NO_CONTENT;
		}

		return ResponseEntity.status(httpStatus).body(messages);
	}
	@PostMapping("/addIncreamentLetter")
	public ResponseEntity<Message<IncreamentLetterDto>>addIncreamentLetter(@RequestBody IncreamentLetterDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<IncreamentLetterDto> message=increamentletterservice.addIncreamentLetter(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}

	@PutMapping("/updateIncreamentLetter")
	public ResponseEntity<Message<IncreamentLetterDto>>updateIncreamentLetter(@RequestBody IncreamentLetterDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<IncreamentLetterDto> message=increamentletterservice.updateIncreamentLetter(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getIncreamentLetterById")
	public ResponseEntity<Message<IncreamentLetterDto>>getIncreamentLetter(@RequestParam("Id") int id){
		log.info("In usercontroller login() with request:{}",id);
		Message<IncreamentLetterDto> message=increamentletterservice.getIncreamentLetterById(id);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getAllIncreamentLetters")
	public ResponseEntity<Message<List<IncreamentLetterDto>>> getAllIncreamentLetters() {
		log.info("In usercontroller login() with request:{}");
	    
	    Message<List<IncreamentLetterDto>> message = increamentletterservice.getAllIncreamentLetter();
	    HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());

	    return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllIncreamentLettersByEmployeeId")
	public ResponseEntity<Message<List<IncreamentLetterDto>>> getAllIncreamentLettersByEmployeeId( @RequestParam("employeeId") String employeeId) {
		log.info("In usercontroller login() with request:{}",employeeId);

	    Message<List<IncreamentLetterDto>> message = increamentletterservice.getIncreamentLettersByEmployeeId(employeeId);
	    HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());

	    return ResponseEntity.status(httpStatus).body(message);
	}




}
