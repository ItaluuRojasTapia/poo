public class Grupo{
  private String nombreGrupo;

  public Grupo(){
    this("");
  }
  public Grupo(String nombreGrupo){
    super();
    this.nombreGrupo = nombreGrupo;
  }
  public Grupo(Grupo grupo){
    this(grupo.nombreGrupo);
  }

  public void modificarGrupo(String nombreGrupo){
    System.out.println("Modificando grupo...");
  }

  @Override
  public String toString(){
    return "nombreGrupo: " + nombreGrupo;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Grupo)){
      return false;
    }

    Grupo grupo = (Grupo) obj;

    return nombreGrupo == grupo.nombreGrupo;
  }

  public void destruir(){
    if (nombreGrupo != null) nombreGrupo = null;

    System.gc();
  }
}
