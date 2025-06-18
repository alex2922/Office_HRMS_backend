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
import com.SaharaAmussmentPark.Service.DocumentManegmentService;
import com.SaharaAmussmentPark.model.DocumentsManegment;
import com.SaharaAmussmentPark.model.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DocumentManegmentServiceimpl implements DocumentManegmentService {
	private final DocumentsRepository documentrepository;
	private final EmployeeRepository employeeRepository;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	@Override
	public Map<String, Object> uploadDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile salarySlip1,MultipartFile salarySlip2,MultipartFile salarySlip3, MultipartFile bankStatement,
            MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate,MultipartFile latestEducationCertificateOrDegree,
           MultipartFile employeeImage, MultipartFile diplomaCertificate,int uId) {

	    Map<String, Object> response = new LinkedHashMap<>();
	    try {
	        // Step 1: Fetch Employee by uId
	        Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
	        if (employeeOpt.isEmpty()) {
	            response.put("status", HttpStatus.NOT_FOUND);
	            response.put("message", "Employee not found for uId: " + uId);
	            return response;
	        }
	        if (employeeOpt.get().isEditableAccess() == false) {
	            response.put("status", HttpStatus.NOT_FOUND);
	            response.put("message", "You have already uploaded documents please coordinate with Admin");
	            return response;
	        }

	        Employee employee = employeeOpt.get();
	        String employeeId = employee.getEmployeeId();
	        String employeeName = employee.getEmployeeName().replaceAll("\\s+", ""); // Clean name

	        // Step 2: Create employee folder if not exists
	        String folderName = employeeId + "_" + employeeName;
	        File basePath = new File(uploadDirectory + File.separator + folderName);
	        if (!basePath.exists()) basePath.mkdirs();

	        // Step 3: Upload files
	        DocumentsManegment documents = new DocumentsManegment();
	        documents.setUId(uId);
	        documents.setEmployeeId(employeeId);
	        documents.setEmployeeName(employee.getEmployeeName());

	        if (adharCard != null && !adharCard.isEmpty())
	            documents.setAdharCard(uploadFile(adharCard, basePath, "adharCard"));

	        if (panCard != null && !panCard.isEmpty())
	            documents.setPanCard(uploadFile(panCard, basePath, "panCard"));

	        if (experianceLetter != null && !experianceLetter.isEmpty())
	            documents.setExperianceLetter(uploadFile(experianceLetter, basePath, "experienceLetter"));

	        if (salarySlip1 != null && !salarySlip1.isEmpty())
	            documents.setSalarySlip1(uploadFile(salarySlip1, basePath, "salarySlip1"));

	        if (salarySlip2 != null && !salarySlip2.isEmpty())
	            documents.setSalarySlip2(uploadFile(salarySlip2, basePath, "salarySlip2"));

	        if (salarySlip3 != null && !salarySlip3.isEmpty())
	            documents.setSalarySlip3(uploadFile(salarySlip3, basePath, "salarySlip3"));

	        if (bankStatement != null && !bankStatement.isEmpty())
	            documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));

	        if (tenthCertificate != null && !tenthCertificate.isEmpty())
	            documents.setTenthCertificate(uploadFile(tenthCertificate, basePath, "tenthCertificate"));

	        if (twelfthCertificate != null && !twelfthCertificate.isEmpty())
	            documents.setTwelfthCertificate(uploadFile(twelfthCertificate, basePath, "twelfthCertificate"));

	        if (degreeCertificate != null && !degreeCertificate.isEmpty())
	            documents.setDegreeCertificate(uploadFile(degreeCertificate, basePath, "degreeCertificate"));

	        if (relevingLetter != null && !relevingLetter.isEmpty())
	            documents.setRelievingLetter(uploadFile(relevingLetter, basePath, "relevingLetter"));

	        if (latestEducationCertificateOrDegree != null && !latestEducationCertificateOrDegree.isEmpty())
	            documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, basePath, "educationCertificate"));

	        if (employeeImage != null && !employeeImage.isEmpty())
	            documents.setEmployeeImage(uploadFile(employeeImage, basePath, "employeeImage"));

	        if (diplomaCertificate != null && !diplomaCertificate.isEmpty())
	            documents.setDiplomaCertificate(uploadFile(diplomaCertificate, basePath, "diplomaCertificate"));

	        // Step 4: Save to DB
	        documentrepository.save(documents);
	        employee.setEditableAccess(false);
	        employeeRepository.save(employee);

	        response.put("status", HttpStatus.OK);
	        response.put("message", "Documents uploaded successfully");
	        response.put("data", documents);
	        return response;

	    } catch (Exception e) {
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

	    try {
	        // Step 1: Fetch existing Documents by uId
	        Optional<DocumentsManegment> existingDocOpt = documentrepository.findByuId(uId);
	        if (existingDocOpt.isEmpty()) {
	            response.put("status", HttpStatus.NOT_FOUND);
	            response.put("message", "Documents not found for uId: " + uId);
	            return response;
	        }
	        DocumentsManegment documents = existingDocOpt.get();

	        // Step 2: Fetch Employee by uId (for folder and naming)
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

	        // Step 3: Prepare folder path
	        File basePath = new File(uploadDirectory + File.separator + folderName);
	        if (!basePath.exists()) basePath.mkdirs();

	        // Step 4: Update files only if new file is provided
	        if (adharCard != null && !adharCard.isEmpty()) {
	            documents.setAdharCard(uploadFile(adharCard, basePath, "adharCard"));
	        }
	        if (panCard != null && !panCard.isEmpty()) {
	            documents.setPanCard(uploadFile(panCard, basePath, "panCard"));
	        }
	        if (experianceLetter != null && !experianceLetter.isEmpty()) {
	            documents.setExperianceLetter(uploadFile(experianceLetter, basePath, "experienceLetter"));
	        }
	        if (salarySlip1 != null && !salarySlip1.isEmpty()) {
	            documents.setSalarySlip1(uploadFile(salarySlip1, basePath, "salarySlip1"));
	        }
	        if (bankStatement != null && !bankStatement.isEmpty()) {
	            documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));
	        }
	        if (salarySlip2 != null && !salarySlip2.isEmpty()) {
	            documents.setSalarySlip2(uploadFile(salarySlip2, basePath, "salarySlip2"));
	        }
	        if (salarySlip3 != null && !salarySlip3.isEmpty()) {
	            documents.setSalarySlip3(uploadFile(salarySlip3, basePath, "salarySlip3"));
	        }
	        if (tenthCertificate != null && !tenthCertificate.isEmpty()) {
	            documents.setTenthCertificate(uploadFile(tenthCertificate, basePath, "tenthCertificate"));
	        }
	        if (twelfthCertificate != null && !twelfthCertificate.isEmpty()) {
	            documents.setTwelfthCertificate(uploadFile(twelfthCertificate, basePath, "twelfthCertificate"));
	        }
	        if (degreeCertificate != null && !degreeCertificate.isEmpty()) {
	            documents.setDegreeCertificate(uploadFile(degreeCertificate, basePath, "degreeCertificate"));
	        }
	        if (relevingLetter != null && !relevingLetter.isEmpty()) {
	            documents.setRelievingLetter(uploadFile(relevingLetter, basePath, "relevingLetter"));
	        }
	        if (latestEducationCertificateOrDegree != null && !latestEducationCertificateOrDegree.isEmpty()) {
	            documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, basePath, "educationCertificate"));
	        }
	        if (employeeImage != null && !employeeImage.isEmpty()) {
	            documents.setEmployeeImage(uploadFile(employeeImage, basePath, "employeeImage"));
	        }
	        if (diplomaCertificate != null && !diplomaCertificate.isEmpty()) {
	            documents.setDiplomaCertificate(uploadFile(diplomaCertificate, basePath, "diplomaCertificate"));
	        }

	        // Step 5: Save updated DocumentsManegment entity
	        documentrepository.save(documents);

	        response.put("status", HttpStatus.OK);
	        response.put("message", "Documents updated successfully");
	        response.put("data", documents);
	        return response;

	    } catch (Exception e) {
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	        response.put("message", "Update failed: " + e.getMessage());
	        response.put("data", Collections.emptyList());
	        return response;
	    }
	}

	@Override
	public Map<String, Object> getDocuments(String employeeId) {
		 Map<String, Object> response = new LinkedHashMap<>();
		    try {
		        // Step 1: Fetch existing Documents by uId
		        Optional<DocumentsManegment> existingDocOpt = documentrepository.findByemployeeId(employeeId);
		        if (existingDocOpt.isEmpty()) {
		            response.put("status", HttpStatus.NOT_FOUND);
		            response.put("message", "Documents not found for uId: " + employeeId);
		            return response;
		        }

		        DocumentsManegment documents = existingDocOpt.get();

		        // Step 2: Map entity to DTO
		        DocumentsManegmentDto documentsDto = new DocumentsManegmentDto();
		        documentsDto.setAdharCard(documents.getAdharCard());
		        documentsDto.setPanCard(documents.getPanCard());
		        documentsDto.setExperianceLetter(documents.getExperianceLetter());
		        documentsDto.setSalarySlip1(documents.getSalarySlip1());
		        documentsDto.setSalarySlip2(documents.getSalarySlip2());
		        documentsDto.setSalarySlip3(documents.getSalarySlip3());
		        documentsDto.setBankStatement(documents.getBankStatement());
		       documentsDto.setTenthCertificate(documents.getTenthCertificate());
		        documentsDto.setTwelfthCertificate(documents.getTwelfthCertificate());
		        documentsDto.setDegreeCertificate(documents.getDegreeCertificate());
		        documentsDto.setRelievingLetter(documents.getRelievingLetter());
		        documentsDto.setLatestEducationCertificateOrDegree(documents.getLatestEducationCertificateOrDegree());
		        documentsDto.setEmployeeImage(documents.getEmployeeImage());
		        documentsDto.setUId(documents.getUId());
		        documentsDto.setEmployeeId(documents.getEmployeeId());
		        documentsDto.setEmployeeName(documents.getEmployeeName());
		        documentsDto.setDId(documents.getDId());
		        documentsDto.setDiplomaCertificate(documents.getDiplomaCertificate());

		        response.put("status", HttpStatus.OK);
		        response.put("message", "Documents found for EmployeeId: " + employeeId);
		        response.put("data", documentsDto);
		        return response;
		    } catch (Exception e) {
		        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		        response.put("message", "Error fetching Documents: " + e.getMessage());
		        response.put("data", Collections.emptyList());
		        return response;
		    }
	}

	private String uploadFile(MultipartFile file, File directory, String type) throws IOException {
		if (file != null && !file.isEmpty()) {
	        if (file.getSize() > 2 * 1024 * 1024) {
	            throw new IOException("File size should be less than or equal to 2MB");
	        }

	        if (!directory.exists()) {
	            boolean created = directory.mkdirs();
	            if (!created) {
	                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
	            }
	        }

	        String originalFilename = file.getOriginalFilename();
	        String extension = "";

	        if (originalFilename != null && originalFilename.contains(".")) {
	            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	        }

	        String newFileName = type + extension;
	        File destFile = new File(directory, newFileName);
	        file.transferTo(destFile);

	        return "https://diwise.cloud/Hrms/" + directory.getName() + "/"+ newFileName;
	    }

	    throw new IOException("File is empty or null");
	}

	
}
