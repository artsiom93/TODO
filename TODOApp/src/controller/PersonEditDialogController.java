package controller;

import javax.swing.JOptionPane;

import com.test.todo.domain.Person;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField loginField;
	@FXML
	private TextField passwordField;

	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPerson(Person person) {
		this.person = person;

		firstNameField.setText(person.getFirstname());
		lastNameField.setText(person.getLastname());
		loginField.setText(person.getLogin());
		passwordField.setText(person.getPassword());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstname(firstNameField.getText());
			person.setLastname(lastNameField.getText());
			person.setLogin(loginField.getText());
			person.setPassword(passwordField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null
				|| firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null
				|| lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (loginField.getText() == null || loginField.getText().length() == 0) {
			errorMessage += "No valid login!\n";
		}

		if (passwordField.getText() == null
				|| passwordField.getText().length() == 0) {
			errorMessage += "No valid password!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, errorMessage, "Error. Bad inputs",
					JOptionPane.OK_OPTION);
			return false;
		}
	}
}