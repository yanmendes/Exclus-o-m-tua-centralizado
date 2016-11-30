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
import framework.Evento;
import framework.Msg;
import framework.SimpleThread_r;

public class SocketThread extends SimpleThread_r {

    String n;
    String m;
    int code;

    public SocketThread(Msg _m, Entidade _u) {
        super(_m, _u);
    }

    @Override
    public void desempacota() {
        if (tmp.contains(" ")) {
            tmp = parse();
        }
        String[] split;
        // Desempacota mensagem
        split = tmp.split(",");
        code = Integer.valueOf(split[0]);
        n = split[1];
        m = split[2];
        //Trata as mensagens com diferentes números de parâmetros
        int i = 3;
        while (i < split.length) {
            m = m + "," + split[i];
            ++i;
        }

        // Cria Evento
        Evento e = new Evento(code, n, m);
        // Coloca no buffer da entidade
        ent.colocaEvento(e);
    }

    public String parse() {
        String[] aux2 = tmp.split(" ");
        int code;
        String name;
        String args;

        switch (aux2[0]) {
            case "requisicao":
                code = 3;
                name = "requisicao";
                args = aux2[1];
                break;
            case "devolver":
                code = 4;
                name = "devolver";
                args = aux2[1];
                break;
            case "entrega":
                code = 5;
                name = "entrega";
                args = aux2[1];
                break;
            default:
                code = 0;
                name = aux2[0];
                args = "<<Comando invalido>>";
                break;
        }

        return code + "," + name + "," + args;
    }
}
