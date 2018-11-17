package Modelo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartGBM {

    public static List consultaDept() {
        List<String> listadep = new ArrayList<>();
        Statement st = null;
        try {
            st = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = st.executeQuery("select dnombre from depart");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++){
                    listadep.add(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listadep;
    }
    public static int consultaDeptNo(String nom) {
        Statement st = null;
        int dep=0;
        try {
            st = ExamenUT1GBM.conexionUp().createStatement();
            ResultSet rs = st.executeQuery("select dept_no from depart where dnombre = '"+nom+"'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i <= numColumnas; i++){
                   dep=Integer.parseInt(rs.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dep;
    }
}
