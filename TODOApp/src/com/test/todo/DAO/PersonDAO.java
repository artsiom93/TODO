package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import com.test.todo.domain.Person;

public class PersonDAO implements IPersonDAO {

	@Override
	public void addPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. addPerson", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			em.getTransaction().begin();
			em.merge(person);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. updatePerson", JOptionPane.OK_OPTION);
		}
	}

	@Override
	public Person getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		Person person = null;
		try {
			EntityManager em = Factory.ef.createEntityManager();
			person = em.find(Person.class, id);
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. getPersonById", JOptionPane.OK_OPTION);
		}
		return person;
	}

	@Override
	public Collection<Person> getAllPersons() throws SQLException {
		// TODO Auto-generated method stub
		List<Person> persons = null;
		try {
			EntityManager em = Factory.ef.createEntityManager();
			persons = em.createQuery("FROM Person r", Person.class)
					.getResultList();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		}
		return persons;
	}

	@Override
	public void deletePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		try {
			EntityManager em = Factory.ef.createEntityManager();
			Person attached = em.find(Person.class, person.getId());
			em.getTransaction().begin();
			em.remove(attached);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error. deletePerson", JOptionPane.OK_OPTION);
		}
	}

}
