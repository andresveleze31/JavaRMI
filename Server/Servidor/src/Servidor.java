import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
            FIleOpsImpl operaciones = new FIleOpsImpl();
            LocateRegistry.createRegistry(1099); //pierto 1099 predeterminado para rmi RECODAR
            Naming.rebind("Servidor_manejo_arch", operaciones);
            System.out.printf("El servidor esta en marcha\n");
        }
        catch (Exception e){
            System.out.print("Ha habido un error en el servidor\n");
            e.printStackTrace();
        }
    }
}