package in.ineuron.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;


public class InsertRecord {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				 transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				
				Product product = new Product();
				product.setPname("Fastrack");
				product.setPrice(7000);
				product.setQty(15);
				
				id = (Integer)session.save(product);
				flag = true;
			}
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(flag) {
				transaction.commit();
				System.out.println("Object updated to the database with the PK ID:: " + id);
			}
			else {
				transaction.rollback();
				System.out.println("Object failed to update...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
