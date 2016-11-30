/*
 * FRAMEWORK:
 * Classe que implementa o conceito de EVENTO.
 */
package framework;

public class Evento {
    public int code;
    public String nome;
    public String msg;
    public Evento(int _c, String _n, String _m){
        code=_c;
        nome=_n;
        msg=_m;
    }
    @Override
    public String toString(){
        return String.valueOf(code)+","+nome+","+msg;    
    }
}
