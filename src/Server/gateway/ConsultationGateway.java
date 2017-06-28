package Server.gateway;

import Server.DatabaseConnection;
import Server.model.Consultation;
import Server.model.Patient;
import javafx.collections.ObservableArray;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by matei on 6/26/2017.
 */
public class ConsultationGateway {
    public ResultSet getAllConsultations() throws SQLException {
        ArrayList<Consultation> consultations = new ArrayList<>();
        Consultation consultation;
        String sql = "Select * from Consultation";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            consultation = new Consultation();
            consultation.setConsultationId(resultSet.getInt(1));
            consultation.setPatientId(resultSet.getInt(2));
            consultation.setDoctorId(resultSet.getInt(3));
            consultation.setDateCond(resultSet.getDate(4));
            consultation.setDescription(resultSet.getString(5));

            consultations.add(consultation);
        }
        return resultSet;
    }

    public boolean addConsultation(Consultation consultation) throws SQLException {
        boolean insert = false;
        String sql = "Insert into Consultation values(?,?,?,?,?)";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, consultation.getConsultationId());
        statement.setInt(2, consultation.getPatientId());
        statement.setInt(3, consultation.getDoctorId());
        statement.setDate(4, consultation.getDateCond());
        statement.setString(5, consultation.getDescription());
        statement.executeUpdate();
        insert = true;
        connection.close();
        return insert;

    }

    public boolean updateConsultation(int consultationId) throws SQLException {
        Consultation consultation = new Consultation();
        boolean update = false;
        String sql = "Update Consultation set patientId=?, doctorId=?, dateCond=?, description=? where consultationId=?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, consultation.getPatientId());
        statement.setInt(2, consultation.getDoctorId());
        statement.setDate(3, consultation.getDateCond());
        statement.setString(4, consultation.getDescription());
        statement.setInt(5, consultation.getConsultationId());
        statement.executeUpdate();
        update = true;
        return update;

    }

    public ResultSet getDoctorConsultations(int idDoctor) throws SQLException {

        ArrayList<Consultation> consultations = new ArrayList<>();
        Consultation consultation;
        String sql = "Select * from Consultation where idDoctor=?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDoctor);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            consultation = new Consultation();
            consultation.setConsultationId(resultSet.getInt(1));
            consultation.setPatientId(resultSet.getInt(2));
            consultation.setDateCond(resultSet.getDate(3));
            consultation.setDescription(resultSet.getString(4));

            consultations.add(consultation);
        }
        return resultSet;
    }

    public boolean isAvailableDoctor(int idDoctor, Date date) throws SQLException{
        boolean available = true;
        String sql = "Select * from Consultation where date=? and idDoctor=?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, date);
        statement.setInt(2, idDoctor);
        ResultSet resultSet = statement.executeQuery();
        available = !resultSet.next();
        return available;

    }

    public boolean deleteConsultation(int consultationId) throws SQLException{
        boolean deleted = false;
        String sql = "Delete from Consultation where consultationId=?";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, consultationId);
        statement.executeUpdate();
        deleted = true;
        connection.close();
        return deleted;
    }



}


