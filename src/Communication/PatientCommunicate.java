package Communication;


import java.sql.Date;

public class PatientCommunicate {
    private int patientId;
    private String name;
    private String identityCard;
    private String pNC;
    private Date dateOfBirth;
    private String adress;
    private int doctorId;

    public PatientCommunicate(){}

    public PatientCommunicate(int patientId, String name, String identityCard, String pNC, Date dateOfBirth, String adress) {
        this.patientId = patientId;
        this.name = name;
        this.identityCard = identityCard;
        this.pNC = pNC;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getpNC() {
        return pNC;
    }

    public void setpNC(String pNC) {
        this.pNC = pNC;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getDoctorId() {
        return doctorId;
    }

}
