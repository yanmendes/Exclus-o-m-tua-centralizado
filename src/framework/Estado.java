/*
 * FRAMEWORK:
 * Essa classe implementa o conceito de ESTADO.
 */
package framework;

public class Estado {
    public Entidade ent;
    public Estado(Entidade _e){
        ent=_e;
    };
    public void transicao(Evento _e){
       // Esse metodo deve ser especializado
    }
}
