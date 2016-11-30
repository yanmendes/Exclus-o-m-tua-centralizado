/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Consumidor;

import framework.Entidade;
import framework.Estado;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Entidade {

    public String ms;
    public Estado _idle;

    private int _coord;
    private int _port;

    public Consumidor(int port, int coord) {

        super(port);

        _idle = new Consumidor_idle(this);

        this._coord = coord;
        this._port = port;
        setState(_idle);

        System.out.println("Entidade consumidor inicializada usando a porta " + port + " coordenador na porta " + coord);
    }

    public void setState(Estado newState) {
        est = newState;

        System.out.print("\n\nInforme o comando: ");
    }

    public void consumir(int recurso) {
        System.out.println("Consumindo o recurso " + recurso);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Acabei de consumir o recurso " + recurso);

        msg.conecta("localhost", this._port);
        msg.envia("devolver " + recurso);
        msg.termina();
    }

    public void devolverRecurso(int recurso) {
        msg.conecta("localhost", this._coord);
        msg.envia("devolver_recurso " + recurso + " " + this._port);
        msg.termina();
    }

    public void requisicao(int recurso) {
        msg.conecta("localhost", this._coord);
        msg.envia("requisicao " + recurso + " " + this._port);
        msg.termina();
    }

}
