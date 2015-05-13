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
 * Servlet implementation class SimulatedAnealingServlet
 * 
 * @created 26 / 4 / 2015
 * @author baonc
 */
public class SimulatedAnealingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimulatedAnealingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double T = 0;
		double alpha = 0;
		double T_min = 0;
		boolean exception = false;
		
		try {
			T = Double.parseDouble(request.getParameter("T"));
			alpha = Double.parseDouble(request.getParameter("alpha"));
			T_min = Double.parseDouble(request.getParameter("T_min"));
		} catch(Exception e) {
			exception = true;
		}
		
		if(exception == true) {
			response.sendRedirect("Error.jsp");
		} else {
			// Reading file to get input
			String fileName = "";
			UploadFileManagerment uploadManager = new UploadFileManagerment();
			fileName = uploadManager.getFileName();
			Chemical chemical = new Chemical(fileName);
			chemical.stateModel();
			VarIntLS result[] = chemical.simulatedAnnealingSearch(T, alpha, T_min, 300);
			
			// sent to client
			RequestDispatcher dispatcher = request.getRequestDispatcher("Result.jsp");
			request.setAttribute("result", result);
			dispatcher.forward(request, response);
		}
	}
}
