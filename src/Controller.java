
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */ 
public class Controller implements ActionListener {
    
    private ProducerConsumerApp parent;
    private Model model;
    private View view;
    
    private Producer[] producers;
    private Consumer[] consumers;
    private Warehouse warehouse;
    
    public Controller (ProducerConsumerApp parent) {
        this.parent = parent;
        this.view = this.parent.getView();
        this.model = this.parent.getModel();
        
    }
    
    public boolean validateData() {        
        boolean producersQuantityValid = this.model.numProducers >= 1 &&
                this.model.numProducers <= 10;
        boolean consumersQuantityValid = this.model.numConsumers >= 1 &&
                this.model.numConsumers <= 10;
        boolean producersSleepValid = this.model.sleepProducers >= 0 &&
                this.model.sleepProducers <= 10000;// 10 seconds
        boolean consumersSleepValid = this.model.sleepConsumers >= 0 &&
                this.model.sleepConsumers <= 10000;
        boolean bufferSizeValid = this.model.bufferSize >= 1 &&
                this.model.bufferSize <= 100;
        return producersQuantityValid && consumersQuantityValid &&
                producersSleepValid && consumersSleepValid && bufferSizeValid;
    }
    
    public void startThreads() {
        this.producers = new Producer[this.model.numProducers];
        this.consumers = new Consumer[this.model.numConsumers];
        this.warehouse = new Warehouse(this.model.bufferSize, this.model, this.view);
        
        for (int i = 0; i < this.model.numProducers; i++) {
            this.producers[i] = new Producer(i + 1, this.warehouse, this.model.sleepProducers, this);
            this.producers[i].start();
        }
        
        for (int i = 0; i < this.model.numConsumers; i++) {
            this.consumers[i] = new Consumer(i + 1, this.warehouse, this.model.sleepConsumers, this);
            this.consumers[i].start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start":
                this.model.numProducers = this.view.getNumProducers();
                this.model.numConsumers = this.view.getNumConsumers();
                this.model.bufferSize = this.view.getBufferSize();
                this.model.sleepProducers = this.view.getSleepTimeProducers();
                this.model.sleepConsumers = this.view.getSleepTimeConsumers();
                if (!this.validateData()) {
                    this.model.validInput = false;
                } else {
                    this.model.validInput = true;
                    this.model.showStartBtn = false;
                    this.model.showStopBtn = true;
                    this.startThreads();
                }
                this.model.resetModelUI();
                this.view.updateOptions();
                this.view.updateProgressBar();
                this.view.updateConsumersView();
                break;
            case "Stop":
                this.stopThreads();
                this.model.resetModelOptions();
                this.view.updateOptions();
                break;
        }
    }
    
    public void stopThreads(){
        for (Producer producer : this.producers) {
            producer.stopThread();
            producer.interrupt();
        }
        for (Consumer consumer : this.consumers) {
            consumer.stopThread();
            consumer.interrupt();
        }
    }
    
    public synchronized void updateConsumersOutput(String output) {
        this.model.consumersOutput.add(output);
        this.model.solvedTasks++;
        this.view.updateConsumersView();
    }
    
    public synchronized void updateProducersOutput(String output) {
        this.model.producersOutput.add(output);
        this.view.updateProducersView();
    }
}
