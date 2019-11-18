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

  public void editarHorario(){
    System.out.println("Editando horario");
    //Edita su propio horario
  }
  public void editarHorario(Alumno alumno){
    System.out.println("Editando horario de " + alumno);
    //edita el horario de un alumno y se lo asigna
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
