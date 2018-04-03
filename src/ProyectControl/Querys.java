/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

import console.JSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author jsarmiento
 */
public class Querys {

    Display callDisplayMetods = new Display();
    private DataSource ds;
    private Connection conn;
    Statement stmt = null;
    ResultSet rs = null;

    private Connection connectDB() {
        ds = Conexion.getSQLLiteDataSource();
        conn = null;
        try {
            conn = this.ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    private void disconnectDB() {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void queryEmail(String email, String password) {
        conn = connectDB();
        //Consultar info de la base de datos.
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from usuarios");
            while (rs.next()) {
                if (rs.getString("email").equals(email) && rs.getString("password").equals(password)) {
                    String usuario = rs.getString("nombreUsuario");
                    int IdUser = rs.getInt("IdUsers");
                    //cierro la base de datos.
                    disconnectDB();
                    //Ingreso al perfil del usuario.
                    callDisplayMetods.Perfil(usuario, email, IdUser);
                    break;
                } else {

                }

            }
        } catch (SQLException e) {
            System.out.println("Ha sucedido un error al insertar la informacion... " + e);
        } finally {
            //cierro la base de datos.
            disconnectDB();
        }
        //Llamar correo encontrado.
        callDisplayMetods.QuestionRecoverPassword();
    }

    public void InsertRegistro(String name, String usuario, String email, String password, String pSeguridad, String rSeguridad) {
        conn = connectDB();
        //Consultar info de la base de datos.

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from usuarios");
            int IdUser = 0;
            while (rs.next()) {
                IdUser = rs.getInt("IdUsers");
            }
            String query = " insert into usuarios(IdUsers, nombreCompleto, nombreUsuario, email, password, preguntaSeguridad, respuestaSeguridad) values (?,?,?,?,?,?,?) ";
            PreparedStatement preStmt = conn.prepareStatement(query);
            IdUser++;
            preStmt.setInt(1, IdUser);
            preStmt.setString(2, name);
            preStmt.setString(3, usuario);
            preStmt.setString(4, email);
            preStmt.setString(5, password);
            preStmt.setString(6, pSeguridad);
            preStmt.setString(7, rSeguridad);
            preStmt.execute();
            System.out.println();
             //cierro la base de datos.
            disconnectDB();

        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            System.out.println("Ha sucedido un error");
            callDisplayMetods.Login();
        } finally {
             //cierro la base de datos.
            disconnectDB();
        }
    }   //Ingresa Proyectos a Base de Datos.

    public void InsertProyect(String usuario, String email, int IdUser, String nombreProyecto, String jefe, String fechaInicio, String fechaFin, String descripcion) {
        conn = connectDB();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from proyectos");
            int IdProyect = 0;
            while (rs.next()) {
                IdProyect = rs.getInt("IdProyect");
            }
            String query = " insert into proyectos(IdProyect, IdUsers, nombreProyecto, jefe, fechaInicio, fechaFin, descripcion) values (?,?,?,?,?,?,?) ";
            PreparedStatement preStmt = conn.prepareStatement(query);
            IdProyect++;
            preStmt.setInt(1, IdProyect);
            preStmt.setInt(2, IdUser);
            preStmt.setString(3, nombreProyecto);
            preStmt.setString(4, jefe);
            preStmt.setString(5, fechaInicio);
            preStmt.setString(6, fechaFin);
            preStmt.setString(7, descripcion);
            preStmt.execute();
             //cierro la base de datos.
            disconnectDB();
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.blue, "\n                    LA INFORMACIÓN SE HA INTRODUCIDO SATISFACTORIAMENTE                   ");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            callDisplayMetods.Perfil(usuario, email, IdUser);
        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            System.out.println("Ha sucedido un error" + e);
            callDisplayMetods.MyProyect(usuario, email, IdUser);
        } finally {
             //cierro la base de datos.
            disconnectDB();

        }
    }
    //Consulta Proyectos a Base de Datos

    public String getInfoProyect(int IdUser) {
        conn = connectDB();      
        PreparedStatement preStmt = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        try {
            String query = "select * from proyectos where IdUsers=?";
            preStmt= conn.prepareStatement(query);
            preStmt.setInt(1, IdUser);
            rs = preStmt.executeQuery();
           
            //Creo una tabla de consulta de la informacion.
            while (rs.next()) {
                tabla.append(rs.getString(3)).append("\t\t");
                tabla.append(rs.getString(4)).append("\t\t");
                tabla.append(rs.getString(5)).append("\t");
                tabla.append(rs.getString(6)).append("\t");
                tabla.append(rs.getString(7)).append("\t \n");
            }
            tabla.append("--------------------------------------------------------------------------------------------\n");
             //cierro la base de datos.
            disconnectDB();
            return tabla.toString();
            
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             //cierro la base de datos.
            disconnectDB();
        }
        return tabla.toString();
    }


//Modifica Proyectos a Base de Datos.
    public void ModifyProyect(int IdUsers, String nombreProyecto, String jefe, String fechaInicio, String fechaFin, String descripcion) {
        conn = connectDB();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from proyectos");
            int IdProyect = 0;
            while (rs.next()) {
                IdProyect = rs.getInt("IdProyect");
            }
            String query = "  insert into proyectos(IdProyect, IdUsers, nombreProyecto, jefe, fechaInicio, fechaFin, descripcion) values (?,?,?,?,?,?,?) where IdProyect= '' ";
            PreparedStatement preStmt = conn.prepareStatement(query);

            preStmt.setInt(1, IdUsers);
            preStmt.setString(2, nombreProyecto);
            preStmt.setString(3, jefe);
            preStmt.setString(4, fechaInicio);
            preStmt.setString(5, fechaFin);
            preStmt.setString(6, descripcion);
            preStmt.execute();
             //cierro la base de datos.
            disconnectDB();
        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
             //cierro la base de datos.
            disconnectDB();

        }
    }   //Borra Proyectos a Base de Datos.

    public void DeleteProyect() {
        conn = connectDB();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from proyectos");
            int IdProyect = 0;
            while (rs.next()) {
                IdProyect = rs.getInt("IdProyect");
            }
            String query = " delete from proyectos(IdProyect, IdUsers, nombreProyecto, jefe, fechaInicio, fechaFin, descripcion) ) where IdProyect = '' ";
            PreparedStatement preStmt = conn.prepareStatement(query);

            preStmt.setInt(1, IdProyect);

            preStmt.execute();

        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
             //cierro la base de datos.
            disconnectDB();

        }
    }

}
