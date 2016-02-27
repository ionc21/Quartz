package com.quartz.model;

public class QueryResult {
	
	private String queryName;
	private String queryResult;
	private String queryExceptions;
	
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}
	public String getQueryExceptions() {
		return queryExceptions;
	}
	public void setQueryExceptions(String queryExceptions) {
		this.queryExceptions = queryExceptions;
	}

}
