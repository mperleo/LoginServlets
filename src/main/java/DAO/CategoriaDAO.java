package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DTO.Categoria;

public class CategoriaDAO {
private static Logger logger = LogManager.getLogger(CategoriaDAO.class);
	
    public static ArrayList<Categoria> seleccionarCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        
        Connection con = Conexion.getConexion();
    

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categorias");
	        
	        ResultSet resultado = ps.executeQuery();
	        
	        while(resultado.next()) {
	        	Categoria categoria = new Categoria(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("descripcion"));  
	        	categorias.add(categoria);
	        }
	        ps.close();
        } catch (SQLException e) {
        	logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
            e.printStackTrace();
        } 
        
        Conexion.desconectar();
        return categorias;
    }
    
    public static Categoria seleccionarCategoria(int id){
    	Categoria categoria = null;
    	Connection con = Conexion.getConexion();
    	
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM categorias WHERE id = ?");
			
			ps.setInt(1, id);
	        
	        ResultSet resultado = ps.executeQuery();
	        resultado.next();
	        
	        categoria = new Categoria(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("descripcion"));  
	        
	        ps.close();
		} catch (SQLException e) {
			logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
			e.printStackTrace();
		}
       
        Conexion.desconectar();
        
        return categoria;
    }
    
    
    public static boolean insertar(Categoria nueva){
    	boolean insertado = false;
    	Connection con = Conexion.getConexion();
	    try {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO categorias (nombre, descripcion) " + "VALUES (?, ?)");
	
	        ps.setString(1, nueva.getNombre());
	        ps.setString(2, nueva.getDescripcion());
	        int resultado = ps.executeUpdate();
	
	        if (resultado != 0) {
	        	insertado = true;
	        }
	
	        ps.close();
	        
	        
    	} catch (SQLException e) {
			logger.error("Fallo al insertar datos de la base de datos" + e.getMessage());
			e.printStackTrace();
		}    
	   
        Conexion.desconectar();
        return insertado;
    }
}
