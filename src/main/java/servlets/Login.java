package servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import DAO.UsuarioDAO;
import DTO.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fichero de logs
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		System.out.println(url);
		PropertyConfigurator.configure(url);
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String clave = request.getParameter("pass");
		
		if(email==null || clave==null) {
			response.sendRedirect("login.html");
		} else {
			Usuario usuario = UsuarioDAO.seleccionarUsuarioLogin(email, clave);
			if(usuario==null) {
				response.sendRedirect("login.html");
			} else {
				if(usuario.getId_rol()==1) {
					response.sendRedirect("/admin/index.html");
				}
				else if(usuario.getId_rol()==2) {
					response.sendRedirect("index.html");
				}
				//response.getWriter().println("Login correcto.");
			}
		}
	}

}
