package gui.memberGui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import gym.Gym;
import gym.GymFileAdapter;
import gym.Member;

public class MembersGui
{
   private JPanel mainPanel, topPanel, bottomPanel;
   private Gym gym;
   private JButton addMember;
   private MyButtonListener listener;
   private JLabel title;
   private Font font;
   private GymFileAdapter gfa;
   private  JTable membersTable;
   private  DefaultTableModel dtm;
   private  JScrollPane membersTableScroll;
   private String [] membersTableColums;
   private AddNewMemberGui addNewMemberGui;
   private MembersGui mg;
   private ArrayList<Member> list;
   
   public MembersGui (Gym gym)
   {
      this.gym = gym;
      gfa = new GymFileAdapter("gym.bin");
      listener = new MyButtonListener();
      font = new Font("Serif",Font.PLAIN, 20);
      mg=this;
      
      title = new JLabel("Members", SwingConstants.CENTER);
      title.setFont(new Font("Serif",Font.BOLD, 20));
      
      //top panel
      
      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1,1,5,5));
      topPanel.add(title);
      
      
      //Member table
      membersTableColums = new String [7];
      membersTableColums[0]= "No";
      membersTableColums[1]= "First Name";      
      membersTableColums[2]= "Last Name";
      membersTableColums[3]= "Address";
      membersTableColums[4]= "Phone";
      membersTableColums[5]= "Email";
      membersTableColums[6]= "Membership";
      
      dtm = new DefaultTableModel(membersTableColums, 0);
      membersTable = new JTable(dtm);
      membersTable.setPreferredScrollableViewportSize(new Dimension(1000,565));
      membersTable.setFillsViewportHeight(true);
      membersTableScroll = new JScrollPane(membersTable);
      membersTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));
      
      //bottom panel
      
      bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1,1, 10,10));
      

      addMember = new JButton("Add New Member");
      addMember.addActionListener(listener);
      addMember.setToolTipText("Add New Member");
      addMember.setBorder(new EmptyBorder(10,10,10,10));
      addMember.setFont(font);
      
      
      bottomPanel.add(addMember);
      
      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(topPanel, BorderLayout.NORTH);
      mainPanel.add(membersTableScroll, BorderLayout.CENTER);
      mainPanel.add(bottomPanel, BorderLayout.SOUTH);
      updateMemberTable();
      
   }

   public JPanel getMainPanel()
   {
     
      return mainPanel;
   }
   
   public void updateMemberTable()
   {
      list = gym.getPersonList().getAllMember();
      
      Object [] [] data = new Object[list.size()][10];
      
      Member temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp= list.get(i);
         
         data[i][0]= i + 1;
         data[i][1]= temp.getFirstName();
         data[i][2]= temp.getLastName();
         data[i][3]= temp.getAddress();
         data[i][4]= temp.getPhone();
         data[i][5]= temp.getEmail();
         data[i][6]= temp.getMembership();
         
      }
      dtm = new DefaultTableModel(data, membersTableColums);
      membersTable.setModel(dtm);
      
   }
   
   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == addMember)
         {

            if(addNewMemberGui == null)//when is pressing add new member first time, it create new instance of the class
            {
               addNewMemberGui = new AddNewMemberGui(gym, gfa, mg);
            }
            else // it takes old instance it will clear the fields and make it visible  
            {
               addNewMemberGui.clearFileds();
               addNewMemberGui.makeVisible();
            }
         }
         
   }
}
}
