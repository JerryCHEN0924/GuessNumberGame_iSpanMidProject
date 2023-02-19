package test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ServletContextListenerTemp implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent e)  {
		try {			
			//抓取JNDI(Java Naming and Directory Interface)的DataSource資源
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/midrush");
			
			/* /META-INF/context.xml
			 * <?xml version="1.0" encoding="UTF-8"?>
			 * <Context> 
			 * 	<Resource name="jdbc/northwind" type="javax.sql.DataSource"
			 * 		driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
			 * 		url="jdbc:sqlserver://localhost:1433;databaseName=northwind;" 
			 * 		username="sa" password="as" />
			 * </Context>
			 */
		
			ServletContext servletContext = e.getServletContext();
			servletContext.setAttribute("ds-sqlsrv-midrush", ds);
			
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
    }	
}
