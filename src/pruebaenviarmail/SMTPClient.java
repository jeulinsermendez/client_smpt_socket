package pruebaenviarmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SMTPClient {

    String SMTPServer;
    int puerto;
    String sender;
    String recipient;
    String body;
    String subject;

    public SMTPClient(String SMTPServer, int port) {
        this.SMTPServer = SMTPServer;
        this.puerto = port;

    }

    public void setSender(String sender) {
        this.sender = sender;

    }

    public void addRecipient(String recipient) {
        this.recipient = recipient;

    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;

    }

    public boolean sendMail() {
        boolean result = false;
        String resposta = "";
        
        
        try {
            Socket socket = new Socket(SMTPServer, puerto);
            socket.setSoTimeout(3000);
            BufferedReader bIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter bOut = new PrintWriter(out, true);
            
            //le enviamos la orden HELO
            resposta = bIn.readLine();
            System.out.println(resposta);
            System.out.println("Enviando la orden HELO...");
            
            
            bOut.println("HELO " + SMTPServer);
            System.out.println("Waiting for response...");
            resposta = bIn.readLine();
            System.out.println("Response:");
            System.out.println(resposta);
            
            bOut.println("MAIL FROM: <" + sender+">");
            resposta = bIn.readLine();
            System.out.println(resposta);
            
            
            bOut.println("rcpt to: <" +recipient+">");
            resposta = bIn.readLine();
            System.out.println(resposta);
            
            
            bOut.println("data");
            resposta = bIn.readLine();
            System.out.println(resposta);
            
            bOut.println(body +"\n ");
            bOut.println(".");
            resposta = bIn.readLine();
            
            System.out.println(resposta);
            
            if(resposta.contains("Message accepted for delivery")){
                result= true;
            }else{
                result = false;
            }
            
           
        } catch (IOException ex) {
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
