/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Consumidor;

import Coordenador.*;
import framework.Entidade;
import framework.Estado;
import framework.Evento;

public class Consumidor_idle extends Estado {

    public Consumidor_idle(Entidade _e) {
        super(_e);
    }

    @Override
    public void transicao(Evento _ev) {

        String[] args = _ev.msg.split(",");
        int consumidor;
        int recurso;

        switch (_ev.code) {

            case 3: // Evento requisicao
                recurso = Integer.parseInt(args[0]);

                ((Consumidor) ent).requisicao(recurso);

                System.out.print("\n\nInforme o comando: ");
                break;
            case 4: // Evento devolver
                recurso = Integer.parseInt(args[0]);

                ((Consumidor) ent).devolverRecurso(recurso);

                System.out.print("\n\nInforme o comando: ");
                break;
            case 5: // Evento entrega
                recurso = Integer.parseInt(args[0]);

                ((Consumidor) ent).consumir(recurso);
                break;
            default: // evento inesperado
                System.out.println("Consumidor descartou evento : " + _ev.code + " em Idle");
                System.out.print("\n\nInforme o comando: ");
        }
    }
}
