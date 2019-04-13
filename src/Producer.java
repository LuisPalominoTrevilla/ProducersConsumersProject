
import java.util.Random;
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
public class Producer extends Thread{
    private int id;
    private Warehouse w;
    private long sleepTime;
    
    public Producer(int id, Warehouse w, long sleep) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            Random r = new Random(System.currentTimeMillis());
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                continue;
            }
            String[] a = {"A", "B", "C", "D"};
            String producto = a[r.nextInt(4)];
            w.produce(producto);
            // System.out.println(String.format("Productor %d produjo %s", this.id, producto));
        }
    }
}
