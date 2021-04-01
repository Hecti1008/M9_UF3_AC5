/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortcp3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Hector
 */
public class ServidorTCP4 {
    public static void main (String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        
        int portNum = 60000;
        ServerSocket servidor = new ServerSocket(portNum);
        String chain = "";
        
        int clients = 0;
        int numClients;
        
        System.out.println("Numero de clients que vols?");
        numClients = scan.nextInt();
        
            while (clients < numClients) {
                try {
                    System.out.println("Esperant conexio... ");
                    Socket clientConnected = servidor.accept();
                    clients++;
                    
                    System.out.println("Client " + clients + " connectat... ");
                    PrintWriter fsortida = new PrintWriter(clientConnected.getOutputStream(), true);
                    
                    BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnected.getInputStream()));
                    
                    fsortida.println("Connexio amb el client: " + clients);
                    while ((chain = fentrada.readLine()) != null) {
                        fsortida.println(chain);
                        System.out.println("Rebent: " + chain);
                        if (chain.equals("*")) break;
                    }
                    
                    System.out.println("Tanca conexio... ");
                    fentrada.close();
                    fsortida.close();
                    clientConnected.close();
                }catch (SocketException e){
                    System.out.println("Error");
                }
            }
            servidor.close();
            scan.close();
    }
    
}
