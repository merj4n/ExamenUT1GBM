package Modelo;

import Vista.EmpleadosDepGBM;
import Vista.InsertaEmpleGBM;
import Vista.SubidaSalarioGBM;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author merjan
 */
public class ExamenUT1GBM {

    public static final String EMPLEADOS="Empleados por departamento";
    public static final String INSERTAR="Insertar Empleado";
    public static final String SALARIO="Subida salario por departamento";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpleadosDepGBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                EmpleadosDepGBM emp = new EmpleadosDepGBM();
                emp.setVisible(true);
                emp.setTitle(EMPLEADOS);
                emp.setLocation(dim.width/2-emp.getSize().width/2, dim.height/2-emp.getSize().height/2);
                InsertaEmpleGBM ins = new InsertaEmpleGBM();
                ins.setVisible(true);
                ins.setTitle(INSERTAR);
                ins.setLocation(dim.width/3-emp.getSize().width/2, dim.height/2-emp.getSize().height/2);
                SubidaSalarioGBM sub = new SubidaSalarioGBM();
                sub.setVisible(true);
                sub.setTitle(SALARIO);
                sub.setLocation((dim.width/5)*3, dim.height/2-emp.getSize().height/2);
            }
        });

    }
    public static Connection conexionUp() {
        Connection conector = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/ejemploGBM?allowMultiQueries=true&useSSL=false", "root", "Surfer25**");
            conector=conexion;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conector;
    }
    
}
