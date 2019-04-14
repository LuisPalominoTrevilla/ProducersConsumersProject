
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
    
    public Controller () {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start":
                System.out.println("Start clicked");
                break;
            case "Stop":
                System.out.println("Stop clicked");
                break;
        }
    }
}
