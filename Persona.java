public class Persona extends Object{
  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String fechaNacimiento;
  private int edad;
  private String curp;
  private String domicilio;

  public Persona(){
    this(null, null, null);
  }
  public Persona(String nombre, String apellidoPaterno, String apellidoMaterno){
    super();
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
  }
  public Persona(Persona persona){
    this(persona.nombre, persona.apellidoPaterno, persona.apellidoMaterno);
  }

  public void setPersona(String nombre, String apellidoPaterno, String apellidoMaterno) {
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
  }

  public String getNombre(){
    return nombre;
  }
  public String getNombreCompleto(){
    return apellidoPaterno + " " + apellidoMaterno + " " + nombre;
  }

  public void obtenerEdad(){
    //Obtiene edad a partir de la fecha de nacimiento
  }

  @Override
  public String toString(){
    return nombre + " " + apellidoPaterno + " " + apellidoMaterno + "\n"
      + "Edad: " + edad + "\n"
      + "Nacio el " + fechaNacimiento + "\n"
      + "CURP: " + curp + "\n"
      + "Domicilio: " + domicilio;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Persona)){
      return false;
    }

    Persona persona = (Persona) obj;

    return nombre.equals(persona.nombre) && apellidoPaterno.equals(persona.apellidoPaterno) && apellidoMaterno.equals(persona.apellidoMaterno)
      && fechaNacimiento.equals(persona.fechaNacimiento) && curp.equals(persona.curp) && domicilio.equals(persona.domicilio) && edad == persona.edad;
  }

  public void destruir(){
    if (nombre != null) nombre = null;
    if (apellidoPaterno != null) apellidoPaterno = null;
    if (apellidoMaterno != null) apellidoMaterno = null;
    if (fechaNacimiento != null) fechaNacimiento = null;
    if (curp != null) curp = null;
    if (domicilio != null) domicilio = null;

    System.gc();
  }
}
