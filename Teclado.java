import java.util.Scanner;

public class Teclado{
  private Scanner teclado;

  public Teclado(){
    this(new Scanner(System.in));
  }
  public Teclado(Scanner teclado){
    this.teclado = teclado;
  }
  public Teclado(Teclado teclado){
    this(teclado.teclado);
  }

  public String leer(String mensaje){
    System.out.print(mensaje);
    return teclado.nextLine();
  }

  @Override
  public String toString(){
    return "Teclado";
  }
  @Override
  public boolean equals(Object obj){
    if (obj == null){
      return false;
    }
    if (!(obj instanceof Teclado)){
      return false;
    }

    Teclado otroTeclado = (Teclado) obj;

    return teclado == otroTeclado.teclado;
  }

  public void destruir(){
    if (teclado != null) teclado = null;

    System.gc();
  }
}
