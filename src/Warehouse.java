    
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
    private Controller controller;
    
    public Warehouse(int bufferSize, Model model, View view, Controller controller) {
        this.tasksBuffer = new LinkedList<>();
        this.bufferCapacity = bufferSize;
        this.model = model;
        this.view = view;
        this.controller = controller;
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
            }
        }
        String product = this.tasksBuffer.remove();
        this.model.queuedProducts = this.tasksBuffer.size();
        this.view.updateProgressBar();
        this.controller.updateProducersOutput();
        notify();
        
        return product;
    }
    
    public synchronized void produce(String product, int producerId) {
        while (this.isFull()) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        this.tasksBuffer.add(product);
        this.model.queuedProducts = this.tasksBuffer.size();
        String output = String.format("Productor %d produjo %s", producerId, product);
        this.controller.updateProducersOutput(output);	

        this.view.updateProgressBar();
        
        notify();
    }
}
