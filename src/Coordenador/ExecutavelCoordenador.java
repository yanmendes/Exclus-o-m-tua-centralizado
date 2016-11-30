/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Coordenador;

import framework.Entidade;
import framework.SThread;

public class ExecutavelCoordenador {

    Thread thread1, thread2, thread3;
    SThread sthread;
    SocketThread xthread;
    InputThread ithread;
    public static final int invalid = 0;
    public static final int requisicao = 1;
    public static final int devolver = 2;
    public static final int printbuffer = 3;
    Entidade coordenador;

    public static void main(String[] args) {
        // TODO code application logic here
        ExecutavelCoordenador exclusaoMutuaCentralizado = new ExecutavelCoordenador();
        exclusaoMutuaCentralizado.init(args);
    }

    public void init(String[] args) {
        switch (args.length) {
            case 1:
                this.coordenador = new Coordenador(Integer.parseInt(args[0]));

                // Inicia thread de tratamento de eventos
                sthread = new SThread(coordenador);
                thread1 = new Thread(sthread);
                thread1.start();

                // Inicia thread de leitura do socket
                xthread = new SocketThread(coordenador.msg, coordenador);
                thread2 = new Thread(xthread);
                thread2.start();

                // Inicia thread de leitura de teclado
                ithread = new InputThread(coordenador);
                thread3 = new Thread(ithread);
                thread3.start();

                ((Coordenador) coordenador).ms = String.join(",", args);
                break;

            default:
                System.out.println("Numero de argumentos invalido!!");
                break;
        }
    }

}
