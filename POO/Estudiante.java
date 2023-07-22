package POO;

public class Estudiante {
    public String nombre;
    int nota1;
    int nota2;
    int promedio;
    String cedula;
    String estado;

    public Estudiante(String nombre, int nota1, int nota2, String cedula) {
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.cedula = cedula;
    }

    public Estudiante(String nombre, int nota1, int nota2, int promedio, String cedula, String estado) {
        this.nombre = nombre;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.promedio = promedio;
        this.cedula = cedula;
        this.estado = estado;
    }

    void calcularPromedio(){
        this.promedio=(nota1+nota2)/2;
    }
    void establecerEstado(){
        if (promedio>=7){
            estado="Aprobado";
        }else {
            estado="Reprobado";
        }
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", promedio=" + promedio +
                ", cedula='" + cedula + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
