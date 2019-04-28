
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luispalomino
 */
public class ProgressBar extends JPanel{
    private Model model;
    private int parentHeight;
    private int parentWidth;
    private int numCells;
    private Color barColor;
    
    public ProgressBar(Model model, int parentHeight, int parentWidth) {
        this.model = model;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.barColor = new Color(this.model.barColor);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.barColor);
        this.paintTicks(g);
        this.fillTicks(g);
    }
            
    public void paintTicks(Graphics g) {
        g.drawRect(0, 0, this.parentWidth, this.parentHeight);
        int cellSpace = (this.numCells == 0)? this.parentWidth : (int) Math.round((double) this.parentWidth / this.numCells);
        for (int i = 1; i < this.numCells; i++) {
            int x = cellSpace * i;
            g.drawLine(x, 0, x, this.parentHeight);
        }
    }
    
    public void fillTicks(Graphics g) {
        int cellSpace = (this.numCells == 0)? this.parentWidth : (int) Math.round((double) this.parentWidth / this.numCells);
        for (int i = 0; i < this.model.queuedProducts; i++) {
            int x = cellSpace * i;
            g.fillRect(x, 0, cellSpace, this.parentHeight);
        }
    }
    
    public void update() {
        this.numCells = this.model.bufferSize;
        this.repaint();
    }
}
