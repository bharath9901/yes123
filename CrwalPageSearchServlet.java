package com.adobe.aem.guides.wknd.core.servlets;

import javax.jcr.Session;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.search.QueryBuilder;


@Component(service = Servlet.class, 
property = { 
		Constants.SERVICE_DESCRIPTION + "=Crwal Page Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, 
		"sling.servlet.paths=" + "/bin/crwalpage" 
		})
public class CrwalPageSearchServlet extends SlingSafeMethodsServlet {
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(CrwalPageSearchServlet.class);

	@Reference
	private QueryBuilder builder;
	
	private Session session;
	
	@Reference
	private CrwalPageConfigModule crwalPageconfigModule;
	
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try{
			String hostname= crwalPageconfigModule.getHostName();
			String extension=crwalPageconfigModule.getExtensionsName();
			String queryStr = "SELECT * FROM [cq:Page] AS nodes WHERE ISDESCENDANTNODE ([/content/we-retail/us/en])";
			ResourceResolver resourceResolver = request.getResourceResolver();
			session = resourceResolver.adaptTo(Session.class);
			javax.jcr.query.QueryManager queryManager= session.getWorkspace().getQueryManager();
			javax.jcr.query.Query query = queryManager.createQuery(queryStr, javax.jcr.query.Query.JCR_SQL2);
			QueryResult result = query.execute();
			javax.jcr.NodeIterator nodeIter  = result.getNodes();
			
			while ( nodeIter.hasNext() ) {
				    javax.jcr.Node node = nodeIter.nextNode();
				    response.setContentType("text/html");
				    response.getWriter().write("<a href="+hostname+node.getPath()+extension+">"+hostname+node.getPath()+extension+"</a>"+"<br>"); 
				}
		}
		catch(Exception e) {
			log.info("exception" + e.getMessage());
		}
		finally {
			
			if(session != null) {
				
				session.logout();
			}

		}
		
		
	}
}