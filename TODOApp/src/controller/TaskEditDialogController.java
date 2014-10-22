package controller;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.test.todo.domain.Task;

public class TaskEditDialogController {
	@FXML
	private TextField nameField;
	@FXML
	private CheckBox stateCheckBox;
	

	private Stage dialogStage;
	private Task task;
	private boolean okClicked = false;
	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTask(Task task) {
		this.task = task;

		nameField.setText(task.getName());
		stateCheckBox.setSelected(task.isState());
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			task.setName(nameField.getText());
			task.setState(stateCheckBox.isSelected());

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

		if (nameField.getText() == null
				|| nameField.getText().length() == 0) {
			errorMessage += "No valid name!\n";
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
