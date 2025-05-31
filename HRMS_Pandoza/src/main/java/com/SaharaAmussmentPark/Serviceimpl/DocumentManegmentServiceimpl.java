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
            MultipartFile certificate, MultipartFile salarySlip1, MultipartFile bankStatement,
             MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage,MultipartFile salarySlip2,MultipartFile salarySlip3,MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate, int uId) {

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
	        documents.setSalarySlip1(uploadFile(salarySlip1, basePath, "salarySlip1"));
	        documents.setSalarySlip2(uploadFile(salarySlip2, basePath, "salarySlip2"));
	        documents.setSalarySlip3(uploadFile(salarySlip3, basePath, "salarySlip3"));
	        documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));
	        documents.setTenthCertificate(uploadFile(tenthCertificate, basePath, "tenthCertificate"));
	        documents.setTwelfthCertificate(uploadFile(twelfthCertificate, basePath, "twelfthCertificate"));
	        documents.setDegreeCertificate(uploadFile(degreeCertificate, basePath, "degreeCertificate"));
	        documents.setRelevingLetter(uploadFile(relevingLetter, basePath, "relevingLetter"));
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
            MultipartFile certificate, MultipartFile salarySlip1, MultipartFile bankStatement,
            MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage,MultipartFile salarySlip2,MultipartFile salarySlip3,MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate, int uId) {
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
	        if (salarySlip1 != null && !salarySlip1.isEmpty()) {
	            documents.setSalarySlip1(uploadFile(salarySlip1, basePath, "salarySlip"));
	        }
	        if (bankStatement != null && !bankStatement.isEmpty()) {
	            documents.setBankStatement(uploadFile(bankStatement, basePath, "bankStatement"));
	        }
	        if (salarySlip2 != null && !salarySlip2.isEmpty()) {
	            documents.setSalarySlip2(uploadFile(salarySlip2, basePath, "otherDocuments"));
	        }
	        if (salarySlip3 != null && !salarySlip3.isEmpty()) {
	        	documents.setSalarySlip3(uploadFile(salarySlip3, basePath, "otherDocuments"));
	        }
	        if(tenthCertificate != null && !tenthCertificate.isEmpty()) {
	        	documents.setTenthCertificate(uploadFile(tenthCertificate, basePath, "tenthCertificate"));
	        }
	        if(twelfthCertificate != null && !twelfthCertificate.isEmpty()) {
		        documents.setTwelfthCertificate(uploadFile(twelfthCertificate, basePath, "tenthCertificate"));
	        }
	        if(degreeCertificate != null && !degreeCertificate.isEmpty()) {
	        	documents.setDegreeCertificate(uploadFile(degreeCertificate, basePath, "degreeCertificate"));
	        }
	        if(relevingLetter != null && !relevingLetter.isEmpty()) {
	        	documents.setRelevingLetter(uploadFile(relevingLetter, basePath, "relevingLetter"));
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
		        documentsDto.setSalarySlip1(documents.getSalarySlip1());
		        documentsDto.setSalarySlip2(documents.getSalarySlip2());
		        documentsDto.setSalarySlip3(documents.getSalarySlip3());
		        documentsDto.setBankStatement(documents.getBankStatement());
		       documentsDto.setTenthCertificate(documents.getTenthCertificate());
		        documentsDto.setTwelfthCertificate(documents.getTwelfthCertificate());
		        documentsDto.setDegreeCertificate(documents.getDegreeCertificate());
		        documentsDto.setRelevingLetter(documents.getRelevingLetter());
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
