package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.test.todo.domain.LogTask;

public class LogTaskAddDialogController {
	@FXML
	private TextField comment;
	@FXML
	private TextField hours;

	private Stage dialogStage;
	private LogTask logTask;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setTask(LogTask logTask) {
		this.logTask = logTask;

		comment.setText(logTask.getComment());
		hours.setText("0");
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			logTask.setComment(comment.getText());
			logTask.setHours(Integer.parseInt(hours.getText()));

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

		if (comment.getText() == null || comment.getText().length() == 0) {
			errorMessage += "No comment!\n";
		}
		if (!isNumeric(hours.getText())) {
			errorMessage += "Hours is not numeric";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, errorMessage, "Error. Bad inputs",
					JOptionPane.OK_OPTION);
			return false;
		}
	}

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
}
