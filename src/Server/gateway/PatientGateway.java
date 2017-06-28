package Server.gateway;

import Server.DatabaseConnection;
import Server.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PatientGateway {
    public ResultSet getAllPatients() throws SQLException{
        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient;
        String sql = "Select * from Patient";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            patient = new Patient();
            patient.setPatientId(resultSet.getInt(1));
            patient.setName(resultSet.getString(2));
            patient.setIdentityCard(resultSet.getString(3));
            patient.setpNC(resultSet.getString(4));
            patient.setDateOfBirth(resultSet.getDate(5));
            patient.setAdress(resultSet.getString(6));
            patient.setDoctorId(resultSet.getInt(7));

            patients.add(patient);
        }
        return resultSet;
    }

    public boolean addPatient(Patient patient) throws SQLException{
        boolean insert = false;
        String sql = "Insert into Patient values(?,?,?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, patient.getPatientId());
        statement.setString(2, patient.getName());
        statement.setString(3, patient.getIdentityCard());
        statement.setString(4, patient.getpNC());
        statement.setDate(5,patient.getDateOfBirth());
        statement.setString(6, patient.getAdress());
        statement.setInt(7, patient.getDoctorId());
        statement.executeUpdate();
        insert = true;
        connection.close();
        return insert;

    }

    public boolean updatePatient(Patient patient) throws SQLException{
        boolean updated = false;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "Update Patient set name=?, identityCard=?, pNC=?, dateOfBirth=?, address=? where patientId=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, patient.getName());
        statement.setString(2, patient.getIdentityCard());
        statement.setString(3, patient.getpNC());
        statement.setDate(4,patient.getDateOfBirth());
        statement.setString(5, patient.getAdress());
        statement.setInt(6, patient.getPatientId());
        statement.executeUpdate();
        updated = true;
        connection.close();
        return updated;

    }

    public boolean deletePatient(int patientId) throws SQLException{
        boolean deleted = false;
        String sql = "Delete from Patient where patientId=?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, patientId);
        statement.executeUpdate();
        deleted = true;
        connection.close();
        return deleted;
    }
}
