
import java.util.Vector;




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
    boolean showStartBtn;
    boolean showStopBtn;
    Vector<String> producersOutput;
    Vector<String> consumersOutput;
    long solvedTasks;
    int barColor;
    int queuedProducts;
    
    public Model() {
        this.resetModel();
    }
    
    public void resetModel() {
        this.numProducers = 1;
        this.numConsumers = 1;
        this.bufferSize = 1;
        this.sleepConsumers = 0;
        this.sleepProducers = 0;
        this.validInput = true;
        this.showStartBtn = true;
        this.showStopBtn = false;
        this.producersOutput = new Vector();
        this.consumersOutput = new Vector();
        this.solvedTasks = 0;
        this.barColor = 0x1b4484;
        this.queuedProducts = 0;
    }
    
    
}
