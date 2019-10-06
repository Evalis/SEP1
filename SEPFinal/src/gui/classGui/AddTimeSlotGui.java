package gui.classGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import gym.Gym;
import gym.GymFileAdapter;
import gym.Instructor;
import gym.InstructorIsBusyException;
import gym.Class;
import gym.TimeSlot;

public class AddTimeSlotGui
{
   private JFrame frame;
   private JPanel leftPanel, mainPanel, datePanel, startTimePanel, endTimePanel;
   private JLabel dateLabel, startTimeLabel, endTimeLabel, dateSeparatorLabel,dateSeparatorLabel2,
   timeSeparatorLabel,timeSeparatorLabel2;
   private JTextField dayField, monthField, yearField, startHourTimeField,
         startMinutesTimeField, endHourTimeField, endMinutesTimeField;
   private Font font;
   private EmptyBorder noBorder;
   private JButton addTimeSlot;
   private ActionListener listener;
   private Gym gym;
   private GymFileAdapter gfa;
   private ClassesGui classesGui;
   private ArrayList<Instructor> list;
   private JTable instructorsTable;
   private DefaultTableModel dtm;
   private JScrollPane instructorsTableScroll;
   private String[] instructorsTableColums;
   private Class cl;
   private LineBorder lineBorder;
   

   public AddTimeSlotGui(Gym gym, GymFileAdapter gfa, ClassesGui classesGui)
   {
      this.gym = gym;
      this.gfa = gfa;
      this.classesGui = classesGui;
      lineBorder = new LineBorder(new Color( 100, 100, 100),1);
      // leftPanel
      leftPanel = new JPanel(new GridLayout(6, 1, 10, 10));
      leftPanel.setPreferredSize(new Dimension(340, 400));
      leftPanel.setBorder(lineBorder);
      
      datePanel = new JPanel(new GridLayout(1, 5, 10, 10));
      startTimePanel = new JPanel(new GridLayout(1, 3, 10, 10));
      endTimePanel = new JPanel(new GridLayout(1, 3, 10, 10));
      font = new Font("Serif", Font.PLAIN, 20);
      noBorder = new EmptyBorder(10, 10, 10, 10);
      listener = new MyButtonListener();

      dateLabel = new JLabel("Date", SwingConstants.CENTER);
      dateLabel.setFont(new Font("Serif", Font.BOLD, 20));

      dateSeparatorLabel = new JLabel("/", SwingConstants.CENTER);
      dateSeparatorLabel.setFont(new Font("Serif", Font.BOLD, 20));
      
      dateSeparatorLabel2 = new JLabel("/", SwingConstants.CENTER);
      dateSeparatorLabel2.setFont(new Font("Serif", Font.BOLD, 20));

      dayField = new JTextField(2);
      dayField.setFont(font);
      dayField.setBorder(noBorder);

      monthField = new JTextField(2);
      monthField.setFont(font);
      monthField.setBorder(noBorder);

      yearField = new JTextField(4);
      yearField.setFont(font);
      yearField.setBorder(noBorder);

      timeSeparatorLabel = new JLabel(":", SwingConstants.CENTER);
      timeSeparatorLabel.setFont(new Font("Serif", Font.BOLD, 20));
      
      timeSeparatorLabel2 = new JLabel(":", SwingConstants.CENTER);
      timeSeparatorLabel2.setFont(new Font("Serif", Font.BOLD, 20));
      
      startTimeLabel = new JLabel("Start Time", SwingConstants.CENTER);
      startTimeLabel.setFont(new Font("Serif", Font.BOLD, 20));

      startHourTimeField = new JTextField(2);
      startHourTimeField.setFont(font);
      startHourTimeField.setBorder(noBorder);

      startMinutesTimeField = new JTextField(2);
      startMinutesTimeField.setFont(font);
      startMinutesTimeField.setBorder(noBorder);

      endTimeLabel = new JLabel("End Time", SwingConstants.CENTER);
      endTimeLabel.setFont(new Font("Serif", Font.BOLD, 20));

      endHourTimeField = new JTextField(2);
      endHourTimeField.setFont(font);
      endHourTimeField.setBorder(noBorder);

      endMinutesTimeField = new JTextField(2);
      endMinutesTimeField.setFont(font);
      endMinutesTimeField.setBorder(noBorder);

      datePanel.add(dayField);
      datePanel.add(dateSeparatorLabel);
      datePanel.add(monthField);
      datePanel.add(dateSeparatorLabel2);
      datePanel.add(yearField);

      startTimePanel.add(startHourTimeField);
      startTimePanel.add(timeSeparatorLabel);
      startTimePanel.add(startMinutesTimeField);

      endTimePanel.add(endHourTimeField);
      endTimePanel.add(timeSeparatorLabel2);
      endTimePanel.add(endMinutesTimeField);

      instructorsTableColums = new String[2];
      instructorsTableColums[0] = "First Name";
      instructorsTableColums[1] = "Last Name";

      dtm = new DefaultTableModel(instructorsTableColums, 0);
      instructorsTable = new JTable(dtm);
      instructorsTable
            .setPreferredScrollableViewportSize(new Dimension(340, 400));
      instructorsTable.setFillsViewportHeight(true);
      instructorsTableScroll = new JScrollPane(instructorsTable);
      instructorsTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));
      
      leftPanel.add(dateLabel);
      leftPanel.add(datePanel);
      leftPanel.add(startTimeLabel);
      leftPanel.add(startTimePanel);
      leftPanel.add(endTimeLabel);
      leftPanel.add(endTimePanel);

      addTimeSlot = new JButton("Add Time Slot");
      addTimeSlot.addActionListener(listener);
      addTimeSlot.setToolTipText("Add Time Slot");
      addTimeSlot.setBorder(new EmptyBorder(10, 10, 10, 10));
      addTimeSlot.setFont(font);
      
      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(leftPanel, BorderLayout.WEST);
      mainPanel.add(instructorsTableScroll, BorderLayout.EAST);
      mainPanel.add(addTimeSlot, BorderLayout.SOUTH);
      // Frame
      frame = new JFrame("Add Time Slot");
      frame.setSize(700, 450);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);

      frame.add(mainPanel);
      updateInstructorTable();
   }

   public void makeVisible()
   {
      frame.setVisible(true);
   }
   
   public void setClass(Class cl)
   {
      this.cl = cl;
   }
   public void clearFileds()
   {
      dayField.setText("");
      monthField.setText("");
      yearField.setText("");
      startHourTimeField.setText("");
      startMinutesTimeField.setText("");
      endHourTimeField.setText("");
      endMinutesTimeField.setText("");

   }

   public LocalDate extractDate()
   {
      int day, month, year;
      day = month = year = 0;
      try
      {
         day = Integer.parseInt(dayField.getText());
         month = Integer.parseInt(monthField.getText());
         year = Integer.parseInt(yearField.getText());
         return LocalDate.of(year, month, day);
      }
      catch (NumberFormatException dateNumberError)
      {
         JOptionPane.showMessageDialog(null, "Please add only numbers for Date",
               "Date Error", JOptionPane.ERROR_MESSAGE);
      }
      catch (DateTimeException dateError)
      {
         JOptionPane.showMessageDialog(null, "Please add a Valid Date",
               "Date Error", JOptionPane.ERROR_MESSAGE);
      }
      return null;
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == addTimeSlot)
         {

            LocalDate date = extractDate();
            LocalTime startTime = extractStartTime();
            LocalTime endTime = extractendTime();
            int index = instructorsTable.getSelectedRow();

            if (index == -1)
            {
               JOptionPane.showMessageDialog(null,
                     "Please select a instructor from the table", "Instructor Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               if (date != null && startTime != null && endTime != null)
               {
                  TimeSlot newTimeSlot = new TimeSlot(date, startTime, endTime, list.get(index), cl);
               try 
               { 
                  gym.getSchedule().addTimeSlot(newTimeSlot);
                  gfa.saveGym(gym);
                  classesGui.updateClassTable();
                  classesGui.updateBothScheduleTables();
                  clearFileds();
                  frame.setVisible(false);
               }
               catch (InstructorIsBusyException e1)
               {
                  JOptionPane.showMessageDialog(null, "Instructor is busy at the time",
                        "Error", JOptionPane.ERROR_MESSAGE);
               }
               }
            }
         }
      }
   }

   public LocalTime extractStartTime()
   {
      int hour, minute;
      hour = minute = 0;
      try
      {
         hour = Integer.parseInt(startHourTimeField.getText());
         minute = Integer.parseInt(startMinutesTimeField.getText());
         
         return LocalTime.of(hour, minute);
      }
      catch (NumberFormatException dateNumberError)
      {
         JOptionPane.showMessageDialog(null, "Please add only numbers for Time",
               "Date Error", JOptionPane.ERROR_MESSAGE);
      }
      catch (DateTimeException dateError)
      {
         JOptionPane.showMessageDialog(null, "Please add a Valid Start Time",
               "Time Error", JOptionPane.ERROR_MESSAGE);
      }

      return null;
   }

   public LocalTime extractendTime()
   {
      int hour, minute;
      hour = minute = 0;
      try
      {
         hour = Integer.parseInt(endHourTimeField.getText());
         minute = Integer.parseInt(endMinutesTimeField.getText());
         
         return LocalTime.of(hour, minute);
      }
      catch (NumberFormatException dateNumberError)
      {
         JOptionPane.showMessageDialog(null, "Please add only numbers for Time",
               "Time Error", JOptionPane.ERROR_MESSAGE);
      }
      catch (DateTimeException dateError)
      {
         JOptionPane.showMessageDialog(null, "Please add a Valid End Time",
               "Time Error", JOptionPane.ERROR_MESSAGE);
      }
      return null;
   }

   public void updateInstructorTable()

   {
      list = gym.getPersonList().getAllInstructor();

      Object[][] data = new Object[list.size()][2];

      Instructor temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp = list.get(i);

         data[i][0] = temp.getFirstName();
         data[i][1] = temp.getLastName();

      }
      dtm = new DefaultTableModel(data, instructorsTableColums);
      instructorsTable.setModel(dtm);

   }
}
