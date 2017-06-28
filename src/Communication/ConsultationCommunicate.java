package Communication;


import java.io.Serializable;
import java.sql.Date;

public class ConsultationCommunicate implements Serializable {
    private int consultationId;
    private int patientId;
    private int doctorId;
    private Date dateCond;
    private String description;

    public ConsultationCommunicate(){}

    public ConsultationCommunicate(int consultationId, int patientId, int doctorId, Date dateCond, String description) {
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateCond = dateCond;
        this.description = description;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getDateCond() {
        return dateCond;
    }

    public void setDateCond(Date dateCond) {
        this.dateCond = dateCond;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

