public class Personal extends Persona{
  private String id;
  private String nss;
  private String cargo;

  public Personal(){
    this(null, null, null, null, "Personal escolar");
  }
  public Personal(String nombre, String apellidoPaterno, String apellidoMaterno, String id, String cargo){
    super(nombre, apellidoPaterno, apellidoMaterno);

    this.id = id;
    this.cargo = cargo;
  }
  public Personal(Personal personal){
    super(personal);

    id = personal.id;
    cargo = personal.cargo;
  }

  public void setPersonal(String nombre, String apellidoPaterno, String apellidoMaterno, String id, String cargo) {
    setPersona(nombre, apellidoPaterno, apellidoMaterno);
    this.id = id;
    this.cargo = cargo;
  }

  public void editarInformacion(String cargo){
    this.cargo = cargo;
  }

  @Override
  public String toString(){
    return "ID " + cargo + ": " + id + "\n" + super.toString() + "\nNSS: " + nss;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Personal)){
      return false;
    }

    Personal personal = (Personal) obj;

    return super.equals(personal) && id.equals(personal.id) && nss.equals(personal.nss) && cargo.equals(personal.cargo);
  }

  public void destruir(){
    if (id != null) id = null;
    if (nss != null) nss = null;
    if (cargo != null) cargo = null;

    System.gc();
  }
}
