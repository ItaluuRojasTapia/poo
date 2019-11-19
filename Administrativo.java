import java.io.File;
import java.io.Serializable;

public class Administrativo extends Personal implements Serializable{
  private Horario horario;
  private String sector;

  public Administrativo(){
    this(null, null, null, null);
  }
  public Administrativo(String nombre, String apellidoPaterno, String apellidoMaterno, String id){
    super(nombre, apellidoPaterno, apellidoMaterno, id, "Personal administrativo");
  }
  public Administrativo(Administrativo administrativo){
    super(administrativo);
  }

  public void setAdministrativo(String nombre, String apellidoPaterno, String apellidoMaterno, String id) {
    setPersonal(nombre, apellidoPaterno, apellidoMaterno, id, "Personal administrativo");
  }
  public void setSector(String sector) {
    this.sector = sector;
  }

  public void editarHorario(){
    System.out.println("Editando horario");
    //Edita su propio horario
  }
  public void editarHorario(Alumno alumno){
    System.out.println("Editando horario de " + alumno);
    //edita el horario de un alumno y se lo asigna
  }

  public void registrar(Alumno alumno, Teclado teclado, Configuracion config) {
    String id = "-1";
    try {
      id = Integer.toString(Integer.parseInt(config.getUltimoIdAlumno()) + 1);
      config.setUltimoIdAlumno(id);
      alumno.setAlumno(teclado.leer("Ingrese nombre del alumno: "), teclado.leer("Ingrese apellido paterno: "), teclado.leer("Ingrese apellido materno: "), id);
    } catch(NumberFormatException nfe) {
      System.out.println("El alumno no pudo ser guardardo, debido a una falla en la configuracion");
      nfe.printStackTrace();
      return;
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
      return;
    }

    // Preguntando si se llenara mas informacion
    if (teclado.leer("Desea ingresar informacion personal del alumno? (s/n): ").toLowerCase().equals("s")) {
      try {
        alumno.setFechaNacimiento(teclado.leer("Ingrese fecha de nacimiento (formato [##/##/####]): ")); // Aqui se podria crear una excepcion (para el formato de fecha, y pal limite de anio (que no se puedan registrar personas muertas pues)) pero nadien me ayuda lmao
        alumno.setEdad(Integer.parseInt(teclado.leer("Ingrese edad: "))); // Igual aqui se podria calcular pero ps se necesita lo de arriba
        alumno.setCurp(teclado.leer("Ingrese CURP: "));
        alumno.setDomicilio(teclado.leer("Ingrese domicilio: "));
      } catch (NumberFormatException nfe) {
        System.out.println("No se ha podido dar de alta al alumno debido a que la edad no fue ingresada con enteros");
        nfe.printStackTrace();
        return;
      } catch (Exception e) {
        System.out.println("ERROR");
        e.printStackTrace();
        return;
      }
    }

    // Guarda alumno en el flujo
    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream(id + ".alumno");
    flujo.escribirObjeto(alumno);
  }
  public void registrar(Profesor profesor, Teclado teclado, Configuracion config) {
    String id = "-1";
    try {
      id = Integer.toString(Integer.parseInt(config.getUltimoIdProfesor()) + 1);
      config.setUltimoIdProfesor(id);
      profesor.setProfesor(teclado.leer("Ingrese nombre del profesor: "), teclado.leer("Ingrese apellido paterno: "), teclado.leer("Ingrese apellido materno: "), id);
    } catch(NumberFormatException nfe) {
      System.out.println("El profesor no pudo ser guardardo, debido a una falla en la configuracion");
      nfe.printStackTrace();
      return;
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
      return;
    }

    // Preguntando si se llenara mas informacion
    if (teclado.leer("Desea ingresar informacion personal del profesor? (s/n): ").toLowerCase().equals("s")) {
      try {
        profesor.setFechaNacimiento(teclado.leer("Ingrese fecha de nacimiento (formato [##/##/####]): ")); // Aqui se podria crear una excepcion (para el formato de fecha, y pal limite de anio (que no se puedan registrar personas muertas pues)) pero nadien me ayuda lmao
        profesor.setEdad(Integer.parseInt(teclado.leer("Ingrese edad: "))); // Igual aqui se podria calcular pero ps se necesita lo de arriba
        profesor.setCurp(teclado.leer("Ingrese CURP: "));
        profesor.setDomicilio(teclado.leer("Ingrese domicilio: "));
      } catch (NumberFormatException nfe) {
        System.out.println("No se ha podido dar de alta al profesor debido a que la edad no fue ingresada con enteros");
        nfe.printStackTrace();
        return;
      } catch (Exception e) {
        System.out.println("ERROR");
        e.printStackTrace();
        return;
      }
    }

    // Guarda profesor en el flujo
    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream(id + ".profesor");
    flujo.escribirObjeto(profesor);
  }
  public void registrar(Administrativo administrativo, Teclado teclado, Configuracion config) {
    String id = "-1";
    try {
      id = Integer.toString(Integer.parseInt(config.getUltimoIdAdministrativo()) + 1);
      config.setUltimoIdAdministrativo(id);
      administrativo.setAdministrativo(teclado.leer("Ingrese nombre del administrativo: "), teclado.leer("Ingrese apellido paterno: "), teclado.leer("Ingrese apellido materno: "), id);
      administrativo.setSector(teclado.leer("Ingrese el sector donde trabajara el administrativo: "));
    } catch(NumberFormatException nfe) {
      System.out.println("El administrativo no pudo ser guardardo, debido a una falla en la configuracion");
      nfe.printStackTrace();
      return;
    } catch (Exception e) {
      System.out.println("ERROR");
      e.printStackTrace();
      return;
    }

    // Preguntando si se llenara mas informacion
    if (teclado.leer("Desea ingresar informacion personal del administrativo? (s/n): ").equals("s")) {
      try {
        administrativo.setFechaNacimiento(teclado.leer("Ingrese fecha de nacimiento (formato [##/##/####]): ")); // Aqui se podria crear una excepcion (para el formato de fecha, y pal limite de anio (que no se puedan registrar personas muertas pues)) pero nadien me ayuda lmao
        administrativo.setEdad(Integer.parseInt(teclado.leer("Ingrese edad: "))); // Igual aqui se podria calcular pero ps se necesita lo de arriba
        administrativo.setCurp(teclado.leer("Ingrese CURP: "));
        administrativo.setDomicilio(teclado.leer("Ingrese domicilio: "));
      } catch (NumberFormatException nfe) {
        System.out.println("No se ha podido dar de alta al administrativo debido a que la edad no fue ingresada con enteros");
        nfe.printStackTrace();
        return;
      } catch (Exception e) {
        System.out.println("ERROR");
        e.printStackTrace();
        return;
      }
    }

    // Guarda administrativo en el flujo
    FlujoObjectOutputStream flujo = new FlujoObjectOutputStream(id + ".admin");
    flujo.escribirObjeto(administrativo);
  }
  public void editarInformacionBasica(Teclado teclado) {
    if (teclado.leer("Desea editar su nombre? (s/n): ").toLowerCase().equals("s")) {

    }
    if (teclado.leer("Desea editar su apellido paterno? (s/n): ").toLowerCase().equals("s")) {

    }
    if (teclado.leer("Desea editar su apellido materno? (s/n): ").toLowerCase().equals("s")) {

    }
  }
  public void editarInformacionPersonal(Teclado teclado) {
    if (teclado.leer("Desea editar su fecha de nacimiento? (s/n): ").toLowerCase().equals("s")) {

    }
    if (teclado.leer("Desea editar su CURP? (s/n): ").toLowerCase().equals("s")) {

    }
    if (teclado.leer("Desea editar su domicilio? (s/n): ").toLowerCase().equals("s")) {

    }
  }
  public void editarSector(Teclado teclado) {
    if (teclado.leer("Desea editar el sector donde trabaja? (s/n): ").toLowerCase().equals("s")) {

    }
  }
  public void eliminar(Alumno alumno, Teclado teclado) {
    if (alumno == null) {
      return;
    }

    if (teclado.leer("Esta seguro de querer eliminar al alumno " + alumno.getNombre() + "? (s/n): ").toLowerCase().equals("s")) {
      if ((new File(alumno.getId() + ".alumno").delete())) {
        System.out.println("El alumno fue borrado exitosamente");
      }
      else {
        System.out.println("El alumno no pudo ser borrado");
      }
    }
  }
  public void eliminar(Profesor profesor, Teclado teclado) {
    if (profesor == null) {
      return;
    }

    if (teclado.leer("Esta seguro de querer eliminar al profesor " + profesor.getNombre() + "? (s/n): ").toLowerCase().equals("s")) {
      if ((new File(profesor.getId() + ".profesor").delete())) {
        System.out.println("El profesor fue borrado exitosamente");
      }
      else {
        System.out.println("El profesor no pudo ser borrado");
      }
    }
  }
  public void eliminar(Administrativo admin, Teclado teclado) {
    if (admin == null) {
      return;
    }

    if (teclado.leer("Esta seguro de querer eliminar al administrativo " + admin.getNombre() + "? (s/n): ").toLowerCase().equals("s")) {
      if ((new File(admin.getId() + ".admin").delete())) {
        System.out.println("El administrativo fue borrado exitosamente");
      }
      else {
        System.out.println("El administrativo no pudo ser borrado");
      }
    }
  }
  public void eliminar(Teclado teclado) {
    if (!getId().equals("admin")){
      System.out.println("Este usuario no puede ser eliminado");
    }
    else {
      if (!teclado.leer("Ingrese su id nuevamente para confirmar").equals(getId())) {
        System.out.println("El ID que ingreso no es el correcto");
      }
      else {
        if ((new File(getId() + ".admin").delete())) {
          System.out.println("El administrativo fue borrado exitosamente");
        }
        else {
          System.out.println("El administrativo no pudo ser borrado");
        }
      }
    }
  }

  @Override
  public String toString(){
    return super.toString() + "\n"
      + "Horario: " + horario + "\n"
        + "Sector: " + sector;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Administrativo)){
      return false;
    }

    Administrativo administrativo = (Administrativo) obj;

    return super.equals(administrativo)
      && horario.equals(administrativo.horario) && sector.equals(administrativo.sector);
  }

  public void destruir(){
    if (horario != null) horario.destruir();
    if (sector != null) sector = null;

    System.gc();
  }
}
