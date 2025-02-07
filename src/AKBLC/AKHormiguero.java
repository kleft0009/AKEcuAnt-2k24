package AKBLC;

import java.util.ArrayList;
import AKBLC.AKEntities.AKCarnivoro;
import AKBLC.AKEntities.AKGenoAlimento;
import AKBLC.AKEntities.AKHLarva;
import AKBLC.AKEntities.AKHormiga;
import AKBLC.AKEntities.AKIngestaNativa;
import AKBLC.AKEntities.AKXY;
import AKDAC.AKHormigueroDAC;
import AKInfra.AKAppException;

public class AKHormiguero {

    public ArrayList<AKHormiga> lstHormiguero = new ArrayList<>();

    public String AKcrearLarva() throws AKAppException {
        AKHormiga hormiga = new AKHLarva(lstHormiguero.size() + 1);
        lstHormiguero.add(hormiga);
        return "HORMIGA LARVA, agregada al hormiguero";
    }

    public String AKeliminarHormiga(int idHormiga) throws Exception {
        for (AKHormiga hormiga : lstHormiguero) {
            if (hormiga.getId() == idHormiga) {
                hormiga.setEstado("MUERTA");
                return hormiga.getTipo() + " marcada como MUERTA";
            }
        }
        return "HORMIGA no encontrada";
    }

    public String AKeguardarHormiguero() throws AKAppException {
        StringBuilder fullDataHormiga = new StringBuilder();
        for (AKHormiga hormiga : lstHormiguero) {
            fullDataHormiga.append(hormiga.toString()).append("\n");
        }
        new AKHormigueroDAC().saveHormigueroToCSV(fullDataHormiga.toString());  // Se pasa la data correctamente
        return "HORMIGUERO almacenado";
    }

    public String AKalimentarHormiga(int idHormiga, String alimentoGeno, String alimentoNativo) throws AKAppException {
        AKGenoAlimento aGeno;
        AKIngestaNativa aNativo;
        AKHormiga hormiga = null;

        // Creando GenoAlimento
        if ("XY".equals(alimentoGeno)) {
            aGeno = new AKXY();
        } else {
            aGeno = new AKXY(alimentoNativo, alimentoNativo);
        }

        // Creando IngestaNativa
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

        // Inyectar alimento genético a la ingesta nativa
        if (!aNativo.AKInyectar(aGeno)) {
            return "Error al inyectar el alimento genético en la hormiga";
        }

        // Alimentar a la hormiga
        hormiga.comer(aNativo);

        return hormiga.getTipo() + " alimentada con éxito";
    }
}
