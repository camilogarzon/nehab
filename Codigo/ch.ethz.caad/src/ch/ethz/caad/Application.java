package ch.ethz.caad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * A simple demo application launching a Processing Applet
 * 
 * Demonstrates the combination of JFrame, JButton, JFileChooser
 * and PApplet.
 * 
 * @author georg munkel
 *
 */
public class Application extends JPanel {
	
	
	
	public static void main(String[] args) {

		//create a frame for the application
		final JFrame frame = new JFrame("PApplet in Java Application");
		
		//make sure to shut down the application, when the frame is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create a panel for the applet and the button panel
		JPanel panel = new JPanel();

		
		//create a panel for the buttons
		JPanel buttonPanel = new JPanel();
		
		
		
		
		
		//create an instance of your processing applet
		final MyApplet applet = new MyApplet();
		
		//start the applet
		applet.init();
		
		
		
		//Buttons
		//create a button labled "create new ball"
		JButton buttonCreate = new JButton("create new ball");
		//assing a tooltip
		buttonCreate.setToolTipText("creates a new ball ");
		//give a name for the command
		//if this is not assigned the actionCommand equals the button label
		buttonCreate.setActionCommand("create ball");
		
		//create a button lable "load file"
		JButton buttonLoad = new JButton("load file");
		buttonLoad.setToolTipText("loads a new background image");
		
		
		//button actions
		
		//the create button is simply linked to the applet
		//the action is executed inside applet.actionPerformed()
		buttonCreate.addActionListener( applet );
		
		
		//this action is implemented NOT in the PApplet on purpose
		//fileDialogues like to crash a PApplet
		//
		//if the JFileChooser returns a valid file
		//loadBgImage() in MyApplet is executed
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				
				//example of an image fileFilter
				//no need to use, just switch it off
			    chooser.setFileFilter(new MyImageFileFilter());
			    
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       
			       //sending the selectedFile to loadBgImage() in the PApplet
			       applet.loadBgImage( chooser.getSelectedFile() );
			       
			    }
			}
			
		});
		
		//store the two buttons in the button panel
		buttonPanel.add(buttonCreate);
		buttonPanel.add(buttonLoad);
		
		//store the applet in panel
		panel.add(applet);
		//store the buttonPanel in panel
		panel.add(buttonPanel);
		
		//store the panel in the frame
		frame.add(panel);
		//assign a size for the frame
		//reading the size from the applet
		frame.setSize(applet.getSize().width, applet.getSize().height +200);
		
		//display the frame
		frame.setVisible(true);

	}



}