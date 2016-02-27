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
@Table(name = "last_fetch")
public class LastFetch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date last_fetch_at;

	public Date getLast_fetch_at() {
		return last_fetch_at;
	}

	public void setLast_fetch_at(final Date last_fetch_at) {
		this.last_fetch_at = last_fetch_at;
	}
}
