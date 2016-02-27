package com.quartz.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fetch_history")
public class FetchHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fetch_at;

	public Date getFetch_at() {
		return fetch_at;
	}

	public void setFetch_at(final Date fetch_at) {
		this.fetch_at = fetch_at;
	}
}
