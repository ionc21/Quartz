package com.quartz.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quartz.datamodel.User;
import com.quartz.model.QueryResult;
import com.quartz.service.util.WriteToCsv;

@Service
@PropertySource("classpath:query.properties")
public class QuartzService {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private WriteToCsv writeToCsv;
	@Autowired
	private Environment environment;
	
	@Transactional
	public void executeSqlQueries() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<QueryResult> queryResults = new ArrayList<QueryResult>();
		
//		Query selectQuery = entityManager.createNativeQuery("Select * from user");
//		List<User> users = (List<User>)selectQuery.getResultList();
//
//		for (User user : users) {
//			System.out.println("here should be user name " + user.getName());
//		}

		// Query updateQuery = entityManager.createNativeQuery("update last_fetch set last_fetch_at = current_timestamp;");
		// int result = updateQuery.executeUpdate();
		// System.out.println(result);

		Query insertQuery = entityManager.createNativeQuery(environment.getProperty("insert"));		
		QueryResult queryResult = new QueryResult();
		queryResult.setQueryName("Insert Query");
		try {
			int insertResult = insertQuery.executeUpdate();
			queryResult.setQueryResult(insertResult +"");
		} catch (Exception e) {
			System.out.println(e);
			queryResult.setQueryExceptions(e.getMessage());
		}
		queryResults.add(queryResult);
		
		writeToCsv.write(queryResults);

		Query deleteQuery = entityManager.createNativeQuery("delete from example_table;");
		int deleteResult = deleteQuery.executeUpdate();
		System.out.println(deleteResult);
	}

}
