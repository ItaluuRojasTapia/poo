import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class FlujoObjectInputStream {
  private FileInputStream fis;
  private ObjectInputStream ois;
  private String archivo;

  public FlujoObjectInputStream(String archivo) {
    this.archivo = archivo;
    fis = null;
    ois = null;
  }

  private void abrirFlujo() {
    try {
      fis = new FileInputStream(archivo);
      ois = new ObjectInputStream(fis);
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private void cerrarFlujo() {
    if (ois != null) {
      try {
        ois.close();
      } catch(IOException ioe) {
        ioe.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (fis != null) {
      try {
        fis.close();
      } catch(IOException ioe) {
        ioe.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public Object leerObjeto() {
    abrirFlujo();
    Object obj = new Object();

    try {
      obj = ois.readObject();
    } catch(EOFException eofe) {
      eofe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      cerrarFlujo();
    }

    return obj;
  }
}
