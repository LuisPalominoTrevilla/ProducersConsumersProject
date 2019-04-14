
import javax.swing.ListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */
public class Model {
    int numProducers;
    int numConsumers;
    int bufferSize;
    long sleepProducers;
    long sleepConsumers;
    boolean runThreads;
    boolean validInput;
    
    public Model() {
        this.numProducers = 1;
        this.numConsumers = 1;
        this.bufferSize = 1;
        this.sleepConsumers = 0;
        this.sleepProducers = 0;
        this.runThreads = false;
        this.validInput = true;
    }
    
    
}
