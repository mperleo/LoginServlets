<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

      <!-- navbar-->
      <header class="header bg-header-page">
        <div class="container-fluid px-0 px-lg-3">
          <nav class="navbar navbar-expand-lg navbar-light py-3 px-lg-0"><a class="navbar-brand" href="index.jsp"><span class="font-weight-bold text-dark font-syne fs-2">Bikë<span class="text-primary">Meisters</span></span></a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto font-syne">
                <li class="nav-item">
                  <!-- Link--><a class="nav-link active" href="index.jsp">&#5125; Home</a>
                </li>
                <li class="nav-item">
                  <!-- Link--><a class="nav-link" href="shop.html">Ofertas</a>
                </li>
                <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorias</a>
                  <div class="dropdown-menu mt-3" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item border-0 transition-link" href="index.jsp">Homepage</a>
                    <a class="dropdown-item border-0 transition-link" href="shop.html">Category</a>
                    <a class="dropdown-item border-0 transition-link" href="detail.html">Product detail</a>
                    <a class="dropdown-item border-0 transition-link" href="cart.html">Shopping cart</a>
                    <a class="dropdown-item border-0 transition-link" href="checkout.html">Checkout</a></div>
                </li>
              </ul>
              <ul class="navbar-nav ms-auto flex-row-reverse">               
                <li class="nav-item"><a class="nav-link" href="cart.html"><i class="fas fa-dolly-flatbed mx-1 text-gray"></i>Cart<small class="text-gray"> (2)</small></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="far fa-heart mx-1"></i><small class="text-gray"></small></a></li>
                
                <%
                	if(session.getAttribute("usuarioNombre") != null ){
    					out.print("<li class='nav-item'><a class='nav-link' href='/ProyectoTienda/Login?logout=true'><i class='fas fa-user-alt mx-1 text-gray'></i>"+session.getAttribute("usuarioNombre")+", Cerrar sesión</a></li>");   
					}
                	else{
                		out.print("<li class='nav-item'><a class='nav-link' href='login.jsp'><i class='fas fa-user-alt mx-1 text-gray'></i>Login</a></li>");
                	}
				%>
              </ul>
            </div>
          </nav>
        </div>
      </header>