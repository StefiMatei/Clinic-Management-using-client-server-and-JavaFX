package client;


import Server.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class SecretaryControllerFx {

    @FXML
    private TableView<Patient> patientTableView;
    @FXML
    private TableColumn<Patient, Integer> patientIdTc;
    @FXML
    private TableColumn<Patient, String> nameTc;
    @FXML
    private TableColumn<Patient, String> identityTc;
    @FXML
    private TableColumn<Patient, String> cnpTc;
    @FXML
    private TableColumn<Patient, Date> dateOfBirtTc;
    @FXML
    private TableColumn<Patient, String> addressTc;
    @FXML
    private TableColumn<Patient, Integer> doctorId;

    @FXML
    private TextField patientIdTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField identityTf;
    @FXML
    private TextField cnpTf;
    @FXML
    private TextField dateOfBirthTf;
    @FXML
    private TextField addressTf;
    @FXML
    private TextField doctorIdTf;

    @FXML
    private Button viewPatients;

    private ObservableList<Patient> patients;

    @FXML
    public void setViewPatients(ActionEvent actionEvent) throws SQLException {
        patients = FXCollections.observableArrayList();
        String sql = "Select * from Patients";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            patients.add(new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getInt(7)));


        }
        patientIdTc.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("patientId"));
        nameTc.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        identityTc.setCellValueFactory(new PropertyValueFactory<Patient, String>("identityCard"));
        cnpTc.setCellValueFactory(new PropertyValueFactory<Patient, String>("pNC"));
        dateOfBirtTc.setCellValueFactory(new PropertyValueFactory<Patient,Date>("dateOfBirth"));
        addressTc.setCellValueFactory(new PropertyValueFactory<Patient, String>("adress"));
        doctorId.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("doctorId"));

        patientTableView.setItems(patients);

    }
}
