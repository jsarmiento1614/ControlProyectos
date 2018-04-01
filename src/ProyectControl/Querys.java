/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

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

    private void disconnectDB(Connection conn) {
        try {
            conn.close();
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
                    //ir al perfil del usuario
                    callDisplayMetods.Perfil(usuario, email);
                    break;
                } else {

                }
            }
        } catch (SQLException e) {
            System.out.println("Ha sucedido un error al insertar la informacion... " + e);
        } finally {
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
            } catch (SQLException e) {
                System.out.println("Ha sucedido un error en cerrar la conexiones de la db... " + e);
            }
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
            String query = " insert into usuarios(IdUsers, nombreCompleto, nombreUsuario, email, password) values (?,?,?,?,?) ";
            PreparedStatement preStmt = conn.prepareStatement(query);
            IdUser++;
            preStmt.setInt(1, IdUser);
            preStmt.setString(2, name);
            preStmt.setString(3, usuario);
            preStmt.setString(4, email);
            preStmt.setString(5, password);
            preStmt.execute();

        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
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
            } catch (SQLException e) {
                System.out.println("Ha sucedido un error en cerrar la conexiones de la db... " + e);
            }
        }
    }   //Ingresa Proyectos a Base de Datos.

    public void InsertProyect(int IdUsers, String nombreProyecto, String jefe, String fechaInicio, String fechaFin, String descripcion) {
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
            preStmt.setInt(2, IdUsers);
            preStmt.setString(3, nombreProyecto);
            preStmt.setString(4, jefe);
            preStmt.setString(5, fechaInicio);
            preStmt.setString(6, fechaFin);
            preStmt.setString(7, descripcion);
            preStmt.execute();

        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
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
            } catch (SQLException e) {
                System.out.println("Ha sucedido un error en cerrar la conexiones de la db... " + e);
            }

        }
    }
    //Consulta Proyectos a Base de Datos

    public String getInfoProyect() {
        conn = connectDB();
        String query = " select * from proyectos";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        PreparedStatement preStmt = null;
        try {
            consulta = conn.prepareStatement(query);
            resultadotabla = consulta.executeQuery();
            tabla.append("Proyecto Id|\tUser Id|\tNombre Proyecto|\tJefe|\tFecha Inicio|\tFecha Fin|\tDescripcion|\n");
            while (resultadotabla.next()) {
                tabla.append(resultadotabla.getInt(1)).append("\t");
                tabla.append(resultadotabla.getInt(2)).append("\t");
                tabla.append(resultadotabla.getString(3)).append("\t");
                tabla.append(resultadotabla.getString(4)).append("\t");
                tabla.append(resultadotabla.getString(5)).append("\t");
                tabla.append(resultadotabla.getString(6)).append("\t");
                tabla.append(resultadotabla.getString(7)).append("\t \n");
            }
            return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tabla.toString();
    }

    String getInfoProyect(String proyectos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
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
            } catch (SQLException e) {
                System.out.println("Ha sucedido un error en cerrar la conexiones de la db... " + e);
            }

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
            } catch (SQLException e) {
                System.out.println("Ha sucedido un error en cerrar la conexiones de la db... " + e);
            }

        }
    }

}
