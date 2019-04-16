
import java.util.LinkedList;
import java.util.Queue;

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
    private Model model;
    private View view;
    
    public Warehouse(int bufferSize, Model model, View view) {
        this.tasksBuffer = new LinkedList<>();
        this.bufferCapacity = bufferSize;
        this.model = model;
        this.view = view;
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
        this.model.queuedProducts = this.tasksBuffer.size();
        this.view.updateProgressBar();
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
        this.model.queuedProducts = this.tasksBuffer.size();
        this.view.updateProgressBar();
        notify();
    }
}
