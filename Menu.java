import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ClassCastException;

public class Menu{
  private Teclado teclado;
  private int opcion;
  private Configuracion configuracion;

  public Menu(){
    this(new Teclado());
  }
  public Menu(Teclado teclado){
    super();
    this.teclado = teclado;

    crearAdmin();
    // Creando configuracion si no existe
    if (!verificarArchivoExiste("config.tmp")) {
      crearConfiguracion();
    } else {
      configuracion = devolverConfiguracion();
    }
  }
  public Menu(Menu menu){
    this(menu.teclado);
  }

  private boolean verificarArchivoExiste(String path) {
    if (new File(path).exists()) {
      return true;
    }

    return false;
  }
  private void crearAdmin() {
    Administrativo admin = new Administrativo("admin", null, null, "0");

    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream("admin.admin");
    flujo.escribirObjeto(admin);
  }
  private void crearConfiguracion() {
    Configuracion config = new Configuracion("2019010000", "2019020000", "2019030000");

    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream("config.tmp");
    flujo.escribirObjeto(config);

    this.configuracion = config;
  }
  private Configuracion devolverConfiguracion() {
    // Buscara en el flujo un alumno buscandolo por su numero de boleta
    Configuracion configuracion = null;
    try {
      FlujoObjectInputStream flujo = new FlujoObjectInputStream("config.tmp");
      configuracion = (Configuracion) flujo.leerObjeto();
    } catch (ClassCastException cce) {
      cce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return configuracion;
    }
  }
  private void guardarConfiguracion() {
    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream("config.tmp");
    flujo.escribirObjeto(configuracion);
  }

  public void ejecutarInicio(){
    // Inicio
    System.out.println("--------------------");
    System.out.println("Gestor escolar");
    System.out.println("--------------------");
    // Usuario escoge su rol
    System.out.println("Entrar como:");
    System.out.println("1) Alumno");
    System.out.println("2) Profesor");
    System.out.println("3) Administrativo");
    System.out.println("0) Salir");
    // Leyendo opcion del usuario
    try {
      boolean opcionValida = false;
      do {
        opcion = Integer.parseInt(teclado.leer("Ingrese su opcion: "));
        if (opcion == 0 || opcion == 1 || opcion == 2 || opcion == 3){
          opcionValida = true;
        }
        else {
          System.out.println("Opcion no valida. Vuelva a intentarlo.");
        }
      } while (!opcionValida);

      limpiarPantalla();
      switch (opcion) {
        case 1: ejecutarLoginAlumno(); break;
        case 2: ejecutarLoginProfesor(); break;
        case 3: ejecutarLoginAdministrativo(); break;
      }
    } catch (NumberFormatException nfe) {
      limpiarPantalla();
      System.out.println("Su entrada no fue un numero. Vuelva a intentarlo ingresando un entero");
      teclado.destruir();
      teclado = new Teclado();
      ejecutarInicio();
    } catch(Exception e) {
      System.out.println("ERROR:");
      e.printStackTrace();
    }

    guardarConfiguracion();
  }

  private void ejecutarLoginAlumno(){
    System.out.println("Login Alumno");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    buscarYDevolverAlumno("Ingrese su ID: ");
  }
  private void ejecutarLoginProfesor(){
    System.out.println("Login Profesor");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    buscarYDevolverProfesor("Ingrese su ID: ");
  }
  private void ejecutarLoginAdministrativo(){
    boolean loginValido = false;

    System.out.println("Login Administrativo");
    System.out.println("--------------------");
    System.out.println("(Ingrese 0 para regresar al inicio)");
    Administrativo administrativo = buscarYDevolverAdministrativo("Ingrese su id: ");
    if (administrativo != null) {
      loginValido = true;
    }

    limpiarPantalla();

    if (loginValido) {
      ejecutarMenuPrincipal(administrativo);
    }
  }

  private Administrativo buscarYDevolverAdministrativo(String mensaje){
    // Buscara en el flujo un administrativo buscandolo por su id
    Administrativo administrativo = null;
    try {
      String id = teclado.leer(mensaje);

      if (id == "0") {
        System.out.println("Volviendo al inicio...");
        return null;
      }

      if (verificarArchivoExiste(id + ".admin")) {
        FlujoObjectInputStream flujo = new FlujoObjectInputStream(id + ".admin");
        administrativo = (Administrativo) flujo.leerObjeto();
      } else {
        System.out.println("Administrativo no encontrado");
      }
    } catch (ClassCastException cce) {
      cce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return administrativo;
    }
  }
  private Alumno buscarYDevolverAlumno(String mensaje) {
    // Buscara en el flujo un alumno buscandolo por su ID
    Alumno alumno = null;
    try {
      String id = teclado.leer(mensaje);

      if (id == "0") {
        System.out.println("Volviendo al inicio...");
        return null;
      }

      if (verificarArchivoExiste(id + ".alumno")) {
        FlujoObjectInputStream flujo = new FlujoObjectInputStream(id + ".alumno");
        alumno = (Alumno) flujo.leerObjeto();
      } else {
        System.out.println("Alumno no encontrado");
      }
    } catch (ClassCastException cce) {
      cce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return alumno;
    }
  }
  private Profesor buscarYDevolverProfesor(String mensaje) {
    // Buscara en el flujo un profesor buscandolo por su ID
    Profesor profesor = null;
    try {
      String id = teclado.leer(mensaje);

      if (id == "0") {
        System.out.println("Volviendo al inicio...");
        return null;
      }

      if (verificarArchivoExiste(id + ".profesor")) {
        FlujoObjectInputStream flujo = new FlujoObjectInputStream(id + ".profesor");
        profesor = (Profesor) flujo.leerObjeto();
      } else {
        System.out.println("Profesor no encontrado");
      }
    } catch (ClassCastException cce) {
      cce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return profesor;
    }
  }

  private void ejecutarMenuPrincipal(Alumno alumno) {
    // Aqui va lo que puede hacer el alumno
  }
  private void ejecutarMenuPrincipal(Profesor profesor) {
    // Aqui va lo que puede hacer el profesor
  }
  private void ejecutarMenuPrincipal(Administrativo admin) {
    do {
      System.out.println("Bienvenido [ingresar nombre del administrativo]");
      System.out.println("-----------------------------------------------");
      System.out.println("1) Dar de alta a alumno");
      System.out.println("2) Editar alumno");
      System.out.println("3) Eliminar alumno");
      System.out.println("4) Dar de alta a profesor");
      System.out.println("5) Editar profesor");
      System.out.println("6) Eliminar profesor");
      System.out.println("7) Editar informacion propia");
      System.out.println("8) Dar de alta a administrativo");
      System.out.println("9) Eliminar otro administrativo");
      System.out.println("10 Eliminar esta cuenta");
      System.out.println("0) Salir");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));

      limpiarPantalla();

      switch (opcion) {
        case 0: ejecutarInicio(); break;
        case 1: admin.registrar(new Alumno(), teclado, configuracion); break;
        case 2: ejecutarMenuEditar(buscarYDevolverAlumno("Ingrese ID del alumno: ")); break;
        case 3: admin.eliminar(buscarYDevolverAlumno("Ingrese ID del alumno: "), teclado); break;
        case 4: admin.registrar(new Profesor(), teclado, configuracion); break;
        case 5: ejecutarMenuEditar(buscarYDevolverProfesor("Ingrese ID del profesor: ")); break;
        case 6: admin.eliminar(buscarYDevolverProfesor("Ingrese ID del profesor: "), teclado); break;
        case 7: ejecutarMenuEditar(admin); break;
        case 8: admin.registrar(new Administrativo(), teclado, configuracion); break;
        case 9: admin.eliminar(buscarYDevolverAdministrativo("Ingrese ID del administrativo: "), teclado); break;
        case 10: admin.eliminar(teclado); break;
        default: break;
      }

      limpiarPantalla();
    } while (opcion != 0);
  }

  private void ejecutarMenuEditar(Alumno alumno) {
    if (alumno == null) {
      return;
    }

    System.out.println("Editando alumno:\n" + alumno + "\n\n");

    do {
      System.out.println("\nEditar alumno");
      System.out.println("---------------");
      System.out.println("1) Editar informacion basica (nombre, apellidos)");
      System.out.println("2) Editar informacion personal (fecha de nacimiento, CURP, domicilio)");
      System.out.println("3) Inscribir horario escolar");
      System.out.println("4) Consultar horario escolar");
      System.out.println("0) Regresar");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));
    } while (opcion != 0);

    opcion = 2;
  }
  private void eliminar(Alumno alumno) {
    if (alumno == null) {
      return;
    }

    teclado.leer("asd: ");
    teclado.leer("Esta seguro de querer eliminar al alumno: " + alumno.getNombre() + "? (s/n): ");
    // Se elimina el objeto y su archivo
  }
  private void ejecutarMenuEditar(Profesor profesor) {
    if (profesor == null) {
      return;
    }

    do {
      System.out.println("\nEditar profesor");
      System.out.println("---------------");
      System.out.println("1) Editar informacion basica (nombre, apellidos)");
      System.out.println("2) Editar informacion personal (fecha de nacimiento, CURP, domicilio)");
      System.out.println("3) Modificar horario escolar");
      System.out.println("4) Consultar horario escolar");
      System.out.println("0) Regresar");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));
    } while (opcion != 0);

    opcion = 2;
  }
  private void eliminar(Profesor profesor) {
    if (profesor == null) {
      return;
    }

    teclado.leer("asd: ");
    teclado.leer("Esta seguro de querer eliminar al alumno: " + profesor.getNombre() + "? (s/n): ");
    // Se elimina el objeto y su archivo
  }
  private void ejecutarMenuEditar(Administrativo admin) {
    do {
      System.out.println("\nEditar administrativo");
      System.out.println("---------------");
      System.out.println("1) Editar informacion basica (nombre, apellidos)");
      System.out.println("2) Editar informacion personal (fecha de nacimiento, CURP, domicilio)");
      System.out.println("3) Editar horario");
      System.out.println("4) Editar sector");
      System.out.println("0) Regresar");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));

      switch (opcion) {
        case 1: admin.editarInformacionBasica(teclado); break;
        case 2: admin.editarInformacionPersonal(teclado); break;

        case 0: default: break;
      }
    } while (opcion != 0);

    opcion = 2;
  }

  public void limpiarPantalla(){
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }

  @Override
  public String toString(){
    return "Menu con " + teclado;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Menu)){
      return false;
    }

    Menu menu = (Menu) obj;

    return teclado == menu.teclado;
  }

  public void destruir(){
    if (teclado != null) teclado.destruir();

    System.gc();
  }
}
