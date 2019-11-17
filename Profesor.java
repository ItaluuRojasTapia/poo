public class Profesor extends Personal{
  private String titulo;
  private boolean activo;
  private Asignatura[] asignaturas;
  private Horario horarioEscolar;
  private Horario horarioLaboral;

  public Profesor(){
    this(null, null, null, null);
  }
  public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno, String id){
    super(nombre, apellidoPaterno, apellidoMaterno, id, "Profesor");
  }
  public Profesor(Profesor profesor){
    super(profesor);
  }

  public void setProfesor(String nombre, String apellidoPaterno, String apellidoMaterno, String id) {
    setPersonal(nombre, apellidoPaterno, apellidoMaterno, id, "Profesor");
  }

  public void agregarHorario(Horario horario){
    horarioLaboral = horario;
  }
  public void agregarHorario(HorarioEscolar horario){
    horarioEscolar = horario;
  }
  public void calificar(Alumno alumno){
    System.out.println("Calificando alumno");
    //Asigna calificacion a un alumno
  }

  @Override
  public String toString(){
    String activoString = "activo";
    if (!activo) activoString = "no activo";

    return titulo + "\nEstado: " + activoString + "\n" + super.toString()
      + "\n" + "Asignaturas: " + asignaturas
      + "\n" + "Horario escolar: " + horarioEscolar
      + "\n" + "Horario laboral: " + horarioLaboral;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Profesor)){
      return false;
    }

    Profesor profesor = (Profesor) obj;

    return titulo.equals(profesor.titulo) && activo == profesor.activo
      && asignaturas.equals(profesor.asignaturas) && horarioEscolar.equals(profesor.horarioEscolar)
      && horarioLaboral.equals(profesor.horarioLaboral);
  }

  public void destruir(){
    if (titulo != null) titulo = null;
    if (asignaturas != null) asignaturas = null;
    if (horarioEscolar != null) horarioEscolar.destruir();
    if (horarioLaboral != null) horarioLaboral.destruir();

    System.gc();
  }
}
