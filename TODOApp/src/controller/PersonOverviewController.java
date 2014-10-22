package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import application.Main;
import com.test.todo.DAO.Factory;
import com.test.todo.domain.LogTask;
import com.test.todo.domain.Person;
import com.test.todo.domain.Task;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable = new TableView<Person>();
	@FXML
	private TableView<Task> taskTable = new TableView<Task>();
	@FXML
	private Button btnAddNewPerson;
	@FXML
	private Button btnDeletePerson;
	@FXML
	private TableColumn<Person, Integer> id;
	@FXML
	private TableColumn<Person, String> lastName;
	@FXML
	private TableColumn<Person, String> firstName;
	@FXML
	private TableColumn<Person, String> login;
	@FXML
	private TableColumn<Person, String> password;
	@FXML
	private TableColumn<Task, Integer> idTask;
	@FXML
	private TableColumn<Task, String> nameTask;
	@FXML
	private TableColumn<Task, Boolean> stateTask;
	@FXML
	private TextArea logsTextArea;
	private ObservableList<Task> tasks = null;
	private Main mainApp;
	private String logsString;

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {
		id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
		lastName.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"lastname"));
		firstName.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"firstname"));
		login.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"login"));
		password.setCellValueFactory(new PropertyValueFactory<Person, String>(
				"password"));
		idTask.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
		nameTask.setCellValueFactory(new PropertyValueFactory<Task, String>(
				"name"));
		stateTask.setCellValueFactory(new PropertyValueFactory<Task, Boolean>(
				"state"));

		personTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> {
							Person person = personTable.getSelectionModel()
									.getSelectedItem();
							if (person != null) {
								try {
									tasks = FXCollections
											.observableArrayList(Factory
													.getInstance().getTaskDAO()
													.getTaskByIdPerson(person));
								} catch (Exception e) {
									e.printStackTrace();
								}
								taskTable.setItems(tasks);
								logsTextArea.setText("");
							}
						});
		taskTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> {
							Task task = taskTable.getSelectionModel()
									.getSelectedItem();
							if (task != null) {
								try {
									ArrayList<LogTask> logs = (ArrayList<LogTask>) Factory
											.getInstance().getLogTaskDAO()
											.getAllLogTasksByTask(task);
									StringBuilder sb = new StringBuilder();
									for (LogTask l : logs) {
										sb.append(l.toString());
									}
									logsString = sb.toString();
									logsTextArea.setText(logsString);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(mainApp.getPersons());
		taskTable.setItems(tasks);
	}

	@FXML
	private void handleNewPerson(ActionEvent event) throws SQLException,
			IOException {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersons().add(tempPerson);
			Factory.getInstance().getPersonDAO().addPerson(tempPerson);
		}
	}

	@FXML
	private void handleDeletePerson() throws SQLException {
		Person person = personTable.getSelectionModel().getSelectedItem();
		if (person == null)
			JOptionPane.showMessageDialog(null,
					"Please select a person in the table.",
					"Error. Delete Person", JOptionPane.OK_OPTION);
		else {
			Factory.getInstance().getPersonDAO().deletePerson(person);
			personTable.getItems().remove(person);
			taskTable.setItems(null);
			logsTextArea.setText("");
		}
	}

	@FXML
	private void handleEditPerson() throws SQLException {
		Person selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				refreshPersonTable();
				Factory.getInstance().getPersonDAO()
						.updatePerson(selectedPerson);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Please select a person in the table.",
					"Error. Edit Person", JOptionPane.OK_OPTION);
		}
	}

	@FXML
	private void handleAddTask() throws SQLException {
		Person person = personTable.getSelectionModel().getSelectedItem();
		if (person == null) {
			JOptionPane.showMessageDialog(null,
					"Please select a person in the table.", "Error. Add Task",
					JOptionPane.OK_OPTION);
		} else {
			Task task = new Task();
			boolean okClicked = mainApp.showTaskEditDialog(task);
			if (okClicked) {
				task.setPerson(person);
				Factory.getInstance().getTaskDAO().addTask(task);
				tasks.add(task);
			}
		}
	}

	@FXML
	private void handleEditTask() throws SQLException {
		Task task = taskTable.getSelectionModel().getSelectedItem();
		if (task == null) {
			JOptionPane.showMessageDialog(null,
					"Please select a person and task in the table.",
					"Error. Edit Task", JOptionPane.OK_OPTION);
		} else {
			boolean okClicked = mainApp.showTaskEditDialog(task);
			if (okClicked) {
				Factory.getInstance().getTaskDAO().updateTask(task);
			}
		}
	}

	@FXML
	private void handleDeleteTask() throws SQLException {
		Task task = taskTable.getSelectionModel().getSelectedItem();
		if (task == null) {
			JOptionPane.showMessageDialog(null,
					"Please select task in the table.", "Error. Delete Task",
					JOptionPane.OK_OPTION);
		} else {
			Factory.getInstance().getTaskDAO().deleteTask(task);
			tasks.remove(task);
		}
	}

	@FXML
	private void handleAddLog() throws SQLException {
		Task task = taskTable.getSelectionModel().getSelectedItem();
		if (task == null) {
			JOptionPane.showMessageDialog(null,
					"Please select task in the table.", "Error. Add Log",
					JOptionPane.OK_OPTION);
		} else {
			LogTask logTask = new LogTask();
			boolean okClicked = mainApp.showLogTaskAddDialog(logTask);
			if (okClicked) {
				logTask.setTask(task);
				logsTextArea.setText(logsString + logTask.toString());
				Factory.getInstance().getLogTaskDAO().addLogTask(logTask);
			}
		}
	}

	private void refreshPersonTable() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		personTable.setItems(null);
		personTable.layout();
		personTable.setItems(mainApp.getPersons());

		personTable.getSelectionModel().select(selectedIndex);
	}
}
