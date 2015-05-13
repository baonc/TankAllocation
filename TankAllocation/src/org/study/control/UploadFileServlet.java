package org.study.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.study.model.UploadFileManagerment;

/**
 * Servlet implementation class UploadFileServlet
 * 
 * @created 26 / 4 / 2015
 * @author baonc
 */
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/home/baonc/data"; 
    private String name;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for(FileItem item : multiparts) {
					if(!item.isFormField()) {
						this.name = new File(item.getName()).getName();
						this.name = UPLOAD_DIRECTORY + File.separator + this.name;
						request.setAttribute("fileName", name);
						item.write(new File(this.name));
						
						// write file name to file
						UploadFileManagerment uploadManagerment = new UploadFileManagerment();
						uploadManagerment.setFileName(this.name);
					}
				}
				
				// file upload successfully
				request.setAttribute("message", "1");
			} catch(Exception e) {
				request.setAttribute("message", "0");
			}
		} else { 
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
		
		request.getRequestDispatcher("Search.jsp").forward(request, response);
	}
}
