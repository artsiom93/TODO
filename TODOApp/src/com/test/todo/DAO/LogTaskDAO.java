package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import com.test.todo.domain.LogTask;
import com.test.todo.domain.Task;

public class LogTaskDAO implements ILogTaskDAO {
	public EntityManager em = Persistence.createEntityManagerFactory("todo")
			.createEntityManager();

	@Override
	public void addLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(logTask);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при вставке", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public void updateLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.merge(logTask);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при обновлении", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Collection<LogTask> getAllLogTasksByTask(Task task)
			throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void deleteLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.remove(logTask);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при удалении", JOptionPane.OK_OPTION);
		}
	}

}
