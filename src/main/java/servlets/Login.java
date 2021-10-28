package servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		// busco si se ha indicado el parametro de cerrar sesion
		String cerrarSesion = request.getParameter("logout");
		
		if(cerrarSesion.equals("true")){
			// si se ha indicado se borra la sesion y mando al usuario al index
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String clave = request.getParameter("pass");

		if(email==null || clave==null) {
			// no se han indicado datos
			response.sendRedirect("login.jsp");
		} else {
			// busco en la bbdd los datos indicados
			Usuario usuario = UsuarioDAO.seleccionarUsuarioLogin(email, clave);
			if(usuario==null) {
				// no se ha encontrado el usuario en la bbdd
				response.sendRedirect("login.jsp");
			} else {
				// se han encontrado los datos
				
				// guardo los datos del usuario en sesion
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuarioNombre", usuario.getNombre());
				sesion.setAttribute("usuarioID", usuario.getCodigo());
				sesion.setAttribute("usuarioROL", usuario.getId_rol());
				sesion.setAttribute("usuarioDateLogin", new Date(sesion.getCreationTime()));
				
				// se manda al usuario si se han econtrado los datos al index correspondiente dependiendo si es admin o usuario registrado
				if(usuario.getId_rol()==1) {
					response.sendRedirect("admin/index.jsp");
				}
				else if(usuario.getId_rol()==2) {
					response.sendRedirect("index.jsp");
				}
				//response.getWriter().println("Login correcto.");
			}
		}
		
	}

}
