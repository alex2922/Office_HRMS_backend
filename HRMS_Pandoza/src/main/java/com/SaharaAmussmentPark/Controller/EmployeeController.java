package com.SaharaAmussmentPark.Controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Service.EmployeeService;
import com.SaharaAmussmentPark.Service.OfficialLetterService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Employee")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class EmployeeController {	
	private final EmployeeService employeeService;
	private final UserService userservice;
	public final OfficialLetterService officialLetterservice;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	
	
	
	 @GetMapping("/GetEmployee/{employeeId}")
		public ResponseEntity<Message<EmployeeDto>> getAllDesignation(@PathVariable String employeeId) {
			log.info("In AdminController get Employee By EmployeeID");
			Message<EmployeeDto> message = employeeService.getByemployeeId(employeeId);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		@GetMapping("/getUserById/{uId}")
		public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
			log.info("In usercontroller login() with request:{}", uId);
			Message<userdetailsResponseDto> message = userservice.getUserById(uId);
			HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
			return ResponseEntity.status(httpStatus).body(message);
//			asbh
		}
		@GetMapping("/getOfficialLetterById")
		public ResponseEntity<Message<OfficialLetterDto>> GetOfficialLetter(@RequestParam("oId") int oId) {
			log.info("In usercontroller login() with request:{}", oId);
			Message<OfficialLetterDto> message = officialLetterservice.GetOfficialLetterById(oId);
			HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
			return ResponseEntity.status(httpStatus).body(message);

		}
//		@GetMapping("/{folder}/{filename:.+}")
//		public ResponseEntity<Resource> serveDocument(@PathVariable String folder,
//		                                              @PathVariable String filename) throws IOException {
//
//		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		    if (authentication == null || !authentication.isAuthenticated()) {
//		        return ResponseEntity.status(401).build();
//		    }
//
//		    // Suppose your JWT details are in authentication.getPrincipal()
//		    // Cast it to your UserDetails or custom JWT class
//
//		    userdetailsResponseDto userDetails = (userdetailsResponseDto) authentication.getPrincipal();
//
//		    String username = userDetails.getEmail();
//		    String employeeIdFromToken = userDetails.getEmployeeId();  // custom getter
//		    String roleFromToken = userDetails.getRole();              // custom getter
//
//		    // Authorization check:
//		    boolean isAdmin = roleFromToken.equalsIgnoreCase("ADMIN");
//
//		    if (!isAdmin && (employeeIdFromToken == null || !folder.startsWith(employeeIdFromToken))) {
//		        return ResponseEntity.status(403).build();
//		    }
//
//		    Path filePath = Paths.get(uploadDirectory, folder, filename);
//		    if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
//		        return ResponseEntity.notFound().build();
//		    }
//
//		    Resource resource = new UrlResource(filePath.toUri());
//
//		    String contentType = Files.probeContentType(filePath);
//		    if (contentType == null) contentType = "application/octet-stream";
//
//		    return ResponseEntity.ok()
//		            .contentType(MediaType.parseMediaType(contentType))
//		            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//		            .body(resource);
//		}
		
//		@Autowired
//		private EmployeeRepository employeeRepository;
//
//		@GetMapping("/{folder}/{filename:.+}")
//		public ResponseEntity<Resource> serveDocument(@PathVariable String folder,
//		                                              @PathVariable String filename) throws IOException {
//		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		    if (authentication == null || !authentication.isAuthenticated()) {
//		        return ResponseEntity.status(401).build();
//		    }
//
//		    User userDetails = (User) authentication.getPrincipal();
//		    String role = userDetails.getRole();
//		    int uId = userDetails.getUId();
//
//		    boolean isAdmin = "ADMIN".equalsIgnoreCase(role);
//
//		    // Fetch employeeId using uId
//		    Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
//		    if (employeeOpt.isEmpty()) {
//		        return ResponseEntity.status(404).body(null);
//		    }
//
//		    String employeeId = employeeOpt.get().getEmployeeId();
//
//		    // Check folder access
//		    if (!isAdmin && (employeeId == null || !folder.startsWith(employeeId))) {
//		        return ResponseEntity.status(403).build();
//		    }
//
//		    Path filePath = Paths.get(uploadDirectory, folder, filename);
//		    if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
//		        return ResponseEntity.notFound().build();
//		    }
//
//		    Resource resource = new UrlResource(filePath.toUri());
//		    String contentType = Files.probeContentType(filePath);
//		    if (contentType == null) contentType = "application/octet-stream";
//
//		    return ResponseEntity.ok()
//		            .contentType(MediaType.parseMediaType(contentType))
//		            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//		            .body(resource);
//		}


		
}
