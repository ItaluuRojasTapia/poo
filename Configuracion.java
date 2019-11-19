import java.io.Serializable;

public class Configuracion implements Serializable{
  private String ultimoIdAlumno;
  private String ultimoIdProfesor;
  private String ultimoIdAdministrativo;

  public Configuracion(String ultimoIdAlumno, String ultimoIdProfesor, String ultimoIdAdministrativo){
    this.ultimoIdAlumno = ultimoIdAlumno;
    this.ultimoIdProfesor = ultimoIdProfesor;
    this.ultimoIdAdministrativo = ultimoIdAdministrativo;
  }

  public void setUltimoIdAlumno(String id) {
    this.ultimoIdAlumno = id;
  }
  public void setUltimoIdProfesor(String id) {
    this.ultimoIdProfesor = id;
  }
  public void setUltimoIdAdministrativo(String id) {
    this.ultimoIdAdministrativo = id;
  }

  public String getUltimoIdAlumno() {
    return ultimoIdAlumno;
  }
  public String getUltimoIdProfesor() {
    return ultimoIdProfesor;
  }
  public String getUltimoIdAdministrativo() {
    return ultimoIdAdministrativo;
  }

  @Override
  public String toString(){
    return "Configuracion\nUltimo ID alumno: " + ultimoIdAlumno + "\n"
      + "Ultimo ID profesor: " + ultimoIdProfesor + "\n"
      + "Ultimo ID administrativo: " + ultimoIdAdministrativo;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Configuracion)){
      return false;
    }

    Configuracion configuracion = (Configuracion) obj;

    return ultimoIdAlumno == configuracion.ultimoIdAlumno && ultimoIdProfesor == configuracion.ultimoIdProfesor && ultimoIdAdministrativo == configuracion.ultimoIdAdministrativo;
  }

  public void destruir(){
    System.gc();
  }
}
