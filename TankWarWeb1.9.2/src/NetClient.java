import sun.nio.ch.Net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhiboliu on 16-7-20.
 */
public class NetClient {

    private static int UDP_PORT_START = 2223;
    private int udpPort;

    public NetClient(){
        udpPort = UDP_PORT_START++;
    }

    //even though u define ur udp port, server does not know
    //so i have to send it to server



    public void connect(String IP, int port){
        Socket s = null;
        try {
            s = new Socket(IP, port);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(udpPort);
            //s.close();//after doing sending data, it should be closed
            //but if sth is wrong with dos.writeInt(udpPort);  u cannot close s, so put it in finally block
System.out.println("Tank client Connects to server!");
        } catch (UnknownHostException e){
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(s != null) {
                try {
                    s.close();
                    s = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
