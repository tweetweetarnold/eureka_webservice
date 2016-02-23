package services;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import model.Food;

import connection.MyConnection;

public class Indexer {
	private static void displayContactTableData() {
		Session session = null;

		try {
			session = MyConnection.getSession();

			// Fetching saved data
			List<Food> contactList = session.createQuery("from Food").list();

			for (Food contact : contactList) {
				System.out.println(contact);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private static void doIndex() throws InterruptedException {
		Session session = MyConnection.getSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();
		System.out.println("INDEXING");
		fullTextSession.close();
	}

	public static void main(String[] args) throws InterruptedException {
		doIndex();
	}

	public List<Food> search(String queryString) {
		Session session = MyConnection.getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Food.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("name").matching(queryString)
				.createQuery();

		// wrap Lucene query in a javax.persistence.Query
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Food.class);

		List<Food> contactList = fullTextQuery.list();

		fullTextSession.close();

		return contactList;
	}
}