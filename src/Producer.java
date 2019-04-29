
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */
public class Producer extends Thread{
    private final int id;
    private final Warehouse w;
    private final long sleepTime;
    private final Controller controller;
    private boolean runThreads;
    
    public Producer(int id, Warehouse w, long sleep, Controller controller) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
        this.controller = controller;
        this.runThreads = true;
    }
    
    @Override
    public void run() {
        while(this.runThreads) {
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                continue;
            }            
            String product = this.createProduct();
            w.produce(product);
            String output = String.format("Productor %d produjo %s", id, product);
            this.controller.updateProducersOutput(output);
        }
        //System.out.println("producer out: " + this.id);
    }
    
    public String createProduct(){
        Random r = new Random();
        char[] symbol = {'*', '/', '+', '-'};
        
        return "(" + symbol[r.nextInt(4)] + " " + r.nextInt(10) + " " + r.nextInt(10) + ")";
    }
    
    public void stopThread() {
        this.runThreads = false;
    }
}
