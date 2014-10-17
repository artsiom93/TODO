package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import com.test.todo.domain.Person;
import com.test.todo.util.HibernateUtil;

public class PersonDAO implements IPersonDAO {

	@Override
	public void addPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(person);
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
	public void updatePerson(Integer id, Person person) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(person);
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
	public Person getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		Person person = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			person = (Person) session.load(Person.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return person;
	}

	@Override
	public Collection<Person> getAllPersons() throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		List<Person> persons = new ArrayList<Person>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			persons = session.createCriteria(Person.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return persons;
	}

	@Override
	public void deletePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(person);
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
