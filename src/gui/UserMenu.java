package gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import users.NameTakenException;
import users.User;
import users.UserManagementService;

import java.util.List;

public class UserMenu extends mainGUI {
	private JLabel frame_title;

	private JButton createNewUserButton;
	private JButton selectUserButton;
	private JList<User> users;
	
	

	public UserMenu() {
		// setup GUI styles/frame
		setup();
		setLayout(new GridLayout(3, 1));
		populateUsersList();
		defineVariables();
		makeButtons();
		setVisible(true);
	}
	
	public void populateUsersList() {
		List<User> userslist = UserManagementService.getUsers();
		users = new JList<User>(userslist.toArray(new User[userslist.size()]));
		add(users);
	}

	private class NewUserDialog extends JFrame {
		private void createAndShowNewUserDialog() {
			setName("Add new user");
			String nameLabel = "Enter Name: ";
			JPanel p = new JPanel();
			JLabel l = new JLabel(nameLabel);
			p.add(l);
			
			final JTextField inputField = new JTextField(20);
			l.setLabelFor(inputField);
			p.add(inputField);
			JButton addUserButton = new JButton("Add User");
			
			addUserButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = inputField.getText();
					if (name == null){
						JOptionPane.showMessageDialog(NewUserDialog.this, "You didn't enter your name!");
					}
					else{
						try {
							UserManagementService.createUser(name);
						} catch (NameTakenException e1) {
							JOptionPane.showMessageDialog(NewUserDialog.this, e1.getError());
						}
					}
				}
			});
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					dispose();
				}
			});
			p.add(addUserButton);
			p.add(cancelButton);
			setContentPane(p);
			setSize(Toolkit.getDefaultToolkit().getScreenSize());
			setVisible(true);
		}
	}

	private class CreateNewUserDialogHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			NewUserDialog dlg = new NewUserDialog();
			dlg.createAndShowNewUserDialog();
		}

	}
	
	private class selectedUserHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {			
			MainMenu s = new MainMenu();
			User selectedUser = users.getSelectedValue(); 
			System.out.println("You selected user: " + selectedUser.getName() + "\n with preferences: " + selectedUser.getPreferences());
		}

	}
	
	@Override
	public void makeButtons() {
		// make buttons 
		createNewUserButton = new JButton("Create New User");
		selectUserButton = new JButton("Select User");
		createNewUserButton.addActionListener(new CreateNewUserDialogHandler());
		selectUserButton.addActionListener(new selectedUserHandler());
		if(users.getModel().getSize() == 0)
		{
			System.out.println("No users");
			selectUserButton.setEnabled(false);
		}
		add(createNewUserButton);
		add(selectUserButton);
	}

}

