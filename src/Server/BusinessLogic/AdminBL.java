package Server.BusinessLogic;

import Server.mapper.PatientMapper;
import Server.mapper.UserMapper;
import Server.model.Patient;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class AdminBL {
    private PatientMapper patientMapper;
    private UserMapper userMapper;
    public AdminBL(){
        this.patientMapper = new PatientMapper();
        this.userMapper = new UserMapper();

    }

    public ObservableList<Patient> readPatients() throws SQLException{
        return patientMapper.readPatients();
    }
    public void createPatient(Patient patient) throws SQLException{
        patientMapper.createPatient(patient);
    }
    public void updatePatient(Patient patient) throws SQLException{
        patientMapper.updatePatient(patient);
    }
    public void deletePatient(Patient patient) throws SQLException{
        patientMapper.deletePatient(patient);
    }
}
