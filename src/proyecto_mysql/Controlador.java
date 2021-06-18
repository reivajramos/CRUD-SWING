
package proyecto_mysql;

import java.awt.event.*;
import java.sql.Date;
import java.util.*;

/**
 * Clase que hace de Controlador, permite interactuar entre la vista y el modelo según la peticioón realizada
 * por el usuario desde la interfaz gráfica
 * @author Cristhian Forero
 * 
 */

public class Controlador extends WindowAdapter implements ActionListener{
    
    private FrmInterfazGrafica formulario;
    private String mensaje;
    private ModeloDatos modelo;
    
    public Controlador(FrmInterfazGrafica formulario) {
        this.formulario = formulario;
        modelo = new ModeloDatos();
    }

    public Controlador(String mensaje) {
        this.mensaje= mensaje;  
    }  
    
    @Override
    public void windowOpened(WindowEvent we){//Se ejecuta cuando se abre la ventana        
        cargarComboBox();
        mostrarEstudiantes();       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {//Maneja los eventos de cada elemento swing
        
        Object componente = e.getSource();//Almacena el componente que origina del evento
        
        //Botón Refrescar
        if(componente == formulario.getBtnRefrescar()){            
            mostrarEstudiantes();
        }
        //Botón Insertar
         else if(componente == formulario.getBtnInsertar()){
            crearEstudiante();                                            
        }
         //Botón Modificar
        else if(componente == formulario.getBtnModificar()){
            actualizarEstudiante();                       
        }
        //Botón Buscar
        else if(componente == formulario.getBtnBuscar()){
            buscarEstudiante();                                    
        }
        //Botón Eliminar 
        else if(componente == formulario.getBtnEliminar()){         
            borrarEstudiante();
        }
        //Botón Limpiar
        else if(componente == formulario.getBtnLimpiar()){
            limpiarCampos();
        }        
    }
   
    //-------------------------CRUD--ESTUDIANTE-------------------------------------------
    
    private void crearEstudiante(){//CREATE estudiante            
        
        if(validacionFormulario()){
            mensaje = "No pueden haber campos vacios";
            generarMensajes();
        }else{
            
            Object estudiantes [][]= modelo.getEstudiantes("BUSCAR", formulario.getCampoCodigo().getText());
            
            if(estudiantes.length==0){                
                modelo.procesaEstudiante(obtenerDatosFormulario(), "INSERTAR", 0);
                mostrarEstudiantes();
                limpiarCampos();
                
            }else{
                mensaje = "El Código ingresado pertenece a un estudiante registrado";
                generarMensajes();
                limpiarCampos();
            }            
        }
    }
    
    public void mostrarEstudiantes(){ // READ estudiante y mostrarlos en JTable        
        
        Object infoEstudiantes [][]= modelo.getEstudiantes("MOSTRAR", "");
        String carreras [][] = modelo.getCarreras();
        
        //----------------------------------------------------------------------
        //Código que guarda en el array el nombre_carrera correspondiente al id_carrera que esta como llave foranea en estudiante
        for (int i = 0; i < infoEstudiantes.length; i++) {
            int x=0;
            while(x<carreras.length){
                if(infoEstudiantes[i][5].toString().equals(carreras[x][0])){
                    infoEstudiantes[i][5]=carreras[x][1];
                }                
                x++;              
            }
        }        
        //----------------------------------------------------------------------
        
        //Pintar datos en el JTable
        String tituloCampos [] ={"ID", "CÓDIGO", "NOMBRE", "FECHA", "GÉNERO", "CARRERA", "INTERESES"};
        formulario.getTablaModelo().setDataVector(infoEstudiantes, tituloCampos);
        formulario.getTablaDatos().setModel(formulario.getTablaModelo());        
    }
    
    private void buscarEstudiante(){//READ---Buscar si un estudiante existe dentro de la BD
        String codigo = formulario.getCampoCodigo().getText();
        
        if(codigo.equals("")){
            mensaje = "Ingrese criterio de búsqueda (CÓDIGO)";
            generarMensajes();
        }else{
            
            Object busqueda [][]= modelo.getEstudiantes("BUSCAR", codigo);
            
            if(busqueda.length==0){
                mensaje = "El estudiante no existe";
                generarMensajes();
                limpiarCampos();
            }else{
                //Poner datos encontrados en cada elemnto swing
                formulario.getCampoId().setText(busqueda[0][0].toString());
                formulario.getCampoNombre().setText(busqueda[0][2].toString());
            
                //Parseo de la fecha de java.sql.Date a java.util.Date            
                java.sql.Date fecha = Date.valueOf(busqueda[0][3].toString());
                java.util.Date fechaP = (Date)fecha;
                formulario.getCampoFecha().setDate(fechaP);
            
                if(busqueda[0][4].toString().equals("M")){
                    formulario.getRadioMasculino().setSelected(true);
                }else if(busqueda[0][4].toString().equals("F")){
                    formulario.getRadioFemenino().setSelected(true);
                }
            
            formulario.getComboCarrera().setSelectedIndex(Integer.parseInt(busqueda[0][5].toString()));
                
            }            
        }     
    }
    
    private void actualizarEstudiante(){//UPDATE estudiante
        
        if(formulario.getCampoId().getText().equals("") || formulario.getCampoCodigo().getText().equals("")){                
            mensaje = "Por favor realice la búsqueda del estudiante primero";
            generarMensajes();
        }else{
            
            if(validacionFormulario()){
                mensaje = "No pueden haber campos vacios";
                generarMensajes();
            }else{
                modelo.procesaEstudiante(obtenerDatosFormulario(), "ACTUALIZAR", Integer.parseInt(formulario.getCampoId().getText()));
                mostrarEstudiantes();
                limpiarCampos();
            }            
        }       
    }
    
    private void borrarEstudiante(){//DELETE estudiante
        
        if(formulario.getCampoId().getText().equals("") || formulario.getCampoCodigo().getText().equals("")){                
            mensaje = "Por favor realice la búsqueda del estudiante primero";
            generarMensajes();
        }else{
            
            if(validacionFormulario()){
                mensaje = "No pueden haber campos vacios";
                generarMensajes();
            }else{
                modelo.procesaEstudiante(obtenerDatosFormulario(), "ELIMINAR", Integer.parseInt(formulario.getCampoId().getText()));
                mostrarEstudiantes();
                limpiarCampos();
            }            
        } 
    }
    
    //-----------------------FIN--CRUD--ESTUDIANTE----------------------------------------
    
    private ArrayList <Object> obtenerDatosFormulario(){
        
        ArrayList <Object> datosForm = new ArrayList<>();
        
        datosForm .add(formulario.getCampoCodigo().getText());
        datosForm .add(formulario.getCampoNombre().getText());
           
        //Parseo de la fecha en formato Date SQL        
        java.util.Date date = formulario.getCampoFecha().getDate();            
        java.sql.Date fecha = new java.sql.Date(date.getTime());        
        datosForm .add(fecha);
        
        if(formulario.getRadioMasculino().isSelected()){
            datosForm .add("M");
        }else if(formulario.getRadioFemenino().isSelected()){
            datosForm .add("F");
        }
        
        datosForm .add(formulario.getComboCarrera().getSelectedIndex());
        
        String intereses = "";//Se concatenan los intereses por el nombre de cada JCheckBox
                
        if(formulario.getCheckDanza().isSelected()) intereses += formulario.getCheckDanza().getActionCommand()+";";
        
        if(formulario.getCheckDeportes().isSelected())intereses += formulario.getCheckDeportes().getActionCommand()+";";
            
        if(formulario.getCheckLectura().isSelected()) intereses += formulario.getCheckLectura().getActionCommand()+";";
            
        if(formulario.getCheckMusica().isSelected())  intereses += formulario.getCheckMusica().getActionCommand()+";";
            
        if(formulario.getCheckTeatro().isSelected()) intereses += formulario.getCheckTeatro().getActionCommand()+";";
            
        if(formulario.getCheckVideo().isSelected()) intereses += formulario.getCheckVideo().getActionCommand()+";";
        
        datosForm .add(intereses);
        
        return datosForm;
    }
    
     private void cargarComboBox(){
        
        String carreras [][] = modelo.getCarreras();        
        //Se cargan en el JComboBox los datos que pertenecen a la columna nombre_carrera
        for (String[] carrera : carreras) {
            formulario.getComboCarrera().addItem(carrera[1]);
        }    
    }
    
    public boolean validacionFormulario(){
        
        boolean invalido = false;        
               
        if(formulario.getCampoCodigo().getText().equals("") 
            || formulario.getCampoNombre().getText().equals("")
            || formulario.getCampoFecha().getCalendar()== null
            || (formulario.getRadioFemenino().isSelected()==false && formulario.getRadioMasculino().isSelected()==false)
            || formulario.getComboCarrera().getSelectedIndex()==0)
        {
            invalido = true;
        }
        return invalido;
    }
    
    public void limpiarCampos(){
        
        formulario.getCampoCodigo().setText(null);
        formulario.getCampoNombre().setText(null);
        formulario.getCampoFecha().setCalendar(null);
        formulario.getComboCarrera().setSelectedIndex(0);
        formulario.getRadioFemenino().setSelected(false);
        formulario.getRadioMasculino().setSelected(false);
        formulario.getCheckDanza().setSelected(false);
        formulario.getCheckDeportes().setSelected(false);
        formulario.getCheckLectura().setSelected(false);
        formulario.getCheckMusica().setSelected(false);
        formulario.getCheckTeatro().setSelected(false);
        formulario.getCheckVideo().setSelected(false);
        formulario.getCampoCodigo().requestFocus();
    }
    
    public void generarMensajes(){        
        javax.swing.JOptionPane.showMessageDialog(formulario, mensaje);
    }  
}
