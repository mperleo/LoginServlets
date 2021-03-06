package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DAO.RolDAO;
import DAO.UsuarioDAO;

/**
 * Servlet implementation class rolNuevo
 */
@WebServlet("/rolNuevo")
public class rolNuevo extends HttpServlet {
	private static Logger logger = LogManager.getLogger(rolNuevo.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rolNuevo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rol = request.getParameter("rol");
		
		if(rol!=null) {
			if(RolDAO.insertar(rol)) {
				response.sendRedirect("admin/roles.jsp");
			}
			else {
				response.sendRedirect("admin/roles.jsp");
			}
		}
	}

}
