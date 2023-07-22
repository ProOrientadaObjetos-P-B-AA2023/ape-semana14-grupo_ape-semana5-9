package POO;

import javax.swing.*;
import java.util.ArrayList;

public class View_Est_Consola {
    public static void main(String[] args) {
        ConexionDB_Est obj= new ConexionDB_Est();
        System.out.println(obj.insertarEstudiante(new Estudiante("Pablo",10,5,"1312312413")));
        ArrayList<Estudiante> listaEst=obj.getListaEstudiantes();
        for(Estudiante est: listaEst){
            System.out.println(est);
        }
    }
}
