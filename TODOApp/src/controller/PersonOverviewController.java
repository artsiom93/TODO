package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import application.Main;

import com.test.todo.DAO.Factory;
import com.test.todo.domain.Person;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable = new TableView<Person>();
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

	private Main mainApp;

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
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(mainApp.getPersons());
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
					"Ошибка при удалении", JOptionPane.OK_OPTION);
		else {
			Factory.getInstance().getPersonDAO().deletePerson(person);
			personTable.getItems().remove(person);
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
					"Ошибка при редактировании", JOptionPane.OK_OPTION);
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
