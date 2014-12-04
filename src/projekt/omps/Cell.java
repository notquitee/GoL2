package projekt.omps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by not_quite on 2014-12-04.
 */
public class Cell extends JPanel {

    private Color defaultBackground = Color.WHITE;
    private Color excitedBackground = Color.BLACK;
    public boolean isClicked = false;

    public Cell() {
        setBackground(defaultBackground);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isClicked)
                    setBackground(defaultBackground);
                else
                    setBackground(excitedBackground);
                isClicked = !isClicked;
            }
        });

    }
    public void refresh(){
        if (!isClicked)
            setBackground(defaultBackground);
        else
            setBackground(excitedBackground);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}
