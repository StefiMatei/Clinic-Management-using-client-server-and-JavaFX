package Server.mapper;

import Server.gateway.UserGateway;
import client.User;

import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class UserMapper {
   private UserGateway userGateway;
   private User user;
   public UserMapper(){
       this.userGateway = new UserGateway();
   }

   public void loginAdmin() throws SQLException{
       userGateway.loginAdmin(user.getUsername(),user.getPassword());
   }

    public void loginDoctor() throws SQLException{
        userGateway.loginDoctor(user.getUsername(),user.getPassword());
    }
    public void loginSecretary() throws SQLException{
        userGateway.loginSecretary(user.getUsername(),user.getPassword());
    }



}
