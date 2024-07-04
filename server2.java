package VideoStream;
import com.github.sarxos.webcam.Webcam;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class server2 {
    public static void main(String[] args) {
        try {
        	//.getDefault()---> Will discover and return first webcam available in the system.
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(new Dimension(640, 480));
            webcam.open();

            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server is running. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            JFrame frame = new JFrame("Server Streaming Portal");
            frame.setSize(640, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel();
            label.setSize(640, 480);
            label.setVisible(true);

            frame.add(label);
            frame.setVisible(true);
            while (true) {
                BufferedImage image = webcam.getImage();
                ImageIcon imageIcon = new ImageIcon(image);
                label.setIcon(imageIcon);
                outputStream.writeObject(imageIcon);
                outputStream.flush();
                Thread.sleep(0);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
