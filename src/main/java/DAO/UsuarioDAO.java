package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import DTO.Usuario;

public class UsuarioDAO {
	
	private static Logger logger = LogManager.getLogger(UsuarioDAO.class);
	
    public static ArrayList<Usuario> seleccionarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        Connection con = Conexion.getConexion();
    

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios");
	        
	        ResultSet resultado = ps.executeQuery();
	        
	        while(resultado.next()) {
	        	Usuario usaurio = new Usuario(resultado.getInt("id"),
	        									resultado.getInt("id_rol"),
	        									resultado.getString("email"),
	        									resultado.getString("clave"),
	        									 resultado.getString("nombre"),
	        									 resultado.getString("apellido1"),
	        									 resultado.getString("apellido2"),
	        									 resultado.getString("direccion"),
	        									 resultado.getString("provincia"),
	        									 resultado.getString("localidad"),
	        									 resultado.getString("telefono"),
	        									 resultado.getString("dni")
	        									);  

	        	usuarios.add(usaurio);
	        }
	        ps.close();
        } catch (SQLException e) {
        	logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
            e.printStackTrace();
        } 
        
        Conexion.desconectar();
        return usuarios;
    }
    
    public static Usuario seleccionarUsuarioLogin(String email, String clave){
        Usuario usuario = null;
        Connection con = Conexion.getConexion();
    

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE email = ? AND clave = ?");
            ps.setString(1, email);
            ps.setString(2, clave);
	        
	        ResultSet resultado = ps.executeQuery();
	        
	        if(resultado.next()) {
	        	usuario = new Usuario(resultado.getInt("id"),
	        									resultado.getInt("id_rol"),
	        									resultado.getString("email"),
	        									resultado.getString("clave"),
	        									 resultado.getString("nombre"),
	        									 resultado.getString("apellido1"),
	        									 resultado.getString("apellido2"),
	        									 resultado.getString("direccion"),
	        									 resultado.getString("provincia"),
	        									 resultado.getString("localidad"),
	        									 resultado.getString("telefono"),
	        									 resultado.getString("dni")
	        									);  
	        }
	        
	        ps.close();
        } catch (SQLException e) {
        	logger.error("Fallo al recuperar datos de la base de datos" + e.getMessage());
            e.printStackTrace();
        } 
        
        Conexion.desconectar();
        return usuario;
    }
}