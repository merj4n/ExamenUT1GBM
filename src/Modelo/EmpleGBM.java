package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleGBM {

    public static String consultaApellidos(String x) {
        String salida="";
        PreparedStatement ps = null;
        try {
            String sql="select apellido\n" +
                    "from emple e, depart d\n" +
                    "where upper(D.DNOMBRE)= ?\n" +
                    "and E.DEPT_NO=D.DEPT_NO;";
            ps = ExamenUT1GBM.conexionUp().prepareStatement(sql);
            ps.setString(1,x);//Dato a obtener del comboBox
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++)
                    salida+=rs.getString(i)+"\n";
                System.out.println();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;
    }
    public static List obtenerOficio(){
        List<String> listaoficio = new ArrayList<>();
        Statement st = null;
        try {
            st = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = st.executeQuery("select oficio from emple group by oficio");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++){
                    listaoficio.add(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaoficio;

    }

    public static List obtenerDir(){
        List<String> listadir = new ArrayList<>();
        Statement st = null;
        try {
            st = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = st.executeQuery("select apellido from emple");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++){
                    listadir.add(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listadir;
    }

    public static int obtenerNumDir(String ape){
        int salida=0;
        Statement st = null;
        try {
            st = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = st.executeQuery("select emp_no from emple where apellido = '"+ape+"'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++){
                    salida=Integer.parseInt(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salida;

    }

    public static void insertaEmp(String ape,String ofi,int dir,int sal,int com,int dep){

        int emp_no = numEmpleado();
        String apellido = ape;
        String oficio = ofi;
        int director = dir;
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        int salario = sal;
        int comision = com;
        int dept_no = dep;
        PreparedStatement ps= null;
        try {
            ps = ExamenUT1GBM.conexionUp().prepareStatement("INSERT INTO EMPLE VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1,emp_no);
            ps.setString(2,apellido);
            ps.setString(3,oficio);
            ps.setInt(4,director);
            ps.setDate(5,sqlDate);
            ps.setInt(6,salario);
            ps.setInt(7,comision);
            ps.setInt(8,dept_no);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int numEmpleado(){
        int num=0;
        Statement sentencia = null;
        try {
            sentencia = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = sentencia.executeQuery("select emp_no from emple;");
            while (rs.next()) {
                String dato = rs.getString(1);
                if (Integer.parseInt(dato) > num) {
                    num = Integer.parseInt(dato);
                }
            }
        sentencia.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num+1;
    }
    public static String listaEmpleados(int numdep){
        String num=Integer.toString(numdep);
        PreparedStatement ps= null;
        String valor="";
        try {
            ps = ExamenUT1GBM.conexionUp().prepareStatement("select * from emple where dept_no = ?");
            ps.setString(1,num);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()){
                for (int i=1;i<=columnsNumber;i++){
                    valor= valor+rs.getString(i)+" ";
                }
                //System.out.println(valor);
                valor=valor+"\n";
            }
            ps.close();
            return valor;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

}
