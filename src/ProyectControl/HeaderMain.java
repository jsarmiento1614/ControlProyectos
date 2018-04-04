/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

import console.JSystem;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author JSarmiento
 */
public class HeaderMain {

    public void header() {
        Calendar calendario = new GregorianCalendar();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, " ************************DEVELOPERS -> Jesús Sarmiento, Francisco Ayala, *******************");
        System.out.println("********************************************************************************************");
        System.out.println("*                            SISTEMA DE CONTROL DE PROYECTOS©                              *");
        System.out.println("*                       CEUTEC-NORTE, SAN PEDRO SULA, CORTÉS, 2018                         *");
        System.out.println("*******************************                             ********************************");
        System.out.println("*******************************                             ********************************");
        System.out.println("___________________________________________" + hora + ":" + minutos + ":" + segundos + ""
                + "_________________________________________");

        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                          ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                          ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                          ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                          ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                           ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                            ");

    }

    public void headerIniciarSesion() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t\tINICIAR SESIÓN");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerRegistro() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t      REGISTRA TU CUENTA");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerPerfil() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t\tPERFIL USUARIO");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerRecoverPassword() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\tRECUPERAR EL ACCESO A TU CUENTA");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerNewAcount() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t\t NUEVA CUENTA");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerMyAcount() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t     CONFIGURACIÓN CUENTA");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerMyProyect() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t        MENU PROYECTOS");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerAddProyect() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t      AGREGAR PROYECTOS");
        System.out.println("\t\t\t\t\t**************");
    }

    public void headerChangePass() {
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.Color.cyan, "");
        System.out.println("\t\t\t\t      CAMBIAR CONTRASEÑA");
        System.out.println("\t\t\t\t\t**************");
    }

    public void EndSoftware() {
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                       CONTROL DE PROYECTOS SE HA CERRADO CON ÉXITO                         ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "     Gracias por preferir nuestro software para una mejor experiencia en tus proyectos.     ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "            Copyright,                                                                      ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow, "                        JESÚS SARMIENTO                                                     ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow, "                        FRANCISCO AYALA                                                     ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                     CEUTEC-NORTE, SAN PEDRO SULA, CORTÉS, HONDURAS C.A                     ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.cyan, "                                                                                            ");
        System.exit(0);
    }
}
