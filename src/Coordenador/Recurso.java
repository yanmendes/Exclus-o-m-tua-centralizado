/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coordenador;

import java.util.ArrayList;

/*
* DCC 064 - Sistemas Distribuídos
* Professor: Ciro Barbosa
* author: Marcos Valadão
* author: Yan Mendes
* date: 27/11/2016
 */
public class Recurso {

    private int _id;
    private boolean _available;
    private ArrayList<Integer> _consumidores;

    public Recurso(int id) {
        this._id = id;
        this._available = true;
        this._consumidores = new ArrayList<Integer>();
    }

    public boolean vazia() {
        return this._consumidores.isEmpty();
    }

    public int getFirst() {
        return this._consumidores.get(0);
    }

    public void remove(int consumidor) {
        this._consumidores.remove(this._consumidores.indexOf(consumidor));
    }

    public void add(int consumidor) {
        this._consumidores.add(consumidor);
    }

    public boolean available() {
        return this._available;
    }

    public void setAvailable(boolean val) {
        this._available = val;
    }

    public int getId() {
        return this._id;
    }

    public ArrayList<Integer> getConsumidores() {
        return this._consumidores;
    }

    public boolean hasConsumidor(int consumidor) {
        return this._consumidores.stream().anyMatch((i) -> (i == consumidor));
    }
}
