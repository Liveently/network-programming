import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("a number of port required");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);

        ServerSocket serverSocket = null;
        int numCon = 0;

        try {
       //     InetAddress ipAddress = InetAddress.getByName("192.168.56.1");
         //   serverSocket = new ServerSocket(port, 0, ipAddress);
              serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                Socket clientSocket = serverSocket.accept(); //ожидает подключение клиента
                System.out.println("Client accepted");
                new Thread(new Connection(clientSocket)).start();  //новый поток для каждого клиента
                numCon++;
            }
        }catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }finally {
            try{
                assert serverSocket != null;
                serverSocket.close();
            }catch (IOException e){
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
