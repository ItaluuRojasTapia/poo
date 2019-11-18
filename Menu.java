import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ClassCastException;

public class Menu{
  private Teclado teclado;
  private int opcion;
  private Administrativo administrativo;
  private Alumno alumno;
  private Profesor profesor;

  public Menu(){
    this(new Teclado());
  }
  public Menu(Teclado teclado){
    super();
    this.teclado = teclado;

    // Creando admin, si no existe
    if (!(new File("admin.tmp")).exists()) {
      crearAdmin();
    }
  }
  public Menu(Menu menu){
    this(menu.teclado);
  }

  public void ejecutarLogin(){
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
      ejecutarLogin();
    } catch(Exception e) {
      System.out.println("ERROR:");
      e.printStackTrace();
    }
  }
  private void crearAdmin() {
    Administrativo admin = new Administrativo("admin", null, null, "0");

    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream("admin.tmp");
    flujo.escribirObjeto(admin);
  }

  private void ejecutarLoginAlumno(){
    System.out.println("Login Alumno");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    opcion = Integer.parseInt(teclado.leer("Ingrese su numero de matricula:"));
  }
  private void ejecutarLoginProfesor(){
    System.out.println("Login Profesor");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    opcion = Integer.parseInt(teclado.leer("Ingrese su numero de matricula:"));
  }
  private void ejecutarLoginAdministrativo(){
    boolean loginValido = false;

    System.out.println("Login Administrativo");
    System.out.println("--------------------");
    System.out.println("(Ingrese 0 para regresar al menu principal)");
    administrativo = devolverAdministrativo();
    if (administrativo != null) {
      loginValido = true;
    }

    limpiarPantalla();

    if (!loginValido) {
      ejecutarLogin();
    } else {
      ejecutarMenuPrincipal(administrativo);
    }
  }
  private Administrativo devolverAdministrativo(){
    String nss = teclado.leer("Ingrese su nss: ");

    if ((new File(nss + ".tmp")).exists()) { // Aqui se devera de validar con un flujo (carpeta con diferentes administrativos)
      FlujoObjectInputStream flujo = new FlujoObjectInputStream(nss + ".tmp");
      Administrativo admin = (Administrativo) flujo.leerObjeto();
      return admin;
    }

    System.out.println("Administrativo no encontrado");
    return null;
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
      System.out.println("0) Salir");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));

      limpiarPantalla();

      switch (opcion) {
        case 0: ejecutarLogin(); break;
        case 1: darDeAlta(new Alumno()); break;
        case 2: editar(buscarAlumno()); break;
        case 3: eliminar(buscarAlumno()); break;
        case 4: darDeAlta(new Profesor()); break;
        case 5: editar(buscarProfesor()); break;
        case 6: eliminar(buscarProfesor()); break;
        case 7: editar(admin); break;
        default: break;
      }

      limpiarPantalla();
    } while (opcion != 0);
  }
  private Alumno buscarAlumno() {
    // Buscara en el flujo un alumno buscandolo por su numero de boleta
    Alumno alumno = null;
    try {
      String id = teclado.leer("Ingrese el ID del alumno: ");
      FlujoObjectInputStream flujo = new FlujoObjectInputStream(id + ".tmp");
      alumno = (Alumno) flujo.leerObjeto();
    } catch (ClassCastException cce) {
      cce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return alumno;
    }
  }
  private Profesor buscarProfesor() {
    teclado.leer("Ingrese ID del profesor: ");
    return null;
  }
  private void darDeAlta(Alumno alumno) {
    String nombre = teclado.leer("Ingrese nombre del alumno: ");
    String apellidoPaterno = teclado.leer("Ingrese el apellido paterno del alumno: ");
    String apellidoMaterno = teclado.leer("Ingrese el apellido materno del alumno: ");

    // Generar ID - A partir de la fecha y de los alumnos que ya existen
    String id = "201901"; // Primer cuatro digitos fecha, 2 digitos siguientes rol, 4 digitos ultimos no. registro

    alumno.setAlumno(nombre, apellidoPaterno, apellidoMaterno, id);
    System.out.println("Guardando Alumno...\n" + alumno);

    // Guardar alumno (objeto) en carpeta de alumnos en archivo nombrado con su id
    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream(id + ".tmp");
    flujo.escribirObjeto(alumno);

    this.alumno = alumno; // Pa que jueguen con el alumno banda
  }
  private void editar(Alumno alumno) {
    if (alumno == null) {
      return;
    }

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
  private void darDeAlta(Profesor profesor) {
    String nombre = teclado.leer("Ingrese nombre de la profesor: ");
    String apellidoPaterno = teclado.leer("Ingrese el apellido paterno del profesor: ");
    String apellidoMaterno = teclado.leer("Ingrese el apellido materno del profesor: ");

    // Generar ID - A partir de la fecha y de los alumnos que ya existen
    String id = "201902"; // Primer cuatro digitos fecha, 2 digitos siguientes rol, 4 digitos ultimos no. registro

    profesor.setProfesor(nombre, apellidoPaterno, apellidoMaterno, id);
    System.out.println("Guardando profesor...\n" + profesor);

    // Guardar alumno (objeto) en carpeta de alumnos en archivo nombrado con su id

    this.profesor = profesor; // Pa que puedan jugar con el profe
  }
  private void editar(Profesor profesor) {
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
  private void editar(Administrativo admin) {
    do {
      System.out.println("\nEditar administrativo");
      System.out.println("---------------");
      System.out.println("1) Editar informacion basica (nombre, apellidos)");
      System.out.println("2) Editar informacion personal (fecha de nacimiento, CURP, domicilio)");
      System.out.println("3) Editar horario");
      System.out.println("4) Editar sector");
      System.out.println("0) Regresar");

      opcion = Integer.parseInt(teclado.leer("Escoja una opcion: "));
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
