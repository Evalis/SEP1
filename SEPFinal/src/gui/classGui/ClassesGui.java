package gui.classGui;

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

import gui.scheduleGui.MScheduleGui;
import gui.scheduleGui.TScheduleGui;
import gym.Gym;
import gym.GymFileAdapter;
import gym.Class;

public class ClassesGui
{

   private JPanel mainPanel, bottomPanel, topPanel;
   private Gym gym;
   private JButton addClass, addTimeSlot;
   private MyButtonListener listener;
   private JLabel title;
   private Font font;
   private GymFileAdapter gfa;
   private JTable classesTable;
   private DefaultTableModel dtm;
   private JScrollPane classesTableScroll;
   private String[] classesTableColums;
   private AddNewClassGui addNewClassGui;
   private ClassesGui classesGui;
   private AddTimeSlotGui addTimeSlotGui;
   private ArrayList<Class> list;
   private TScheduleGui tsg;
   private MScheduleGui msg;

   public ClassesGui(Gym gym, TScheduleGui tsg, MScheduleGui msg)
   {
      this.gym = gym;
      this.tsg = tsg;
      this.msg = msg;
      gfa = new GymFileAdapter("gym.bin");
      listener = new MyButtonListener();
      font = new Font("Serif", Font.PLAIN, 20);
      classesGui = this;

      title = new JLabel("Classes", SwingConstants.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 20));

      // top panel

      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1, 1, 5, 5));
      topPanel.add(title);

      // Member table

      classesTableColums = new String[3];
      classesTableColums[0] = "No";
      classesTableColums[1] = "Class name";
      classesTableColums[2] = "Max Number Of Members";

      dtm = new DefaultTableModel(classesTableColums, 0);
      classesTable = new JTable(dtm);
      classesTable.setPreferredScrollableViewportSize(new Dimension(1000, 565));
      classesTable.setFillsViewportHeight(true);
      classesTableScroll = new JScrollPane(classesTable);
      classesTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));

      // bottom panel

      bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));

      addClass = new JButton("Add New Class");
      addClass.addActionListener(listener);
      addClass.setToolTipText("Add New Class");
      addClass.setBorder(new EmptyBorder(10, 10, 10, 10));
      addClass.setFont(font);

      addTimeSlot = new JButton("Add Time Slot");
      addTimeSlot.addActionListener(listener);
      addTimeSlot.setToolTipText("Add Time Slot");
      addTimeSlot.setBorder(new EmptyBorder(10, 10, 10, 10));
      addTimeSlot.setFont(font);

      bottomPanel.add(addClass);
      bottomPanel.add(addTimeSlot);

      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(topPanel, BorderLayout.NORTH);
      mainPanel.add(classesTableScroll, BorderLayout.CENTER);
      mainPanel.add(bottomPanel, BorderLayout.SOUTH);
      updateClassTable();

   }

   public JPanel getMainPanel()
   {

      return mainPanel;
   }

   public void updateClassTable()

   {
      list = gym.getClassList().getClassList();

      Object[][] data = new Object[list.size()][10];

      Class temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp = list.get(i);

         data[i][0] = i + 1;
         data[i][1] = temp.getClassName();
         data[i][2] = temp.getMaxNumberOfMembers();

      }
      dtm = new DefaultTableModel(data, classesTableColums);
      classesTable.setModel(dtm);

   }
   
   public void updateBothScheduleTables()
   {
      tsg.updateTscheduleTable();
      msg.updateMscheduleTable();
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == addClass)
         {

            if (addNewClassGui == null)
            {
               addNewClassGui = new AddNewClassGui(gym, gfa, classesGui);
            }
            else
            {
               addNewClassGui.clearFileds();
               addNewClassGui.makeVisible();
            }
         }
         if (e.getSource() == addTimeSlot)
         {
            int index = classesTable.getSelectedRow();

            if (index == -1)
            {
               JOptionPane.showMessageDialog(null,
                     "Please select a class from the table", "Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {

               if (addTimeSlotGui == null)
               {
                  addTimeSlotGui = new AddTimeSlotGui(gym, gfa, classesGui);
                  addTimeSlotGui.setClass(list.get(index));
               }
               else
               {
                  addTimeSlotGui.setClass(list.get(index));
                  addTimeSlotGui.updateInstructorTable();
                  addTimeSlotGui.makeVisible();
               }
            }

         }
      }
   }
}
