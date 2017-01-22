import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by s.sakaigawa on 2017/01/22.
 */
public class UDPSend {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    DatagramSocket sendSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
    int inetPort = 5060;

    public UDPSend() throws Exception {

    }

    public boolean send() throws Exception {
        System.out.print("msg==>");
        String msg = br.readLine();
        byte[] buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, inetPort);
        sendSocket.send(packet);

        if (msg.equals("")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        UDPSend test = new UDPSend();
        if (args.length == 1) {
            test.inetAddress = InetAddress.getByName(args[0]);
        }
        if(args.length==2){
            test.inetAddress = InetAddress.getByName(args[0]);
            test.inetPort = Integer.getInteger(args[1]);
        }
        while (test.send() == true) ;
        test.sendSocket.close();
        System.out.print("Press Enter Key");
        br.readLine();
    }

}
