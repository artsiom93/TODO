package com.test.todo.DAO;
import java.sql.SQLException;
import java.util.Collection;
import com.test.todo.domain.LogTask;
import com.test.todo.domain.Task;

public interface ILogTaskDAO {
	public void addLogTask(LogTask logTask) throws SQLException;
	public Collection<LogTask> getAllLogTasksByTask(Task task) throws SQLException;
}
