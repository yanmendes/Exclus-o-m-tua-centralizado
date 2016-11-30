/*
 * FRAMEWORK:
 * Essa classe implementa operações
 * necessárias para comunicação via SOCKET.
 */ 
package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Msg {
    //Declaro o socket cliente  
    public Socket sc = null;  
    //Declaro o socket servidor
     public Socket ss = null;
    //Declaro a Stream de saida de dados  
    public PrintStream ps = null;  
    //Declaro o ServerSocket  
    ServerSocket serv=null;   
    //Declaro o leitor para a entrada de dados  
    BufferedReader entrada=null;
    public int lPort;
        
    public Msg(int _lp){
        lPort=_lp;       
    }
    // Para Envio
    public int conecta(String _host, int _dest){
        int ret = 0;
            try { //Cria o socket com o recurso desejado na porta especificada 
                    sc = new Socket(_host,_dest);
                    System.out.println("\nConectado a porta "+_dest);
            } catch (UnknownHostException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
            } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                    //System.out.println("Tentando nova conexao");
                    ret=1;  
            }
            return ret;
    }
      
    public void termina(){
         //Encerra o socket cliente  
           try {           
                    //if (serv!=null)
                    //    serv.close();
                    sc.close();
            } catch (IOException e1) {
                    // TODO Auto-generated catch block
            }		
    }
    
    public void fecha_leitura(){
        try {
            serv.close();
        } catch (IOException ex) {
            Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public int envia( String _msg) {
        int ret=0;
        //Cria a Stream de saida de dados 
        try {
                ps = new PrintStream(sc.getOutputStream());
                //Imprime uma linha para a stream de saída de dados  
                ps.print(_msg);  
        } catch (IOException e2) {
                // TODO Auto-generated catch block
                // e2.printStackTrace();
                ret=1;
        }					
        return ret;							         
    }
    
    // Para recebimento
    public int conecta(int _to){
        int ret = 0;
        try {
            serv = new ServerSocket(lPort);	
            //Aguarda uma conexão na porta especificada e cria retorna o socket que irá comunicar com o cliente
            //System.out.println("Wainting for connection\n");
            serv.setSoTimeout(_to); 	
            ss = serv.accept();  	              
            //Cria um BufferedReader para o canal da stream de entrada de dados do socket s  
            entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));  
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            ret=1;
        }  	 
        return ret;
    }    	
    public String recebe() {
        String msg = null;		              
        //Cria o ServerSocket na porta 7000 se estiver disponível  
        try {               	              
                //Aguarda por algum dado e imprime a linha recebida quando recebe 
                entrada = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                msg = entrada.readLine();  
        } catch (IOException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
            System.out.println("\nErro no recebimento");
        }  	 
        return msg;
    }
	
}

