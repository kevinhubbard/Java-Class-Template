package net.kevinjr;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui {
	String className, extendString;
	boolean mainClass;
	JFrame frame = new JFrame("Create a Class");
	JPanel panel = new JPanel();
	NamePanel classNamePanel = new NamePanel();
	CbPanel checkBoxPanel = new CbPanel();
	TfPanel textFieldPanel = new TfPanel();
	BtnPanel addBtnPanel = new BtnPanel();
	ArrayList<String> importList = new ArrayList<String>();
	ArrayList<String> implementList = new ArrayList<String>();
	ClassConstructor cc = new ClassConstructor();

	public Gui() {
		panel.add(classNamePanel);
		panel.add(checkBoxPanel);
		panel.add(textFieldPanel);
		panel.add(addBtnPanel);
		JButton create = new JButton("Create");
		panel.add(create);
		addBtnPanel.importBtn.addActionListener(new AddImport());
		addBtnPanel.extendBtn.addActionListener(new AddExtend());
		addBtnPanel.implementBtn.addActionListener(new AddImplement());
		checkBoxPanel.importCb.addActionListener(new ImportCbListener());
		checkBoxPanel.extendCb.addActionListener(new ExtendCbListener());
		checkBoxPanel.implementCb.addActionListener(new ImplementCbListener());
		create.addActionListener(new SubmitListener());
		panel.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(330, 225);
		frame.setResizable(false);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((dim.width/2)-(frame.getWidth()/2), (dim.height/2)-(frame.getHeight()/2));
		frame.setVisible(true);
	}

	public void clearScreen() {
		classNamePanel.classNameTf.setText("");
		textFieldPanel.importTf.setText("");
		textFieldPanel.extendTf.setText("");
		textFieldPanel.implementTf.setText("");
		checkBoxPanel.mainCb.setSelected(false);
		checkBoxPanel.importCb.setSelected(false);
		checkBoxPanel.extendCb.setSelected(false);
		checkBoxPanel.implementCb.setSelected(false);
		className = null;
		mainClass = false;
		importList.clear();
		extendString = null;
		implementList.clear();
		textFieldPanel.importTf.setEnabled(false);
		addBtnPanel.importBtn.setEnabled(false);
		textFieldPanel.implementTf.setEnabled(false);
		addBtnPanel.implementBtn.setEnabled(false);
	}
/*
	public boolean classNameCheck() {
		boolean testPass = false;
		className = classNamePanel.classNameTf.getText();
		className = className.replaceAll("\\s", "");
		if (className.length() == 0) {
			JOptionPane.showMessageDialog(frame, "Invalid Class Name.");
		} else {
			className = className.substring(0,1).toUpperCase() + className.substring(1);
			testPass = true;
		}
		return testPass;
	}*/
//CHECKBOX LISTENERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public class ImportCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.importCb.isSelected()) {
				textFieldPanel.importTf.setEnabled(true);
				addBtnPanel.importBtn.setEnabled(true);
			} else {
				textFieldPanel.importTf.setEnabled(false);
				addBtnPanel.importBtn.setEnabled(false);
			}
		}
	}

	public class ExtendCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.extendCb.isSelected()) {
				textFieldPanel.extendTf.setEnabled(true);
				addBtnPanel.extendBtn.setEnabled(true);
			} else {
				textFieldPanel.extendTf.setEnabled(false);
				addBtnPanel.extendBtn.setEnabled(false);
			}
		}
	}

	public class ImplementCbListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkBoxPanel.implementCb.isSelected()) {
				textFieldPanel.implementTf.setEnabled(true);
				addBtnPanel.implementBtn.setEnabled(true);
			} else {
				textFieldPanel.implementTf.setEnabled(false);
				addBtnPanel.implementBtn.setEnabled(false);
			}
		}
	}
//ADD BUTTON LISTENERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public class AddImport implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			importList.add(textFieldPanel.importTf.getText());
			textFieldPanel.importTf.setText("");
			//~~~VALIDATE INPUT~~~~//
			
		}
	}

	public class AddExtend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			extendString = textFieldPanel.extendTf.getText();
			System.out.println("extendString is " + extendString);
			

			if (cc.validExtend(extendString) == false ) {
				JOptionPane.showMessageDialog(frame, "Invalid Extend Name.");
			}



			textFieldPanel.extendTf.setEnabled(false);
			addBtnPanel.extendBtn.setEnabled(false);
			//~~~VALIDATE INPUT~~~~//
		}
	}

	public class AddImplement implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			implementList.add(textFieldPanel.implementTf.getText());
			textFieldPanel.implementTf.setText("");
			//~~~VALIDATE INPUT~~~~//
		}
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	public class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			className = classNamePanel.classNameTf.getText();
			if (cc.validClassName(className)) {
				cc.generateMainClass(checkBoxPanel.mainCb.isSelected());
				System.out.println(cc.returnS());
				clearScreen();
			} else {
				JOptionPane.showMessageDialog(frame, "Invalid Class Name.");
			}
		}
	}
}