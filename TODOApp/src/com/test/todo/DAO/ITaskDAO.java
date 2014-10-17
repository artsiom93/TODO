package com.test.todo.DAO;
import java.sql.SQLException;
import java.util.Collection;
import com.test.todo.domain.Task;

public interface ITaskDAO {
	public void addTask(Task task) throws SQLException;
	public void updateTask(Integer id,Task task) throws SQLException;
	public Task getTaskById(Integer id) throws SQLException;
	public Collection<Task> getAllTasks() throws SQLException;
	public void deleteTask(Task task) throws SQLException;
}




	
	

