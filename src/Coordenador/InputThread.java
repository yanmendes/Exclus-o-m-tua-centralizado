/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Coordenador;

import framework.Entidade;
import framework.Evento;
import framework.SThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputThread extends SThread {

    public InputThread(Entidade _ent) {
        super(_ent);
    }

    @Override
    public void run() {
        String aux1;
        Boolean continua = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (continua) {
            try {
                aux1 = in.readLine();
                String[] aux2 = aux1.split(" ");

                switch (aux2[0]) {
                    case "requisicao":
                        ((Coordenador) ent).colocaEvento(new Evento(1, aux2[0], aux2[1] + "," + aux2[2]));
                        break;
                    case "devolver_recurso":
                        ((Coordenador) ent).colocaEvento(new Evento(2, aux2[0], aux2[1]));
                        break;
                    case "print_buffer":
                        ((Coordenador) ent).colocaEvento(new Evento(3, aux2[0], ""));
                        break;
                    case "end":
                        continua = false;
                        break;
                    default:
                        ((Coordenador) ent).colocaEvento(new Evento(0, aux2[0], "<<Comando invalido>>"));
                }
            } catch (IOException ex) {
                Logger.getLogger(Coordenador_idle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
