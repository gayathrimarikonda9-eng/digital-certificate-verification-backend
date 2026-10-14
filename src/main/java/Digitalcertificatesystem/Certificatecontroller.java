package Digitalcertificatesystem;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/certificates")
public class Certificatecontroller {

    private final Map<String, Certificate> certificates = new ConcurrentHashMap<>();

    @GetMapping({"/", ""})
    public String home() {
        return "Digital Certificate System backend is running.";
    }

    @GetMapping("/all")
    public Collection<Certificate> getAllCertificates() {
        return certificates.values();
    }

    @PostMapping("/add")
    public String addCertificate(@RequestBody Certificate certificate) {
        if (certificate == null) {
            return "Failed: Certificate payload is missing.";
        }

        String id = certificate.getCertificateId();
        if (id == null || id.trim().isEmpty()) {
            id = certificate.getDept();
        }
        if (id == null || id.trim().isEmpty()) {
            return "Failed: Certificate ID cannot be empty.";
        }

        id = id.trim();
        certificate.setCertificateId(id);

        String studentName = certificate.getStudentName();
        if (studentName == null || studentName.trim().isEmpty()) {
            return "Failed: Student name cannot be empty.";
        }
        certificate.setStudentName(studentName.trim());

        if (certificate.getStatus() == null || certificate.getStatus().trim().isEmpty()) {
            certificate.setStatus("VALID");
        }

        if (certificate.getDept() == null || certificate.getDept().trim().isEmpty()) {
            certificate.setDept("N/A");
        }

        if (certificates.containsKey(id)) {
            return "Failed: Certificate ID already exists.";
        }

        certificates.put(id, certificate);
        return "Certificate added successfully for student: " + certificate.getStudentName();
    }

    @GetMapping("/verify/{id}")
    public String verifyCertificate(@PathVariable String id) {
        Certificate certificate = certificates.get(id);

        if (certificate == null) {
            return "Certificate not found";
        }

        if ("REVOKED".equalsIgnoreCase(certificate.getStatus())) {
            return "Certificate is revoked";
        }

        return "Certificate is valid";
    }

    @PostMapping("/revoke/{id}")
    public String revokeCertificate(@PathVariable String id) {
        Certificate certificate = certificates.get(id);

        if (certificate == null) {
            return "Certificate not found";
        }

        certificate.setStatus("REVOKED");
        return "Certificate revoked successfully";
    }
}