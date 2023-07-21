package MODEL;
public class Estudiante {
    public String nombreEst, estado;
    public double nota1, nota2, promedio;
    public Estudiante(String nombreEst, double nota1, double nota2) {
        this.nombreEst = nombreEst;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }
    public Estudiante(String nombreEst, double nota1, double nota2, double promedio,  String estado) {
        this.nombreEst = nombreEst;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.promedio = promedio;
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Estudiante{" + "nombreEst=" + nombreEst + ", nota1=" + nota1 + ", nota2=" + nota2 + ", promedio=" + promedio + ", estado=" + estado + '}' ;
    }
    
}
