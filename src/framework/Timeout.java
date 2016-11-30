/*
 * FRAMEWORK:
 * Essa classe implementa um mecanismo 
 * para disparo do evento Timeout
 */
package framework;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Timeout implements Runnable{
    Entidade ent;
    int tempo;
    boolean ativo;
    public Timeout(Entidade _ent, int _tempo){
        ent=_ent;
        tempo=_tempo;
    }
    public void paraTimer(){
        ativo=false;
    }
    @Override
    public void run(){
        ativo=true;
        try {
            // contagem regressiva para disparo do Timeout
            Thread.sleep(tempo);
            if (ativo)
                // Entrega o evento para a classe solicitante.
                ent.colocaEvento(new Evento(3,"timeout",""));
        } catch (InterruptedException ex) {
            Logger.getLogger(Timeout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
