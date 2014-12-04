package projekt.omps;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by not_quite on 2014-11-12.
 */
public class Plansza extends JPanel {
    private Cell[][] tablica = new Cell[9][11];
    private boolean[][] nextStep = new boolean[9][11];
    public boolean stop;
    public Counter stepCounter;
    public JToggleButton StartStop;

    public Plansza(){
        stop = false;
        stepCounter = new Counter();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i=0; i<tablica.length; i++) {
            for (int j = 0; j < tablica[i].length; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                Cell cellPane = new Cell();
                Border border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                cellPane.setBorder(border);
                add(cellPane, gbc);
                this.tablica[i][j] = cellPane;
            }
        }
        StartStop = new JToggleButton("Start");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.LINE_START;
        gbc.weightx = 0.0;
        gbc.gridwidth = 5;
        add(StartStop,gbc);
        StartStop.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED)
                    gameOn();
                else if (state == ItemEvent.DESELECTED) {
                    stop = true;
                    stepCounter.clear();
                }
            }
        });

    }
    private void evaluateNextStep() {
        for (int i = 0; i < tablica.length; i++) {
            for (int j = 0; j < tablica[i].length; j++) {
                if (countNeighbours(i, j) < 2 || countNeighbours(i, j) > 3)
                    nextStep[i][j] = false;
                else if (countNeighbours(i, j) == 3)
                    nextStep[i][j] = true;
                else if (countNeighbours(i, j) == 2)
                    nextStep[i][j] = tablica[i][j].isClicked;
            }
        }
    }
    public void step(int steps){
        for (int k=0; k<steps; k++){
            evaluateNextStep();
            setTablica(this.nextStep);
            for (int i = 0; i < tablica.length; i++)
                for (int j = 0; j <tablica[i].length; j++)
                    tablica[i][j].refresh();
            stepCounter.increment();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void gameOn(){
        stop = false;
        while(!stop) {
            step(1);

            /*for (int i=0; i<tablica.length; i++) {
                for (int j = 0; j < tablica[i].length; j++) {
                    if(tablica[i][j].isClicked)
                        System.out.print("x ");
                    else
                        System.out.print("o ");
                }
                System.out.print("\n");
            }
            System.out.println("\n\n");*/
            if (stepCounter.getCount() == 11) {
                stop = true;
            }
        }
    }
    public void changeCellValue(int i, int j){
        tablica[i][j].isClicked = !tablica[i][j].isClicked;
    }

    public int countNeighbours(int w,int k){
        int n=0;
        for (int i=0; i<tablica.length; i++) {
            for (int j = 0; j < tablica[i].length; j++) {
                if (Math.abs(w-i)<2 && Math.abs(k-j)<2 && !(w==i && k==j) && tablica[i][j].isClicked)
                    n++;
            }
        }
        return n;
    }
    public boolean getCellValue(int w,int k){
        return tablica[w][k].isClicked;
    }
    public Cell[][] getTablica(){
        return tablica;
    }
    public void setTablica(boolean[][] newTablica) {
        for (int i = 0; i < newTablica.length; i++)
            for (int j = 0; j <newTablica[i].length; j++)
            this.tablica[i][j].isClicked = newTablica[i][j];
    }

}