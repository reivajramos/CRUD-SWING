
package proyecto_mysql;

import java.sql.*;
import java.util.*;

/**
 * Clase que hace de Modelo, permite enviar y recibir información de la base de datos
 * 
 * @author Cristhian Forero
 * 
 */

public class ModeloDatos {
    
    private Conexion conex;    
    private ResultSet resultados;    
    private Controlador control;

    public ModeloDatos() {
        conex = new Conexion();
    }
    
    public Object [][] getEstudiantes(String peticion, String codigo){//Solicita datos a la BD
        
        Object estudiante [][] = null;
        Connection conectar = conex.getConexion();
        resultados = null;                
        
        try {
            
            String consultaSQL ="";            
            
            switch(peticion){
                
                case "MOSTRAR":                    
                    consultaSQL = "SELECT * FROM estudiante";                    
                break;
                
                case "BUSCAR":
                    consultaSQL = "SELECT * FROM estudiante WHERE codigo="+codigo;
                break;
            }
            
            Statement estatuto = conectar.createStatement();          
            resultados = estatuto.executeQuery(consultaSQL);            
            
            resultados.last();//Envio el resulset a la ultima posicion
            int dimension = resultados.getRow();//Obtengo el número de renglones
            resultados.beforeFirst();//Dejo el resulset por default en su posición inicial            
            estudiante = new Object[dimension][resultados.getMetaData().getColumnCount()];//Guarda estudiantes
            int i =0;          
                
            while (resultados.next()) {                
                for (int j = 0; j < resultados.getMetaData().getColumnCount(); j++) {
                    estudiante[i][j]=String.valueOf(resultados.getObject(j+1));
                }
                i++;                                
            }        
            
            resultados.close();
            conectar.close();
            
        } catch (Exception e) {
            control = new Controlador("ERROR AL LEER ENTIDAD ESTUDIANTES:\n " + e);
            control.generarMensajes();
        }
        
        return  estudiante;
    }
    
    public void procesaEstudiante(ArrayList datos, String peticion, int id){
        
        Connection conectar = conex.getConexion();
        
        try {
            
            String consulta="";
            String mensaje="";
            
            switch (peticion) {
                
                case "INSERTAR":                    
                    consulta ="INSERT INTO estudiante (codigo, nombre, fecha, genero, carrera, intereses) VALUES(?, ?, ?, ?, ?, ?)";
                    mensaje="Estudiante Ingresado";
                break;
                    
                case "ACTUALIZAR":                    
                    consulta="UPDATE estudiante SET codigo=?, nombre=?, fecha=?, genero=?, carrera=?, intereses=? WHERE id="+id;
                    mensaje="Estudiante Actualizado";
                break;
                
                case "ELIMINAR":                    
                    consulta="DELETE FROM estudiante WHERE id=?";
                    mensaje="Estudiante Eliminado";
                break;
            }
            
            PreparedStatement sentencia = conectar.prepareStatement(consulta);
            
            if(peticion.equals("ELIMINAR")){
                sentencia.setInt(1, id); 
            }else{                
                for (int i = 0; i < datos.size(); i++) {                
                    sentencia.setString((i+1), datos.get(i).toString());                       
                }                 
            }                     
            
            int r = sentencia.executeUpdate();            
            
            if(r>0){                
                control = new Controlador(mensaje);
                control .generarMensajes();                                
            }else{
                control  = new Controlador("Ha ocurrido un error");
                control .generarMensajes();                                
            }
                        
            conectar.close();
            
        } catch (Exception e) {
            control = new Controlador("ERROR AL PROCESAR ESTUDIANTE: "+ e);
            control.generarMensajes();
        }        
    }
    
    public String [][] getCarreras(){//Guarda en una coleccíon toda la informacion de la tabla carreras
        
        String carreras[][] = null;
        Connection conectar = conex.getConexion();
        
        try {
            
            Statement sentencia = conectar.createStatement();
            resultados = sentencia.executeQuery("SELECT * FROM carreras");
            resultados.last();//Envio el resulset a la ultima posicion
            int dimension = resultados.getRow();//Obtengo el número de renglones
            resultados.beforeFirst();//Dejo el resulset por default en su posición inicial
            carreras = new String[dimension][resultados.getMetaData().getColumnCount()];//guarda las carreras
            int i =0;
            
            while(resultados.next()){                
                for (int j = 0; j < resultados.getMetaData().getColumnCount(); j++) {
                    carreras[i][j]= String.valueOf(resultados.getObject(j+1));
                }
                i++;
            }
            
            resultados.close();
            conectar.close();
            
        } catch (Exception e) {
            control = new Controlador("ERROR AL LEER ENTIDAD CARRERAS:\n " + e);
            control.generarMensajes();
        }
        
        return carreras;
    }    
}//END CLASS
