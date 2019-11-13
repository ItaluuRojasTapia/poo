import java.util.InputMismatchException;

public class Menu{
  private Teclado teclado;
  private int opcion;

  public Menu(){
    this(new Teclado());
  }
  public Menu(Teclado teclado){
    super();
    this.teclado = teclado;
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
        opcion = teclado.leerEntero("Ingrese su opcion: ");
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
    } catch (InputMismatchException ime) {
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

  private void ejecutarLoginAlumno(){
    System.out.println("Login Alumno");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    opcion = teclado.leerEntero("Ingrese su numero de matricula:");
  }
  private void ejecutarLoginProfesor(){
    System.out.println("Login Profesor");
    System.out.println("--------------------");
    System.out.println("Aun no tiene numero de matricula? Registrese con el personal administrativo. (Ingrese \"0\" para regresar)\n\n");
    opcion = teclado.leerEntero("Ingrese su numero de matricula:");
  }
  private void ejecutarLoginAdministrativo(){
    System.out.println("Login Administrativo");
    System.out.println("--------------------");
    opcion = teclado.leerEntero("Ingrese su NSS:");
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
