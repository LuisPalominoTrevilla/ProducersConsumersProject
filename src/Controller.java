/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */
public class Controller {
    
    public Controller () {
        Warehouse w = new Warehouse(5);
        Consumer[] consumers = new Consumer[4];
        Producer[] producers = new Producer[7];
        
        for (int i = 0; i < 4; i++) {
            consumers[i] = new Consumer(i+1, w, 1000);
            consumers[i].start();
        }
        
        for (int i = 0; i < 7; i++) {
            producers[i] = new Producer(i+1, w, 1000);
            producers[i].start();
        }
    }
}
