package com.perscholas.HospitalManagementSystem.Controller;

import com.perscholas.HospitalManagementSystem.Entity.Patient;
import com.perscholas.HospitalManagementSystem.Exception.NoPatientsFoundException;
import com.perscholas.HospitalManagementSystem.Exception.PatientNotFoundException;
import com.perscholas.HospitalManagementSystem.Repository.PatientRepository;
import com.perscholas.HospitalManagementSystem.Service.PatientService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    private static final String UPLOAD_DIR = "path/to/report/uploads";

    @GetMapping("/patient")
    public String showPatientHome(Model model) {
        Patient patient=new Patient();
        model.addAttribute("patient", patient);
        return "patient_home";
    }
    @GetMapping("/patient/form")
    public String showPatientForm(Model model) {
        Patient patient=new Patient();
        model.addAttribute("patient", patient);
        return "patient_form";
    }
    @GetMapping("/patient/view")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        if(patients.isEmpty()){
            throw new NoPatientsFoundException("No patients found");
        }
        model.addAttribute("patients", patients);
        return "view_patient_details";
    }
    @GetMapping("patient/patientById")
    public String getPatientById(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "view_patient_by_id";
    }

    @GetMapping("/patient/")
    public String getPatientByPatientId(@RequestParam("patientId")  Long patientId, Model model)
    {
        try{
        Patient patient=patientService.getPatientById(patientId);
        model.addAttribute("patient", patient);
        return "view";
        }
        catch (RuntimeException e){
        model.addAttribute("errorMessage", "Patient not found");
        return "error_page";
    }
    }

@GetMapping("/patient/file/{patientId}")
public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("patientId") Long patientId) {
    // Fetch the patient entity from the repository
    Optional<Patient> optionalPatient = patientRepository.findById(patientId);

    if (optionalPatient.isPresent()) {
        Patient patient = optionalPatient.get();
        byte[] fileData = patient.getFileData();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileData);
             PDDocument document = PDDocument.load(inputStream)) {

            // Extract the text content from the PDF document
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // Process the extracted text as needed
            System.out.println(text);

            // Create a ByteArrayResource with the file data to return as the response
            ByteArrayResource resource = new ByteArrayResource(fileData);

            // Setting the appropriate response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + text + ".pdf\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileData.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (IOException e) {
            throw new RuntimeException("Failed to download the file.", e);
        }
    } else {
        throw new RuntimeException("Patient not found");
    }
}

@PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient patient,Model model, @RequestParam("file") MultipartFile file)  {


        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                patient.setFileData(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
                // catch file upload error
            }
        }
        patientService.savePatient(patient);

        return "success";

    }
    @GetMapping("/patient/edit/{patientId}")
    public String viewPatientDetails(@PathVariable("patientId") Long patientId, Model model) {
        try{
        // Fetch the patient from the database
        Patient patient = patientService.getPatientById(patientId);

        // Pass the patient object to the view
        model.addAttribute("patient", patient);

        // Return the view name
        return "patient_update";}
        catch (PatientNotFoundException e) {
            // catch the exception when the patient is not found
            model.addAttribute("errorMessage", "Patient not found");
            return "error_page";
        }
    }

        @PutMapping("/patient/edit/{patientId}")
        public String updatePatient(@PathVariable("patientId") Long patientId, Model model, @ModelAttribute Patient patient,@RequestParam("file") MultipartFile file) {
            try {
                // Fetch the existing patient from the database
                patientService.updatePatientById(patientId, patient, file);
                return "success";
            }
            catch (PatientNotFoundException e) {
                // catch the exception when the patient is not found
                model.addAttribute("errorMessage", "Patient not found");
                return "error_page";
                    }
    }
    @GetMapping("/patient/delete/{patientId}")
    public String deletPatient(@PathVariable("patientId") Long patientId){
        try{
         patientService.deletePatientById(patientId);
         return "success1";}
        catch (PatientNotFoundException e) {
            // catch exception when the patient is not found
            return "error_page";
        }
    }

    @GetMapping("/patient/search")
    public String getAllPatients(@RequestParam(value = "searchText", required = false) String searchText,
                                 HttpServletRequest request, Model model) {
        List<Patient> patients;
        if (searchText != null && !searchText.isEmpty()) {
            // Perform search based on the search text
            patients = patientService.searchPatients(searchText);
        } else {
            // Fetch all patients if no search text is provided
            patients = patientService.getAllPatients();
        }
        if (patients.isEmpty()) {
            // If no search results are found, redirect back to the previous page
            String referer = request.getHeader("referer");
            return "redirect:" + referer;
        }

        model.addAttribute("patients", patients);
        return "view_patient_details";
    }

}