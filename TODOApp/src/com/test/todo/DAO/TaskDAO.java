package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import com.test.todo.domain.Task;
import com.test.todo.util.HibernateUtil;

public class TaskDAO implements ITaskDAO {

	@Override
	public void addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при вставке", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public void updateTask(Integer id, Task task) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при обновлении", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public Task getTaskById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		Task task = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			task = (Task) session.load(Task.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return task;
	}

	@Override
	public Collection<Task> getAllTasks() throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		List<Task> tasks = new ArrayList<Task>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tasks = session.createCriteria(Task.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return tasks;
	}

	@Override
	public void deleteTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(task);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при удалении", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
