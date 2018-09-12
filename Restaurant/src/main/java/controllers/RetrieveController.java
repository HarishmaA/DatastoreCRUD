
package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@WebServlet(name = "RetrieveController", urlPatterns = "/retrieve")
public class RetrieveController extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Integer receipeId = Integer.parseInt(request.getParameter("receipeId"));
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key receipeKey = KeyFactory.createKey("Receipe", receipeId);
		Entity result = null;
		try {
			result = datastore.get(receipeKey);
		}
		catch (EntityNotFoundException e) {
			out.println("Oops! Receipe not found!!");
		}
		out.println(result);
	}
}



//String receipeKeyString1 = KeyFactory.keyToString(receipeKey);
//out.println(receipeKeyString1);
