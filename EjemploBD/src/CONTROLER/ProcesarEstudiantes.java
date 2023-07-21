package CONTROLER;
import MODEL.Estudiante;
import MODEL.ConeccionDB;
import java.sql.Connection;
import java.util.ArrayList;
public class ProcesarEstudiantes {
    public ArrayList<Estudiante> lstEstudiantes;
    public ProcesarEstudiantes(ArrayList<Estudiante> lstEstudiantes) {
        this.lstEstudiantes = lstEstudiantes;
    }
    public void calculoPromedios(){
        for(Estudiante est : lstEstudiantes)
            est.promedio = (est.nota1 + est.nota2) / 2;
    }
    public void calculoEstados(){
        for(Estudiante est : lstEstudiantes)
            est.estado = (est.promedio >= 7) ? "Aprobado" : "Reprobado";
    }
    public void insertarEstudiante(Estudiante estudiante){
        (new ConeccionDB()).insertarEstudiante(estudiante);
    }
    public ArrayList<Estudiante> getLstEstudiantes() {
        return (new ConeccionDB()).getLstEstudiantes();
    }
}
