package client;


import Server.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminControllerFx {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, Integer> userIdTc;
    @FXML
    private TableColumn<User, String> usernameTc;
    @FXML
    private TableColumn<User, String> passwordTc;
    @FXML
    private TableColumn<User, Integer> roleIdTc;

    @FXML
    private TextField userIdTf;
    @FXML
    private TextField usernameTf;
    @FXML
    private TextField passwordTf;
    @FXML
    private TextField roleIdTf;

    @FXML
    private Button addUser;
    @FXML
    private Button viewUser;
    @FXML
    private Button updateUser;
    @FXML
    private Button deleteUser;

    private ObservableList<User> users;

    @FXML
    public void setAddUserButton(javafx.event.ActionEvent actionEvent){

    }
    @FXML
    public void setViewUserButton(javafx.event.ActionEvent actionEvent) throws SQLException{

        users = FXCollections.observableArrayList();
        String sql = "SELECT * FROM USER";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));

        }
       // connection.close();

        userIdTc.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        usernameTc.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordTc.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        roleIdTc.setCellValueFactory(new PropertyValueFactory<User, Integer>("roleId"));

        userTableView.setItems(users);
    }
    @FXML
    public void setUpdateUserButton(javafx.event.ActionEvent actionEvent){

    }
    @FXML
    public void setDeleteUserButton(javafx.event.ActionEvent actionEvent){

    }

}
