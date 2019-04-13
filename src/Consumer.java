
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
public class Consumer extends Thread{
    private int id;
    private Warehouse w;
    private long sleepTime;
    
    public Consumer(int id, Warehouse w, long sleep) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                continue;
            }
            String product = w.consume();
            // System.out.println(String.format("Consumidor %d consumió %s", this.id, product));
        }
    }
}
