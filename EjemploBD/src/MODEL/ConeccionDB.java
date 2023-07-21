package MODEL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ConeccionDB {
    public Connection concDB;
    public ArrayList<Estudiante> lstEstudiantes;
    public String msj;
    public void setConcDB(String url) {
        try {
            this.concDB = DriverManager.getConnection(url);
        } catch (SQLException sqlException) {
            this.msj = sqlException.getMessage();
        }
    }
    public ArrayList<Estudiante> getLstEstudiantes() {
        lstEstudiantes = new ArrayList<Estudiante>();
        try {
            setConcDB("jdbc:sqlite:bd/dbTest1.db");
            Statement statement = concDB.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Estudiante");
            while (resultSet.next()) {
                lstEstudiantes.add(new Estudiante(resultSet.getString("nombreEst"),
                                                  resultSet.getDouble("nota1"),
                                                  resultSet.getDouble("nota2"),
                                                  resultSet.getDouble("promedio"), 
                                                  resultSet.getString("estado")) );
            }
            statement.close();
        } catch (SQLException sqlException) {
            this.msj = sqlException.getMessage();
        }
        return lstEstudiantes;
    }
    public void insertarEstudiante(Estudiante estudiante) {
        try {
            setConcDB("jdbc:sqlite:bd/dbTest1.db");
            Statement statement = concDB.createStatement();
            String strInsertEst = String.format("insert into Estudiante(nombreEst, nota1, nota2, promedio, estado) "
                                               + "values('%s', %d, %d, %d, '%s')", estudiante.nombreEst, (int) estudiante.nota1,
                                                    (int) estudiante.nota2, (int) estudiante.promedio,estudiante.estado);
            statement.executeUpdate(strInsertEst);
            statement.close();
        } catch (SQLException sqlException) {
            this.msj = sqlException.getMessage();
        }
    }
}
