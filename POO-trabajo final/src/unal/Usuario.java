package unal;

public class Usuario {
    // atributos
    public String id;
    public String nombre;
    public  String apellido;
    

    Usuario(String id , String nombre, String apellido){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
      
    }

    public  String getUserData(){

        return   id+"," + nombre +"," +apellido;
    }

}
