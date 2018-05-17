/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author Diego Álvarez
 */
public class VentanaBuscaminas extends javax.swing.JFrame {

    int filas= 15;
    int columnas=20;
    
    
    Boton [][] arrayBotones = new Boton[filas][columnas];
    

    /**
     * Creates new form VentanaBuscaminas
     */
    public VentanaBuscaminas() {
        initComponents();
        setSize(800,600);
        getContentPane().setLayout(new GridLayout(filas, columnas));
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                Boton boton = new Boton(i,j);
                boton.setBorder(null);
                getContentPane().add(boton);
                arrayBotones[i][j] = boton;
                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt){
                        botonPulsado(evt);
                    }
                });
            }
        }
        ponMinas(30);
        cuentaMinas();
    }
    private void botonPulsado(MouseEvent e){
        Boton miBoton = (Boton) e.getComponent();
        if(e.getButton() == MouseEvent.BUTTON3){
            miBoton.setText("?");
        }
        else{
         if(miBoton.getNumeroMinasAlrededor() == 0){
                ArrayList<Boton> listaDeCasillasAMirar = new ArrayList();
                listaDeCasillasAMirar.add(miBoton);
                
                while(listaDeCasillasAMirar.size() > 0){
                    Boton b = listaDeCasillasAMirar.get(0);
                    for(int k = -1; k<2; k++){
                        for(int m = -1; m<2; m++){
                            if((b.getI() + k >= 0)&&(b.getJ() + m >= 0)&&(b.getI() + k < filas) && (b.getJ() + m < columnas)){
                                if(arrayBotones[b.getI() + k][b.getJ() + m].isEnabled()){
                                    if(arrayBotones[b.getI() + k][b.getJ() + m].getNumeroMinasAlrededor() == 0){
                                        arrayBotones[b.getI() + k][b.getJ() + m].setEnabled(false);
                                        listaDeCasillasAMirar.add(arrayBotones[b.getI() + k][b.getJ() + m]);
                                    }
                                }
                            }
                        }
                    }
                    listaDeCasillasAMirar.remove(b);
                } 
            }        
        }
    }
    private void ponMinas(int numeroMinas){
        Random r = new Random();
        for(int i=0;i<numeroMinas;i++){
            int f = r.nextInt(filas);
            int c = r.nextInt(columnas);
            //TODO hay que hacer una versión que chequee si en la casilla
            //seleccionada ya hay una mina, porque en ese caso tiene que
            //buscar otra
            arrayBotones[f][c].setMina(1);
            arrayBotones[f][c].setText("m");
        }
    }
     //cuentaMinas es un método que para cada botón calcula el numero de 
    //minas que tiene alrededor
    private void cuentaMinas(){
        //TODO falta por hacer que calcule las minas en el borde exterior
        int minas = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ((i > 0) && (j > 0) && (i < filas-1) && (j < columnas-1)) {
                    minas = minas + arrayBotones[i - 1][j - 1].getMina();//la mina de arriba a la izquierda
                    minas = minas + arrayBotones[i][j - 1].getMina();//la mina de la izquierda 
                    minas = minas + arrayBotones[i + 1][j - 1].getMina();//la mina de abajo a la izquierda

                    minas = minas + arrayBotones[i - 1][j].getMina(); //la mina de encima
                    minas = minas + arrayBotones[i + 1][j].getMina(); //la mina de abajo

                    minas = minas + arrayBotones[i - 1][j + 1].getMina();//la mina de arriba a la derecha
                    minas = minas + arrayBotones[i][j + 1].getMina();//la mina de la derecha
                    minas = minas + arrayBotones[i + 1][j + 1].getMina();//la mina de abajo a la derecha
                }
                arrayBotones[i][j].setNumeroMinasAlrededor(minas);
                //TODO comentar la siguiente parte para que no aparezcan los numeros
                //al iniciar la partida
                if(arrayBotones[i][j].getMina() == 0){
                    arrayBotones[i][j].setText(String.valueOf(minas));
                }
                minas = 0; 
                   //uso un bucle anidado para recorrer
                //las 9 casillas que hay alrededor
                for (int k=-1; k<2; k++){
                    for(int m=-1; m<2; m++){
                        if ((i+k >= 0) && (j+m >= 0)&&(i+k < filas) && (j+m <columnas)){
                            minas = minas + arrayBotones[i+k][j+m].getMina();
                        }
                    }
                }
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBuscaminas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
