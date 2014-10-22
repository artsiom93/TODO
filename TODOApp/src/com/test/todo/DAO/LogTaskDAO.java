package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import com.test.todo.domain.LogTask;
import com.test.todo.domain.Task;

public class LogTaskDAO implements ILogTaskDAO {
	@Override
	public void addLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			em.getTransaction().begin();
			em.persist(logTask);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. addLogTask", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Collection<LogTask> getAllLogTasksByTask(Task task)
			throws SQLException {
		// TODO Auto-generated method stub
		List<LogTask> logs = null;
		try {
			EntityManager em = Factory.ef.createEntityManager();
			logs = em
					.createQuery("FROM LogTask r WHERE r.task.id LIKE :id",
							LogTask.class).setParameter("id", task.getId())
					.getResultList();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. getAllLogTasksByTask", JOptionPane.OK_OPTION);
		}
		return logs;
	}

}
