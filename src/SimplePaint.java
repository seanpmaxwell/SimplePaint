/**
 * overall container for simple paint.
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JColorChooser;


class SimplePaint extends JFrame {

    private static final long serialVersionUID = 1L;


    SimplePaint() {
        super("Sean's Paint");
    }


    void start() {
        // Add subcomponents
        var colorChooser = new JColorChooser();
        var canvas = new Canvas(colorChooser);
        var btnPanel = new ButtonPanel(canvas);
        super.add(btnPanel, BorderLayout.WEST);
        super.add(canvas);
        super.add(colorChooser, BorderLayout.SOUTH);
        // Intiailize parent container layout
        super.setBounds(0, 0, 1500, 900);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}
