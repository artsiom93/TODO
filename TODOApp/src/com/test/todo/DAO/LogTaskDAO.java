package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.Query;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import com.test.todo.domain.LogTask;
import com.test.todo.domain.Person;
import com.test.todo.domain.Task;
import com.test.todo.util.HibernateUtil;

public class LogTaskDAO implements ILogTaskDAO {

	@Override
	public void addLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(logTask);
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
	public void updateLogTask(Integer id, LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(logTask);
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
	public LogTask getLogTaskById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		LogTask logTask = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			logTask = (LogTask) session.load(LogTask.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return logTask;
	}

	@Override
	public Collection<LogTask> getAllLogTasksByTask(Task task)
			throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		List<LogTask> logTasks = new ArrayList<LogTask>();
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			logTasks = session.createCriteria(LogTask.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return logTasks;
	}

	@Override
	public void deleteLogTask(LogTask logTask) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(logTask);
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
