package Server.mapper;

import Server.gateway.ConsultationGateway;
import Server.model.Consultation;
import Server.model.Patient;
import Server.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class ConsultationMapper {

    private ConsultationGateway gateway;
    private ObservableList<Consultation> consultations;

    public ConsultationMapper() {
        this.gateway = new ConsultationGateway();
    }
    public ObservableList<Consultation> readConsultations() throws SQLException {
       consultations = FXCollections.observableArrayList();
        ResultSet resultSet = gateway.getAllConsultations();
        while (resultSet.next()) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(resultSet.getInt(1));
            consultation.setPatientId(resultSet.getInt(2));
            consultation.setDoctorId(resultSet.getInt(3));
            consultation.setDateCond(resultSet.getDate(4));
            consultation.setDescription(resultSet.getString(5));

            consultations.add(consultation);
        }
        return consultations;

    }
    public void createConsultation(Consultation consultation) throws SQLException{
        gateway.addConsultation(consultation);
    }

    public void updateConsultation(Consultation consultation) throws SQLException{
        gateway.updateConsultation(consultation.getConsultationId());
    }

    public void deleteConsultation(Consultation consultation) throws SQLException {
        gateway.deleteConsultation(consultation.getConsultationId());


    }

    public ObservableList<Consultation> readDoctorConsultations(User user) throws SQLException {
        consultations = FXCollections.observableArrayList();
        ResultSet resultSet = gateway.getDoctorConsultations(user.getUserId());
        if (resultSet.next()) {
            Consultation consultation = new Consultation();
            consultation.setConsultationId(resultSet.getInt(1));
            consultation.setPatientId(resultSet.getInt(2));
            consultation.setDateCond(resultSet.getDate(3));
            consultation.setDescription(resultSet.getString(4));

            consultations.add(consultation);
        }
        return consultations;

    }

}
