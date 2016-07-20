import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhiboliu on 16-7-20.
 */
public class NetClient {
    //public static final


    public void connect(String IP, int port){
        try {
            Socket s = new Socket(IP, port);
System.out.println("Tank client Connects to server!");
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
