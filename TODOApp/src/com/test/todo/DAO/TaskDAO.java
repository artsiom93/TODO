package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import com.test.todo.domain.Task;

public class TaskDAO implements ITaskDAO {
	public EntityManager em = Persistence.createEntityManagerFactory("todo")
			.createEntityManager();

	@Override
	public void addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(task);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при вставке", JOptionPane.OK_OPTION);
		}

	}

	@Override
	public void updateTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.merge(task);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при обновлении", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Task getTaskById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Task task = null;
		try {
			task = em.find(Task.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		}
		return task;
	}

	@Override
	public Collection<Task> getAllTasks() throws SQLException {
		// TODO Auto-generated method stub
		TypedQuery<Task> namedQuery = null;
		try {
			namedQuery = em.createNamedQuery("Task.getAll", Task.class);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		}
		return namedQuery.getResultList();
	}

	@Override
	public void deleteTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.remove(task);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при удалении", JOptionPane.OK_OPTION);
		}
	}

}
