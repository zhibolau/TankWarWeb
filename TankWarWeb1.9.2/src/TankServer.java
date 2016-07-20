import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhiboliu on 16-7-20.
 */
public class TankServer {

    public static final int TCP_PORT = 8888;

    public static void main(String[] args){
        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true){
                Socket s = ss.accept();
System.out.println("a client is connected! Address: " + s.getInetAddress() + ":" + s.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
