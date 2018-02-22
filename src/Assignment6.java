// Assignment #: 6
//         Name: Diep Tran
//    StudentID: 1212729529
//      Lecture: MWF 11:50am)
//  Description: The Assignment 6 class creates a Tabbed Pane with
//               two tabs, one for creation and one for
//               selection. and adds it as its Applet content
//               and also sets its size.

import javax.swing.*;

import java.awt.GridLayout;
import java.util.*;

public class Assignment6 extends JApplet
 {
   private int APPLET_WIDTH = 800, APPLET_HEIGHT = 300;
   private JTabbedPane tPane;	
   private CreatePanel createPanel;
   private SelectPanel selectPanel;
   private Vector projectList;
   //The method init initializes the Applet with a Pane with two tabs
   public void init()
    {
	 //list of projects to be used in every panel
	 projectList = new Vector();

	 selectPanel = new SelectPanel(projectList);

     createPanel = new CreatePanel(projectList, selectPanel);
     //create a tabbed pane with two tabs
     tPane = new JTabbedPane();
     tPane.addTab("Project creation", createPanel);
     tPane.addTab("Project selection", selectPanel);
     getContentPane().add(tPane);
     setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
    }
}
