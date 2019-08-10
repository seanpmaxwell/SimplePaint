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
        // Initialize subcomponents
        var colorChooser = new JColorChooser();
        var drawPanel = new Canvas(colorChooser);
        var btnPanel = new ButtonPanel(drawPanel);
        super.add(btnPanel, BorderLayout.WEST);
        super.add(drawPanel);
        super.add(colorChooser, BorderLayout.SOUTH);
        // Intiailize parent container layout
        super.setVisible(true);
        super.setBounds(400, 100, 1500, 900);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
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
