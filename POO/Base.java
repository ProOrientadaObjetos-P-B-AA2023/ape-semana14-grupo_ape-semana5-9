package POO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class Base {
    private JButton agregarButton;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton actualizarButton;
    private JTable table1;
    private JPanel panelprincipal;
    private JTextField txt_nota1;
    private JTextField txt_cedula;
    private JTextField txt_nota2;
    private JTextField txt_Nombre;
    private JTextField txt_Borrador;
    private JTextField txt_cedMod;
    private JButton modificarButton1;
    private JTextField txt_Buscar;
    String cedulaMOD;
    String []titulos= {"Nombre","nota1","nota2","promedio","Estado","Cedula"};

    DefaultTableModel defaultTableModel= new DefaultTableModel(null,titulos);
    void mostrarDatos(){
        ConexionDB_Est obj = new ConexionDB_Est();
        ArrayList<Estudiante> listaEst = obj.getListaEstudiantes();
        if (!listaEst.isEmpty()) {
            String[][] datos = new String[listaEst.size()][6];
            for (int i = 0; i < listaEst.size(); i++) {
                datos[i][0]=listaEst.get(i).nombre;
                datos[i][1]= String.valueOf(listaEst.get(i).nota1);
                datos[i][2]= String.valueOf(listaEst.get(i).nota2);
                datos[i][3]=String.valueOf(listaEst.get(i).promedio);
                datos[i][4]=listaEst.get(i).estado;
                datos[i][5]=listaEst.get(i).cedula;
            }
            defaultTableModel.setDataVector(datos, titulos);
            table1.setModel(defaultTableModel);
        }
    }
public Base() {


    table1.addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);
            mostrarDatos();
        }
    });
    agregarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ConexionDB_Est c= new ConexionDB_Est();
            String nombre= txt_Nombre.getText();
            int nota1= Integer.parseInt(txt_nota1.getText());
            int nota2= Integer.parseInt(txt_nota2.getText());
            String cedula= txt_cedula.getText();
            c.insertarEstudiante(new Estudiante(nombre,nota1,nota2,cedula));
            mostrarDatos();
        }
    });
    eliminarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ConexionDB_Est obj = new ConexionDB_Est();
            ArrayList<Estudiante> listaEst = obj.getListaEstudiantes();
            String cedula= txt_Borrador.getText();
            System.out.println(cedula);
            for (Estudiante estudiante: listaEst){
                System.out.println(estudiante.cedula);
                if (cedula.equals(estudiante.cedula)){
                    obj.eliminarEstudiante(cedula);
                }
            }
            mostrarDatos();
        }
    });

    modificarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cedulaMOD= txt_cedMod.getText();
            ConexionDB_Est obj = new ConexionDB_Est();
            ArrayList<Estudiante> listaEst = obj.getListaEstudiantes();
            for (Estudiante estudiante: listaEst) {
                if (cedulaMOD.equals(estudiante.cedula)) {
                    txt_Nombre.setText(estudiante.nombre);
                    txt_nota1.setText(String.valueOf(estudiante.nota1));
                    txt_nota2.setText(String.valueOf(estudiante.nota2));
                    txt_cedula.setText(estudiante.cedula);
                }
            }
        }
    });
    modificarButton1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ConexionDB_Est conexionDB_est= new ConexionDB_Est();
            conexionDB_est.eliminarEstudiante(cedulaMOD);
            String nombre= txt_Nombre.getText();
            String cedulaEst= txt_cedula.getText();
            int nota1= Integer.parseInt(txt_nota1.getText());
            int nota2= Integer.parseInt(txt_nota2.getText());
            conexionDB_est.insertarEstudiante(new Estudiante(nombre,nota1,nota2,cedulaEst));
            mostrarDatos();
        }
    });
    buscarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dt= txt_Buscar.getText();
            ConexionDB_Est c= new ConexionDB_Est();
            ArrayList<Estudiante> listaEst= c.getListaEstudiantes();
            ArrayList<Estudiante> mostrarEst= new ArrayList<>();
            for (Estudiante estudiante: listaEst){
                if (dt.equals(estudiante.nombre)||dt.equals(estudiante.cedula)||dt.equals(estudiante.nota1)||dt.equals(estudiante.nota2)
                ||dt.equals(estudiante.estado)){
                    mostrarEst.add(estudiante);
                }
            }
            if (!mostrarEst.isEmpty()) {
                String[][] datos = new String[mostrarEst.size()][6];
                for (int i = 0; i < mostrarEst.size(); i++) {
                    datos[i][0]=listaEst.get(i).nombre;
                    datos[i][1]= String.valueOf(listaEst.get(i).nota1);
                    datos[i][2]= String.valueOf(listaEst.get(i).nota2);
                    datos[i][3]=String.valueOf(listaEst.get(i).promedio);
                    datos[i][4]=listaEst.get(i).estado;
                    datos[i][5]=listaEst.get(i).cedula;
                }
                defaultTableModel.setDataVector(datos, titulos);
                table1.setModel(defaultTableModel);
            }
        }
    });
}

    public static void main(String[] args) {
        JFrame inicio= new JFrame("asd");
        inicio.pack();
        inicio.setContentPane(new Base().panelprincipal);
        inicio.setVisible(true);
    }
}
