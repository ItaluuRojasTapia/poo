public class Asignatura{
    private String nombreAsignatura;
    private Salon[] salones;
    private Calificacion calificacion;

    public Asignatura(String nombreAsignatura, Salon[] salones, Calificacion calificacion){

        this.nombreAsignatura = nombreAsignatura;
        this.salones = salones;
        this.calificacion = calificacion;
    }
    public Asignatura(){
        this("", null, null);
    }
    public Asignatura(Asignatura asignatura){
        this(asignatura.nombreAsignatura, asignatura.salones, asignatura.calificacion);
    }



    public void destruir(){
      if (nombreAsignatura != null) nombreAsignatura = null;
      if (salones != null) salones = null;
      if (calificacion != null) calificacion = null;

      System.gc();
    }
    @Override
    public boolean equals(Object o){
      if(o==null){
        return false;
      }
      if(!(o instanceof Asignatura)){
        return false;
      }

      Asignatura asignatura = (Asignatura) o;
      return nombreAsignatura.equals(asignatura.nombreAsignatura) && salones.equals(asignatura.salones) && calificacion.equals(asignatura.calificacion);
    }
    @Override
    public String toString(){
        return "Nombre Asignatura: "
                +nombreAsignatura;
    }


}
