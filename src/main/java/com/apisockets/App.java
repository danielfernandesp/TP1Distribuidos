package com.apisockets;

import java.net.ServerSocket;
import java.net.*;
import java.io.*;

import com.apisockets.ServidorSocket.*;

public class App 
{
    public static void main( String[] args )
    {
        try{
            int serverPort = 7000; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                new Conexao(listenSocket.accept()).start();
            }
        } catch(IOException e) {
            System.out.println("Escutando ao Socket:"+e.getMessage());}
    }
}
