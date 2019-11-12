public class Historial {
    private String fecha;
    private Asignatura[] asignaturas;

    public Historial(String fecha, Asignatura[] asignaturas){
    this.asignaturas = asignaturas;
    this.fecha = fecha;
    }
    public Historial(){
        this ("", null);
    }
    public Historial(Historial historial){
      this(historial.fecha, historial.asignaturas);
    }

    public void vizualizarHistorial(Alumno alumno){
      System.out.println("Visualizando historial de " + alumno.getNombreCompleto() + "...");
    }

    public void destruir(){
      if(fecha != null){
        fecha = null;
      }
      System.gc();
    }
    @Override
    public boolean equals(Object o){
      if (o == null){
        return false;
      }
      if(!(o instanceof Historial)){
        return false;
      }
      Historial historial = (Historial) o;
      return fecha.equals(historial.fecha) && asignaturas == historial.asignaturas;
    }
    @Override
    public String toString(){
      return "Historial"
              +"\nFecha: "
              +fecha;
    }
}
