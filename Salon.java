public class Salon{
  private String id;
  private int capacidad;
  private Grupo[] grupos;

  public Salon(String id, int capacidad){
    this.id = id;
    this.capacidad = capacidad;
  }
  public Salon(){
    this("", 0);
  }
  public Salon(Salon salon){
    this(salon.id, salon.capacidad);
  }

  public void modificarSalon(String nombreGrupo){
    System.out.println("Modificando salon...");
  }
  public void modificarSalon(){
    System.out.println("Modificando toda la informacion del salon");
  }

  public void destruir(){
    if(id != null){
      id = null;
    }
    System.gc();
  }
  @Override
  public boolean equals(Object o){
    if (o == null){
      return false;
    }
    if(!(o instanceof Salon)){
      return false;
    }
    Salon salon = (Salon) o;
    return id.equals(salon.id) && capacidad == salon.capacidad;
  }
  @Override
  public String toString(){
    return "Salon id: "
            +id
            +"\nCapacidad: "
            +capacidad;
  }
}
