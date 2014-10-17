package com.test.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOGSTASKS")
public class LogTask {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	@Column(name = "COMMENT")
	private String comment;
	@Column(name = "HOURS")
	private Integer hours;
	@ManyToOne
	private Task task;

	public LogTask() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	};
}
