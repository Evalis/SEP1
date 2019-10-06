package gui.classGui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gym.Gym;
import gym.GymFileAdapter;
import gym.AlreadyExistExeption;
import gym.Class;

public class AddNewClassGui
{
   private JFrame frame;
   private JPanel mainPanel;
   private Font font;
   private EmptyBorder noBorder;
   private JButton addClass;
   
   private ActionListener listener;
   private Gym gym;
   private GymFileAdapter gfa;
   private ClassesGui classesGui;
   private JLabel classNameLabel, maxNumberOfMembersLabel;
   private JTextField classNameField, maxNumberOfMembersField;
  

   public AddNewClassGui(Gym gym, GymFileAdapter gfa, ClassesGui classesGui)
   {
      this.gym = gym;
      this.gfa = gfa;
      this.classesGui=classesGui;
      // mainPanel
      mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
      font = new Font("Serif", Font.PLAIN, 20);
      noBorder = new EmptyBorder(10, 10, 10, 10);
      listener = new MyButtonListener();

      classNameLabel = new JLabel("Class name", SwingConstants.CENTER);
      classNameLabel.setFont(new Font("Serif", Font.BOLD, 20));

      classNameField = new JTextField(30);
      classNameField.setFont(font);
      classNameField.setBorder(noBorder);

      maxNumberOfMembersLabel = new JLabel("Max Number Of Members", SwingConstants.CENTER);
      maxNumberOfMembersLabel.setFont(new Font("Serif", Font.BOLD, 20));

      maxNumberOfMembersField = new JTextField(30);
      maxNumberOfMembersField.setFont(font);
      maxNumberOfMembersField.setBorder(noBorder);

    

      mainPanel.add(classNameLabel);
      mainPanel.add(classNameField);
      mainPanel.add(maxNumberOfMembersLabel);
      mainPanel.add(maxNumberOfMembersField);
     
      addClass = new JButton("Add New Class");
      addClass.addActionListener(listener);
      addClass.setToolTipText("Add New Class");
      addClass.setBorder(new EmptyBorder(10, 10, 10, 10));
      addClass.setFont(font);
      
      mainPanel.add(addClass);
      // Frame
      frame = new JFrame("Add New Class");
      frame.setSize(400, 500);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
   
      frame.add(mainPanel);
   }

   public void makeVisible()
   {
      frame.setVisible(true);
   }

   public void clearFileds()
   {
      classNameField.setText("");
      maxNumberOfMembersField.setText("");
     
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == addClass)
         {
            if (classNameField.getText().equals("")
                  || maxNumberOfMembersField.getText().equals("")
                  )
            {
               JOptionPane.showMessageDialog(null,
                     "Please fill all the blank filds", "Blank Field Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               String className = classNameField.getText();
               int maxNumber = Integer.parseInt(maxNumberOfMembersField.getText());
               
               Class newClass = new Class(maxNumber, className);
               try
               {
                  gym.getClassList().addNewClass(newClass);
                  gfa.saveGym(gym);
                  classesGui.updateClassTable();
                  
                  clearFileds();
                  frame.setVisible(false);                  
               }
               catch (AlreadyExistExeption e1)
               {

                  JOptionPane.showMessageDialog(null, "Class already exist",
                        "Error", JOptionPane.ERROR_MESSAGE);
               }
            }
         }
      }
   }
}
