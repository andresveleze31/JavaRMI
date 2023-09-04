import java.io.FileOutputStream;
import java.rmi.Naming;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Cliente {
    public static void main(String[] args) {
        try {
            FileOps interfaz = (FileOps) Naming.lookup("rmi://10.43.101.142/Servidor_manejo_arch");
            Scanner lector = new Scanner(System.in);
            Scanner Arch = new Scanner(System.in);
            Scanner Arch2 = new Scanner(System.in);
            String Archivo = new String();
            String Archivo2 = new String();
            String eleccion = new String();
            Scanner scanner = new Scanner(System.in);


            int numeroElegido, resultado, bytes;

            byte[] Darchivo = new byte[1024];
            boolean bandera = true;
            String[] lista_archivos = new String[]{new String()};
            while(bandera){
                System.out.print("\033[H\033[2J");

                System.out.printf("Realize una de estas operaciones:\n\t(1) Descargar\n\t(2) Escribir\n\t(3) Borrar\n\t(4) listar\n\t(5) Ver numero de archivos en el servidor\n\t(6) Salir\n");
                eleccion = lector.nextLine();
                numeroElegido = Integer.parseInt(eleccion);
                if(numeroElegido == 1){

                    System.out.printf("Escriba el archivo a descargar\n");
                    Archivo = Arch.nextLine();
                    Darchivo = interfaz.readFile(Archivo);
                    String contenidoDescargado = new String(Darchivo);
                    System.out.println(contenidoDescargado);
                    System.out.print("Ingrese un caracter para continuar: ");
                    String continuar = scanner.nextLine();

                    if (Darchivo != null) {
                        // Pide al usuario el nombre del archivo en el que se guardará el contenido descargado
                        System.out.print("Ingrese el nombre del archivo en el que desea guardar el contenido descargado: ");
                        String nombreArchivo = scanner.nextLine();

                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo + ".bin");
                            fileOutputStream.write(Darchivo);
                            fileOutputStream.close();

                            System.out.println("El archivo se ha guardado correctamente como '" + nombreArchivo + "'.");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Ha ocurrido un error al guardar el archivo.");
                        }
                    } else {
                        System.out.println("No se pudo descargar el archivo o el archivo no existe en el servidor.");
                    }

                } else if (numeroElegido == 2) {
                    System.out.printf("Escriba el archivo que se va a enviar");
                    Archivo2 = Arch2.nextLine();
                    FileInputStream fileInputStream = new FileInputStream(Archivo2);
                    while ((bytes = fileInputStream.read(Darchivo)) != -1) {
                    }
                    fileInputStream.close();
                    System.out.printf("Escriba el nombre que va a tener el archivo\n");
                    Archivo = Arch.nextLine();
                    interfaz.writeFile(Archivo, Darchivo);
                    System.out.print("Ingrese un caracter para continuar: ");
                    String continuar = scanner.nextLine();

                } else if (numeroElegido == 3) {
                    System.out.printf("Escriba el archivo a borrar\n");
                    Archivo = Arch.nextLine();
                    resultado = interfaz.deleteFile(Archivo);
                    if (resultado == 1){
                        System.out.printf("Eliminado correctamente\n");
                    }
                    else {
                        System.out.printf("Ha ocurrido algun error\n");
                    }
                    System.out.print("Ingrese un caracter para continuar: ");
                    String continuar = scanner.nextLine();
                } else if (numeroElegido == 4) {
                    lista_archivos = interfaz.listFiles();
                    for(int i = 0; i < lista_archivos.length; i++){
                        System.out.printf("\n\t" + lista_archivos[i] +"\n\t" );
                    }
                    System.out.print("Ingrese un caracter para continuar: ");
                    String continuar = scanner.nextLine();
                } else if (numeroElegido == 5) {
                    resultado = interfaz.NumeroArchivos();
                    System.out.printf("Hay " + resultado + " archivos en el servidor\n");
                    System.out.print("Ingrese un caracter para continuar: ");
                    String continuar = scanner.nextLine();
                } else if (numeroElegido == 6) {
                    bandera = false;

                } else{
                    System.out.printf("Por favor digite uno de los anteriores digitos\n");

                }
            }

        }
        catch (Exception e){
            System.out.print("Ha habido un error en el servidor\n");
            e.printStackTrace();
        }
    }
}