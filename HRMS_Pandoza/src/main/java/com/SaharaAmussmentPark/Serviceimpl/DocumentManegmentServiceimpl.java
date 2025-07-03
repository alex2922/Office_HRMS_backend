package com.SaharaAmussmentPark.Serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SaharaAmussmentPark.Dto.DocumentsManegmentDto;
import com.SaharaAmussmentPark.Repository.DocumentsRepository;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.DocumentManegmentService;
import com.SaharaAmussmentPark.model.DocumentsManegment;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DocumentManegmentServiceimpl implements DocumentManegmentService {
	private final DocumentsRepository documentrepository;
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	@Override
	public Map<String, Object> uploadDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile salarySlip1,MultipartFile salarySlip2,MultipartFile salarySlip3, MultipartFile bankStatement,
            MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate,MultipartFile latestEducationCertificateOrDegree,
           MultipartFile employeeImage, MultipartFile diplomaCertificate,int uId) {

		 Map<String, Object> response = new LinkedHashMap<>();
		 log.info("welcome to uploadservice "+uId);
		    Optional<User> user = userRepository.findById(uId);
		    log.info("welcome to uploadservice "+user);

		    try {
		        // Fetch Employee by uId
		        Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
		        System.out.println("welcome to uploadservice employeeData"+employeeOpt);
		        if (employeeOpt.isEmpty()) {
		            response.put("status", HttpStatus.NOT_FOUND);
		            response.put("message", "Employee not found for uId: " + uId);
		            return response;
		        }

		        if (user.isPresent() && user.get().getRole().equals("EMPLOYEE")) {
		            if (!employeeOpt.get().isEditableAccess()) {
		                response.put("status", HttpStatus.FORBIDDEN);
		                response.put("message", "You have already uploaded documents, please coordinate with Admin");
		                return response;
		            }
		        }

		        Employee employee = employeeOpt.get();
		        String employeeId = employee.getEmployeeId();
		        String employeeName = employee.getEmployeeName().replaceAll("\\s+", "");
		        String folderName = employeeId + "_" + employeeName;

		        DocumentsManegment documents = new DocumentsManegment();
		        documents.setUId(uId);
		        documents.setEmployeeId(employeeId);
		        documents.setEmployeeName(employee.getEmployeeName());

		        if (adharCard != null && !adharCard.isEmpty())
		            documents.setAdharCard(uploadFile(adharCard, folderName, "adharCard"));

		        if (panCard != null && !panCard.isEmpty())
		            documents.setPanCard(uploadFile(panCard, folderName, "panCard"));

		        if (experianceLetter != null && !experianceLetter.isEmpty())
		            documents.setExperianceLetter(uploadFile(experianceLetter, folderName, "experienceLetter"));

		        if (salarySlip1 != null && !salarySlip1.isEmpty())
		            documents.setSalarySlip1(uploadFile(salarySlip1, folderName, "salarySlip1"));

		        if (salarySlip2 != null && !salarySlip2.isEmpty())
		            documents.setSalarySlip2(uploadFile(salarySlip2, folderName, "salarySlip2"));

		        if (salarySlip3 != null && !salarySlip3.isEmpty())
		            documents.setSalarySlip3(uploadFile(salarySlip3, folderName, "salarySlip3"));

		        if (bankStatement != null && !bankStatement.isEmpty())
		            documents.setBankStatement(uploadFile(bankStatement, folderName, "bankStatement"));

		        if (tenthCertificate != null && !tenthCertificate.isEmpty())
		            documents.setTenthCertificate(uploadFile(tenthCertificate, folderName, "tenthCertificate"));

		        if (twelfthCertificate != null && !twelfthCertificate.isEmpty())
		            documents.setTwelfthCertificate(uploadFile(twelfthCertificate, folderName, "twelfthCertificate"));

		        if (degreeCertificate != null && !degreeCertificate.isEmpty())
		            documents.setDegreeCertificate(uploadFile(degreeCertificate, folderName, "degreeCertificate"));

		        if (relevingLetter != null && !relevingLetter.isEmpty())
		            documents.setRelievingLetter(uploadFile(relevingLetter, folderName, "relevingLetter"));

		        if (latestEducationCertificateOrDegree != null && !latestEducationCertificateOrDegree.isEmpty())
		            documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, folderName, "educationCertificate"));

		        if (employeeImage != null && !employeeImage.isEmpty())
		            documents.setEmployeeImage(uploadFile(employeeImage, folderName, "employeeImage"));

		        if (diplomaCertificate != null && !diplomaCertificate.isEmpty())
		            documents.setDiplomaCertificate(uploadFile(diplomaCertificate, folderName, "diplomaCertificate"));

		        // Save documents & update employee editableAccess
		        documentrepository.save(documents);
		        employee.setEditableAccess(false);
		        employeeRepository.save(employee);

		        response.put("status", HttpStatus.OK);
		        response.put("message", "Documents uploaded successfully");
		        response.put("data", documents);
		        return response;

		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		        response.put("message", "Upload failed: " + e.getMessage());
		        response.put("data", Collections.emptyList());
		        return response;
		    }
		}
	@Override
	public Map<String, Object> updateDocuments(
			MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile salarySlip1,MultipartFile salarySlip2,MultipartFile salarySlip3, MultipartFile bankStatement,
            MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate,MultipartFile latestEducationCertificateOrDegree,
           MultipartFile employeeImage, MultipartFile diplomaCertificate,int uId) {

		 Map<String, Object> response = new LinkedHashMap<>();
		 log.info("In DocumentManegmentServiceimpl updateDocuments()"+uId);

		    try {
		        Optional<DocumentsManegment> existingDocOpt = documentrepository.findByuId(uId);
		        if (existingDocOpt.isEmpty()) {
		            response.put("status", HttpStatus.NOT_FOUND);
		            response.put("message", "Documents not found for uId: " + uId);
		            return response;
		        }
                 log.info("In DocumentManegmentServiceimpl updateDocuments()"+existingDocOpt);
                 System.out.println("In DocumentManegmentServiceimpl updateDocuments()"+existingDocOpt);
		        DocumentsManegment documents = existingDocOpt.get();

		        Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
		        if (employeeOpt.isEmpty()) {
		            response.put("status", HttpStatus.NOT_FOUND);
		            response.put("message", "Employee not found for uId: " + uId);
		            return response;
		        }

		        Employee employee = employeeOpt.get();
		        String employeeId = employee.getEmployeeId();
		        String employeeName = employee.getEmployeeName().replaceAll("\\s+", "");
		        String folderName = employeeId + "_" + employeeName;

		        if (adharCard != null && !adharCard.isEmpty())
		            documents.setAdharCard(uploadFile(adharCard, folderName, "adharCard"));

		        if (panCard != null && !panCard.isEmpty())
		            documents.setPanCard(uploadFile(panCard, folderName, "panCard"));

		        if (experianceLetter != null && !experianceLetter.isEmpty())
		            documents.setExperianceLetter(uploadFile(experianceLetter, folderName, "experienceLetter"));

		        if (salarySlip1 != null && !salarySlip1.isEmpty())
		            documents.setSalarySlip1(uploadFile(salarySlip1, folderName, "salarySlip1"));

		        if (salarySlip2 != null && !salarySlip2.isEmpty())
		            documents.setSalarySlip2(uploadFile(salarySlip2, folderName, "salarySlip2"));

		        if (salarySlip3 != null && !salarySlip3.isEmpty())
		            documents.setSalarySlip3(uploadFile(salarySlip3, folderName, "salarySlip3"));

		        if (bankStatement != null && !bankStatement.isEmpty())
		            documents.setBankStatement(uploadFile(bankStatement, folderName, "bankStatement"));

		        if (tenthCertificate != null && !tenthCertificate.isEmpty())
		            documents.setTenthCertificate(uploadFile(tenthCertificate, folderName, "tenthCertificate"));

		        if (twelfthCertificate != null && !twelfthCertificate.isEmpty())
		            documents.setTwelfthCertificate(uploadFile(twelfthCertificate, folderName, "twelfthCertificate"));

		        if (degreeCertificate != null && !degreeCertificate.isEmpty())
		            documents.setDegreeCertificate(uploadFile(degreeCertificate, folderName, "degreeCertificate"));

		        if (relevingLetter != null && !relevingLetter.isEmpty())
		            documents.setRelievingLetter(uploadFile(relevingLetter, folderName, "relevingLetter"));

		        if (latestEducationCertificateOrDegree != null && !latestEducationCertificateOrDegree.isEmpty())
		            documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, folderName, "educationCertificate"));

		        if (employeeImage != null && !employeeImage.isEmpty())
		            documents.setEmployeeImage(uploadFile(employeeImage, folderName, "employeeImage"));

		        if (diplomaCertificate != null && !diplomaCertificate.isEmpty())
		            documents.setDiplomaCertificate(uploadFile(diplomaCertificate, folderName, "diplomaCertificate"));

		        documentrepository.save(documents);

		        response.put("status", HttpStatus.OK);
		        response.put("message", "Documents updated successfully");
		        response.put("data", documents);
		        return response;

		    } catch (Exception e) {
		        e.printStackTrace();
		        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		        response.put("message", "Update failed: " + e.getMessage());
		        response.put("data", Collections.emptyList());
		        return response;
		    }
		}

//	@Override
//	public Map<String, Object> getDocuments(String employeeId) {
//		 Map<String, Object> response = new LinkedHashMap<>();
//		 
//		    try {
//		        // Step 1: Fetch existing Documents by uId
//		        Optional<DocumentsManegment> existingDocOpt = documentrepository.findByemployeeId(employeeId);
//		        if (existingDocOpt.isEmpty()) {
//		            response.put("status", HttpStatus.NOT_FOUND);
//		            response.put("message", "Documents not found for uId: " + employeeId);
//		            return response;
//		        }
//
//		        DocumentsManegment documents = existingDocOpt.get();
//
//		        // Step 2: Map entity to DTO
//		        DocumentsManegmentDto documentsDto = new DocumentsManegmentDto();
//		        documentsDto.setAdharCard(documents.getAdharCard());
//		        documentsDto.setPanCard(documents.getPanCard());
//		        documentsDto.setExperianceLetter(documents.getExperianceLetter());
//		        documentsDto.setSalarySlip1(documents.getSalarySlip1());
//		        documentsDto.setSalarySlip2(documents.getSalarySlip2());
//		        documentsDto.setSalarySlip3(documents.getSalarySlip3());
//		        documentsDto.setBankStatement(documents.getBankStatement());
//		       documentsDto.setTenthCertificate(documents.getTenthCertificate());
//		        documentsDto.setTwelfthCertificate(documents.getTwelfthCertificate());
//		        documentsDto.setDegreeCertificate(documents.getDegreeCertificate());
//		        documentsDto.setRelievingLetter(documents.getRelievingLetter());
//		        documentsDto.setLatestEducationCertificateOrDegree(documents.getLatestEducationCertificateOrDegree());
//		        documentsDto.setEmployeeImage(documents.getEmployeeImage());
//		        documentsDto.setUId(documents.getUId());
//		        documentsDto.setEmployeeId(documents.getEmployeeId());
//		        documentsDto.setEmployeeName(documents.getEmployeeName());
//		        documentsDto.setDId(documents.getDId());
//		        documentsDto.setDiplomaCertificate(documents.getDiplomaCertificate());
//
//		        response.put("status", HttpStatus.OK);
//		        response.put("message", "Documents found for EmployeeId: " + employeeId);
//		        response.put("data", documentsDto);
//		        return response;
//		    } catch (Exception e) {
//		        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//		        response.put("message", "Error fetching Documents: " + e.getMessage());
//		        response.put("data", Collections.emptyList());
//		        return response;
//		    }
//	}
	@Override
	public Map<String, Object> getDocuments(String employeeId) {
	    Map<String, Object> response = new LinkedHashMap<>();

	    try {
	        Optional<DocumentsManegment> existingDocOpt = documentrepository.findByemployeeId(employeeId);
	        if (existingDocOpt.isEmpty()) {
	            response.put("status", HttpStatus.NOT_FOUND);
	            response.put("message", "Documents not found for employeeId: " + employeeId);
	            return response;
	        }

	        DocumentsManegment documents = existingDocOpt.get();
	        DocumentsManegmentDto documentsDto = new DocumentsManegmentDto();

	        documentsDto.setUId(documents.getUId());
	        documentsDto.setEmployeeId(documents.getEmployeeId());
	        documentsDto.setEmployeeName(documents.getEmployeeName());
	        documentsDto.setDId(documents.getDId());

	        // Convert stored file paths to secure URLs
	        documentsDto.setAdharCard(convertToSecureUrl(documents.getAdharCard()));
	        documentsDto.setPanCard(convertToSecureUrl(documents.getPanCard()));
	        documentsDto.setExperianceLetter(convertToSecureUrl(documents.getExperianceLetter()));
	        documentsDto.setSalarySlip1(convertToSecureUrl(documents.getSalarySlip1()));
	        documentsDto.setSalarySlip2(convertToSecureUrl(documents.getSalarySlip2()));
	        documentsDto.setSalarySlip3(convertToSecureUrl(documents.getSalarySlip3()));
	        documentsDto.setBankStatement(convertToSecureUrl(documents.getBankStatement()));
	        documentsDto.setTenthCertificate(convertToSecureUrl(documents.getTenthCertificate()));
	        documentsDto.setTwelfthCertificate(convertToSecureUrl(documents.getTwelfthCertificate()));
	        documentsDto.setDegreeCertificate(convertToSecureUrl(documents.getDegreeCertificate()));
	        documentsDto.setRelievingLetter(convertToSecureUrl(documents.getRelievingLetter()));
	        documentsDto.setLatestEducationCertificateOrDegree(convertToSecureUrl(documents.getLatestEducationCertificateOrDegree()));
	        documentsDto.setEmployeeImage(convertToSecureUrl(documents.getEmployeeImage()));
	        documentsDto.setDiplomaCertificate(convertToSecureUrl(documents.getDiplomaCertificate()));

	        response.put("status", HttpStatus.OK);
	        response.put("message", "Documents found for employeeId: " + employeeId);
	        response.put("data", documentsDto);
	        return response;

	    } catch (Exception e) {
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	        response.put("message", "Error fetching documents: " + e.getMessage());
	        response.put("data", Collections.emptyList());
	        return response;
	    }
	}

	// Helper method to convert stored relative path to full URL
	private String convertToSecureUrl(String storedPath) {
	    if (storedPath == null || storedPath.isBlank()) return null;
	    return  storedPath;
	}
	
	
	
	private String uploadFile(MultipartFile file, String folderName, String type) throws IOException {
		log.info("welcome to uploadservice upload file method ");
	    if (file != null && !file.isEmpty()) {
	        if (file.getSize() > 2 * 1024 * 1024) {
	            throw new IOException("File size should be less than or equal to 2MB");
	        }

	        File baseDirectory = new File(uploadDirectory + File.separator + folderName);
	        log.info("welcome to uploadservice "+baseDirectory);
	        if (!baseDirectory.exists()) {
	            boolean created = baseDirectory.mkdirs();
	            if (!created) {
	                throw new IOException("Failed to create directory: " + baseDirectory.getAbsolutePath());
	            }
	        }

	        String originalFilename = file.getOriginalFilename();
	        String extension = "";

	        if (originalFilename != null && originalFilename.contains(".")) {
	            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	        }

	        String newFileName = type + extension;
	        File destFile = new File(baseDirectory, newFileName);
	        file.transferTo(destFile);
	        log.info("welcome to uploadservice upload file method"+folderName + "/" + newFileName);
	        // ✅ Return relative path
	        return folderName + "/" + newFileName;
	        
	    }

	    throw new IOException("File is empty or null");
	}

	
}
