package com.test.todo.DAO;

public class Factory {

	private static IPersonDAO personDAO = null;
	private static ITaskDAO taskDAO = null;
	private static ILogTaskDAO logTaskDAO = null;
	private static Factory instance = null;

	public static synchronized Factory getInstance() {
		if (instance == null) {
			instance = new Factory();
		}
		return instance;
	}

	public IPersonDAO getPersonDAO() {
		if (personDAO == null) {
			personDAO = new PersonDAO();
		}
		return personDAO;
	}

	public ITaskDAO getTaskDAO() {
		if (taskDAO == null) {
			taskDAO = new TaskDAO();
		}
		return taskDAO;
	}

	public ILogTaskDAO getLogTaskDAO() {
		if (logTaskDAO == null) {
			logTaskDAO = new LogTaskDAO();
		}
		return logTaskDAO;
	}
}