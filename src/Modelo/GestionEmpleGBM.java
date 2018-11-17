package Modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class GestionEmpleGBM {

    public static void crearProcedimientos() {
        Statement call1 = null;

        try {
            String func1 ="SET GLOBAL log_bin_trust_function_creators = 1;\n"+ //Para el error 1418 de MySql
                            "drop function if exists subirSalario;\n" +
                            "create function subirSalario (codigo int, subida int) returns varchar(30)\n" +
                            "begin\n" +
                            "declare total varchar(30);\n" +
                            "\n" +
                            "update emple set salario = salario + subida where dept_no = codigo;\n" +
                            "\n" +
                            "select count(*) into total from emple where dept_no = codigo;\n" +
                            "return total;\n" +
                            "\n" +
                            "end;";

            call1 = ExamenUT1GBM.conexionUp().createStatement();
            call1.execute(func1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (call1 != null) {
                try {
                    call1.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }

    public static String subirSalario(int numdep, String cantidad){
        String sql2 = "{? = call subirSalario(?,?)}";
        try {
            CallableStatement llamada = ExamenUT1GBM.conexionUp().prepareCall(sql2);
            llamada.registerOutParameter(1, Types.ARRAY);
            llamada.setInt(2, numdep);
            llamada.setInt(3, Integer.parseInt(cantidad));
            llamada.executeUpdate();
            String nombre = llamada.getString(1);
            llamada.close();
            return nombre;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "-1";
    }


}
