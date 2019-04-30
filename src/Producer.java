
import java.util.ArrayList;
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
    private final ArrayList<Character> operators;
    private int minVal;
    private int maxVal;
    
    public Producer(int id, Warehouse w, long sleep, Controller controller, ArrayList<Character> operators, int minVal, int maxVal) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
        this.operators = operators;
        this.controller = controller;
        this.runThreads = true;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
    
    @Override
    public void run() {
        while(this.runThreads) {
            String product = this.createProduct();
            w.produce(product, id);
<<<<<<< HEAD
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                continue;
            }            
            
=======
            
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                
            }
>>>>>>> a72a94ffc5b3e827542fcbfa207dd749d562db54
        }
    }
    
    public String createProduct(){
        Random r = new Random();
        return "(" + operators.get(r.nextInt(operators.size())) + " " + (r.nextInt(maxVal-minVal+1) + minVal) + " " + (r.nextInt(maxVal-minVal+1) + minVal) + ")";
    }
    
    public void stopThread() {
        this.runThreads = false;
    }
}
