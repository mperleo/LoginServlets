<!DOCTYPE html>
<html lang="en">
    <%@ include file="js/piezas/head.jsp" %>
    <body>
         <%@ include file="js/piezas/headerYnav.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Roles</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html">Administracion</a></li>
                            <li class="breadcrumb-item active">Roles</li>
                        </ol>
                        <!--<div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                   Esto queda bien para poner los errores
                                </p>
                            </div>
                        </div>-->

                        <table class="table table-bordered table-striped table-hover">
                            <thead class="table-dark">
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Acciones</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>
                                    <a class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i> Eliminar</a>
                                    <a class="btn btn-primary btn-sm"><i class="fas fa-pen"></i> Modificar</a>
                                </td>
                              </tr>
                              <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>Otto</td>
                              </tr>
                              <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>Otto</td>
                              </tr>
                              
                            </tbody>
                        </table>

                        <div class="d-flex justify-content-end">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                                </ul>
                            </nav>
                        </div>
                        
                    </div>
                </main>
                
                 <%@ include file="js/piezas/footerYscripts.jsp" %>
                 
            </div>
        </div>  <!-- cierre del contenido que se importa del archivo js/piezas/headerYnac.jsp -->

    </body>
</html>
