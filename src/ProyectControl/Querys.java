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

    public boolean validarPassword(String password, String email) {

        conn = connectDB();
        String query = " Select password from usuarios where email = ?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, email);
            resultadotabla = consulta.executeQuery();

            if (password.equals(resultadotabla.getString(1))) {
                return true;
            }
            disconnectDB();
        } catch (SQLException e) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            disconnectDB();
        }

        return false;
    }

    public void insertNewPass(String newPass, String userEmail) {
        conn = connectDB();
        String query = "UPDATE usuarios SET password = ? WHERE email = ?;";
        PreparedStatement consulta = null;
        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, newPass);
            consulta.setString(2, userEmail);
            boolean resultado = consulta.execute();
            if (resultado) {
                System.out.println("Error al almacenar la nueva contraseña");
            } else {
                JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
                JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white, "\n------------------------CONTRASEÑA MODIFICADA SATISFACTORIAMENTE----------------------");
                JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            }
           // disconnectDB();
        } catch (SQLException e) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            disconnectDB();
        }
    }

    public boolean DelUser(int IdUser) {
        conn = connectDB();
        String query = " DELETE from usuarios where IdUsers = ?;";
        String query2 = " DELETE from proyectos where IdUsers = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, IdUser);
            consulta.executeUpdate();
            //System.out.println("eliminada");
            consulta = conn.prepareStatement(query2);
            consulta.setInt(1, IdUser);
            consulta.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("La cuenta no se pudo eliminar");
            return true;
        } finally {
            disconnectDB();
        }
    }
   /* public boolean DelTareaAndEncargados(int IdTarea) {
        conn = connectDB();
        String query = " DELETE * from tareas where IdProyect = ?;";
        String query2 = " DELETE from encargados where IdTarea = ?;";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, IdTarea);
            consulta.executeUpdate();
            //System.out.println("eliminada");
            consulta = conn.prepareStatement(query2);
            consulta.setInt(1, IdTarea);
            consulta.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("La cuenta no se pudo eliminar");
            return true;
        } finally {
            disconnectDB();
        }
    }*/

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
            //disconnectDB();

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
            //disconnectDB();
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

    public void InsertTask(String usuario, String email, int IdUser, int IdProyect, String nombreTarea, String actividadTarea, String fechaInicio, String fechaFin, String estado) {
        conn = connectDB();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from tareas");
            int IdTarea = 0;
            while (rs.next()) {
                IdTarea = rs.getInt("IdTarea");
            }
            String query = " insert into tareas(IdTarea, IdProyect, nombreTarea, actividadTarea,fechaInicio,fechaFin,estado) values (?,?,?,?,?,?,?) ";
            PreparedStatement preStmt = conn.prepareStatement(query);
            IdTarea++;
            preStmt.setInt(1, IdTarea);
            preStmt.setInt(2, IdProyect);
            preStmt.setString(3, nombreTarea);
            preStmt.setString(4, actividadTarea);
            preStmt.setString(5, fechaInicio);
            preStmt.setString(6, fechaFin);
            preStmt.setString(7, estado);
            preStmt.execute();
            //cierro la base de datos.
            //disconnectDB();
            /*JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.blue, "\n                    LA INFORMACIÓN SE HA INTRODUCIDO SATISFACTORIAMENTE                   ");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            callDisplayMetods.MyProyect(usuario, email, IdUser);*/
        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            System.out.println("Ha sucedido un error" + e);
            callDisplayMetods.MyProyect(usuario, email, IdUser);
        } finally {
            //cierro la base de datos.
            //disconnectDB();

        }
    }

    public void InsertEncargados(String usuario, String email, int IdUser, int Idtarea, String encargado) {
        conn = connectDB();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from encargados");
            int IdEncargado = 0;
            while (rs.next()) {
                IdEncargado = rs.getInt("IdEncargado");
            }
            String query = " insert into encargados(IdEncargado, IdTarea, encargado) values (?,?,?) ";
            PreparedStatement preStmt = conn.prepareStatement(query);
            IdEncargado++;
            preStmt.setInt(1, IdEncargado);
            preStmt.setInt(2, Idtarea);
            preStmt.setString(3, encargado);
            preStmt.execute();
            //cierro la base de datos.
           //disconnectDB();
           //callDisplayMetods.MyProyect(usuario, email, IdUser);
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
            preStmt = conn.prepareStatement(query);
            preStmt.setInt(1, IdUser);
            rs = preStmt.executeQuery();

            //Creo una tabla de consulta de la informacion.
            while (rs.next()) {
                tabla.append(rs.getInt(1)).append("\t");
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
            rs = stmt.executeQuery("select from proyectos where nombreProyecto = ? ");
            while (rs.next()) {
                nombreProyecto = rs.getString("nombreProyecto");

            }
            String query = "  update proyectos set (Idusers,nombreProyecto, jefe, fechaInicio, fechaFin, descripcion) values (?,?,?,?,?,?,?) where nombreProyecto = ? ";
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
    }
    //Borra Proyectos a Base de Datos.

    public void DeleteProyect(String nombreProyecto) {
        conn = connectDB();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from proyectos where nombreProyecto = ?");

            while (rs.next()) {
                nombreProyecto = rs.getString("nombreProyecto");
            }
            String query = " delete from proyectos where nombreProyecto = ? ";
            PreparedStatement preStmt = conn.prepareStatement(query);

            preStmt.setString(1, nombreProyecto);

            preStmt.execute();
            disconnectDB();
        } catch (SQLException e) {
            //Llamar error de registro encontrado.
            callDisplayMetods.QuestionRecoverPassword();
        } finally {
            //cierro la base de datos.
            disconnectDB();

        }

    }
    //Consulta Tareas a Base de Datos

    public String getInfoTask() {
        conn = connectDB();
        String query = " select * from tareas";
        PreparedStatement consulta = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        PreparedStatement preStmt = null;
        try {
            consulta = conn.prepareStatement(query);
            rs = consulta.executeQuery();
            tabla.append("Tarea Id|\tProyecto Id|\tNombre Tarea|\tActividad Tarea|\tEncargado|\tFecha Inicio|\tFecha Fin|\tEstado|\n");
            while (rs.next()) {
                tabla.append(rs.getInt(1)).append("\t");
                tabla.append(rs.getInt(2)).append("\t");
                tabla.append(rs.getString(3)).append("\t");
                tabla.append(rs.getString(4)).append("\t");
                tabla.append(rs.getString(5)).append("\t");
                tabla.append(rs.getString(6)).append("\t");
                tabla.append(rs.getString(7)).append("\t");
                tabla.append(rs.getString(8)).append("\t \n");
            }
           disconnectDB();
            return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnectDB();
        }
        return tabla.toString();
    }

    String getInfoTask(String tareas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Consulta Documentos Compartidos a Base de Datos
    public String getDocumentShare() {
        conn = connectDB();
        String query = " select * from docCompartidos";
        PreparedStatement consulta = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        PreparedStatement preStmt = null;
        try {
            consulta = conn.prepareStatement(query);
            rs = consulta.executeQuery();
            while (rs.next()) {
                tabla.append(rs.getInt(1)).append("\t");
                tabla.append(rs.getInt(2)).append("\t");
                tabla.append(rs.getString(3)).append("\t");
                tabla.append(rs.getString(4)).append("\t \n");
            }
           //  disconnectDB();
            return tabla.toString();
          
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnectDB();
        }
        return tabla.toString();
    }

    public String getInfoPerson(int IdUser) {
        conn = connectDB();
        String query = " select * from usuarios where IdUsers = ?;";
        PreparedStatement consulta = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        PreparedStatement preStmt = null;
        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, IdUser);
            rs = consulta.executeQuery();
            while (rs.next()) {
                tabla.append(rs.getInt(1)).append("\t\t");
                tabla.append(rs.getString(2)).append("\t\t");
                tabla.append(rs.getString(3)).append("\t");
                tabla.append(rs.getString(4)).append("\t\t");
                tabla.append(rs.getString(5)).append("\n");
            }
            //disconnectDB();
            return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnectDB();
        }
        return tabla.toString();
    }
    
    public int retornarIDProyect(int IdUser) {
        conn = connectDB();
        String query = "Select IdProyect from proyectos where IdUsers = ?;";
        PreparedStatement consulta = null;
        int id = 0;

        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, IdUser);
            rs = consulta.executeQuery();
            id = rs.getInt(1);
            disconnectDB();
        } catch (SQLException e) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            disconnectDB();
        }

        return id;
    }
    
    public boolean retornarNameProyect(int codigoProyecto) {
        conn = connectDB();
        String query = "Select * from proyectos where IdProyect = ?;";
        PreparedStatement consulta = null;
        try {
            consulta = conn.prepareStatement(query);
            consulta.setInt(1, codigoProyecto);
            rs = consulta.executeQuery();
            int tmp=rs.getInt(1);
            if (codigoProyecto==(tmp)) {
                return true;
            }
            disconnectDB();
        } catch (SQLException e) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            disconnectDB();
        }

        return false;
    }
    
    public int retornarIdTarea(String nameTarea) {
        conn = connectDB();
        String query = "Select * from tareas where nombreTarea = ?;";
        PreparedStatement consulta = null;
        int tmp=0;
        try {
            consulta = conn.prepareStatement(query);
            consulta.setString(1, nameTarea);
            rs = consulta.executeQuery();
            tmp=rs.getInt(1);            
            //disconnectDB();
        } catch (SQLException e) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            disconnectDB();
        }

       return tmp;
    }

}
