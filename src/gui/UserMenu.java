package gui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

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
		setLayout(new GridLayout(1, 2));
		populateUsersList();
		makeButtons();
		setVisible(true);
	}
	
	public void populateUsersList() {
		List<User> userslist = UserManagementService.getInstance().getUsers();
		users = new JList<User>(userslist.toArray(new User[userslist.size()]));
		
		users.setAutoscrolls(true);
		if(userslist.size() != 0) {
			users.setSelectedIndex(0);
		}
	
		add(users);
		add(new JScrollPane(users));
		users.requestFocus();
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
			addUserButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "addUserButtonPressed");
			addUserButton.getActionMap().put("addUserButtonPressed", new CreateUserAction(inputField, this));
			addUserButton.addActionListener(new CreateUserAction(inputField, this) );
			
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

	private class CreateUserAction extends AbstractAction {
		private String name;
		private NewUserDialog dlg;
		private JTextField inputField;
		
		CreateUserAction(JTextField inputField, NewUserDialog dlg) {
			this.inputField = inputField;
			this.dlg = dlg;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			name = inputField.getText();
			if (name.equals("")){
				JOptionPane.showMessageDialog(dlg, "You didn't enter your name!");
			}
			else{
				try {
					User newUser = UserManagementService.getInstance().createUser(name);
					UserManagementService.getInstance().setMainUser(newUser);
					userPref(UserManagementService.getInstance().getMainUser());
					goToMainMenu();
					dispose();
				} catch (NameTakenException e1) {
					JOptionPane.showMessageDialog(dlg, e1.getError());
				}
			}
		}
	}
	
	private class CreateNewUserDialogAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			NewUserDialog dlg = new NewUserDialog();
			dlg.createAndShowNewUserDialog();
		}

	}
	
	private class selectedUserAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			User selectedUser = users.getSelectedValue(); 
			UserManagementService.getInstance().setMainUser(selectedUser);
			System.out.println("You selected user: " + selectedUser.getName() + "\n with preferences: " + selectedUser.getPreferences());
			goToMainMenu();
		}
	}
	
	private void goToMainMenu(){
		MainMenu s = new MainMenu();
		this.dispose();
	}
	
	@Override
	public void makeButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		// make buttons 
		createNewUserButton = new JButton("Create New User");
		createNewUserButton.setText("<html><font color=\"#FF6600\">C</font>" + "reate New User</html>");
		
		selectUserButton = new JButton("Select User");
		selectUserButton.setText("<html><font color=\"#FF6600\">S</font>" + "elect User</html>");
		
		createNewUserButton.addActionListener(new CreateNewUserDialogAction());
		selectUserButton.addActionListener(new selectedUserAction());
		
		selectUserButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "gameButtonPressed");
		selectUserButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "gameButtonPressed");
		selectUserButton.getActionMap().put("gameButtonPressed", new selectedUserAction());
		
		createNewUserButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "gameButtonPressed");
		createNewUserButton.getActionMap().put("gameButtonPressed", new CreateNewUserDialogAction());
		
		selectUserButton.setEnabled(users.getModel().getSize()!=0); // disables button if there are no users
		buttonPanel.add(createNewUserButton);
		buttonPanel.add(selectUserButton);
		add(buttonPanel);
	}
}

