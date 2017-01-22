import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Created by s.sakaigawa on 2017/01/22.
 */
public class UDPReceive {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket recSocket = new DatagramSocket(5060);
    SocketAddress socketAddress;

    public UDPReceive() throws Exception {

    }

    public boolean receive() throws Exception {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        recSocket.receive(packet);
        socketAddress = packet.getSocketAddress();

        int len = packet.getLength();
        String msg = new String(buf, 0, len);

        if (msg.equals("")) {
            return false;
        }
        System.out.println(msg + " : " + len + "byte receive by" + socketAddress.toString());
        return true;
    }

    public static void main(String[] args) throws Exception {
        UDPReceive test = new UDPReceive();
        if (args.length == 1) {
            test.recSocket = new DatagramSocket(Integer.getInteger(args[0]));
        }

        while (test.receive() == true) ;
        System.out.print("Press Enter Key");
        br.readLine();

    }
}