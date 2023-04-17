/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import busqueda_informada.AEstrella;
import busqueda_informada.BeamSearch;
import busqueda_informada.HillClimbing;
import busqueda_no_informada.Anchura;
import busqueda_no_informada.CostoUniforme;
import busqueda_no_informada.Profundidad;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import mapa.CargarMapa;
import mapa.ImprimirMapa;

/**
 *
 * @author Stiven Vélez Bedoya
 * @author Cristian David Gómez Becerra
 */
public class Main {

    public static void main(String[] args) {

        CargarMapa cargarMapa = new CargarMapa();
        ImprimirMapa imprimirMapa = new ImprimirMapa();

        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Seleccionar archivo de texto del mapa");
        selectorArchivo.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));

        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            File archivoSeleccionado = selectorArchivo.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();

            try {

                char[][] mapa = cargarMapa.cargarMapaDesdeArchivo(rutaArchivo);
                System.out.println("MAPA INICIAL");
                imprimirMapa.imprimirMapa(mapa);
                System.out.println("-----------");
                mapa = menu(mapa);
                imprimirMapa.imprimirMapa(mapa);

            } catch (IOException ex) {
                System.out.println("Error al cargar el mapa Bomberman desde el archivo: " + ex.getMessage());
            }

        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    public static char[][] menu(char[][] mapa) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int respuestaHeuristica = 0;

        Anchura anchura = new Anchura();
        Profundidad profundidad = new Profundidad();
        CostoUniforme costoUniforme = new CostoUniforme();
        HillClimbing hillClimbing = new HillClimbing();
        BeamSearch beamSearch = new BeamSearch();
        AEstrella aEstrella = new AEstrella();

        System.out.println("----- MENU -----");
        System.out.println("1. Anchura");
        System.out.println("2. Profundidad");
        System.out.println("3. Costo Uniforme");
        System.out.println("4. Hill Climbing");
        System.out.println("5. Beam Search");
        System.out.println("6. A*");
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado Anchura");
                mapa = anchura.busquedaAnchura(mapa, 0, 0, 2, 6);
                break;
            case 2:
                System.out.println("Has seleccionado Profundidad");
                mapa = profundidad.busquedaProfundidad(mapa, new int[]{2, 2}, new int[]{0, 0});
                break;
            case 3:
                System.out.println("Has seleccionado Costo Uniforme");
                mapa = costoUniforme.busquedaCostoUniforme(mapa, new int[]{2, 2}, new int[]{0, 0});
                break;
            case 4:
                System.out.println("Has seleccionado Hill Climbing");
                respuestaHeuristica = menuHeuristica();
                if (respuestaHeuristica == 1) {
                    System.out.println("Eligio Manhattan");
                    mapa = hillClimbing.busquedaManhattan(mapa, new int[]{2, 1}, new int[]{0, 0});
                } else if (respuestaHeuristica == 2) {
                    System.out.println("Eligio Euclidiana");
                    mapa = hillClimbing.busquedaEuclidiana(mapa, new int[]{2, 1}, new int[]{0, 0});
                } else {
                    System.out.println("Opción de heurística invalida");
                }
                break;
            case 5:
                System.out.println("Has seleccionado Beam Search");
                respuestaHeuristica = menuHeuristica();
                if (respuestaHeuristica == 1) {
                    System.out.println("Eligio Manhattan");
                    mapa = beamSearch.beamSearchManhattan(mapa, 3, 6, 0, 1);
                } else if (respuestaHeuristica == 2) {
                    System.out.println("Eligio Euclidiana");
                    mapa = beamSearch.beamSearchEuclidiana(mapa, 3, 6, 0, 1);
                } else {
                    System.out.println("Opción de heurística invalida");
                }
                break;
            case 6:
                System.out.println("Has seleccionado A* ");
                respuestaHeuristica = menuHeuristica();
                if (respuestaHeuristica == 1) {
                    System.out.println("Eligio Manhattan");
                    mapa = aEstrella.aEstrellaManhattan(mapa, 3, 6, 0, 1);
                } else if (respuestaHeuristica == 2) {
                    System.out.println("Eligio Euclidiana");
                    mapa = aEstrella.aEstrellaEuclidiana(mapa, 3, 6, 0, 1);
                } else {
                    System.out.println("Opción de heurística invalida");
                }
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                break;
        }

        scanner.close();

        return mapa;
    }

    public static int menuHeuristica() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("----- MENU HEURISTICA -----");
        System.out.println("1. Manhattan");
        System.out.println("2. Euclidiana");
        System.out.print("Ingrese su opción: ");
        opcion = scanner.nextInt();
        scanner.close();
        return opcion;
    }

}
