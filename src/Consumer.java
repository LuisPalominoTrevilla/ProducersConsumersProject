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
    private final int id;
    private final Warehouse w;
    private final long sleepTime;
    private final Controller controller;
    private boolean runThreads;
    
    public Consumer(int id, Warehouse w, long sleep, Controller controller) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
        this.controller = controller;
        this.runThreads = true;
    }
    
    @Override
    public void run() {
        while(this.runThreads) {
            String product = w.withdrawProduct();
            
            String result;
            try{
                result = "Consumidor " + this.id + " consumió " + product + ", resultado = " + Integer.toString(this.consume(product)); 
            } catch(ArithmeticException ae){
                result = "Consumidor " + this.id + " consumió " + product + " pero arrojó un error por dividir entre 0";
            }
            
            this.controller.updateConsumersOutput(result);
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
    
    public int consume(String product){
        int a = Character.getNumericValue(product.charAt(3));
        int b = Character.getNumericValue(product.charAt(5));
        
        switch(product.charAt(1)){
            case '+':
                return a + b; 
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default: 
                return 0;
        }        
    }
    
    public void stopThread() {
        this.runThreads = false;
    }
}
