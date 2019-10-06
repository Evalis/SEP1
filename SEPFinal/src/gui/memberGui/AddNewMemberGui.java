package gui.memberGui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import gym.Member;

public class AddNewMemberGui
{
   private JFrame frame;
   private JPanel mainPanel;
   private JLabel firstNameLabel, lastNameLabel, addressLabel, phoneLabel,
         emailLabel, membershipLabel;
   private JTextField firstNameField, lastNameField, addressField, phoneField,
         emailField;
   private Font font;
   private EmptyBorder noBorder;
   private JComboBox<String> membershipBox;
   private JButton addMember;
   private ActionListener listener;
   private Font buttonFont;
   private Gym gym;
   private GymFileAdapter gfa;
   private MembersGui mg;

   public AddNewMemberGui(Gym gym, GymFileAdapter gfa, MembersGui mg)
   {
      this.gym = gym;
      this.gfa = gfa;
      this.mg = mg;

      // mainPanel
      mainPanel = new JPanel(new GridLayout(7, 1, 10, 10));
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

      membershipLabel = new JLabel("Membership", SwingConstants.CENTER);
      membershipLabel.setFont(new Font("Serif", Font.BOLD, 20));

      membershipBox = new JComboBox<String>();
      membershipBox.addItem("Regular");
      membershipBox.addItem("Premium");
      membershipBox.setFont(font);
      membershipBox.setBorder(noBorder);

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
      mainPanel.add(membershipLabel);
      mainPanel.add(membershipBox);

      addMember = new JButton("Add new Member");
      addMember.addActionListener(listener);
      addMember.setToolTipText("Add New Member");
      addMember.setBorder(new EmptyBorder(10, 10, 10, 10));
      addMember.setFont(buttonFont);

      mainPanel.add(addMember);
      // Frame
      frame = new JFrame("Add New Member");
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
         if (e.getSource() == addMember)
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
               String membership = (String) membershipBox.getSelectedItem();

               Member newMember = new Member(firstName, lastName, address,
                     phone, email, membership);
               try
               {
                  gym.getPersonList().addNewMember(newMember);
              
                  gfa.saveGym(gym);
                  mg.updateMemberTable();

                  clearFileds();
                  frame.setVisible(false);
               }
               catch (AlreadyExistExeption e1)
               {

                  JOptionPane.showMessageDialog(null, "Member already exist",
                        "Error", JOptionPane.ERROR_MESSAGE);
               }
            }
         }
      }
   }
}
