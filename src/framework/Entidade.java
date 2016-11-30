/*
 * FRAMEWORK:
 * Essa classe implementa o conceito de ENTIDADE 
 * de um protocolo.
 */

package framework;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entidade { 
   public ArrayQueue<Evento> buffer;
   public Estado est;
   public Msg msg;
   public Entidade(int _lport){
       buffer = new ArrayQueue<Evento>(0xa);
       msg = new Msg(_lport);
   }
   public void transicao(Evento _e){
            est.transicao(_e);
   }
   public Evento pegaEvento(){
       Evento e = null;
       if(buffer.size()>0){
           e = buffer.get(0);
           buffer.remove(0);
       }
       return e;
   }
   public void colocaEvento(Evento _e){
        buffer.add(_e);
   } 
   public void trabalha(){
        Evento e;
        while(true){            
            e = pegaEvento();
            while (e!=null){
                transicao(e);
                e=pegaEvento();                
            }
            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
                Logger.getLogger(Entidade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
