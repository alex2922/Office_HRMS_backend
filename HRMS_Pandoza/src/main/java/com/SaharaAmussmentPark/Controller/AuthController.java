package com.SaharaAmussmentPark.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.EmployeeResponse;
import com.SaharaAmussmentPark.Dto.EmployeeSummaryDto;
import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.Dto.RestTemplateDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.EmployeeService;
import com.SaharaAmussmentPark.Service.OfficialLetterService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/AuthController")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class AuthController {
	private final UserService userservice;
	@Autowired
	private EmployeeRepository employeeRepository;
	private final EmployeeService employeeService;
	public final OfficialLetterService officialLetterservice;
	private final UserRepository userRepository;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	@PostMapping("/Login")
	public ResponseEntity<Message<LoginResponseDto>> loginUser(@RequestBody LoginDto request) {
		log.info("In UserController login() with request: {}", request);
		System.out.println(request);
		Message<LoginResponseDto> message = userservice.loginUser(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/SendOtp")
	public ResponseEntity<Message<UserDto>> sendOTP(@RequestParam("Email") String email) {
		log.info("In UserController sendOTP() with request: {}", email);
		Message<UserDto> message = userservice.sendOtp(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/VerifyOtp")
	public ResponseEntity<Message<UserDto>> verifyOTP(@RequestParam("Email") String email,
			@RequestParam("Otp") String otp) {
		log.info("In UserController verifyOTP() with request: {}", email);
		Message<UserDto> message = userservice.verifyOtp(email, otp);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<Message<UserDto>> updatePassword(@RequestBody ChangePasswordDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<UserDto> message = userservice.updatePassword(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getUserById/{uId}")
	public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
		log.info("In usercontroller getUserById() with request:{}", uId);
		Message<userdetailsResponseDto> message = userservice.getUserById(uId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getUserByemail/{email}")
	public ResponseEntity<Message<RestTemplateDto>> getUserById(@PathVariable String email) {
		log.info("In usercontroller getUserById() with request:{}", email);
		Message<RestTemplateDto> message = userservice.findByEmail(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@GetMapping("/UserByemail/{email}")
	public ResponseEntity<Message<EmployeeResponse>> getUserByemial(@PathVariable String email) {
		log.info("In usercontroller getUserByemial() with request:{}", email);
		Message<EmployeeResponse> message = userservice.getByEmail(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@GetMapping("/GetEmployeeByUId/{uId}")
	public ResponseEntity<Message<EmployeeDto>> getAllEmployee(@PathVariable int uId) {
		log.info("In AdminController get Employee By EmployeeID");
		Message<EmployeeDto> message = employeeService.getEmployeeByUid(uId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	@GetMapping("/getEmployeeSummary")
	public ResponseEntity<Message<EmployeeSummaryDto>> getEmployeeSummary() {
	    log.info("In EmployeeController getEmployeeSummary()");
	    Message<EmployeeSummaryDto> response = employeeService.getEmployeeSummary();
	    HttpStatus status = HttpStatus.valueOf(response.getStatus().value());
	    return ResponseEntity.status(status).body(response);
	}
	
	@GetMapping("/{folder}/{filename:.+}")
	public ResponseEntity<Resource> serveDocument(@PathVariable String folder,
	                                              @PathVariable String filename) throws IOException {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    String username = authentication.getName();
	    User userDetails = userRepository.getByEmail(username); // You must implement this

	    if (userDetails == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    String role = userDetails.getRole();
	    int uId = userDetails.getUId();

	    // ✅ Allow ADMIN to access any folder
	    if (!"ADMIN".equalsIgnoreCase(role)) {
	        // 🔐 Restrict EMPLOYEE to only their own folder
	        Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
	        if (employeeOpt.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }

	        String employeeId = employeeOpt.get().getEmployeeId();
	        if (employeeId == null || !folder.startsWith(employeeId)) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	        }
	    }

	    // 📂 Secure and normalize path
	    Path basePath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
	    Path filePath = basePath.resolve(folder).resolve(filename).normalize();

	    // 🔐 Prevent path traversal
	    if (!filePath.startsWith(basePath)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    }

	    // 📁 Check file existence and readability
	    if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }

//	    Resource resource = new UrlResource(filePath.toUri());

	    // 📦 Determine content type
	    String contentType = Files.probeContentType(filePath);
	    if (contentType == null) {
	        if (filename.endsWith(".pdf")) {
	            contentType = "application/pdf";
	        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
	            contentType = "image/jpeg";
	        } else if (filename.endsWith(".png")) {
	            contentType = "image/png";
	        } else {
	            contentType = "application/octet-stream";
	        }
	    }
	    InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));
	    // ✅ Return file with headers for inline display
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(contentType))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename*=UTF-8''" + URLEncoder.encode(filename, StandardCharsets.UTF_8))

	            .body(resource);
	}



   
	@GetMapping("/getOfficialLetterByEmployeeName")
    public ResponseEntity<Message<OfficialLetterDto>> GetOfficialLetter(@RequestParam("EmpName") String employeeName) {
	log.info("In usercontroller login() with request:{}", employeeName);
	Message<OfficialLetterDto> message = officialLetterservice.GetOfficialLetterByEmployeeName(employeeName);
	HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
	return ResponseEntity.status(httpStatus).body(message);

}
}

