package clientes;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ClienteRMI {
    
    private final int nRequisicoes;
    private final ArrayList respostaRequisicao = new ArrayList();
    
    public ClientRMI(int nRequisicoes){
        this.nRequisicoes = nRequisicoes;
    }

    public void run() {
        try {
            int i;
            Registry registry = LocateRegistry.getRegistry(8999);
            InterfaceRemota stub = (InterfaceRemota) registry.lookup("InterfaceRemota");
            for(i=0;i<nRequisicoes;i++){
                String response = stub.escolheFigura();
                System.out.println(response);
                requestReply.add(i, response);
            }
        } catch (Exception e) {
            System.err.println("Cliente excessão: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public ArrayList obterResposta(){
        return respostaRequisicao;
    }
}
