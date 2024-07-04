package VideoStream;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            JFrame frame = new JFrame("Client Streaming Portal");
            frame.setSize(640, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel();
            label.setSize(640, 480);
            label.setVisible(true);

            frame.add(label);
            frame.setVisible(true);

            while (true) {
                
            	label.setIcon((ImageIcon)inputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

