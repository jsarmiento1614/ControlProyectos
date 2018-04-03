/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

import console.JSystem;
import java.util.Scanner;

/**
 *
 * @author jsarmiento
 */
public class Display {
    //Instancio Scanner dentro de la funcion Display principal para uso general.

    Scanner teclado = new Scanner(System.in);
    private String name;
    private String usuario;
    private String email;
    private String password;
    private String nPassword;
    private String pSeguridad;
    private String rSeguridad;
    //more variables
    private String nameProyect;
    private String jefeProyect;
    private String fInicio;
    private String fFin;
    private String descripcion;

    /**
     * **** Instancio de manera general las clases usadas.*****
     */
    HeaderMain callHeaderMain = new HeaderMain();
    //Display callDisplayMetodscallHeaderMain = new Display();
    ValidarEmail callValidator = new ValidarEmail();
    //Funcion de Inicio de Sesion de Usuarios.   

    //**************FIN INSTANCIA*****************//
    public void Login() {
        Querys callQuerys = new Querys();//Instanciar Consultas
        Display callDisplayMetods = new Display();
        int exit = 0;
        do {
            callHeaderMain.headerIniciarSesion();//llamo el menu de inicio de sesion.
            System.out.print("Ingrese Correo Electrónico:");
            JSystem.out.printColorln(JSystem.Color.cyan, " \t\t\t\t      (R) for Sign up - (S) for Exit");
            System.out.print(" ");
            email = teclado.nextLine();
            if ("R".equalsIgnoreCase(email)) {
                callDisplayMetods.registroUser();
            } else if ("S".equalsIgnoreCase(email)) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                callHeaderMain.header();
                callHeaderMain.EndSoftware();
                System.exit(0);
            }
            callValidator.validar(email, "L");//ingreso el correo al validador.
            System.out.println("Ingrese su contraseña");
            System.out.print(" ");
            password = teclado.nextLine();
            if ("R".equalsIgnoreCase(password)) {
                callDisplayMetods.registroUser();
            } else if ("S".equalsIgnoreCase(password)) {
                System.exit(0);
            }
            //Aqui programacion para insertar el correo a la consulata de la bd.
            callQuerys.queryEmail(email, password);
        } while (exit != 1);
    }

    //Funcion Registro de Usuarios.
    public void registroUser() {
        Display callDisplayMetods = new Display();
        int exit = 0;

        do {
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.blue, "\n-------------Control de Proyectos, Registrate a Continuacion , ¡Siempre Gratis!-------------");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            //Llamo al menu de registro.
            callHeaderMain.headerRegistro();
            callDisplayMetods.dateregistroUser();
        } while (exit != 1);
    }

    public void dateregistroUser() {
        Querys callQuerys = new Querys();//Instanciar Consultas
        Display callDisplayMetods = new Display();
        System.out.print("Ingrese su Nombre:");
        JSystem.out.printColorln(JSystem.Color.cyan, "\t\t\t\t\t\t              (C) for Cancel");
        System.out.print(" ");
        name = teclado.nextLine();
        callDisplayMetods.cancelar(name, null, null, null, null, null);
        System.out.println();//space line.
        System.out.println("Ingresa su Usuario:");
        System.out.print(" ");
        usuario = teclado.nextLine();
        System.out.println();//space line.
        callDisplayMetods.cancelar(null, usuario, null, null, null, null);
        System.out.println("Ingrese su Correo:");
        System.out.print(" ");
        email = teclado.nextLine();
        System.out.println();//space line.
        callDisplayMetods.cancelar(null, null, email, null, null, null);
        callValidator.validar(email, "R");//mando el correo al validador para la verificacion.
        System.out.println("Ingrese su Contraseña:");
        System.out.print(" ");
        password = teclado.nextLine();
        callDisplayMetods.cancelar(null, null, null, password, null, null);
        System.out.println();//space line.
        System.out.println("Seleccione una pregunta de seguridad:");
        System.out.println("1) ¿Cuál es el nombre de tu primer mascota?");
        System.out.println("2) ¿Cuál es el nombre de tu pelicula favorita?");
        System.out.println("3) ¿Últimos dígitos de tu número de identidad?");
        System.out.print(" ");
        int opt = teclado.nextInt();
        switch (opt) {
            case 1:
                pSeguridad = "¿Cuál es el nombre de tu primer mascota?";
                break;
            case 2:
                pSeguridad = "¿Cuál es el nombre de tu pelicula favorita?";
                break;
            case 3:
                pSeguridad = "¿Últimos dígitos de tu número de identidad?";
                break;
            default:
                break;
        }
        teclado.nextLine();
        callDisplayMetods.cancelar(null, null, null, null, pSeguridad, null);
        System.out.println();//space line.
        System.out.println("Ingrese la respuesta secreta:");
        System.out.print(" ");
        rSeguridad = teclado.nextLine();
        callDisplayMetods.cancelar(null, null, null, null, null, rSeguridad);
        //Aqui el codigo de la programacion de la consulta.
        callQuerys.InsertRegistro(name, usuario, email, password, pSeguridad, rSeguridad);
        //letter code.
        JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white, "                                       REGISTRO EXITOSO                                     ");
        JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white, "                              Ya puedes iniciar sesión con tu cuenta.                       ");
        JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white, "                                                                                            ");
        callDisplayMetods.Login();
    }

  

    public void QuestionVerificationEmail() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\t\tDeseas Registrarte Ahora-->'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.registroUser();//llamo a la pantalla registro de usuarios.    
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.  
            callDisplayMetods.Login();//llamo a login de usurios.
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usurios.
        }
    }

    public void QuestionVerificationEmailRegister() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\tDeseas Continuar con el registro --> 'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.dateregistroUser();
            //Contunuo con el registro. 
            callDisplayMetods.registroUser();
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.  
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            System.out.println();
            callDisplayMetods.Login();//llamo a login de usurios.

        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usuarios.
        }
    }

    public void QuestionRecoverPassword() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n---------------Los datos ingresados no se encuentran en nuestra base de datos---------------");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColor(JSystem.Color.cyan, "¿TIENES PROBLEMAS PARA INGRESAR A TU CUENTA?");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.red, "S/N");
        String opt = teclado.nextLine();
        if ("S".equalsIgnoreCase(opt)) {
            callDisplayMetods.RecoverPassword();
        } else if ("N".equalsIgnoreCase(opt)) {
            callDisplayMetods.Login();//llamo a login de usuarios.  
        }
    }

    public void RecoverPassword() {
        Display callDisplayMetods = new Display();
        callHeaderMain.headerRecoverPassword();
        JSystem.out.printColorln(JSystem.Color.blue, "DEBES PROPORCIONAR LOS SIGUIENTES DATOS PARA RECUPERAR TU ACCESO.");
        JSystem.out.printColorln(JSystem.Color.cyan, "☻ Dirección de correo electrónico");
        JSystem.out.printColorln(JSystem.Color.cyan, "☻ Respuesta a la pregunta de seguridad");
        JSystem.out.printColor(JSystem.Color.blue, "CONTINUAR ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.red, "S/N");
        System.out.print(" ");
        String opt = teclado.nextLine();
        if ("S".equalsIgnoreCase(opt)) {
            callDisplayMetods.DatesRecoverPassword();
        } else if ("N".equalsIgnoreCase(opt)) {
            callDisplayMetods.Login();//llamo a login de usuarios.  
        }
    }

    public void DatesRecoverPassword() {
        Display callDisplayMetods = new Display();
        System.out.println("Ingrese su Correo electrónico");
        System.out.print(" ");
        email = teclado.nextLine();
        callValidator.validar(email, "S");
        System.out.println();
        System.out.println("Ingrese la respuesta a la siguiente pregunta");
        //SACAR LA PREGUNTA DE LA BD.
        JSystem.out.printColor(JSystem.Color.blue, " ");
        System.out.println(pSeguridad);
        System.out.print(" ");
        rSeguridad = teclado.nextLine();
        /*REALIZAR LA CONSULATA A LA BD SI EXISTE LA RESPUESTA
            SI EXISTE CONTINUAR*/
        System.out.println("Ingrese su nueva contraseña");
        System.out.print(" ");
        nPassword = teclado.nextLine();
        //REALIZAR EL UPDATE DE LA NUEVA PASSWORD A LA BD.
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.blue, "\n                          LA CUENTA SE HA RECUPERADO EXITOSAMENTE                           ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "\t\t\t SE LE HA ENVIADO UN CORREO DE CONFIRMACIÓN\t\t\t    ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.blue, "\t\t\tYA PUEDES INICIAR SESIÓN CON TU NUEVA CUENTA\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        callDisplayMetods.Login();
    }

    public void QuestionVerificationEmailRecover() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\tDeseas Continuar con la recuperacion --> 'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.DatesRecoverPassword();
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.Login();//llamo a login de usurios.
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usuarios.
        }
    }

    public void Perfil(String nombreUsuario, String email, int IdUser) {
        Display callDisplayMetods = new Display();

        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "\n\n\n");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                          BIENVENIDO A SU CUENTA CONTROL DE PROYECTOS                       ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                 " + nombreUsuario + "                                     " + email + "            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                                                                                            ");
        int salir = 0;
        do {
            callHeaderMain.headerPerfil();
            System.out.print("Menú de opciones");
            JSystem.out.printColorln(JSystem.Color.cyan, " \t\t\t\t                               (S) for Exit");
            System.out.println(" 1) Mi Cuenta\n 2) Proyectos\n 3) Documentos Compartidos\n 4) Cerrar Sesion");
            System.out.println();
            System.out.println("Selecciona una Opción");
            System.out.print(" ");
            String opt = teclado.nextLine();
            if ("S".equalsIgnoreCase(opt)) {
                callHeaderMain.EndSoftware();
            }
            switch (opt) {
                case "1":
                    System.out.println("Ingreso ver mi cuenta");
                    callDisplayMetods.MyAccount(usuario, email, IdUser);
                    break;
                case "2":
                    System.out.println("Ingreso mis proyectos");
                    callDisplayMetods.MyProyect(usuario, email, IdUser);
                    break;
                case "3":
                    System.out.println("Ingreso  documentos compartidos");
                    break;
                case "4":
                    System.out.println("Ingreso cerrar sesion");
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                              ");
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                           LA SESIÓN SE HA CERRADO SATISFACTORIAMENTE                         ");
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                              ");
                    callDisplayMetods.Login();
                    break;
                default:;
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "______________________________NO HA ELEGIDO NINGUNA DE LAS OPCIONES_________________________");
                    callDisplayMetods.Perfil(usuario, email, IdUser);
                    break;
            }
        } while (salir != 2);
    }

    public void MyAccount(String usuario, String email, int IdUser) {
        JSystem.out.printColorln(JSystem.Color.blue, "Ver los datos de mi cuenta");
        JSystem.out.printColorln(JSystem.Color.blue, "MANTENIMIENTO");
        Display callDisplayMetods = new Display();
        callHeaderMain.headerMyAcount();
        int salir = 0;
        do {
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            System.out.print("Menú de opciones");
            JSystem.out.printColorln(JSystem.Color.cyan, " \t\t\t\t                               (S) for Exit");
            System.out.println(" 1) Información Personal\n 2) Cambiar Password\n 3) Eliminar Cuenta\n 4) Regresar");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            //AÑADIR AQUI LOS DATOS CONSULTADOS DE LA BASE DE DATOS.
            System.out.println();
            System.out.println("Selecciona una Opción");
            System.out.print(" ");
            String opt = teclado.nextLine();
            if ("S".equalsIgnoreCase(opt)) {
                callHeaderMain.EndSoftware();
            }
            switch (opt) {
                case "1":
                    System.out.println("Ingreso Información Personal");
                    // callDisplayMetods.MyAccount();
                    break;
                case "2":
                    System.out.println("Ingreso Cambiar Password");
                    //callDisplayMetods.MyProyect();
                    break;
                case "3":
                    System.out.println("Ingreso Eliminar Cuenta");
                    break;
                case "4":
                    System.out.println("Ingreso Regresar");
                    callDisplayMetods.Perfil(usuario, email, IdUser);
                    break;
                default:;
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "______________________________NO HA ELEGIDO NINGUNA DE LAS OPCIONES_________________________");
                    callDisplayMetods.Perfil(usuario, email, IdUser);
                    break;
            }
        } while (salir != 2);

    }

    public void QuestionVerificationNewAcount() {

    }

    public void MyProyect(String usuario, String email, int IdUser) {
        Display callDisplayMetods = new Display();
        Querys callQuerys = new Querys();//Instanciar Consultas
        callHeaderMain.headerMyProyect();
        
        int salir = 0;
        do {
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            System.out.print("Menú de opciones");
            JSystem.out.printColorln(JSystem.Color.cyan, " \t\t\t\t                               (S) for Exit");
            System.out.println(" 1) Ver Proyectos\n 2) Agregar Proyectos\n 3) Modificar Proyectos\n 4) Eliminar Proyectos\n 5) Regresar");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            //AÑADIR AQUI LOS DATOS CONSULTADOS DE LA BASE DE DATOS.
            System.out.println();
            System.out.println("Selecciona una Opción");
            System.out.print(" ");
            String opt = teclado.nextLine();
            if ("S".equalsIgnoreCase(opt)) {
                callHeaderMain.EndSoftware();
            }
            switch (opt) {
                case "1":
                    //Ver proyectos ingresados por usuario.
                    JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white,"--------------------------------------------------------------------------------------------");
                    JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white,"Nombre Proyecto|\tJefe|\t\tFecha Inicio|\tFecha Fin|\tDescripcion|\t    ");
                    JSystem.out.printColorln(JSystem.ColorBg.green, JSystem.Color.white,"--------------------------------------------------------------------------------------------");
                    System.out.println(callQuerys.getInfoProyect(IdUser));
                    break;
                case "2":
                    System.out.println("Ingreso Agregar Proyectos");
                    callDisplayMetods.AddProyect(usuario, email, IdUser);
                    break;
                case "3":
                    System.out.println("Ingreso Modificar Proyectos");
                    break;
                case "4":
                    System.out.println("Ingreso Eliminar Proyectos");
                    break;
                case "5":
                    System.out.println("Ingreso Regresar");
                    callDisplayMetods.Perfil(usuario, email, IdUser);
                    break;
                default:;
                    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "______________________________NO HA ELEGIDO NINGUNA DE LAS OPCIONES_________________________");
                    callDisplayMetods.MyAccount(usuario, email, IdUser);
                    break;
            }
        } while (salir != 2);
    }

    public void AddProyect(String usuario, String email, int IdUser) {
        Querys callQuerys = new Querys();//Instanciar Consultas
        Display callDisplayMetods = new Display();
        callHeaderMain.headerAddProyect();
        System.out.print("Ingrese nombre del Proyecto:");
        JSystem.out.printColorln(JSystem.Color.cyan, "\t\t\t\t\t\t              (C) for Cancel");
        System.out.print(" ");
        nameProyect = teclado.nextLine();
        callDisplayMetods.cancelRegistroProyect(usuario, email, IdUser, nameProyect, null, null, null, null);
        System.out.println();//space line.
        System.out.println("Ingrese Jefe del Proyecto:");
        System.out.print(" ");
        jefeProyect = teclado.nextLine();
        callDisplayMetods.cancelRegistroProyect(usuario, email, IdUser, null, jefeProyect, null, null, null);
        System.out.println();//space line.
        System.out.println("Ingrese la Fecha de Inicio:");
        System.out.print(" ");
        fInicio = teclado.nextLine();
        System.out.println();//space line.
        callDisplayMetods.cancelRegistroProyect(usuario, email, IdUser, null, null, fInicio, null, null);
        System.out.println("Ingrese la Fecha de Fin:");
        System.out.print(" ");
        fFin = teclado.nextLine();
        callDisplayMetods.cancelRegistroProyect(usuario, email, IdUser, null, null, null, fFin, null);
        System.out.println();//space line.
        System.out.println("Ingrese la descripción:");
        System.out.print(" ");
        descripcion = teclado.nextLine();
        callDisplayMetods.cancelRegistroProyect(usuario, email, IdUser, null, null, null, null, descripcion);
        System.out.println();//space line.
        callQuerys.InsertProyect(usuario, email, IdUser, nameProyect, jefeProyect, fInicio, fFin, descripcion);
    }
    
      public void cancelar(String name, String usuario, String email, String password, String pSeguridad, String rSeguridad) {
        Display callDisplayMetods = new Display();
        if ("C".equalsIgnoreCase(name) || "C".equalsIgnoreCase(usuario) || "C".equalsIgnoreCase(email) || "C".equalsIgnoreCase(password) || "C".equalsIgnoreCase(pSeguridad) || "C".equalsIgnoreCase(rSeguridad)) {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-----------------------------------SUSCRIPCIÓN CANCELADA------------------------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();
        }
    }
      
      public void cancelRegistroProyect(String usuario, String email, int IdUser, String nameProyect, String jefeProyect, String fInicio, String fFin, String descripcion) {
        Display callDisplayMetods = new Display();
        if ("C".equalsIgnoreCase(nameProyect) || "C".equalsIgnoreCase(jefeProyect) || "C".equalsIgnoreCase(fInicio) || "C".equalsIgnoreCase(fFin) || "C".equalsIgnoreCase(descripcion)) {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-----------------------------------SUSCRIPCIÓN CANCELADA------------------------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.MyProyect(usuario, email, IdUser);
        }
    }
}
