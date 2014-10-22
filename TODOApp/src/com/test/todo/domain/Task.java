package com.test.todo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "STATE")
	private boolean state;

	@ManyToOne()
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(mappedBy = "task", orphanRemoval = true)
	private List<LogTask> logs = new ArrayList<LogTask>();

	public Task() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<LogTask> getLogs() {
		return logs;
	}

	public void setLogs(List<LogTask> logs) {
		this.logs = logs;
	};
}
