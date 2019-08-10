/**
 * overall container for simple paint.
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


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
        // Set look and feel to Nimbus
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
		} catch (Exception e) {
			System.err.println("Couldn't use the system look and feel: " + e.toString());
		}
    }
}
