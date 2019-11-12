public class Calificacion{
  private Asignatura asignatura;
  private float calificacion;

  public Calificacion (Asignatura asignatura, float calificacion){
    this.asignatura  = asignatura;
    this.calificacion = calificacion;
  }
  public Calificacion(){
    this(null,0.0f);
  }
  public Calificacion(Calificacion calificacion){
    this(calificacion.asignatura, calificacion.calificacion);
  }

  public void asignarCalificacion(Calificacion calificacion, Alumno alumno){
		System.out.println("Asignando calificacion "+calificacion.calificacion+" a alumno"+alumno.getNombreCompleto());
  }
  public void asignarCalificacion(Alumno alumno){
    System.out.println("Asignando todas las calificaiones de " + alumno.getNombreCompleto() + "...");
    //yendo materia por materia, asignando calificaiones
    //crea nueva calificacion
    //llama a asignarCalificacion(Calificacion calificacion, Alumno alumno) y asigna
  }

  public void destruir(){
    System.gc();
  }
  @Override
  public boolean equals(Object o){
    if(o==null){
      return false;
    }
    if(!(o instanceof Calificacion)){
      return false;
    }
    Calificacion calificacion = (Calificacion) o;
    return this.calificacion == calificacion.calificacion;
  }
  @Override
  public String toString(){
    return "Calificaion: "
            +calificacion;
  }
}
