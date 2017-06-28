package client;

import Server.DatabaseConnection;
import Server.gateway.UserGateway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private Label lblTxt;

    @FXML
    private TextField userTxt;

    @FXML
    private PasswordField pasText;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField userNameTxt;

    @FXML
    private PasswordField pasUserTxt;

    @FXML
    private Button loginUserTxt;

    @FXML
    private TextField secretaryUserName;

    @FXML
    private PasswordField secretaryPass;

    @FXML
    private Button loginSecretary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = DatabaseConnection.getConnection();
        System.out.println("Connection established!");
    }

    @FXML

    public void setLoginBtn(ActionEvent actionEvent) throws SQLException {
        UserGateway userGateway = new UserGateway();
        String userName = userTxt.getText();
        String password = pasText.getText();
        boolean l;
        l = userGateway.loginAdmin(userName, password);
        if (l == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("Login succsefully!");
            //alert.showAndWait();
            try {
                Stage secondStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                secondStage.setTitle("Admin Page");
                secondStage.setScene(new Scene(root, 750, 700));
                secondStage.show();
                // Hide this current window (if this is what you want)
                //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    @FXML

    public void setLoginUserBtn(ActionEvent actionEvent) throws SQLException {
        UserGateway userGateway = new UserGateway();
        String userName = userNameTxt.getText();
        String password = pasUserTxt.getText();
        boolean l;
        l = userGateway.loginDoctor(userName, password);
        if (l == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("Login succsefully!");
            //alert.showAndWait();
            try {
                Stage secondStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("doctor.fxml"));
                secondStage.setTitle("Doctor Page");
                secondStage.setScene(new Scene(root, 750, 700));
                secondStage.show();
                // Hide this current window (if this is what you want)
                //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

    @FXML

    public void setLoginSecretaryBtn(ActionEvent actionEvent) throws SQLException {
        UserGateway userGateway = new UserGateway();
        String userName = secretaryUserName.getText();
        String password = secretaryPass.getText();
        boolean l;
        l = userGateway.loginSecretary(userName, password);
        if (l == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setContentText("Login succsefully!");
            //alert.showAndWait();
            try {
                Stage secondStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("secretary.fxml"));
                secondStage.setTitle("Secretary Page");
                secondStage.setScene(new Scene(root, 750, 700));
                secondStage.show();
                // Hide this current window (if this is what you want)
                //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error!");
            alert.showAndWait();
        }
    }

}
