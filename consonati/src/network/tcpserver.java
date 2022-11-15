package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class tcpserver {

	public static void main(String[] args) throws Exception {
		try {
			
			int count=0;
			
			int vocali = 0;
			
		    int consonanti = 0;
			
			// Listen to port
			ServerSocket server = new ServerSocket(8698);
			System.out.println("Apertura del socket e attesa connessioni");
			Socket serverClientSocket = server.accept();
			
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());
			
			String clientMessage = "";
			
			while(!clientMessage.equals("quit")) 
				{
		    clientMessage = inStream.readUTF();
		    
		    for(int i = 0; i < clientMessage.length(); i++)
		    {	 
		    	if (Character.isLetter(clientMessage.charAt(i)))
		    		count++;
			}
		    
		    for (int i = 0; i < clientMessage.length(); ++i)
		    {
		         char ch = clientMessage.charAt(i);
		         if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
		         {
		            vocali++;
		         }
		         else
		         if ((ch >= 'a' && ch <= 'z'))
		         {
		            consonanti++;
		         }
		         
		    }
		       
			outStream.writeUTF("Server: " + clientMessage + " Lettere: " + count + "\nnumero consonanti : " + consonanti + "\nnumero vocali :" + vocali);
			
			count=0;
			
			outStream.flush();
			
			 if(consonanti==vocali)
	         {
	        	 clientMessage="quit";
	         }
			
					}
			}
		 catch (Exception e) {
			System.out.println(e);
		}
	}
		}