package servlets;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Atras
 */
@WebServlet("/Atras")
public class Atras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atras() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String atras = request.getParameter("atras");
		String direccionIr =null;
		
		HttpSession sesion = request.getSession();
		Stack<String> pilaAtras = (Stack<String>) sesion.getAttribute("pilaAtras");
		
		if(pilaAtras != null && atras.equals("true") ) {
			String anterior = pilaAtras.pop();
			
			// si se da atras en la pagina por la que se entra a la web y la pila queda vacia se manda a la pagina desde la que se pulso el boton atras
			if(pilaAtras.empty()) {
				direccionIr = anterior;
				
			}else {
				direccionIr = pilaAtras.peek();
				
			}
		}
		System.out.println(direccionIr);
		sesion.setAttribute("pilaAtras", pilaAtras);
		response.sendRedirect(direccionIr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
