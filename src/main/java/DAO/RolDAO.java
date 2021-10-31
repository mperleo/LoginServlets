package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DTO.Rol;

public class RolDAO {
	private static Logger logger = LogManager.getLogger(RolDAO.class);
	
    public static ArrayList<Rol> seleccionarRoles(){
        ArrayList<Rol> roles = new ArrayList<Rol>();
        
        Connection con = Conexion.getConexion();
    

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM roles");
	        
	        ResultSet resultado = ps.executeQuery();
	        
	        while(resultado.next()) {
	        	Rol rol = new Rol(resultado.getInt("id"), resultado.getString("rol"));  
	        	roles.add(rol);
	        }
	        ps.close();
        } catch (SQLException e) {
        	logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
            e.printStackTrace();
        } 
        
        Conexion.desconectar();
        return roles;
    }
    
    public static Rol seleccionarRol(int id){
    	Rol rol = null;
    	Connection con = Conexion.getConexion();
    	
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM roles WHERE id = ?");
			
			ps.setInt(1, id);
	        
	        ResultSet resultado = ps.executeQuery();
	        resultado.next();
	        
	        rol = new Rol(resultado.getInt("id"), resultado.getString("nombre"));
	        
	        ps.close();
		} catch (SQLException e) {
			logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
			e.printStackTrace();
		}
       
        Conexion.desconectar();
        
        return rol;
    }
    
    
    public static boolean insertar(String rol){
    	boolean insertado = false;
    	Connection con = Conexion.getConexion();
	    try {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO roles (rol) " + "VALUES (?)");
	
	        ps.setString(1, rol);
	        int resultado = ps.executeUpdate();
	
	        if (resultado != 0) {
	        	insertado = true;
	        }
	
	        ps.close();
	        
	        
    	} catch (SQLException e) {
			logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
			e.printStackTrace();
		}    
	   
        Conexion.desconectar();
        return insertado;
    }
}
