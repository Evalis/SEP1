package gui.instructorGui;

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

import gym.AlreadyExistExeption;
import gym.Gym;
import gym.GymFileAdapter;
import gym.Instructor;

public class AddNewInstructorGui
{
   private JFrame frame;
   private JPanel mainPanel;
   private JLabel firstNameLabel, lastNameLabel, addressLabel, phoneLabel,
         emailLabel, classLabel;
   private JTextField firstNameField, lastNameField, addressField, phoneField,
         emailField, classField;
   private Font font;
   private EmptyBorder noBorder;
   private JButton addInstructor;
   private ActionListener listener;
   private Font buttonFont;
   private Gym gym;
   private GymFileAdapter gfa;
   private InstructorsGui instructorGui;

   public AddNewInstructorGui(Gym gym, GymFileAdapter gfa,
         InstructorsGui instructorGui)
   {
      this.gym = gym;
      this.gfa = gfa;
      this.instructorGui = instructorGui;

      // mainPanel
      mainPanel = new JPanel(new GridLayout(13, 1, 10, 10));
      font = new Font("Serif", Font.PLAIN, 20);
      noBorder = new EmptyBorder(10, 10, 10, 10);
      listener = new MyButtonListener();

      firstNameLabel = new JLabel("First Name", SwingConstants.CENTER);
      firstNameLabel.setFont(new Font("Serif", Font.BOLD, 20));

      firstNameField = new JTextField(30);
      firstNameField.setFont(font);
      firstNameField.setBorder(noBorder);

      lastNameLabel = new JLabel("Last Name", SwingConstants.CENTER);
      lastNameLabel.setFont(new Font("Serif", Font.BOLD, 20));

      lastNameField = new JTextField(30);
      lastNameField.setFont(font);
      lastNameField.setBorder(noBorder);

      addressLabel = new JLabel("Address", SwingConstants.CENTER);
      addressLabel.setFont(new Font("Serif", Font.BOLD, 20));

      addressField = new JTextField(30);
      addressField.setFont(font);
      addressField.setBorder(noBorder);

      phoneLabel = new JLabel("Phone", SwingConstants.CENTER);
      phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));

      phoneField = new JTextField(30);
      phoneField.setFont(font);
      phoneField.setBorder(noBorder);

      emailLabel = new JLabel("Email", SwingConstants.CENTER);
      emailLabel.setFont(new Font("Serif", Font.BOLD, 20));

      emailField = new JTextField(30);
      emailField.setFont(font);
      emailField.setBorder(noBorder);

      classField = new JTextField(30);
      classField.setFont(font);
      classField.setBorder(noBorder);

      classLabel = new JLabel("Class", SwingConstants.CENTER);
      classLabel.setFont(new Font("Serif", Font.BOLD, 20));
      
      addInstructor = new JButton("Add new Instructor");
      addInstructor.addActionListener(listener);
      addInstructor.setToolTipText("Add New Member");
      addInstructor.setBorder(new EmptyBorder(10, 10, 10, 10));
      addInstructor.setFont(buttonFont);

      mainPanel.add(firstNameLabel);
      mainPanel.add(firstNameField);
      mainPanel.add(lastNameLabel);
      mainPanel.add(lastNameField);
      mainPanel.add(addressLabel);
      mainPanel.add(addressField);
      mainPanel.add(phoneLabel);
      mainPanel.add(phoneField);
      mainPanel.add(emailLabel);
      mainPanel.add(emailField);
      mainPanel.add(classLabel);
      mainPanel.add(classField);
      mainPanel.add(addInstructor);

      // Frame
      frame = new JFrame("Add New Instructor");
      frame.setSize(700, 800);
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
      firstNameField.setText("");
      lastNameField.setText("");
      addressField.setText("");
      phoneField.setText("");
      emailField.setText("");
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == addInstructor)
         {
            if (firstNameField.getText().equals("")
                  || lastNameField.getText().equals("")
                  || addressField.getText().equals("")
                  || phoneField.getText().equals("")
                  || emailField.getText().equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "Please fill all the blank filds", "Blank Field Error",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               String firstName = firstNameField.getText();
               String lastName = lastNameField.getText();
               String address = addressField.getText();
               String phone = phoneField.getText();
               String email = emailField.getText();
               String gymClass = classField.getText();

               Instructor newInstructor = new Instructor(firstName, lastName,
                     address, phone, email, gymClass);

               try
               {
                  gym.getPersonList().addNewInstructor(newInstructor);

                  gfa.saveGym(gym);
                  instructorGui.updateInstructorTable();

                  clearFileds();
                  frame.setVisible(false);
               }
               catch (AlreadyExistExeption e1)
               {

                  JOptionPane.showMessageDialog(null,
                        "Instructor already exist", "Error",
                        JOptionPane.ERROR_MESSAGE);
               }
            }
         }
      }
   }
}
