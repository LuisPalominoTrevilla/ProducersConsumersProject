
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */
public class Warehouse {
    private Queue<String> tasksBuffer;
    private int bufferCapacity;
    
    public Warehouse(int bufferSize) {
        this.tasksBuffer = new LinkedList<>();
        this.bufferCapacity = bufferSize;
    }
    
    public boolean isFull() {
        return this.tasksBuffer.size() == this.bufferCapacity;
    }
    
    public boolean isEmpty() {
        return this.tasksBuffer.isEmpty();
    }
    
    public synchronized String withdrawProduct() {
        while (this.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                continue;
            }
        }
        String product = this.tasksBuffer.remove();
        System.out.println(String.format("Consumidor consumió %s", product));
        System.out.println(this);
        
        notify();
        
        return product;
    }
    
    public synchronized void produce(String product) {
        while (this.isFull()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                continue;
            }
        }
        this.tasksBuffer.add(product);
        System.out.println(String.format("Productor produjo %s", product));
        System.out.println(this);
        notify();
    }
    
    public String toString() {
        String res = "";
        for (String s : this.tasksBuffer) {
            res += String.format("%s->", s);
        }
        return res;
    }
}
