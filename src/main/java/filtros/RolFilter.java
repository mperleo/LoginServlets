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
		
		HttpSession sesion = req.getSession(false);
		String path = req.getRequestURI(); 
		
		if (sesion != null || path.endsWith("login.jsp")) {	
			
			chain.doFilter(request, response);
		} else {
			// redirige al login, cuando no hay una sesion activa
			res.sendRedirect("/ProyectoTienda/login.jsp");
			logger.info("Sesion caducada");
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
