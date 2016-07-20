import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiboliu on 16-7-20.
 */
public class TankServer {

    public static final int TCP_PORT = 8888;

    List<Client> clients = new ArrayList<Client>();

    /**
     * start 方法为动态方法 ，已经有一个this的对象了 所以可以解决直接new client 未先new tankServer的问题
     */
    public void start(){
        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true){
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String IP = s.getInetAddress().getHostAddress();
                int udpPort = dis.readInt();
                Client c = new Client(IP, udpPort); // u cannot new client which is within tankserver without newing tankServer first
                // use start method to solve this
                clients.add(c);
                s.close();
System.out.println("a client is connected! Address: " + s.getInetAddress() + ":" + s.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new TankServer().start();
    }

    private class Client{
        //save some info for udp use in future
        String IP;
        // I have to know udp port to send data to client udp
        int udpPort;

        public Client(String IP, int udpPort){
            this.IP = IP;
            this.udpPort = udpPort;
        }
    }
}
