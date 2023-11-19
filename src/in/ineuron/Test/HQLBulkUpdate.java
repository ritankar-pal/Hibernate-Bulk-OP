package in.ineuron.Test;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class HQLBulkUpdate {

	public static void main(String[] args) {
		
		Session session = null;
		int count = 0; 
		boolean flag = false;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			
			transaction = session.beginTransaction();
			//Prepare the Query to hold HQL::
			Query query = session.createQuery("update in.ineuron.model.Product set qty = qty + :newQty where pname like"
					+ " :initialLetter");
			
			query.setParameter("newQty", 10);
			query.setParameter("initialLetter", "f%");
			
			//execute the query:: 
			count = query.executeUpdate();
			flag = true; 
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(flag) {
				transaction.commit();
				System.out.println("No of rows affected:: " + count);
			}
			else {
				transaction.rollback();
				System.out.println("Rows Failed to update");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
