package Server.mapper;

import Server.DatabaseConnection;
import Server.gateway.PatientGateway;
import Server.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by matei on 6/26/2017.
 */
public class PatientMapper {
    private PatientGateway gateway;
    private ObservableList<Patient> patients;

    public PatientMapper(){
        gateway = new PatientGateway();

    }
    public ObservableList<Patient> readPatients() throws SQLException {
        patients = FXCollections.observableArrayList();
        ResultSet resultSet = gateway.getAllPatients();
        while(resultSet.next()){
            Patient patient = new Patient();
            patient.setPatientId(resultSet.getInt(1));
            patient.setName(resultSet.getString(2));
            patient.setIdentityCard(resultSet.getString(3));
            patient.setpNC(resultSet.getString(4));
            patient.setDateOfBirth(resultSet.getDate(5));
            patient.setAdress(resultSet.getString(6));
            patient.setDoctorId(resultSet.getInt(7));

            patients.add(patient);
        }
        return patients;

    }

    public void createPatient(Patient patient) throws SQLException{
        gateway.addPatient(patient);
    }

    public void deletePatient(Patient patient) throws SQLException{
        gateway.deletePatient(patient.getPatientId());
    }
    public  void updatePatient(Patient patient) throws SQLException{
        gateway.updatePatient(patient);
    }


}
