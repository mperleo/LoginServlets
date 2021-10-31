package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DAO.UsuarioDAO;

/**
 * Servlet Filter implementation class RolFilter
 */
@WebFilter("/RolFilter")
public class RolFilter implements Filter {
	
	private static Logger logger = LogManager.getLogger(RolFilter.class);
    /**
     * Default constructor. 
     */
    public RolFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession sesion = req.getSession();
		String path = req.getRequestURI(); 

		if (sesion == null || sesion.getAttribute("usuarioROL") == null) {	
			// si no hay usuario registrado o si no hay una sesion iniciada

			// si intenta acceder a la parte de paginas de administrador se deniega la entrada
			if(path.contains("/admin")) {
				logger.error("Usuario ha intentado acceder a una pagina sin permisos "+req.getRequestURI());
				res.sendRedirect("/ProyectoTienda/error.jsp");
			}
			// si intenta acceder a las partes publicas de la web se permite el acceso
			// si se intenta acceder a un recurso .jsp
			else if (path.contains(".jsp") ){
				if( path.contains("login.jsp") || path.contains("index.jsp") || path.contains("signin.jsp") || path.contains("error.jsp") || path.contains("Login") ) {
					chain.doFilter(request, response);
				}
				// si intenta acceder a las partes de la web para usuarios registrados se manda al usuario a la pagina de login
				else {
					logger.warn("Usuario ha intentado acceder a un recurso de usuario registrado sin estar registrado "+req.getRequestURI());
					res.sendRedirect("/ProyectoTienda/login.jsp");
				}
			}
			// si se pide un recurso que no esta ni en la carpta administrador ni es una pagina jsp se manda la peticion
			else {
				chain.doFilter(request, response);
			}
			
		} else {
			Integer rolUsuario = (Integer) sesion.getAttribute("usuarioROL");
			
			// administrador tiene acceso a todas las partes
			if(rolUsuario == 1) {
				chain.doFilter(request, response);
			}
			else if(rolUsuario == 2) {
				// si intenta acceder a la parte de paginas de administrador se deniega la entrada
				if(path.contains("/admin")) {
					logger.error("Usuario ha intentado acceder a una pagina sin permisos "+req.getRequestURI());
					res.sendRedirect("/ProyectoTienda/error.jsp");
				}
				// el resto de peticiones se permiten
				else {
					chain.doFilter(request, response);
				}
			}
			// redirige al login, cuando no hay una sesion activa
			//res.sendRedirect("/ProyectoTienda/login.jsp");
			
			
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
