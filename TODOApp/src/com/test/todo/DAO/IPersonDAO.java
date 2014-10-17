package com.test.todo.DAO;

import java.sql.SQLException;
import java.util.Collection;

import com.test.todo.domain.Person;

public interface IPersonDAO {
	
	public void addPerson(Person person) throws SQLException;
	public void updatePerson(Person person) throws SQLException;
	public Person getPersonById(Integer id) throws SQLException;
	public Collection<Person> getAllPersons() throws SQLException;
	public void deletePerson(Person person) throws SQLException;
}
