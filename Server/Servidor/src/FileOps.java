import java.rmi.Remote;
import java.rmi.RemoteException;
public interface FileOps extends Remote {
    byte[] readFile(String filename) throws RemoteException;

    void writeFile(String filename, byte[] data) throws RemoteException;

    int deleteFile(String filename) throws RemoteException;

    String[] listFiles() throws RemoteException;

    int NumeroArchivos() throws RemoteException;
}
