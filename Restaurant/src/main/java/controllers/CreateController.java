package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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



@WebServlet(name = "CreateController", urlPatterns = "/create")
public class CreateController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Integer receipeId = Integer.parseInt(request.getParameter("receipeId"));
		String receipeName = request.getParameter("receipeName");
		Entity receipe = new Entity("Receipe", receipeId);
		receipe.setProperty("receipeId", receipeId);
		receipe.setProperty("receipeName", receipeName);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key receipeKey = KeyFactory.createKey("Receipe", receipeId);
		datastore.put(receipe);
		out.println("The added receipe is...");
		Entity result = null;
		try {
			result = datastore.get(receipeKey);
		} catch (EntityNotFoundException e) {
			out.println("Oops! Receipe not found!!");
		}
		out.println(result);
	}
}




// String receipeKeyString1 = KeyFactory.keyToString(receipeKey);
// String receipeKeyString2 = KeyFactory.keyToString(receipeKey1);
// String userKey = KeyFactory.keyToString(receipe.getKey());
// out.println(receipeKeyString1);
// out.println(receipeKeyString2);
// out.println(userKey);
// String receipeKeyString = KeyFactory.keyToString(receipeKey);
// Key k = KeyFactory.stringToKey(k_str);
