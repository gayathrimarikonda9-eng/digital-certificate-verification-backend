package Digitalcertificatesystem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @Column(name = "certificate_id")
    private String certificateId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course")
    private String course;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "status")
    private String status = "VALID";

    @Column(name = "dept")
    private String dept;

    @Column(name = "subject")
    private String subject;

    public Certificate() {}

    public String getCertificateId() { return certificateId; }
    public void setCertificateId(String certificateId) { this.certificateId = certificateId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}