public class Escuela{
  private String nombreEscuela;
  private String domicilio;
  private Alumno[] alumnos;
  private Profesor[] profesores;
  private Administrativo[] administrativos;
  private Apoyo[] apoyos;
  private Horario horario;
  private ProgramaAcademico[] programasAcademicos;

  public Escuela(){
    this("", "");
  }
  public Escuela(String nombreEscuela, String domicilio){
    super();
    this.nombreEscuela = nombreEscuela;
    this.domicilio = domicilio;
  }
  public Escuela(Escuela escuela){
    this(escuela.nombreEscuela, escuela.domicilio);
  }

  public void editarInformacion(){
    System.out.println("Editando la informacion...");
  }
  public void agregarProgramaAcademico(ProgramaAcademico programa){
    //agregara nuevo programa academico
  }

  @Override
  public String toString(){
    return nombreEscuela + "\n" + domicilio;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Escuela)){
      return false;
    }

    Escuela escuela = (Escuela) obj;

    return nombreEscuela.equals(escuela.nombreEscuela) && domicilio.equals(escuela.domicilio) && alumnos == escuela.alumnos
       && profesores == escuela.profesores && administrativos == escuela.administrativos && apoyos == escuela.apoyos
       && horario.equals(escuela.horario) && programasAcademicos == escuela.programasAcademicos;
  }

  public void destruir(){
    if (nombreEscuela != null) nombreEscuela = null;
    if (domicilio != null) domicilio = null;
    if (alumnos != null) alumnos = null;
    if (profesores != null) profesores = null;
    if (administrativos != null) administrativos = null;
    if (apoyos != null) apoyos = null;
    if (horario != null) horario.destruir();
    if (programasAcademicos != null) programasAcademicos = null;

    System.gc();
  }
}
