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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@WebServlet(name = "DeleteController", urlPatterns = "/delete")
public class DeleteController extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Integer receipeId = Integer.parseInt(request.getParameter("receipeId"));
		Key receipeKey = KeyFactory.createKey("Receipe", receipeId);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.delete(receipeKey);
		out.println("Successfully deleted!!!");

	}
}



//String receipeKeyString2 = KeyFactory.keyToString(receipeKey1);
// String userKey = KeyFactory.keyToString(receipe.getKey());
// out.println(receipeKeyString1);
// out.println(receipeKeyString2);
// out.println(userKey);
