package application;

import java.io.IOException;
import java.sql.SQLException;

import com.test.todo.DAO.Factory;
import com.test.todo.domain.LogTask;
import com.test.todo.domain.Person;
import com.test.todo.domain.Task;

import controller.LogTaskAddDialogController;
import controller.PersonEditDialogController;
import controller.PersonOverviewController;
import controller.TaskEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> persons = FXCollections
			.observableArrayList();

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;

		initMainLayout();

		showPersonOverview();
	}

	public void initMainLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainView.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void showPersonOverview() {
		try {
			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("/view/PersonOverview.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage);

			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showPersonEditDialog(Person person) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/NewPerson.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showTaskEditDialog(Task task) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/NewTask.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Task");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			TaskEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTask(task);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showLogTaskAddDialog(LogTask logTask) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/NewLogTask.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add LogTask");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			LogTaskAddDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTask(logTask);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Main() throws SQLException {
		persons = FXCollections.observableArrayList(Factory.getInstance()
				.getPersonDAO().getAllPersons());
	}

	public ObservableList<Person> getPersons() {
		return persons;
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void stop() throws Exception {
	// TODO Auto-generated method stub
	Factory.ef.close();
	super.stop();
}
}
