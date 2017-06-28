package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by matei on 6/26/2017.
 */
public class MainServer {
    public static void main(String[] args) throws IOException, SQLException {

        ServerSocket serverSocket = new ServerSocket(2346);
        int count = 0;
        while (true) {
            Socket client = serverSocket.accept();
            Runnable runnable = new SocketServer(client, ++count);
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }
}

