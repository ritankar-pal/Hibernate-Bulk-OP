package in.ineuron.Test;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class BulkSelectNamedParameter {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getSession();
			//Prepare the Query to hold HQL::
			Query query = session.createQuery("From in.ineuron.model.Product where pname in(:prod1, :prod2)");
			
			//set values to the named parameter::
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			//Execute the Query::
			List<Product> products = query.list();
			
			//process the list object:: 
			products.forEach(System.out::println);
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
