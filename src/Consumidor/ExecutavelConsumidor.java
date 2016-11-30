/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
package Consumidor;

import framework.Entidade;
import framework.SThread;

public class ExecutavelConsumidor {

    Thread thread1, thread2, thread3;
    SThread sthread;
    SocketThread xthread;
    InputThread ithread;
    public static final int invalid = 0;
    public static final int requisicao = 3;
    public static final int devolver = 4;
    public static final int entrega = 5;
    Entidade consumidor;

    public static void main(String[] args) {
        // TODO code application logic here
        ExecutavelConsumidor exclusaoMutuaCentralizado = new ExecutavelConsumidor();
        exclusaoMutuaCentralizado.init(args);
    }

    public void init(String[] args) {
        switch (args.length) {
            case 2:
                this.consumidor = new Consumidor(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

                // Inicia thread de tratamento de eventos
                sthread = new SThread(consumidor);
                thread1 = new Thread(sthread);
                thread1.start();

                // Inicia thread de leitura do socket
                xthread = new SocketThread(consumidor.msg, consumidor);
                thread2 = new Thread(xthread);
                thread2.start();

                // Inicia thread de leitura de teclado
                ithread = new InputThread(consumidor);
                thread3 = new Thread(ithread);
                thread3.start();

                ((Consumidor) consumidor).ms = String.join(",", args);
                break;

            default:
                System.out.println("Numero de argumentos invalido!!");
                break;
        }
    }

}
