package POO;

import java.sql.*;
import java.util.ArrayList;

public class ConexionDB_Est {
    public Connection concDB;
    public ArrayList<Estudiante> listaEstudiantes;
    public String msj;

    public void setConcDB(String url) {
        try{
            this.concDB=DriverManager.getConnection(url);
        }catch(SQLException sqlException){
            this.msj=sqlException.getMessage();
        }
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        try{
            listaEstudiantes=new ArrayList<Estudiante>();
            setConcDB("jdbc:sqlite:src/dbTest1.db");
            Statement statement= concDB.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from Estudiante");
            while(resultSet.next()){
                listaEstudiantes.add(new Estudiante(resultSet.getString("Nombre"),
                        resultSet.getInt("nota1"),
                        resultSet.getInt("nota2"),
                        resultSet.getInt("promedio"),
                        resultSet.getString("cedula"),
                        resultSet.getString("estado")));
            }
            statement.close();
        }catch(SQLException sqlException){
            this.msj=sqlException.getMessage();
        }
        return listaEstudiantes;
    }
    public String insertarEstudiante(Estudiante est){
        try{
            est.establecerEstado();
            est.calcularPromedio();
            setConcDB("jdbc:sqlite:src/dbTest1.db");
            Statement statement= concDB.createStatement();
            String strInsertEst=String.format("insert into Estudiante(Nombre, nota1, nota2, promedio, estado, cedula) values('%s', %d, %d, %d, '%s', '%s')", est.nombre,est.nota1,est.nota2,est.promedio,est.estado,est.cedula);
            statement.executeUpdate(strInsertEst);
            statement.close();
        }catch(SQLException sqlException){
            this.msj=sqlException.getMessage();
        }
        return msj;
    }
    public void eliminarEstudiante(String cedula) {
        try {
            setConcDB("jdbc:sqlite:src/dbTest1.db");
            Statement statement=concDB.createStatement();
            String strDeleteEst="DELETE FROM Estudiante WHERE cedula='"+cedula + "'";
            statement.executeUpdate(strDeleteEst);
            statement.close();
        } catch (SQLException sqlException) {
            this.msj = sqlException.getMessage();
        }
    }


}
