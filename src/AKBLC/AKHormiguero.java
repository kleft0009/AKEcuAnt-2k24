package AKBLC;

import AKBLC.AKEntities.AKCarnivoro;
import AKBLC.AKEntities.AKGenoAlimento;
import AKBLC.AKEntities.AKHLarva;
import AKBLC.AKEntities.AKHormiga;
import AKBLC.AKEntities.AKIngestaNativa;
import AKBLC.AKEntities.AKXY;
import AKDAC.AKHormigueroDAC;
import AKInfra.AKAppException;
import java.util.ArrayList;

/**
 * Clase que representa un hormiguero, permitiendo la creación, eliminación,
 * almacenamiento y alimentación de hormigas.
 */
public class AKHormiguero {

    /**
     * Lista que almacena todas las hormigas dentro del hormiguero.
     */
    public ArrayList<AKHormiga> lstHormiguero = new ArrayList<>();

    /**
     * Crea una nueva larva y la agrega al hormiguero.
     *
     * @return Mensaje confirmando la creación de la larva.
     * @throws AKAppException Si ocurre un error en la creación de la larva.
     */
    public String AKcrearLarva() throws AKAppException {
        AKHormiga hormiga = new AKHLarva(lstHormiguero.size() + 1);
        lstHormiguero.add(hormiga);
        return "HORMIGA LARVA, agregada al hormiguero";
    }

    /**
     * Marca una hormiga como muerta en el hormiguero.
     *
     * @param idHormiga ID de la hormiga a eliminar.
     * @return Mensaje indicando si la hormiga fue encontrada y eliminada o no.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    public String AKeliminarHormiga(int idHormiga) throws Exception {
        for (AKHormiga hormiga : lstHormiguero) {
            if (hormiga.getId() == idHormiga) {
                hormiga.setEstado("MUERTA");
                return hormiga.getTipo() + " marcada como MUERTA";
            }
        }
        return "HORMIGA no encontrada";
    }

    /**
     * Guarda el estado actual del hormiguero en un archivo CSV.
     *
     * @return Mensaje confirmando el almacenamiento del hormiguero.
     * @throws AKAppException Si ocurre un error en la operación.
     */
    public String AKeguardarHormiguero() throws AKAppException {
        StringBuilder fullDataHormiga = new StringBuilder();
        for (AKHormiga hormiga : lstHormiguero) {
            fullDataHormiga.append(hormiga.toString()).append("\n");
        }
        new AKHormigueroDAC().saveHormigueroToCSV(null);  // Se pasa la data correctamente
        return "HORMIGUERO almacenado";
    }

    /**
     * Alimenta una hormiga específica con un alimento genético y un alimento
     * nativo.
     *
     * @param idHormiga ID de la hormiga a alimentar.
     * @param alimentoGeno Tipo de alimento genético (ejemplo: "XY").
     * @param alimentoNativo Tipo de alimento nativo (ejemplo: "Carnívoro").
     * @return Mensaje indicando el resultado de la alimentación.
     * @throws AKAppException Si ocurre un error durante el proceso de
     * alimentación.
     */
    public String AKalimentarHormiga(int idHormiga, String alimentoGeno, String alimentoNativo) throws AKAppException {
        AKGenoAlimento aGeno;
        AKIngestaNativa aNativo;
        AKHormiga hormiga = null;

        // Creando el alimento genético
        if ("XY".equals(alimentoGeno)) {
            aGeno = new AKXY(alimentoNativo, alimentoNativo);
        } else {
            aGeno = new AKXY(alimentoNativo, alimentoNativo);
        }

        // Creando el alimento nativo
        if ("Carnívoro".equals(alimentoNativo)) {
            aNativo = new AKCarnivoro();
        } else {
            return "Ups...! Alimento nativo no válido";
        }

        // Buscar la hormiga en la lista
        for (AKHormiga h : lstHormiguero) {
            if (h.getId() == idHormiga) {
                hormiga = h;
                break;
            }
        }

        // Validaciones
        if (hormiga == null || "MUERTA".equals(hormiga.getEstado())) {
            return "Ups...! La hormiga no es válida o está muerta";
        }

        // Inyectar el alimento genético en la ingesta nativa
        if (!aNativo.AKInyectar(aGeno)) {
            return "Error al inyectar el alimento genético en la hormiga";
        }

        // Alimentar a la hormiga
        hormiga.AKComer(aNativo);

        return hormiga.getTipo() + " alimentada con éxito";
    }
}
