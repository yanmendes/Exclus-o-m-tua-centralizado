/*
 * FRAMEWORK:
 *   Classe para tratamento do buffer de mensagens
 *   do Socket.
 */
package framework;

public class SThread implements Runnable {
    public Entidade ent;
    public SThread(Entidade _ent) {
      ent=_ent;
    }
    @Override
    public void run() {   
        ent.trabalha();
    }
}