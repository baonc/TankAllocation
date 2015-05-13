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
 * Servlet implementation class TabuServlet
 * 
 * @created 26 / 4 / 2015
 * @author baonc
 */
public class TabuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tabuLength = 0;
		int maxTime = 0;
		int maxIteration = 0;
		int maxStable = 0;
		boolean exception = false;
		
		try{
			tabuLength = Integer.parseInt(request.getParameter("tabuLength"));
			maxTime =  Integer.parseInt(request.getParameter("maxTime"));
			maxIteration = Integer.parseInt(request.getParameter("maxIte"));
			maxStable = Integer.parseInt(request.getParameter("maxStable"));
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
			VarIntLS result[] = chemical.tabuSearchLib(tabuLength, maxTime, maxIteration, maxStable);
			// sent to client
			RequestDispatcher dispatcher = request.getRequestDispatcher("Result.jsp");
			request.setAttribute("result", result);
			dispatcher.forward(request, response);
		}
	}

}
