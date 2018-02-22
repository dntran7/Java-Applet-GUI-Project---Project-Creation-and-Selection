import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreatePanel extends JPanel		//extends JPanel defined in the javax.swing package
{
	private Vector<String> projectList; 			//a list of project objects
	private SelectPanel selectPanel; //an obj of SelectPanel
	private JTextField onef;
	private JTextField twof;
	private JTextField threef;
	private JTextArea projectlisttext;
	private String settextforpl="";
	private JLabel errormessage;
	
	public CreatePanel(Vector projectList, SelectPanel selectPanel)
	{
		this.projectList = projectList;
		this.selectPanel = selectPanel;
		//sets layout of createpanel
		setLayout(new GridLayout(1,2));
		
		//creating multiple jpanels to se later to layer on top of each oter
		projectlisttext =new JTextArea("No Project");
		JPanel center = new JPanel();	// the Jpanel that is the first column of create panel - on the left
		JPanel tf1 = new JPanel();		//column 1 for jlabels declared below one,two, three
		JPanel tf2 = new JPanel();		//column 2 for jtextfields, onef,twof,threef declared below
		JPanel butt = new JPanel();		//panel for button
		JButton actualbutt = new JButton("Create a project");
		errormessage = new JLabel("");
		center.setLayout(new GridLayout(3,1,0,0));
		
		//Section where user types in project title number and location
		JLabel one = new JLabel("Project Title");
		JLabel two = new JLabel("Project Number");
		JLabel three = new JLabel("Project Location");
		onef = new JTextField();
		twof = new JTextField();
		threef = new JTextField();
		
		//adding jlabels
		tf1.setLayout(new GridLayout(3,1));
		tf1.add(one);
		tf1.add(two);
		tf1.add(three);
		
		//adding jtextfields
		tf2.setLayout(new GridLayout(3,1));
		tf2.add(onef);
		tf2.add(twof);
		tf2.add(threef);
		
		//creating a tf3 jpanel to combine both tf1 and tf2 to add to middle row of center
		JPanel tf3 = new JPanel();
		tf3.setLayout(new GridLayout(1,2));
		tf3.add(tf1);
		tf3.add(tf2);
		
		
		//add tf3 to center along with error message on top and adding button
		butt.add(actualbutt);
		center.add(errormessage);
		center.add(tf3);
		center.add(butt);
		
		//combining all of them together on the left of createpanel, and then add a scrollpane for the text area on the right
		add(center);
		JScrollPane projectlisttexts = new JScrollPane(projectlisttext);
		add(projectlisttexts);
		
		//registering button listener
		actualbutt.addActionListener(new ButtonListener());
	}
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			//setting error messages format as red
			errormessage.setForeground(Color.red);
			
			//what to show if not all fields are filled in
			if (onef.getText().length()==0 ||twof.getText().length()==0||threef.getText().length()==0)
			{
				errormessage.setText("Please enter all fields");
			}
			
			//what to show if checkInteger (method described below) does not return true
			else if (!checkInteger(twof.getText()))
			{
				
				errormessage.setText("Please enter an integer for the project number");
			}
			
			//what to show if none of previous errors showed up
			else
			{	
				//getting user input
				String title = onef.getText();
				int num  = Integer.parseInt(twof.getText());
				String location = threef.getText();
				
				//creating new project based on user input
				Project newproj = new Project();
				newproj.setProjLocation(location);
				newproj.setProjNumber(num);
				newproj.setProjTitle(title);
				
				//since tostring method formats like /n and /t isn't recognized by vector, I took a roundabout way to display the new projects in available projects jlist
				String gettingmethod = "Project Title: " + newproj.getProjTitle()
	                    + ", Project Number: " + newproj.getProjNumber()
	                    + ", Project Location: " + newproj.getProjLocation();
				projectList.add(gettingmethod);  //add string to vector
				selectPanel.updateProjectList();  //updates the available project jlist on selectpanel
				
				//adds project to jtextarea on createpanel
				settextforpl += newproj.toString(); 	
				projectlisttext.setText(settextforpl);
				projectlisttext.updateUI(); //updates jtextarea to make sure it reflects the changes
				
				//empties out the textfields so user can input something else
				errormessage.setText("Project added");
				onef.setText("");
				twof.setText("");
				threef.setText("");
			}
		}
		
		//method uses the try catch method to make sure user entered an integer in the project number textfield
		public boolean checkInteger(String text ) 
		{
		    try 
		    {
		        Integer.parseInt( text );
		        return true;
		    }
		    catch( Exception e ) 
		    {
		        return false;
		    }
		}
		
	}
}

