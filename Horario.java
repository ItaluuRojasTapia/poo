public class Horario extends Object{
  private String[][] horario;

  public Horario(){
    this((String[][]) null);
  }
  public Horario(String[][] horario){
    super();
    this.horario = horario;
  }
  public Horario(Horario horario){
    this(horario.horario);
  }

  public void modificarHorario(Horario otroHorario){
    System.out.println("Modificando horario...");
  }

  @Override
  public String toString(){
    String horarioString = "";

    if (this.horario != null){
      for (int i = 0; i < this.horario.length; i++) {
        for (int j = 0; j < horario[0].length; j++) {
          horarioString += horario[i][j];
          horarioString += "\t";
        }
        horarioString += "\n";
      }
    }

    return horarioString;
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Horario)){
      return false;
    }

    Horario horario = (Horario) obj;

    return this.horario == horario.horario;
  }

  public void destruir(){
    if (horario != null) horario = null;

    System.gc();
  }
}
