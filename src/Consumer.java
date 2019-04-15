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
    private Model model;
    private View view;
    private boolean runThreads;
    
    public Consumer(int id, Warehouse w, long sleep, Model model, View view) {
        this.id = id;
        this.w = w;
        this.sleepTime = sleep;
        this.model = model;
        this.view = view;
        this.runThreads = true;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                continue;
            }
            if (!this.runThreads) break;
            
            String product = w.withdrawProduct();
            
            String result;
            try{
                result = "Consumidor " + this.id + " consumió " + product + ", resultado = " + Integer.toString(this.consume(product)); 
            } catch(ArithmeticException ae){
                result = "Consumidor " + this.id + " consumió " + product + " pero arrojó un error por dividir entre 0";
            }
            
            this.model.consumersOutput.add(result);
            this.model.solvedTasks++;
            this.view.updateConsumersView();
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
