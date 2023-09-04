import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
public class FIleOpsImpl extends UnicastRemoteObject implements FileOps {
    private Map<String, byte[]> Archivos;
    protected FIleOpsImpl() throws RemoteException {
        Archivos = new HashMap<>();
    }

    @Override
    public byte[] readFile(String filename) throws RemoteException {
        return Archivos.get(filename);
    }

    @Override
    public void writeFile(String filename, byte[] data) throws RemoteException {
        Archivos.put(filename, data);
    }

    @Override
    public int deleteFile(String filename) throws RemoteException {
        if (Archivos.containsKey(filename)) {
            Archivos.remove(filename);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String[] listFiles() throws RemoteException {
        return Archivos.keySet().toArray(new String[0]);
    }

    @Override
    public int NumeroArchivos() throws RemoteException {
        return Archivos.size();
    }
}
