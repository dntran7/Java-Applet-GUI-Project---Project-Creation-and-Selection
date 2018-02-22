import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class SelectPanel extends JPanel				//SelectPanel class extends JPanel defined in javax.swing package
{
	private Vector projectList = new Vector(); 			//a list of project objects
	private Vector selectedprojects = new Vector();
	private JList availableprojects;
	private JList selectedproj;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JPanel top =new JPanel();
	private JPanel bottom;
	private DefaultListModel modela;
	private String projecttexts;
	private ButtonListener clickthebuttonadd = new ButtonListener();//declaring a buttonlistener
	private JButton add;
	private JButton remove;
	private int numberofselectedprojs;
	private JLabel numofselectproj;
	
	public SelectPanel(Vector projectList)		//create SelectPanel GUI with jpanels within jpanels
	{
		numberofselectedprojs = 0;
		numofselectproj = new JLabel("The total number of selected projects:"+ numberofselectedprojs);
		add=new JButton("Add");
		remove=new JButton("Remove");
		top =new JPanel();
		bottom = new JPanel();
		this.projectList = projectList;
		availableprojects = new JList(projectList);		//creating jlist of available projects
		selectedproj = new JList(selectedprojects);//creating jlist of selected projects
		scrollPane1 = new JScrollPane(availableprojects);
		scrollPane2 = new JScrollPane(selectedproj);
		
		//creating 2 different jpanels to easily lay out buttons and jlist positions
		JPanel pl = new JPanel();	
		JPanel butt = new JPanel();  
		
		//butt JPanel is middle row with 2 buttons
		butt.setLayout(new GridLayout(1,2));
		butt.add(add);
		butt.add(remove);
		
		//top and bottom Jlists are put in two separate jpanels to set Border titles easily
		top.setLayout(new BorderLayout());
		bottom.setLayout(new BorderLayout());
		Border compound = BorderFactory.createLineBorder(Color.black);
		scrollPane1.setBorder((BorderFactory.createTitledBorder(compound, "Available project(s)",TitledBorder.ABOVE_TOP, TitledBorder.BELOW_TOP)));
		scrollPane2.setBorder((BorderFactory.createTitledBorder(compound, "Selected project(s)",TitledBorder.ABOVE_TOP, TitledBorder.BELOW_TOP)));
		top.add(BorderLayout.CENTER,scrollPane1);
		bottom.add(BorderLayout.CENTER,scrollPane2);
		
		//adding top, button middle, and bottom Jpanels together in gridlayout of pl
		pl.setLayout(new GridLayout(3,1));
		pl.add(top);
		pl.add(butt);	
		pl.add(bottom);
		
		//pl JPanel is then put in the actual SelectPanel(BorderLayout) in the center
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER,pl);
		add(BorderLayout.SOUTH,numofselectproj);//shows number of selected ptojects in the south section
		
		//registering Button LIstener and setting action command to distinguish 2 buttons when clicked
		add.addActionListener(clickthebuttonadd);
		remove.addActionListener(clickthebuttonadd);
		add.setActionCommand("add");
		remove.setActionCommand("remove");
	}
	
	//updates project list after a new project is added in the createpanel through updateUI
	public void updateProjectList()
	{
		availableprojects.updateUI();
		updateUI();
	}
	
	//where the code happens for BUtton LIstener - what happens when buttons are clicked
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			//if user clicked add and have selected something from available projects, play code below
			if(a.getActionCommand().equals("add"))
			{
				if(!availableprojects.isSelectionEmpty())
				{
					selectedprojects.addElement(availableprojects.getSelectedValue().toString());
					selectedproj.updateUI();		//updates JList after new element has been added in Vector
					numberofselectedprojs++;		//increment number of selected projects to show in jlabel
					numofselectproj.setText("The total number of selected projects:" + numberofselectedprojs);;
					numofselectproj.updateUI();   //making sure jlabel is updated
					updateUI();
				}
			}
			
			//if user clicked remove and have selected something from selected projects, play code below
			else if (a.getActionCommand().equals("remove"))
			{
				if (!selectedproj.isSelectionEmpty()) {
					Object an = selectedproj.getSelectedValue();
					selectedprojects.remove(an);
					selectedproj.updateUI(); //updates Jlist after new element has been added to vector
					selectedproj.clearSelection();	//clears selection after deletion
					numberofselectedprojs--;  //decreasing number of selected projects by one to update in jlabel
					numofselectproj.setText("The total number of selected projects:" + numberofselectedprojs);
					;
					numofselectproj.updateUI();  //making sure jlabel is updated
					updateUI();
				}
			}
		}
		//that implements ActionListener interface. 
		//Thus you need to define its actionPerformed method that is supposed to update the number of selected projects 
		//and display it when the "Add" or "Remove" button is pushed.
	}
}
