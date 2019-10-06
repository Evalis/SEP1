package gui.scheduleGui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

public class MScheduleGui
{
   private JPanel mainPanel, topPanel, bottomPanel, navPanel;
   private Gym gym;
   private JButton checkMember,pre, next;
   private MyButtonListener listener;
   private JLabel title, monthLabel;
   private Font font;

   private GymFileAdapter gfa;
   private JTable tscheduleTable;
   private DefaultTableModel dtm;
   private JScrollPane tscheduleTableScroll;
   private String[] tscheduleTableColums;
   private CheckMemberGui checkMemberGui;
   private MScheduleGui msg;
   ArrayList<TimeSlot> list;
   private LocalDate date;

   public MScheduleGui(Gym gym)
   {
      this.gym = gym;
      gfa = new GymFileAdapter("gym.bin");
      listener = new MyButtonListener();
      font = new Font("Serif", Font.PLAIN, 20);
      msg = this;
      date = LocalDate.now();

      title = new JLabel("Month´s Schedule", SwingConstants.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 25));

      // top panel

      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(2, 1, 5, 5));
      topPanel.add(title);

      // navigation panel
      navPanel = new JPanel(new GridLayout(1, 3, 5, 5));

      monthLabel = new JLabel("", SwingConstants.CENTER);
      monthLabel.setFont(new Font("Serif", Font.BOLD, 20));
      monthLabel.setText(date.getMonth().toString() + ", " + date.getYear());

      pre = new JButton("Previous");
      pre.addActionListener(listener);
      pre.setToolTipText("Previous Month");
      pre.setBorder(new EmptyBorder(10, 10, 10, 10));
      pre.setFont(font);

      next = new JButton("Next");
      next.addActionListener(listener);
      next.setToolTipText("Next Month");
      next.setBorder(new EmptyBorder(10, 10, 10, 10));
      next.setFont(font);

      navPanel.add(pre);
      navPanel.add(monthLabel);
      navPanel.add(next);

      topPanel.add(navPanel);

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
            .setPreferredScrollableViewportSize(new Dimension(1000, 485));
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
      updateMscheduleTable();

   }

   public JPanel getMainPanel()
   {

      return mainPanel;
   }

   public void updateMscheduleTable()

   {
      list = gym.getSchedule().getMonthSchedule(date);

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
                  checkMemberGui = new CheckMemberGui(gym, gfa, null, msg);
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
         if (e.getSource() == pre)
         {
            date = date.minusMonths(1);
            updateMscheduleTable();
            monthLabel.setText(date.getMonth().toString() + ", " + date.getYear());
         }
         if (e.getSource() == next)
         {
            date = date.plusMonths(1);
            updateMscheduleTable();
            monthLabel.setText(date.getMonth().toString() + ", " + date.getYear());
         }
      }
   }
}
