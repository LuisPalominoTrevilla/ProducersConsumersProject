
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
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
                this.model.numProducers <= 9;
        boolean consumersQuantityValid = this.model.numConsumers >= 1 &&
                this.model.numConsumers <= 9;
        boolean producersSleepValid = this.model.sleepProducers >= 0 &&
                this.model.sleepProducers <= Long.MAX_VALUE;
        boolean consumersSleepValid = this.model.sleepConsumers >= 0 &&
                this.model.sleepConsumers <= Long.MAX_VALUE;
        boolean bufferSizeValid = this.model.bufferSize >= 1 &&
                this.model.bufferSize <= 100;
        return producersQuantityValid && consumersQuantityValid &&
                producersSleepValid && consumersSleepValid && bufferSizeValid;
    }
    
    public void startThreads() {
        this.producers = new Producer[this.model.numProducers];
        this.consumers = new Consumer[this.model.numConsumers];
        this.warehouse = new Warehouse(this.model.bufferSize);
        
        for (int i = 0; i < this.model.numProducers; i++) {
            this.producers[i] = new Producer(i + 1, this.warehouse, this.model.sleepProducers);
            this.producers[i].start();
        }
        
        for (int i = 0; i < this.model.numConsumers; i++) {
            this.consumers[i] = new Consumer(i + 1, this.warehouse, this.model.sleepConsumers);
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
                    this.startThreads();
                }
                this.view.updateOptions();
                break;
            case "Stop":
                System.out.println("Stop clicked");
                break;
        }
    }
}
