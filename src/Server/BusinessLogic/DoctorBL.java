package Server.BusinessLogic;

import Server.mapper.ConsultationMapper;
import Server.model.Consultation;
import Server.model.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class DoctorBL {
    private ConsultationMapper consultationMapper;

    public DoctorBL() {

        this.consultationMapper = new ConsultationMapper();
    }

    public ObservableList<Consultation> viewConsultations(User user) throws SQLException{
       return consultationMapper.readDoctorConsultations(user);
    }
}
