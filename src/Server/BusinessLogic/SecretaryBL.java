package Server.BusinessLogic;

import Server.mapper.ConsultationMapper;
import Server.model.Consultation;
import Server.model.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class SecretaryBL {

    private ConsultationMapper consultationMapper;

    public SecretaryBL() {

        this.consultationMapper = new ConsultationMapper();
    }

    public ObservableList<Consultation> readConsultations() throws SQLException{
        return consultationMapper.readConsultations();
    }

    public void createConsultation(Consultation consultation) throws SQLException{
        consultationMapper.createConsultation(consultation);
    }

    public ObservableList<Consultation> readDoctorsConsultation(User user) throws SQLException{

        return consultationMapper.readDoctorConsultations(user);
    }

    public void deleteConsultation(Consultation consultation) throws SQLException{
        consultationMapper.deleteConsultation(consultation);
    }

}
