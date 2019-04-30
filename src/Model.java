
import java.util.ArrayList;




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
    boolean showStartBtn;
    boolean showStopBtn;
    int minProducerValue;
    int maxProducerValue;
    ArrayList<String> producersOutput;
    ArrayList<String> consumersOutput;
    ArrayList<Character> operators;
    long solvedTasks;
    int barColor;
    int queuedProducts;
    String errorMessage;
    
    public Model() {
        this.resetModelOptions();
        this.resetModelUI();
    }
    
    public void resetModelUI() {
        this.solvedTasks = 0;
        this.barColor = 0x1b4484;
        this.queuedProducts = 0;
        this.minProducerValue = 0;
        this.maxProducerValue = 0;
        this.producersOutput = new ArrayList();
        
        this.consumersOutput = new ArrayList();
    }
    
    public void resetModelOptions() {
        this.numProducers = 1;
        this.numConsumers = 1;
        this.bufferSize = 1;
        this.sleepConsumers = 0;
        this.sleepProducers = 0;
        this.minProducerValue = 0;
        this.maxProducerValue = 9;
        this.showStartBtn = true;
        this.showStopBtn = false;
        this.operators = new ArrayList();

        this.producersOutput = new ArrayList();
        this.consumersOutput = new ArrayList();
    }
}
