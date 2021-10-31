package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DAO.CategoriaDAO;
import DTO.Categoria;

/**
 * Servlet implementation class rolNuevo
 */
@WebServlet("/categoriaNuevo")
public class categoriaNuevo extends HttpServlet {
	private static Logger logger = LogManager.getLogger(categoriaNuevo.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categoriaNuevo() {
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
		Categoria nueva = new Categoria( request.getParameter("nombre"),request.getParameter("descripcion")) ;
		
		if(nueva!=null) {
			if(CategoriaDAO.insertar(nueva)) {
				response.sendRedirect("admin/categorias.jsp");
			}
			else {
				response.sendRedirect("admin/categorias.jsp");
			}
		}
	}

}
