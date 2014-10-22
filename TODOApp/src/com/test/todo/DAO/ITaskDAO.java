package com.test.todo.DAO;
import java.sql.SQLException;
import java.util.Collection;

import com.test.todo.domain.Person;
import com.test.todo.domain.Task;

public interface ITaskDAO {
	public void addTask(Task task) throws SQLException;
	public void updateTask(Task task) throws SQLException;
	public Collection<Task> getTaskByIdPerson(Person person) throws SQLException;
	public Collection<Task> getAllTasks() throws SQLException;
	public void deleteTask(Task task) throws SQLException;
}




	
	

