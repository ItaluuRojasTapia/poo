public class Apoyo extends Personal{
  private Horario horario;
  private String empresa;

  public Apoyo(){
    this(null, null, null, null, "Personal de apoyo");
  }
  public Apoyo(String nombre, String apellidoPaterno, String apellidoMaterno, String id, String cargo){
    super(nombre, apellidoPaterno, apellidoMaterno, id, cargo);
  }
  public Apoyo(Apoyo apoyo){
    super(apoyo);
  }

  public void editarHorario(Horario horario){
    this.horario = horario;
  }

  @Override
  public String toString(){
    return super.toString()
      + "Horario: " + horario;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Apoyo)){
      return false;
    }

    Apoyo apoyo = (Apoyo) obj;

    return super.equals(apoyo)
      && horario.equals(apoyo.horario) && empresa.equals(apoyo.empresa);
  }

  public void destruir(){
    if (horario != null) horario.destruir();
    if (empresa != null) empresa = null;

    System.gc();
  }
}
