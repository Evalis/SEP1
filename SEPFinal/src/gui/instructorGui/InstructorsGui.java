package gui.instructorGui;

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
import gym.Instructor;

public class InstructorsGui
{
   private JPanel mainPanel, topPanel, bottomPanel;;
   private Gym gym;
   private JButton addInstructor;
   private MyButtonListener listener;
   private JLabel title;
   private Font font;
   private GymFileAdapter gfa;
   private JTable instructorsTable;
   private DefaultTableModel dtm;
   private JScrollPane instructorsTableScroll;
   private String[] instructorsTableColums;
   private AddNewInstructorGui addNewInstructorGui;
   private InstructorsGui instructorGui;
   private ArrayList<Instructor> list;

   public InstructorsGui(Gym gym)
   {
      this.gym = gym;
      gfa = new GymFileAdapter("gym.bin");
      listener = new MyButtonListener();
      font = new Font("Serif", Font.PLAIN, 20);
      instructorGui = this;

      title = new JLabel("Instructors", SwingConstants.CENTER);
      title.setFont(new Font("Serif", Font.BOLD, 20));

      // top panel

      topPanel = new JPanel();
      topPanel.setLayout(new GridLayout(1, 1, 5, 5));
      topPanel.add(title);
      // Instructor table

      instructorsTableColums = new String[7];
      instructorsTableColums[0] = "No";
      instructorsTableColums[1] = "First Name";
      instructorsTableColums[2] = "Last Name";
      instructorsTableColums[3] = "Address";
      instructorsTableColums[4] = "Phone";
      instructorsTableColums[5] = "Email";
      instructorsTableColums[6] = "Class";

      dtm = new DefaultTableModel(instructorsTableColums, 0);
      instructorsTable = new JTable(dtm);
      instructorsTable
            .setPreferredScrollableViewportSize(new Dimension(1000, 565));
      instructorsTable.setFillsViewportHeight(true);
      instructorsTableScroll = new JScrollPane(instructorsTable);
      instructorsTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));

      // bottom panel

      bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1, 1, 10, 10));

      addInstructor = new JButton("Add New Instructor");
      addInstructor.addActionListener(listener);
      addInstructor.setToolTipText("Add New Instructor");
      addInstructor.setBorder(new EmptyBorder(10, 10, 10, 10));
      addInstructor.setFont(font);

      bottomPanel.add(addInstructor);

      mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(topPanel, BorderLayout.NORTH);
      mainPanel.add(instructorsTableScroll, BorderLayout.CENTER);
      mainPanel.add(bottomPanel, BorderLayout.SOUTH);
      updateInstructorTable();
   }

   public JPanel getMainPanel()
   {

      return mainPanel;
   }

   public void updateInstructorTable()

   {
      list = gym.getPersonList().getAllInstructor();

      Object[][] data = new Object[list.size()][7];

      Instructor temp = null;
      for (int i = 0; i < list.size(); i++)
      {
         temp = list.get(i);

         data[i][0] = i + 1;
         data[i][1] = temp.getFirstName();
         data[i][2] = temp.getLastName();
         data[i][3] = temp.getAddress();
         data[i][4] = temp.getPhone();
         data[i][5] = temp.getEmail();
         data[i][6] = temp.getGymClass();

      }
      dtm = new DefaultTableModel(data, instructorsTableColums);
      instructorsTable.setModel(dtm);

   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == addInstructor)
         {

            if (addNewInstructorGui == null)
            {
               addNewInstructorGui = new AddNewInstructorGui(gym, gfa,
                     instructorGui);
            }
            else
            {
               addNewInstructorGui.clearFileds();
               addNewInstructorGui.makeVisible();
            }
         }
      }
   }
}
