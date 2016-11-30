/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Coordenador;

import framework.Entidade;
import framework.Estado;
import java.util.ArrayList;

public class Coordenador extends Entidade {
    
    public String ms;
    public Estado _idle;
    
    private int _port;
    private ArrayList<Recurso> _buffer;
    
    public Coordenador(int port) {
        
        super(port);
        
        _idle = new Coordenador_idle(this);
        
        this._buffer = new ArrayList();
        this._port = port;
        setState(_idle);

        //Iniciando 3 recursos
        this._buffer.add((new Recurso(0)));
        this._buffer.add((new Recurso(1)));
        this._buffer.add((new Recurso(2)));
        
        System.out.println("Entidade coordenador inicializada usando a porta " + port);
    }
    
    public void setState(Estado newState) {
        est = newState;
        
        System.out.print("\n\nInforme o comando: ");
    }
    
    public void atualizaBuffer(int recurso, int consumidor) {
        if (!this._buffer.get(recurso).vazia() && this._buffer.get(recurso).hasConsumidor(consumidor)) {
            this._buffer.get(recurso).remove(consumidor);
        }
    }
    
    public void verificaBuffer(int recurso) {
        if (!this._buffer.get(recurso).vazia()) {
            this.entrega(recurso, this._buffer.get(recurso).getFirst());
        } else {
            this._buffer.get(recurso).setAvailable(true);
        }
    }
    
    public void entrega(int recurso, int consumidor) {
        //entregando o recurso 
        msg.conecta("localhost", consumidor);
        msg.envia("entrega " + recurso);
        msg.termina();
        
        this._buffer.get(recurso).setAvailable(false);
        if (!this._buffer.get(recurso).vazia()) {
            this._buffer.get(recurso).remove(consumidor);
        }
    }
    
    public void bufferizar(int recurso, int consumidor) {
        this._buffer.get(recurso).add(consumidor);
    }
    
    public boolean isDisponivel(int recurso, int consumidor) {
        return (this._buffer.get(recurso).vazia() || this._buffer.get(recurso).getFirst() == consumidor) && this._buffer.get(recurso).available();
    }
    
    public void printBuffer() {
        for (Recurso r : this._buffer) {
            System.out.print("Recurso " + r.getId() + ": ");
            for (Integer i : r.getConsumidores()) {
                System.out.print(i + "\t");
            }
            System.out.println("");
        }
    }
    
}
