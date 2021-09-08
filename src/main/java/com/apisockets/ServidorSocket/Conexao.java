package com.apisockets.ServidorSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao extends Thread{
    
    /**
     * author: Daniel e Taianne
     */

    private static final PrintStream OUT2 = System.out;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;

    public Conexao(Socket aClientSocket){
        
        try {
            this.clientSocket = aClientSocket;
            this.in = new DataInputStream( clientSocket.getInputStream());
            this.out= new DataOutputStream( clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void run(){
        int i;
        
        if(!clientSocket.isConnected()){
            OUT2.println("Conexão não sucedida");
        }

        while(clientSocket.isConnected()){
            try { 
                String data = in.readUTF(); 
                System.out.println("Requisao recebida do Cliente");
            }catch (EOFException e){
                System.out.println("EOF:"+e.getMessage());
                break;
            } catch(IOException e) {
                System.out.println("ReadLine1:"+e.getMessage());
            }
        }try{
            clientSocket.close();
            System.out.println("Uma conexão foi fechada");
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
