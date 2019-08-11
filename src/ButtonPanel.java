import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


class ButtonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
    private Canvas canvas;
    private JFileChooser fileChooser;

    private JButton rectangleBtn;
    private JButton circleBtn;
    private JButton triangleBtn;
    private JButton fill;
    private JButton undo;
    private JButton redo;
    private JButton sortSize;
    private JButton save;
    private JButton load;
	

	ButtonPanel(Canvas canvas) {
		this.canvas = canvas;
        super.setLayout(new GridLayout(9,1));
        // Init buttons
        rectangleBtn = new JButton("Rectangle");
        this.circleBtn = new JButton("Circle");
        this.triangleBtn = new JButton("Triangle");
        this.fill = new JButton("Fill Shape");
        this.undo = new JButton("Undo");
        this.redo = new JButton("Redo");
        this.sortSize = new JButton("Sort by Size");
        this.save = new JButton("Save Picture");
        this.load = new JButton("Load Picture");
        // Init action listeners
        this.rectangleBtn.addActionListener(this);
        this.circleBtn.addActionListener(this);
        this.triangleBtn.addActionListener(this);
        this.fill.addActionListener(this);
        this.circleBtn.addActionListener(this);
        this.undo.addActionListener(this);
        this.redo.addActionListener(this);
        this.sortSize.addActionListener(this);
        this.save.addActionListener(this);
        this.load.addActionListener(this);
        // Add Buttons
        super.add(rectangleBtn);
        super.add(circleBtn);
        super.add(triangleBtn);
        super.add(fill);
        super.add(undo);
        super.add(redo);
        super.add(sortSize);
        super.add(save);
        super.add(load);
    }
    

    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.rectangleBtn) {
            this.canvas.setCurrBrush(BrushOptions.Rectangle);
        } else if (e.getSource() == this.circleBtn) {
            this.canvas.setCurrBrush(BrushOptions.Cirlce);
        } else if (e.getSource() == this.triangleBtn) {
            this.canvas.setCurrBrush(BrushOptions.Triangle);
        } else if (e.getSource() == this.fill) {
            this.fill();
        } else if (e.getSource() == this.undo) {
            this.undo();
        } else if (e.getSource() == this.redo) {
            this.redo();
        } else if (e.getSource() == this.sortSize) {
            this.canvas.sort();
        } else if (e.getSource() == this.save) {
            this.save();
        } else if (e.getSource() == this.load) {
            this.load();
        }	
    }


	private void fill() {
        if (this.canvas.getIsFilled()) {
            fill.setBackground(null);
            canvas.setIsFilled(false);
        } else {
            canvas.setIsFilled(true);
            fill.setBackground(Color.YELLOW);
        }
    }


    private void load() {
        // Set filter
        var filter = new FileNameExtensionFilter("SeanPic file", new String[] {"SeanPic"});
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(filter);
        // Open file chooser dialog
        if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                if (this.fileChooser.getSelectedFile() == null) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Wrong File Type");
                    throw new RuntimeException("Wrong File Type");
                }
                var fileInputStream = new FileInputStream(this.fileChooser.getSelectedFile());
                var objectInputStream = new ObjectInputStream(fileInputStream);
                ShapeList instance = (ShapeList)objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                this.canvas.setShapeList(instance);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException("File Could Not be Found");
            }
        }
    }


    private void redo() {
        if (this.canvas.getRedoList().getSize() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "All Shapes Re-Added");
            throw new RuntimeException("There is Nothing Else to Add");
        }
        this.canvas.redo();
    }


    private void save() {
        this.fileChooser = new JFileChooser();
        if (this.fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                var fileName = (this.fileChooser.getSelectedFile() + ".SeanPic");
                var fileOutputStream = new FileOutputStream(fileName);
                var objOutputStream = new ObjectOutputStream(fileOutputStream);
                objOutputStream.writeObject(this.canvas.getList());
                objOutputStream.close();
                fileOutputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException("File Could Not be Saved");
            }
        }
    }


    private void undo() {
        if (this.canvas.getList().getSize() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot Undo from Empty Picture");
            throw new RuntimeException("Cannot Undo from Empty Picture");
        }
        this.canvas.undo();
    }
}
