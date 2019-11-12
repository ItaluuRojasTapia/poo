public class ProgramaAcademico{
  private String nombrePrograma;
  private int duracion;
  private float creditosTotales;

  public ProgramaAcademico(String nombrePrograma, int duracion, float creditosTotales){
    this.nombrePrograma = nombrePrograma;
    this.duracion = duracion;
    this.creditosTotales = creditosTotales;
  }
  public ProgramaAcademico(){
    this("", 0, 0.0f);
  }

  public ProgramaAcademico(ProgramaAcademico programaAcademico){
    this(programaAcademico.nombrePrograma, programaAcademico.duracion, programaAcademico.creditosTotales);
  }
  
  //Método que se encarga de crear el programa academico
  public void crearProgramaAcademico(String nombre, float creditosTotales){
	System.out.println("creando programa academico");
  }
  @Override
  public String toString(){
    return "Programa academico de nombre: "
            +nombrePrograma
            +"\ncon duracion: "
            +duracion
            +"\ny creditos totales: "
            +creditosTotales;
  }
  public void destruir(){
    if(nombrePrograma != null){
      nombrePrograma = null;
    }
    System.out.println("destruyendo ProgramaAcademico");
    System.gc();
  }

  @Override
  public boolean equals(Object o){
    if(o == null){
      return false;
    }
    if(!(o instanceof ProgramaAcademico)){
      return false;
    }

    ProgramaAcademico programaAcademico = (ProgramaAcademico) o;
    return nombrePrograma.equals(programaAcademico.nombrePrograma) && duracion==programaAcademico.duracion && creditosTotales==programaAcademico.creditosTotales;
  }

}
