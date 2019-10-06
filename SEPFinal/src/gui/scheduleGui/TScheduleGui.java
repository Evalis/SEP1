package gui.scheduleGui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import gym.Gym;
import gym.GymFileAdapter;
import gym.TimeSlot;

public class TScheduleGui
{
   private JPanel mainPanel, topPanel, bottomPanel;
   private Gym gym;
   private JButton checkMember;
   private MyButtonListener listener;
   private JLabel title;
   private Font font;

   private GymFileAdapter gfa;
   private JTable tscheduleTable;
   private DefaultTableModel dtm;
   private JScrollPane tscheduleTableScroll;
   private String[] tscheduleTableColums;
   private CheckMemberGui checkMemberGui;
   private TScheduleGui tsg;
   ArrayList<TimeSlot> list;

   public TScheduleGui(Gym gym)
   {
      this.gym = gym;
      gfa = new GymFileAdapter("gym.bin");
      listener = new MyButtonListener();
      font = new Font("Serif", Font.PLAIN, 20);
      tsg = this;

      title = new JLabel("Today´s Schedule", SwingConstants.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 25));

      // top panel

      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2, 1, 5, 5));
      topPanel.add(title);
      

      // TSchedule table
      tscheduleTableColums = new String[7];
      tscheduleTableColums[0] = "Date";
      tscheduleTableColums[1] = "Start Time";
      tscheduleTableColums[2] = "End Time";
      tscheduleTableColums[3] = "Class Name";
      tscheduleTableColums[4] = "Instructor";
      tscheduleTableColums[5] = "Max";
      tscheduleTableColums[6] = "Actual";

      dtm = new DefaultTableModel(tscheduleTableColums, 0);
      tscheduleTable = new JTable(dtm);
      tscheduleTable
            .setPreferredScrollableViewportSize(new Dimension(1000, 515));
      tscheduleTable.setFillsViewportHeight(true);
      tscheduleTableScroll = new JScrollPane(tscheduleTable);
      tscheduleTableScroll.setBorder(new EmptyBorder(10, 5, 5, 5));

      // bottom panel

      bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1, 1, 10, 10));

      checkMember = new JButton("Assign Member");
      checkMember.addActionListener(listener);
      checkMember.setToolTipText("Assign Member");
      checkMember.setBorder(new EmptyBorder(10, 10, 10, 10));
      checkMember.setFont(font);

      bottomPanel.add(checkMember);

      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(topPanel, BorderLayout.NORTH);
      mainPanel.add(tscheduleTableScroll, BorderLayout.CENTER);
      mainPanel.add(bottomPanel, BorderLayout.SOUTH);
      updateTscheduleTable();

   }

   public JPanel getMainPanel()
   {

      return mainPanel;
   }

   public void updateTscheduleTable()

   {
      list = gym.getSchedule().getTodaysSchedule();

      Object[][] data = new Object[list.size()][7];

      TimeSlot temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp = list.get(i);

         data[i][0] = temp.getDate();
         data[i][1] = temp.getStartTime();
         data[i][2] = temp.getEndTime();
         data[i][3] = temp.getGymClass().getClassName();
         data[i][4] = temp.getInstructor().getFirstName() + " "
               + temp.getInstructor().getLastName();
         data[i][5] = temp.getGymClass().getMaxNumberOfMembers();
         data[i][6] = temp.getNumberOfMembers();

      }
      dtm = new DefaultTableModel(data, tscheduleTableColums);
      tscheduleTable.setModel(dtm);

   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      
         if (e.getSource() == checkMember)
         {
            int index = tscheduleTable.getSelectedRow();

            if (index == -1)
            {
               JOptionPane.showMessageDialog(null,
                     "Please select a class from the Schedule", "Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {

               if (checkMemberGui == null)
               {
                  checkMemberGui = new CheckMemberGui(gym, gfa, tsg, null);
                  checkMemberGui.updateMemberTable2(list.get(index));
                  checkMemberGui.updateMemberTable();
               }
               else
               {
                  checkMemberGui.updateMemberTable2(list.get(index));
                  checkMemberGui.updateMemberTable();
                  checkMemberGui.makeVisible();
               }
            }
         }
      }
   }
}
