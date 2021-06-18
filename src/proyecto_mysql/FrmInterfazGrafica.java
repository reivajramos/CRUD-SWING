
package proyecto_mysql;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Clase que contiene la Interfaz Gráfica (Vista)
 * 
 * @author Cristhian Forero
 * 
 */

public class FrmInterfazGrafica extends javax.swing.JFrame {

    private DefaultTableModel tablaModelo = new DefaultTableModel();           
    
    public FrmInterfazGrafica() {
        initComponents();
        
        //Agregando objeto evento a cada elemento swing
        //JButton
        btnRefrescar.addActionListener(new Controlador(this));
        btnInsertar.addActionListener(new Controlador(this));
        btnEliminar.addActionListener(new Controlador(this));
        btnLimpiar.addActionListener(new Controlador(this));
        btnModificar.addActionListener(new Controlador(this));
        btnBuscar.addActionListener(new Controlador(this));
        
        //JRadioButton        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioMasculino);
        grupo.add(radioFemenino);
        radioMasculino.addActionListener(new Controlador(this));
        radioFemenino.addActionListener(new Controlador(this));
        
        //JComboBox
        comboCarrera.addActionListener(new Controlador(this));
        comboCarrera.addItem("SELECCIONE CARRERA");
        
        //JCheckBox
        checkDanza.addActionListener(new Controlador(this));
        checkDeportes.addActionListener(new Controlador(this));
        checkLectura.addActionListener(new Controlador(this));
        checkMusica.addActionListener(new Controlador(this));
        checkTeatro.addActionListener(new Controlador(this));
        checkVideo.addActionListener(new Controlador(this));
                
        campoFecha.setDateFormatString("yyyy/MM/d");//Formato de fecha para el Jcalendar
        
        campoId.setVisible(false); // campo oculto que almacena el id estudiante 
        
        ejecutarBarraProgreso();//Metodo que ejecuta la carga de la barra de progreso en segundo plano
        
        //Evento de tipo ventana para cargar JComboBox y JTable al inicializar el programa
        addWindowListener(new Controlador(this));           
    }
    
    //Metodos accesores para componentes encapsulados    
    
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnInsertar() {
        return btnInsertar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnRefrescar() {
        return btnRefrescar;
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public JDateChooser getCampoFecha() {
        return campoFecha;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JCheckBox getCheckDanza() {
        return checkDanza;
    }

    public JCheckBox getCheckDeportes() {
        return checkDeportes;
    }

    public JCheckBox getCheckLectura() {
        return checkLectura;
    }

    public JCheckBox getCheckMusica() {
        return checkMusica;
    }

    public JCheckBox getCheckTeatro() {
        return checkTeatro;
    }

    public JCheckBox getCheckVideo() {
        return checkVideo;
    }

    public JComboBox<String> getComboCarrera() {
        return comboCarrera;
    }

    public JRadioButton getRadioFemenino() {
        return radioFemenino;
    }

    public JRadioButton getRadioMasculino() {
        return radioMasculino;
    }
    
    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public DefaultTableModel getTablaModelo() {
        return tablaModelo;
    } 

    public JTextField getCampoId() {
        return campoId;
    }

    public  JProgressBar getBarraProgreso() {
        return barraProgreso;
    }

    public JPanel getPanelFormulario() {
        return panelFormulario;
    }

    public JPanel getPanelTabla() {
        return panelTabla;
    }

    public JLabel getLabelProgreso() {
        return labelProgreso;
    }
    
    public void ejecutarBarraProgreso(){
        
        Thread hilo = new Thread(new Runnable() {
            
            int x = 0;
            
            @Override
            public void run() {
                try {
                    
                    while (x<100) {                        
                        barraProgreso.setValue(x);
                        labelProgreso.setText(x + "%");
                        x+=2;                        
                        Thread.sleep(100);//Delay para la carga de la barra de progreso

                        if(x==100){
                          barraProgreso.setVisible(false);
                          labelProgreso.setVisible(false);
                          panelFormulario.setVisible(true);
                          panelTabla.setVisible(true);
                          titulo.setVisible(true);
                          setResizable(true);
                          setBounds(250, 0, 600, 670);
                          
                        }else{
                            panelFormulario.setVisible(false);
                            panelTabla.setVisible(false);
                            titulo.setVisible(false);
                            setResizable(false);
                            setBounds(250, 100, 250, 200);                            
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error en Thread:" +e);
                }  
            }
        });
        
        hilo.start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoFecha = new com.toedter.calendar.JDateChooser();
        radioMasculino = new javax.swing.JRadioButton();
        radioFemenino = new javax.swing.JRadioButton();
        jlabel5 = new javax.swing.JLabel();
        comboCarrera = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        checkLectura = new javax.swing.JCheckBox();
        checkMusica = new javax.swing.JCheckBox();
        checkTeatro = new javax.swing.JCheckBox();
        checkDeportes = new javax.swing.JCheckBox();
        checkDanza = new javax.swing.JCheckBox();
        checkVideo = new javax.swing.JCheckBox();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        campoId = new javax.swing.JTextField();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        btnRefrescar = new javax.swing.JButton();
        barraProgreso = new javax.swing.JProgressBar();
        labelProgreso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setText("FRANCISCO RAMOS - CRUD - SWING - OPTATIVO 2");

        panelFormulario.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel2.setText("CÓDIGO: ");

        jLabel3.setText("NOMBRE:  ");

        jLabel4.setText("FECHA NACIMIENTO:");

        radioMasculino.setText("MASCULINO");

        radioFemenino.setText("FEMENINO");

        jlabel5.setText("CARRERA: ");

        jLabel5.setText("INTERESES (Opcional): ");

        checkLectura.setText("LECTURA");

        checkMusica.setText("MÚSICA");

        checkTeatro.setText("TEATRO");

        checkDeportes.setText("DEPORTES");

        checkDanza.setText("DANZA");

        checkVideo.setText("VIDEOJUEGOS");

        btnInsertar.setText("INSERTAR");

        btnModificar.setText("MODIFICAR");

        btnEliminar.setText("ELIMINAR");

        btnBuscar.setText("BUSCAR");

        btnLimpiar.setText("LIMPIAR");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkMusica)
                                    .addComponent(checkLectura))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkDeportes)
                                    .addComponent(checkTeatro))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkVideo)
                                    .addComponent(checkDanza))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addComponent(jlabel5)
                                .addGap(37, 37, 37)
                                .addComponent(comboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(62, 62, 62)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoNombre)
                                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addComponent(radioMasculino)
                                .addGap(18, 18, 18)
                                .addComponent(radioFemenino)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(btnInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(campoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioMasculino)
                                    .addComponent(radioFemenino))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabel5)))
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addComponent(btnInsertar)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkLectura)
                            .addComponent(checkDeportes)
                            .addComponent(checkDanza)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createSequentialGroup()
                        .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkMusica)
                    .addComponent(checkTeatro)
                    .addComponent(checkVideo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaDatos.setBackground(new java.awt.Color(242, 243, 244));
        tablaDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaDatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaDatos);

        btnRefrescar.setText("REFRESCAR");

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefrescar)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnRefrescar))
        );

        barraProgreso.setForeground(new java.awt.Color(0, 153, 0));

        labelProgreso.setText("0%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(titulo)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(labelProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        FrmInterfazGrafica form = new FrmInterfazGrafica();        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                form.setTitle("PROYECTO CRUD - SWING - GITHUB - FRANCISCO RAMOS");                
                form.setVisible(true);             
            }
        });        
    }    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JTextField campoCodigo;
    private com.toedter.calendar.JDateChooser campoFecha;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JCheckBox checkDanza;
    private javax.swing.JCheckBox checkDeportes;
    private javax.swing.JCheckBox checkLectura;
    private javax.swing.JCheckBox checkMusica;
    private javax.swing.JCheckBox checkTeatro;
    private javax.swing.JCheckBox checkVideo;
    private javax.swing.JComboBox<String> comboCarrera;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel5;
    private javax.swing.JLabel labelProgreso;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JRadioButton radioFemenino;
    private javax.swing.JRadioButton radioMasculino;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    
}





