package unal;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {
    
    // Ubicacipon del archivo --> CAMBIAR LA UBICACIÓN (El archivo se encuentra en la carpeta del proyecto)
    public  static  String filename="J:\\2022-2s\\POO\\Trabajo 6\\UltimoTrabajo\\database.txt";
    
// main
    public static void main(String[] args) {
      crearNuevoArchivoEncabezado();
        displayServices();
    }

    // MÉTODOS
    
    //Método para escribir el encabezado en el archivob
    public static void crearNuevoArchivoEncabezado(){
        File baseDatos=new File(filename);

        try {

            if( baseDatos.createNewFile()==true){

                try {
                    FileWriter escribir=new FileWriter(filename,true); // escribe sonre el archivo
                    escribir.append("ID"+","+"Nombre"+","+"Apellido");
                    escribir.append("\n");
                    escribir.close();
                    System.out.println("Archivo creado!"+"\n"); // mensaje final
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // Método para añadir un nuevo usuario
    public static void crearAñadirNuevoUsuario( Scanner input){
        System.out.println("Ingresa tu ID  ");
        String id=input.next();
        id+= "$"+new Random().nextInt(100); //Agg un código al ID para hacer registros diferentes 
        System.out.println("Ingresa tu nombre");
        String nombre=input.next();
        System.out.println("Ingresa tu apellido");
        String apellido=input.next();
        
        Usuario usuario=new Usuario(id,nombre,apellido);// crea el usuario

        try {
            FileWriter escribir=new FileWriter(filename,true);
            escribir.append(usuario.getUserData());
            escribir.append("\n");
            escribir.close();
            System.out.println("Persona añadida!"+"\n"); // mensaje final
       
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //Método para borrar un usuario 
    public static void  borrarPorId(ArrayList<String> arrayList,Scanner input){
        System.out.println("Introduce el Id o cualquier palabra para identificar el usuario");
        String searchKey=input.next(); //busca la llave
        String linea;
        try {

            BufferedReader reader=new BufferedReader(new FileReader(filename)); // lee el archivo
            while ((linea=reader.readLine())!=null){
                if(linea.contains(searchKey)){
                    System.out.println(linea);
                    continue;
                }else {
                    arrayList.add(linea);
                }
            }

        }catch (Exception e){

        }
        try {
            FileWriter escribir=new FileWriter(filename);
            for (int i=0;i<arrayList.size();i++){
                escribir.append( arrayList.get(i));
                escribir.append("\n");
            }
            escribir.close();

        }catch (Exception e){
            System.out.println(e);
        }finally { // crea el mensaje final 
            System.out.println("Listo!"+"\n"); 
        }}
    
    
    // Método para editar algun usuario
    public static void actualizarUsuario(ArrayList<String>arrayList,Scanner input){
        try {
            BufferedReader leer=new BufferedReader(new FileReader(filename));
            System.out.println("Ingresa alguna llave para actualizar al usuario");
            String searchKey=input.next();
            String linea;
            while ((linea=leer.readLine())!=null){
                if(   linea.contains(searchKey)){
                    System.out.println("Introduce el texto que quieras cambiar");
                    String oldValue=input.next();
                    System.out.println("Introduce el nuevo texto que quieres cambiar");
                    String newValue=input.next();
                    arrayList.add(linea.replace(oldValue,newValue));
                }else {
                    arrayList.add(linea);
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }

        try {
            FileWriter escribir=new FileWriter(filename);
            for(int i=0;i<arrayList.size();i++){
                escribir.append(arrayList.get(i));
                escribir.append("\n");
            }
            escribir.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    
    
    //método para encontrar un usuario
    public static void buscarUsuarioId(Scanner input){
        try {
            BufferedReader leer=new BufferedReader(new FileReader(filename));
            System.out.println("Introduce la ID del usuario");
            String searchKey=input.next();
            String linea;
            while ((linea=leer.readLine())!=null){
                if(   linea.contains(searchKey)){
                    System.out.println(linea);
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }
    
    
    //método para mostrar la lista de los usuarios 
    public static void mostrarTodos(){

        try {
            BufferedReader leer=new BufferedReader(new FileReader(filename));
            String linea;
            while ((linea=leer.readLine())!=null){
                System.out.println(linea);
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }


    //Método para mostrar el menú en la consola 
    public static void  displayServices(){
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while(true){
            ArrayList<String> arrayList=new ArrayList<String>();

            System.out.println(" ");
            System.out.println("Escoge cualquier servicio");
            System.out.println("===========================");
            System.out.println("Presiona 1 para añadir nuevo Usuario");
            System.out.println("Presiona 2 para borrar algun Usuario");
            System.out.println("Presiona 3 para alctualizar algun Usuario");
            System.out.println("Presiona 4 para buscar Usuario por Id");
            System.out.println("Presiona 5 para mostrar todos los Usuarios");
            System.out.println("Presiona 0 para salir");
            System.out.println("===========================");
            System.out.println(" ");
            
            // manejo del menú 
            int x=  input.nextInt();
            if(x==0) break;
            else if(x==1){
                crearAñadirNuevoUsuario(input);
            }
            else if(x==2){
                borrarPorId(arrayList,input);
            }
            else if(x==3){
                actualizarUsuario(arrayList,input);
            }
            else if(x==4){
                buscarUsuarioId(input);
            }
            else if(x==5){
                mostrarTodos();
            }
            else {
                System.out.println("Por favor ingresa un numero valido");
            }
        }
    }

}
