package br.com.sysloccOficial.conf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;

public class HibernateUtil {
	 
	  private static SessionFactory factory;
	 
	  static {
	    factory = new Configuration().configure().buildSessionFactory();
	  }
	 
	  public static Session openSession() {
	    return factory.openSession();
	  }
	 
	  public static Statistics getStatistics() {
	    if (!factory.getStatistics().isStatisticsEnabled()) {
	      factory.getStatistics().setStatisticsEnabled(true);
	    }
	    return factory.getStatistics();
	  }
	}
