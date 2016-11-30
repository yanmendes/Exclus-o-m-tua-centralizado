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
import framework.Evento;

public class Coordenador_idle extends Estado {

    public Coordenador_idle(Entidade _e) {
        super(_e);
    }

    @Override
    public void transicao(Evento _ev) {

        String[] args = _ev.msg.split(",");
        int consumidor;
        int recurso;

        switch (_ev.code) {

            case 1: // Evento requisicao
                
                consumidor = Integer.parseInt(args[1]);
                recurso = Integer.parseInt(args[0]);

                if (((Coordenador) ent).isDisponivel(recurso, consumidor)) {
                    ((Coordenador) ent).entrega(recurso, consumidor);
                } else {
                    ((Coordenador) ent).bufferizar(recurso, consumidor);
                }

                break;
            case 2: // Evento devolver

                consumidor = Integer.parseInt(args[1]);
                recurso = Integer.parseInt(args[0]);

                ((Coordenador) ent).atualizaBuffer(recurso, consumidor);
                ((Coordenador) ent).verificaBuffer(recurso);
               
                break;
            case 3: //Evento imprimir buffer
                ((Coordenador) ent).printBuffer();
                
                break;
            default: // evento inesperado
                System.out.println("Coordenador descartou evento : " + _ev.code + " em Idle");
                System.out.print("\n\nInforme o comando: ");
        }
    }
}
