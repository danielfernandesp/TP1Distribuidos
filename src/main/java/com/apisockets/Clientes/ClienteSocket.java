package com.apisockets.Clientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClienteSocket {
    
    private Integer id = -1;
    private final int porta;
    private final int nRequisicoes;
    private final ArrayList respostaRequisicao = new ArrayList();
    
    public ClienteSocket(int nRequicoes) {
        this.id = id + 1;
        this.porta = 7000;
        this.nRequisicoes = nRequicoes;
    }

    public void run() {
        Socket s = null;
        try{
            int i;
            s = new Socket("localhost", this.porta);
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());
            for(i=0;i<this.nRequisicoes;i++){
                out.writeUTF(id.toString());
                String data = in.readUTF();
                System.out.println("Cliente " + id + " Recebido: "+ data);
                respostaRequisicao.add(i, data);
            }
        }catch (UnknownHostException e){
            System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){
            System.out.println("Readline2:"+e.getMessage());
        }finally {
            if(s!=null) try {
                s.close(); // fecha a conexao 
            }catch (IOException e){
            System.out.println("Ao fechar:"+e.getMessage());}
        }
    }

    public ArrayList obterResposta(){
        return respostaRequisicao;
    }

}
