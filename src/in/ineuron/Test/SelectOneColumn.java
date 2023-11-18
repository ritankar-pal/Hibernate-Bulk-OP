package in.ineuron.Test;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class SelectOneColumn {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getSession();
			//Prepare the Query to hold HQL::
			Query query = session.createQuery("select price from in.ineuron.model.Product where pname in(:prod1, :prod2)");
			
			//set values of the named parameter::
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			//Execute the Query::
			List<Integer> products = query.getResultList();
			
			//process the list object:: 
			System.out.println("PRICE");
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
