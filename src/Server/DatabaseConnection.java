package Server;


import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    public static Connection connection;


    public static DatabaseConnection getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static Connection getConnection()
    {
        if(connection==null)
        {
            try{
                connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor","root","ionela0107");
                System.out.println("DB connect successful!!");
            }
            catch(Exception exp){
                exp.printStackTrace();
            }
        }
        return connection;

    }
}