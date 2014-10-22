package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import com.test.todo.domain.Person;
import com.test.todo.domain.Task;

public class TaskDAO implements ITaskDAO {

	@Override
	public void addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			em.getTransaction().begin();
			em.persist(task);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. addTask", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public void updateTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			em.getTransaction().begin();
			em.merge(task);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. updateTask", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Collection<Task> getTaskByIdPerson(Person person)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Task> task = new ArrayList<Task>();
		try {
			EntityManager em = Factory.ef.createEntityManager();
			task = (ArrayList<Task>) em
					.createQuery(
							"SELECT r FROM Task r WHERE person_ID = :chrId",
							Task.class).setParameter("chrId", person.getId())
					.getResultList();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. getTaskByIdPerson", JOptionPane.OK_OPTION);
		}
		return task;
	}

	@Override
	public Collection<Task> getAllTasks() throws SQLException {
		// TODO Auto-generated method stub
		TypedQuery<Task> namedQuery = null;
		try {
			EntityManager em = Factory.ef.createEntityManager();
			namedQuery = em.createNamedQuery("Task.getAll", Task.class);
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. getAllTasks", JOptionPane.OK_OPTION);
		}
		return namedQuery.getResultList();
	}

	@Override
	public void deleteTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			Task attached = em.find(Task.class, task.getId());
			em.getTransaction().begin();
			em.remove(attached);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. deleteTask", JOptionPane.OK_OPTION);
		}
	}

}
