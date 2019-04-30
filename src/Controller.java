    
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
        boolean producersValValid = this.model.minProducerValue >= 0 && this.model.minProducerValue <= 9
                && this.model.maxProducerValue >= 0 && this.model.maxProducerValue <= 9 &&
                this.model.minProducerValue <= this.model.maxProducerValue;
        if (!producersValValid) {
            this.model.errorMessage = "Los valores de operaciones no son correctos";
            return false;
        }
        boolean producersQuantityValid = this.model.numProducers >= 1 &&
                this.model.numProducers <= 10;
        if (!producersQuantityValid) {
            this.model.errorMessage = "El número de productores es inválido";
            return false;
        }
        boolean consumersQuantityValid = this.model.numConsumers >= 1 &&
                this.model.numConsumers <= 10;
        if (!consumersQuantityValid) {
            this.model.errorMessage = "El número de consumidores es inválido";
            return false;
        }
        boolean producersSleepValid = this.model.sleepProducers >= 0 &&
                this.model.sleepProducers <= 10000;// 10 seconds
        if (!producersSleepValid) {
            this.model.errorMessage = "El tiempo de dormir de productores es inválido";
            return false;
        }
        boolean consumersSleepValid = this.model.sleepConsumers >= 0 &&
                this.model.sleepConsumers <= 10000;
        if (!consumersSleepValid) {
            this.model.errorMessage = "El tiempo de dormir de consumidores es inválido";
            return false;
        }
        boolean bufferSizeValid = this.model.bufferSize >= 1 &&
                this.model.bufferSize <= 100;
        if (!bufferSizeValid) {
            this.model.errorMessage = "El tamaño del buffer es inválido";
            return false;
        }
        boolean operatorsAreValid = this.model.operators.size() > 0;
        if(!operatorsAreValid){
            this.model.errorMessage = "No has seleccionado operadores";
            return false;
        }
        
        return true;
    }
    
    public void startThreads() {
        this.producers = new Producer[this.model.numProducers];
        this.consumers = new Consumer[this.model.numConsumers];
        this.warehouse = new Warehouse(this.model.bufferSize, this.model, this.view, this);

        for (int i = 0; i < this.model.numProducers; i++) {
            this.producers[i] = new Producer(i + 1, this.warehouse, this.model.sleepProducers, this, this.model.operators, this.model.minProducerValue, this.model.maxProducerValue);
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
                this.model.operators = this.view.getOperators();
                this.model.sleepProducers = this.view.getSleepTimeProducers();
                this.model.sleepConsumers = this.view.getSleepTimeConsumers();
                this.model.minProducerValue = this.view.getMinProducerValue();
                this.model.maxProducerValue = this.view.getMaxProducerValue();
                
                if (this.validateData()) {
                    this.model.errorMessage = "";
                    this.model.showStartBtn = false;
                    this.model.showStopBtn = true;
                    this.startThreads();
                }
                this.model.resetModelUI();
                this.view.updateOptions();
                this.view.updateProgressBar();
                this.view.updateProducersView();
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
        if(this.producers == null || this.consumers == null){
            return;
        }
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
