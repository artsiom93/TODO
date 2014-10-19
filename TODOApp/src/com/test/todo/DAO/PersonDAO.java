package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.test.todo.domain.Person;

public class PersonDAO implements IPersonDAO {
	public EntityManager em = Persistence.createEntityManagerFactory("todo")
			.createEntityManager();

	@Override
	public void addPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"������ ��� �������", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.merge(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"������ ��� ����������", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Person getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Person person = null;
		try {
			person = em.find(Person.class, id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"������ 'findById'", JOptionPane.OK_OPTION);
		}
		return person;
	}

	@Override
	public Collection<Person> getAllPersons() throws SQLException {
		// TODO Auto-generated method stub
		List<Person> persons = null;
		try {
			persons = em.createQuery("SELECT r FROM Person r",Person.class)
		            .getResultList();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"������ 'getAll'", JOptionPane.OK_OPTION);
		}
		return persons;
	}

	@Override
	public void deletePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.remove(person);
			em.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"������ ��� ��������", JOptionPane.OK_OPTION);
		}
	}

}
