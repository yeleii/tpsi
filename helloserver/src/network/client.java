package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static void main(String[] args) throws Exception {
		try {
			String severAddress="127.0.0.1";  // localhost
			int severPort=8698;
			String clientMessage = "";
			String serverMessage = "";
			
			// Create connection to server socket
			System.out.print("Client: Tentativo di connessione server=" + severAddress + ":" + severPort + " ... ");
			Socket socket = new Socket(severAddress, severPort); //

			System.out.println("Connected");
			// Create streams to read/write data
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

			Scanner scanner = new Scanner(System.in);
			

			
			
			while (!clientMessage.equals("end")) {

				// Prompt user to enter some number or 'end'
				System.out.print("Client: inserisci il messaggio da inviare> ");
				clientMessage = scanner.nextLine();

				// Send the entered number to server
				System.out.println("Client: invio il messaggio: " + clientMessage);
				outStream.writeUTF(clientMessage);
				outStream.flush();

				// Read data from socket input stream
				serverMessage = inStream.readUTF();
				System.out.println("Client: ricevuto il messaggio: " + serverMessage);
			}

			// Close resources
			outStream.close();
			outStream.close();
			socket.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
