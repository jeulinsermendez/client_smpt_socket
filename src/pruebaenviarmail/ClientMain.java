/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaenviarmail;

/**
 *
 * @author mati
 */
public class ClientMain {
    public static void main(String[] args) {
        
        //instaciar la clase
        SMTPClient cliente = new SMTPClient("localhost",25);
        
        //indicar quien le envia el email
        cliente.setSender("leinormendez190890@gmail.com");
        
        //indicar los destinatarios del correo
        cliente.addRecipient("leinormendez@yopmail.com");
        
        //subject
        cliente.setSubject("PROVA 2");

        //body
        cliente.setBody("Esto es una prueba");

        //enviar correu amb les dades anteriors
        if (cliente.sendMail()) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
        
    }
    
}
