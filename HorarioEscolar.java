public class HorarioEscolar extends Horario{
  private Asignatura[] asignaturas;
  private ProgramaAcademico programa;

  public HorarioEscolar(){
    this(null, null, null);
  }
  public HorarioEscolar(String[][] horario, Asignatura[] asignaturas, ProgramaAcademico programa){
    super(horario);
    this.asignaturas = asignaturas;
    this.programa = programa;
  }
  public HorarioEscolar(HorarioEscolar horarioEscolar){
    super(horarioEscolar);
    this.asignaturas = horarioEscolar.asignaturas;
    this.programa = horarioEscolar.programa;
  }

  public void modificarPrograma(){
    System.out.println("Escoja el programa academico:");
    //Desplegar programas academicos
    //Escoger
  }

  @Override
  public String toString(){
    return "Programa academico: " + programa + "\n" + "Asignaturas: ";
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof HorarioEscolar)){
      return false;
    }

    HorarioEscolar horarioEscolar = (HorarioEscolar) obj;

    return super.equals(horarioEscolar) && asignaturas == horarioEscolar.asignaturas && programa.equals(horarioEscolar.programa);
  }

  public void destruir(){
    if (asignaturas != null) asignaturas = null;
    if (programa != null) programa.destruir();

    System.gc();
  }
}
