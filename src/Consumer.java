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
            String product = w.withdrawProduct();
            
            String result;
            try{
                result = "---> Consumidor " + this.id + " consumió " + product + " = " + Integer.toString(this.consume(product)); 
            } catch(ArithmeticException ae){
                result = "---> El consumidor " + this.id + " arrojó un error por dividir entre 0";
                System.out.println("------------------------");
            }
            
            System.out.println(result);
            // System.out.println(String.format("Consumidor %d consumió %s", this.id, product));
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
}
