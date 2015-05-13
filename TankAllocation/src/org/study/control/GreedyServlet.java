package org.study.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import localsearch.model.VarIntLS;

import org.study.model.Chemical;
import org.study.model.UploadFileManagerment;

/**
 * Servlet implementation class GreedyServlet
 * 
 * @created 25 / 4 / 2015
 * @author baonc
 */
public class GreedyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GreedyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maxIteration = request.getParameter("maxIteration");
		boolean exception = false;
		int maxIt = 0;
		try {
			maxIt = Integer.parseInt(maxIteration);
		} catch(Exception e) {
			exception = true;
		}
		
		if(exception == true) {
			response.sendRedirect("Error.jsp");
		} else {
			// reading file to get path
			String fileName = "";
			UploadFileManagerment uploadManager = new UploadFileManagerment();
			fileName = uploadManager.getFileName();
			Chemical chemical = new Chemical(fileName);
			chemical.stateModel();
			VarIntLS result[] = chemical.greedySearch(maxIt);
			
			// sent result to client
			RequestDispatcher dispatcher = request.getRequestDispatcher("Result.jsp");
			request.setAttribute("result", result);
			dispatcher.forward(request, response);	
		}
	}
}
