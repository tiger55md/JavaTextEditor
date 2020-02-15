package textEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class TextEditorGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea t;
	private JFrame f;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TextEditorGUI pao = new TextEditorGUI();
		
		
	}

	/**
	 * Create the frame.
	 */
	public TextEditorGUI() {
		f = new JFrame("Text.Editor");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch(Exception e) {
			
		}
		t = new JTextArea();
		JMenuBar bar = new JMenuBar();
		JMenu m1 = new JMenu("file");
		JMenuItem mi2 = new JMenuItem("New");
		JMenuItem mi3 = new JMenuItem("Open");
		JMenuItem mi4 = new JMenuItem("Save");
		JMenuItem mi5 = new JMenuItem("Print");
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		JMenu m2 = new JMenu("edit");
		JMenuItem mi6 = new JMenuItem("Copy");
		JMenuItem mi7 = new JMenuItem("Cut");
		JMenuItem mi8 = new JMenuItem("Paste");
		mi6.addActionListener(this);
		mi7.addActionListener(this);
		mi8.addActionListener(this);
		JMenuItem mc = new JMenuItem("Close");
		mc.addActionListener(this);
		
		
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi4);
		m1.add(mi5);
		m2.add(mi6);
		m2.add(mi7);
		m2.add(mi8);
		bar.add(m1);
		bar.add(m2);
		bar.add(mc);
		bar.setVisible(true);
		f.setJMenuBar(bar);
		f.add(t);
		f.setSize(500,500);
		f.setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if( action == "Copy") {
			t.copy();
		}
		else if(action == "Paste") {
			t.paste();
		}
		else if(action == "Cut") {
			t.cut();
		}
		else if(action == "Save") {
			JFileChooser j = new JFileChooser("f: ");
			int r = j.showSaveDialog(null);
			if( r == JFileChooser.APPROVE_OPTION) {
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				try{
					FileWriter fr = new FileWriter(fi, false);
					BufferedWriter wr = new BufferedWriter(fr);
					wr.write(t.getText());
					wr.flush();
					wr.close();
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(f, e1.getMessage());
					
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "User cancelled the action");
			}
		}
		else if(action == "Open") {
			JFileChooser j = new JFileChooser("f: ");
			int r = j.showOpenDialog(null);
			if( r == JFileChooser.APPROVE_OPTION) {
				File fi = new File(j.getSelectedFile().getAbsolutePath());
				try{
					String s1 = "", sl = "";
					FileReader fr = new FileReader(fi);
					BufferedReader wr = new BufferedReader(fr);
					sl = wr.readLine();
					while((s1 = wr.readLine()) != null) {
						sl = sl + "\n" + s1;
					}
					t.setText(sl);
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(f, e1.getMessage());
					
				}
			}
			else {
				JOptionPane.showMessageDialog(f, "User cancelled the action");
			}
		}
		else if(action == "New") {
			t.setText("");
		}
		else if (action == "Close") {
			f.setVisible(false);
		}
	}
}
