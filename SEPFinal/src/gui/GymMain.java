package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.classGui.ClassesGui;
import gui.instructorGui.InstructorsGui;
import gui.memberGui.MembersGui;
import gui.scheduleGui.MScheduleGui;
import gui.scheduleGui.TScheduleGui;
import gym.Gym;
import gym.GymFileAdapter;

public class GymMain
{
private Gym gym;
private GymFileAdapter gfa;
private JFrame frame;
private JPanel menuPanel;
private JPanel mainPanel, mainMenu, membersPanel, instructorsPanel, classesPanel,todaysSchedulePanel, monthSchedulePanel;
private JButton members, instructors, classes, todaysSchedule, monthSchedule;
private MyButtonListener listener;
private Font buttonFont;
private LineBorder lineBorder;
private ClassesGui classGui;
private InstructorsGui instructorGui;
private MembersGui memberGui;
private TScheduleGui tsg;
private MScheduleGui msg;


public GymMain()
{
   gfa = new GymFileAdapter("gym.bin");
   gym = gfa.getGym();
   memberGui = new MembersGui(gym);
   tsg = new TScheduleGui(gym);
   msg = new MScheduleGui(gym);
   classGui = new ClassesGui(gym, tsg, msg);
   instructorGui = new InstructorsGui(gym);
   
   //MainMenu
   
   mainMenu = new JPanel();
   mainMenu.setLayout(new GridLayout(1,5, 10,10));
   lineBorder = new LineBorder(new Color( 100, 100, 100),2);
   mainMenu.setBorder(lineBorder);
   
   //MainMenuButton
   
   listener = new MyButtonListener();
   buttonFont = new Font("Serif", Font.BOLD,20);
   
   members = new JButton("Members");
   members.addActionListener(listener);
   members.setBorder(new EmptyBorder(10,10,10,10));
   members.setFont(buttonFont);
   
   instructors = new JButton("Instructors");
   instructors.addActionListener(listener);
   instructors.setToolTipText("Instructors");
   instructors.setBorder(new EmptyBorder(10,10,10,10));
   instructors.setFont(buttonFont);
   
   classes = new JButton("Classes");
   classes.addActionListener(listener);
   classes.setToolTipText("Classes");
   classes.setBorder(new EmptyBorder(10,10,10,10));
   classes.setFont(buttonFont);
   
   todaysSchedule = new JButton("Today큦 Schedule");
   todaysSchedule.addActionListener(listener);
   todaysSchedule.setToolTipText("Today큦 Schedules");
   todaysSchedule.setBorder(new EmptyBorder(10,10,10,10));
   todaysSchedule.setFont(buttonFont);
   
   monthSchedule = new JButton("Month큦 Schedule");
   monthSchedule.addActionListener(listener);
   monthSchedule.setToolTipText("Month큦 Schedule");
   monthSchedule.setBorder(new EmptyBorder(10,10,10,10));
   monthSchedule.setFont(buttonFont);
   
   //add button to main menu
   mainMenu.add(members);
   mainMenu.add(instructors);
   mainMenu.add(classes);
   mainMenu.add(todaysSchedule);
   mainMenu.add(monthSchedule);
   
   //Menu Panels
   membersPanel = memberGui.getMainPanel();
   instructorsPanel = instructorGui.getMainPanel();  
   classesPanel = classGui.getMainPanel();  
   todaysSchedulePanel = tsg.getMainPanel();
   monthSchedulePanel = msg.getMainPanel();
   
   
   membersPanel.setVisible(true);
   instructorsPanel.setVisible(false);
   classesPanel.setVisible(false);
   todaysSchedulePanel.setVisible(false);
   monthSchedulePanel.setVisible(false);
   
   menuPanel = new JPanel();
   menuPanel.add(membersPanel);
   menuPanel.add(instructorsPanel);
   menuPanel.add(classesPanel);
   menuPanel.add(todaysSchedulePanel);
   menuPanel.add(monthSchedulePanel);
   
   //MainPanel
   mainPanel = new JPanel();
   mainPanel.setLayout(new BorderLayout());
   mainPanel.add(mainMenu, BorderLayout.NORTH);
   mainPanel.add(menuPanel, BorderLayout.CENTER);
  
   //Frame
   
   frame = new JFrame("VIAfit");
   frame.setSize(1024, 768);
   frame.setVisible(true);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setLocationRelativeTo(null);
   frame.add(mainPanel);
   frame.setResizable(false);
}

private class MyButtonListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource() == members)
      {
         membersPanel.setVisible(true);
         instructorsPanel.setVisible(false);
         classesPanel.setVisible(false);
         todaysSchedulePanel.setVisible(false);
         monthSchedulePanel.setVisible(false);      
      }
      
      if(e.getSource() == instructors)
      {
         membersPanel.setVisible(false);
         instructorsPanel.setVisible(true);
         classesPanel.setVisible(false);
         todaysSchedulePanel.setVisible(false);
         monthSchedulePanel.setVisible(false);      
      }
      if(e.getSource() == classes)
      {
         membersPanel.setVisible(false);
         instructorsPanel.setVisible(false);
         classesPanel.setVisible(true);
         todaysSchedulePanel.setVisible(false);
         monthSchedulePanel.setVisible(false);      
      }
      if(e.getSource() == todaysSchedule)
      {
         tsg.updateTscheduleTable();
         membersPanel.setVisible(false);
         instructorsPanel.setVisible(false);
         classesPanel.setVisible(false);
         todaysSchedulePanel.setVisible(true);
         monthSchedulePanel.setVisible(false);      
      }
      if(e.getSource() == monthSchedule)
      {
        
         membersPanel.setVisible(false);
         instructorsPanel.setVisible(false);
         classesPanel.setVisible(false);
         todaysSchedulePanel.setVisible(false);
         monthSchedulePanel.setVisible(true);      
      }
   }
   
}

public static void main(String[]args)
{
   new GymMain();
}
}
