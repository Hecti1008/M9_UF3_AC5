/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortcp3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Hector
 */
public class ClientTCP2 {
    public static void main (String[] args) throws Exception {
        String host = "localhost";
        int portNum = 60000;
        Socket client = new Socket(host, portNum);
        
        PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
        
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String chain, eco ="";
        System.out.println("Introdueix la cadena: ");
        chain = in.readLine();
        
        while (chain != null) {
            fsortida.println(chain);
            eco = fentrada.readLine();
            System.out.println(" =>ECO: " + eco);
            chain = in.readLine();
        }
        
        fsortida.close();
        fentrada.close();
	System.out.println("Finalització de l'enviament...");
        in.close();
	client.close();
    }
}
