package com.quartz.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.quartz.datamodel.User;
import com.quartz.model.QueryResult;
import com.quartz.service.util.WriteToCsv;

@Repository
@PropertySource("classpath:query.properties")
public class QuartzDao {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private WriteToCsv writeToCsv;
	@Autowired
	private Environment environment;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void executeSqlQueries() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<QueryResult> queryResults = new ArrayList<QueryResult>();
		
		Query insertUser = entityManager.createNativeQuery(environment.getProperty("insert.to.user"));
		entityManager.joinTransaction();
		insertUser.executeUpdate();
		
		Query selectQuery = entityManager.createNativeQuery(environment.getProperty("select.from.user"), User.class);
		List<User> users = selectQuery.getResultList();
		QueryResult selectQueryResult = new QueryResult();
		for (User user : users) {
			selectQueryResult.setQueryName("Select Query");
			selectQueryResult.setQueryResult("userName: " +user.getName());
		}
		queryResults.add(selectQueryResult);
		
		Query insertToLastFetch = entityManager.createNativeQuery(environment.getProperty("insert.to.last.fetch"));
		entityManager.joinTransaction();
		insertToLastFetch.executeUpdate();		

		Query updateLastFetch = entityManager.createNativeQuery(environment.getProperty("update.last.fetch"));
		QueryResult updateQueryResult = new QueryResult();
		updateQueryResult.setQueryName("Update Query");	
		entityManager.joinTransaction();
		int result = updateLastFetch.executeUpdate();
		updateQueryResult.setQueryResult(result +"");			
		queryResults.add(updateQueryResult);
		 
		Query insertQuery = entityManager.createNativeQuery(environment.getProperty("insert.to.fetch.history"));		
		QueryResult insertQueryResult = new QueryResult();
		insertQueryResult.setQueryName("Insert Query");	
		entityManager.joinTransaction();
		int insertResult = insertQuery.executeUpdate();
		insertQueryResult.setQueryResult(insertResult +"");		
		queryResults.add(insertQueryResult);
		
		Query deleteQuery = entityManager.createNativeQuery(environment.getProperty("delete.from.user"));
		QueryResult deleteQueryResult = new QueryResult();
		deleteQueryResult.setQueryName("Delete Query");
		entityManager.joinTransaction();
		int deleteUserResult = deleteQuery.executeUpdate();
		deleteQueryResult.setQueryResult(deleteUserResult +"");		
		queryResults.add(deleteQueryResult);
		
		writeToCsv.write(queryResults);
	}

}
