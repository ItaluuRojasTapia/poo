import java.io.Serializable;

public class Alumno extends Persona implements Serializable {
  private String id;
  private float creditos;
  private boolean activo;
  private Horario horario;
  private ProgramaAcademico programa;
  private Historial historial;
  private Asignatura[] asignaturas;

  public Alumno(){
    this(null, null, null, null);
  }
  public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, String id){
    super(nombre, apellidoPaterno, apellidoMaterno);

    this.id = id;
  }
  public Alumno(Alumno alumno){
    super(alumno);

    id = alumno.id;
  }

  public void setAlumno(String nombre, String apellidoPaterno, String apellidoMaterno, String id){
    setPersona(nombre, apellidoPaterno, apellidoMaterno);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void inscribirHorario(){
    System.out.println("Escoja las materias...");
    // Desplegar materias
    // Escoger materias
    // Asignar horario
    new HorarioEscolar();
  }
  public void verHorario(){
    System.out.println("Mostrando horario...");
    //Muestra horario escolar, con su programa academico
  }
  public boolean verificarSiEstaInscrito(){
    return activo;
  }

  @Override
  public String toString(){
    return "ID Alumno: " + id + "\n" + super.toString();
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Alumno)){
      return false;
    }

    Alumno alumno = (Alumno) obj;

    return super.equals(alumno) && id.equals(alumno.id) && creditos == alumno.creditos && activo == alumno.activo
      && horario.equals(alumno.horario) && asignaturas.equals(alumno.asignaturas) && programa.equals(alumno.programa)
      && historial.equals(alumno.historial);
  }

  public void destruir(){
    if (id != null) id = null;
    if (horario != null) horario.destruir();
    if (asignaturas != null) asignaturas = null;
    if (programa != null) programa.destruir();
    if (historial != null) historial.destruir();

    System.gc();
  }
}
