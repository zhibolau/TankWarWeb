import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhiboliu on 16-7-20.
 */
public class NetClient {

    TankClient tc;
    private static int UDP_PORT_START = 2223;
    private int udpPort;

    public NetClient(TankClient tc){

        this.tc = tc;

        udpPort = UDP_PORT_START++; // will not work, as we run two client, each will be 2223
        // run two times tankClient will not make udpPort diff
    }

    //even though u define ur udp port, server does not know
    //so i have to send it to server



    public void connect(String IP, int port){
        Socket s = null;
        try {
            s = new Socket(IP, port);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(udpPort);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            int id = dis.readInt(); //should be saved in tank as each tank has a unique id num
            tc.myTank.id = id;

            //s.close();//after doing sending data, it should be closed
            //but if sth is wrong with dos.writeInt(udpPort);  u cannot close s, so put it in finally block
System.out.println("Tank client Connects to server! and server gives me an ID " + id);
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
