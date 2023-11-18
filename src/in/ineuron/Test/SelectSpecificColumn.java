package in.ineuron.Test;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class SelectSpecificColumn {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getSession();
			//Prepare the Query to hold HQL::
			Query query = session.createQuery("select pname, price, qty from in.ineuron.model.Product where pname in(:prod1, :prod2)");
			
			//set values to the named parameter::
			query.setParameter("prod1", "fossil");
			query.setParameter("prod2", "tissot");
			
			//Execute the Query::
			List<Object[]> products = query.list();
			
			//process the list object:: 
			System.out.println("BRAND\tPRICE\tQTY");
//			System.out.println(products.size());
			products.forEach(row -> {
				for (Object objects : row) {
					System.out.print(objects + "\t");
				}
				System.out.println();
			});
			
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
