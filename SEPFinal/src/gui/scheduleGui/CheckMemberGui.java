package gui.scheduleGui;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gym.ClassFullException;
import gym.Gym;
import gym.GymFileAdapter;
import gym.Member;
import gym.MemberIsNotPremiumExeption;
import gym.TimeSlot;

public class CheckMemberGui
{
   private JPanel mainPanel, centerPanel;
   private Gym gym;
   private JButton assignMember;
   private MyButtonListener listener;
   private Font font;
   private GymFileAdapter gfa;
   private JTable membersTable;
   private DefaultTableModel dtm;
   private JScrollPane membersTableScroll;
   private JTable membersTable2;
   private DefaultTableModel dtm2;
   private JScrollPane membersTableScroll2;
   private String[] membersTableColums;
   private TScheduleGui tsg;
   private MScheduleGui msg;
   private ArrayList<Member> list;
   private ArrayList<Member> list2;
   private TimeSlot timeSlot;
   private JFrame frame;

   public CheckMemberGui(Gym gym, GymFileAdapter gfa, TScheduleGui tsg, MScheduleGui msg)
   {
      this.gym = gym;
      this.gfa = gfa;
      this.tsg = tsg;
      this.msg = msg;
      // mainPanel
      mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));
      font = new Font("Serif", Font.PLAIN, 20);
      listener = new MyButtonListener();
      // Member table
      membersTableColums = new String[7];
      membersTableColums[0] = "No";
      membersTableColums[1] = "First Name";
      membersTableColums[2] = "Last Name";
      membersTableColums[3] = "Address";
      membersTableColums[4] = "Phone";
      membersTableColums[5] = "Email";
      membersTableColums[6] = "Membership";

      dtm = new DefaultTableModel(membersTableColums, 0);
      membersTable = new JTable(dtm);
      membersTable.setPreferredScrollableViewportSize(new Dimension(1000, 300));
      membersTable.setFillsViewportHeight(true);
      membersTableScroll = new JScrollPane(membersTable);
      membersTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));

      // Member table

      dtm2 = new DefaultTableModel(membersTableColums, 0);
      membersTable2 = new JTable(dtm2);
      membersTable2
            .setPreferredScrollableViewportSize(new Dimension(1000, 300));
      membersTable2.setFillsViewportHeight(true);
      membersTableScroll2 = new JScrollPane(membersTable2);
      membersTableScroll2.setBorder(new EmptyBorder(5, 5, 5, 5));

      centerPanel = new JPanel();
      centerPanel.setLayout(new GridLayout(1, 1, 10, 10));

      assignMember = new JButton("Assign Member");
      assignMember.addActionListener(listener);
      assignMember.setToolTipText("Assign Member");
      assignMember.setBorder(new EmptyBorder(10, 10, 10, 10));
      assignMember.setFont(font);
      
      centerPanel.add(assignMember);

      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(membersTableScroll, BorderLayout.NORTH);
      mainPanel.add(centerPanel, BorderLayout.CENTER);
      mainPanel.add(membersTableScroll2, BorderLayout.SOUTH);

      // Frame
      frame = new JFrame("Assign Member");
      frame.setSize(1000, 750);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);

      frame.add(mainPanel);

      updateMemberTable();

   }

   public void updateMemberTable()
   {
      list = gym.getPersonList().getAllMember();

      Object[][] data = new Object[list.size()][7];

      Member temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp = list.get(i);

         data[i][0] = i + 1;
         data[i][1] = temp.getFirstName();
         data[i][2] = temp.getLastName();
         data[i][3] = temp.getAddress();
         data[i][4] = temp.getPhone();
         data[i][5] = temp.getEmail();
         data[i][6] = temp.getMembership();

      }
      dtm = new DefaultTableModel(data, membersTableColums);
      membersTable.setModel(dtm);

   }

   public void updateMemberTable2(TimeSlot timeSlot)
   {
      this.timeSlot = timeSlot;
      list2 = timeSlot.getMembers();

      Object[][] data = new Object[list2.size()][7];

      Member temp = null;
      for (int i = 0; i < list2.size(); i++)
      {
         temp = list2.get(i);

         data[i][0] = i + 1;
         data[i][1] = temp.getFirstName();
         data[i][2] = temp.getLastName();
         data[i][3] = temp.getAddress();
         data[i][4] = temp.getPhone();
         data[i][5] = temp.getEmail();
         data[i][6] = temp.getMembership();

      }
      dtm2 = new DefaultTableModel(data, membersTableColums);
      membersTable2.setModel(dtm2);

   }

   public void makeVisible()
   {
      frame.setVisible(true);
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == assignMember)
         {
            int index = membersTable.getSelectedRow();

            if (index == -1)
            {
               JOptionPane.showMessageDialog(null,
                     "Please select a member from the top table to assign",
                     "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {

               Member member = list.get(index);
               if (list2.contains(member))
               {
                  JOptionPane.showMessageDialog(null, "Member already assigned",
                        "Error", JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                  try
                  {
                     timeSlot.assignMemberToTimeSlot(member);
                     updateMemberTable2(timeSlot);
                     gfa.saveGym(gym);
                     if(tsg != null)
                     {
                        tsg.updateTscheduleTable();                     
                     }
                     if(msg != null)
                     {
                        msg.updateMscheduleTable();                     
                     }
                  }
                  catch (ClassFullException e1)
                  {

                     JOptionPane.showMessageDialog(null,
                           "Class is full at this time", "Error",
                           JOptionPane.ERROR_MESSAGE);
                  }
                  catch (MemberIsNotPremiumExeption e1)
                  {

                     JOptionPane.showMessageDialog(null,
                           "Member does not have Premium membership", "Error",
                           JOptionPane.ERROR_MESSAGE);
                  }
                  
               }

            }
         }
      }

   }
}
