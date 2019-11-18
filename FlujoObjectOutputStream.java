import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FlujoObjectOutputStream {
  private FileOutputStream fos;
  private ObjectOutputStream oos;
  private String archivo;

  public FlujoObjectOutputStream(String archivo) {
    this.archivo = archivo;
    fos = null;
    oos = null;
  }

  private void abrirFlujo() {
    try {
      fos = new FileOutputStream(archivo);
      oos = new ObjectOutputStream(fos);
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private void cerrarFlujo() {
    if (oos != null) {
      try {
        oos.close();
      } catch(IOException ioe) {
        ioe.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (fos != null) {
      try {
        fos.close();
      } catch(IOException ioe) {
        ioe.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void escribirObjeto(Object obj) {
    abrirFlujo();

    try {
      oos.writeObject(obj);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      cerrarFlujo();
    }
  }
}
