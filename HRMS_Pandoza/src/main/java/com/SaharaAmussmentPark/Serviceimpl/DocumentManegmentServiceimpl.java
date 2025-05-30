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
	                                           MultipartFile certificate, MultipartFile salarySlip, MultipartFile bankStatement,
	                                           MultipartFile otherDocuments, MultipartFile latestEducationCertificateOrDegree,
	                                           MultipartFile employeeImage, int uId) {

	    Map<String, Object> response = new LinkedHashMap<>();
	    try {
	        // Step 1: Fetch Employee by uId
	        Optional<Employee> employeeOpt = employeeRepository.findByuId(uId);
	        if (employeeOpt.isEmpty()) {
	            response.put("status", HttpStatus.NOT_FOUND);
	            response.put("message", "Employee not found for uId: " + uId);
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

	        documents.setAdharCard(uploadFile(adharCard, basePath, "adharCard"));
	        documents.setPanCard(uploadFile(panCard, basePath, "panCard"));
	        documents.setExperianceLetter(uploadFile(experianceLetter, basePath, "experienceLetter"));
	        documents.setCertificate(uploadFile(certificate, basePath, "certificate"));
	        documents.setSalarySlip(uploadFile(salarySlip, basePath, "salarySlip"));
	        documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));
	        documents.setOtherDocuments(uploadFile(otherDocuments, basePath, "otherDocuments"));
	        documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, basePath, "educationCertificate"));
	        documents.setEmployeeImage(uploadFile(employeeImage, basePath, "employeeImage"));

	        // Step 4: Save to DB
	        documentrepository.save(documents);

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
	public Map<String, Object> updateDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
	                                           MultipartFile certificate, MultipartFile salarySlip, MultipartFile bankStatement,
	                                           MultipartFile otherDocuments, MultipartFile latestEducationCertificateOrDegree,
	                                           MultipartFile employeeImage, int uId) {
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

	        // Step 4: Update files if new file provided, else keep existing URL
	        if (adharCard != null && !adharCard.isEmpty()) {
	            documents.setAdharCard(uploadFile(adharCard, basePath, "adharCard"));
	        }
	        if (panCard != null && !panCard.isEmpty()) {
	            documents.setPanCard(uploadFile(panCard, basePath, "panCard"));
	        }
	        if (experianceLetter != null && !experianceLetter.isEmpty()) {
	            documents.setExperianceLetter(uploadFile(experianceLetter, basePath, "experienceLetter"));
	        }
	        if (certificate != null && !certificate.isEmpty()) {
	            documents.setCertificate(uploadFile(certificate, basePath, "certificate"));
	        }
	        if (salarySlip != null && !salarySlip.isEmpty()) {
	            documents.setSalarySlip(uploadFile(salarySlip, basePath, "salarySlip"));
	        }
	        if (bankStatement != null && !bankStatement.isEmpty()) {
	            documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));
	        }
	        if (otherDocuments != null && !otherDocuments.isEmpty()) {
	            documents.setOtherDocuments(uploadFile(otherDocuments, basePath, "otherDocuments"));
	        }
	        if (latestEducationCertificateOrDegree != null && !latestEducationCertificateOrDegree.isEmpty()) {
	            documents.setLatestEducationCertificateOrDegree(uploadFile(latestEducationCertificateOrDegree, basePath, "educationCertificate"));
	        }
	        if (employeeImage != null && !employeeImage.isEmpty()) {
	            documents.setEmployeeImage(uploadFile(employeeImage, basePath, "employeeImage"));
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
	public Map<String, Object> getDocuments(int uId) {
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

		        // Step 2: Map entity to DTO
		        DocumentsManegmentDto documentsDto = new DocumentsManegmentDto();
		        documentsDto.setAdharCard(documents.getAdharCard());
		        documentsDto.setPanCard(documents.getPanCard());
		        documentsDto.setExperianceLetter(documents.getExperianceLetter());
		        documentsDto.setCertificate(documents.getCertificate());
		        documentsDto.setSalarySlip(documents.getSalarySlip());
		        documentsDto.setBankStatement(documents.getBankStatement());
		        documentsDto.setOtherDocuments(documents.getOtherDocuments());
		        documentsDto.setLatestEducationCertificateOrDegree(documents.getLatestEducationCertificateOrDegree());
		        documentsDto.setEmployeeImage(documents.getEmployeeImage());
		        documentsDto.setUId(documents.getUId());
		        documentsDto.setEmployeeId(documents.getEmployeeId());
		        documentsDto.setEmployeeName(documents.getEmployeeName());
		        documentsDto.setDId(documents.getDId());

		        response.put("status", HttpStatus.OK);
		        response.put("message", "Documents found for uId: " + uId);
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
	        // Ensure directory exists
	        if (!directory.exists()) {
	            boolean created = directory.mkdirs(); // create parent folders if needed
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

	        // Save the file to local directory
	        File destFile = new File(directory, newFileName);
	        file.transferTo(destFile);

	        // Return the public URL instead of local path
	        return "https://media.saharaamusement.com/sahara/" + newFileName;
	    }
	    return null;
	}

	
}
