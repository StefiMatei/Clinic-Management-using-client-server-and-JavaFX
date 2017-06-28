package client;


import Server.DatabaseConnection;
import Server.mapper.ConsultationMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class DoctorControllerFx {

    @FXML
    private TableView<Consultation> consultationTableView;
    @FXML
    private TableColumn<Consultation, Integer> consultationId;
    @FXML
    private TableColumn<Consultation, Integer> patientId;
    @FXML
    private TableColumn<Consultation, Integer> doctorId;
    @FXML
    private TableColumn<Consultation,Date> dateCond;
    @FXML
    private TableColumn<Consultation,String> description;


    @FXML
    private Button viewConsultations;

    private ObservableList<Consultation> consultations;

    @FXML
    public void setViewConsultationsButton(ActionEvent actionEvent) throws SQLException{
        consultations = FXCollections.observableArrayList();
        String sql = "Select * from Consultation";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
        {
            consultations.add(new Consultation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getString(5)));

        }

        consultationId.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("consultationId"));
        patientId.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("patientId"));
        doctorId.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("doctorId"));
        dateCond.setCellValueFactory(new PropertyValueFactory<Consultation, Date>("dateCond"));
        description.setCellValueFactory(new PropertyValueFactory<Consultation, String>("description"));

        consultationTableView.setItems(consultations);
    }
}
