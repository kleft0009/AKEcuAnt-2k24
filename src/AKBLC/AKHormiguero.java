package AKBLC;

import java.util.ArrayList; 
import AKBLC.AKEntities.AKCarnivoro;    
import AKBLC.AKEntities.AKGenoAlimento;        
import AKBLC.AKEntities.AKHLarva; 
import AKBLC.AKEntities.AKHormiga; 
import AKBLC.AKEntities.IAKIngestaNativa; 
import AKBLC.AKEntities.AKXY; 
import AKDAC.AKHormigueroDAC; 
import AKInfra.AKAppException;
public class AKHormiguero {

    public ArrayList<AKHormiga> lstHormiguero = new ArrayList<>();

    public String crearLarva() throws AKAppException {
        AKHormiga hormiga = new AKHLarva(lstHormiguero.size() + 1);
        lstHormiguero.add(hormiga);
        return "HORMIGA LARVA, agregada al hormiguero";
    }

    public String eliminarHormiga(int idHormiga) throws Exception {
        String msg = "HORMIGA, no encontrada";
        for (int i = 0; i < lstHormiguero.size(); i++) {
            if (lstHormiguero.get(i).getId() == idHormiga) {
                msg = lstHormiguero.get(i).getTipo() + ", eliminada del hormiguero";
                lstHormiguero.remove(i);
                break;
            }
        }
        return msg;
    }

    public String guardarHormiguero() throws AKAppException {
        String fullDataHormiga = "";
        for (AKHormiga hormiga : lstHormiguero)
            fullDataHormiga += hormiga.toString();
        new AKHormigueroDAC().saveHormigueroToCSV(fullDataHormiga);
        return "HORMIGUERO, almacenado";
    }

    public String alimentarHormiga(int IdHormiga, String alimentoGeno, String alimentoNativo) throws AKAppException {
        int indexlist = 0;
        AKGenoAlimento aGeno = null;
        IAKIngestaNativa aNativo = null;
        AKHormiga hormiga = null;

        // Creando GenoAlimento
        switch (alimentoGeno) {
            case "XX": aGeno = new AKXY(); break;
            case "XY": aGeno = new AKXY(); break;
            default: aGeno = new AKGenoAlimento(); break;
        }

        // Creando IngestaNativa
        switch (alimentoNativo) {
            case "Carnívoro": aNativo = new AKCarnivoro(); break;
            case "Herbívoro": aNativo = new IAKIngestaNativa(); break;
            case "Omnívoro": aNativo = new IAKIngestaNativa(); break;
            case "Insectívoro": aNativo = new IAKIngestaNativa(); break;
            case "Nectarívoros": aNativo = new IAKIngestaNativa(); break;
        }
        //buscar indexList y hormiga a alimentar 
        for (;indexList < lstHormiguero.size(); indexList++) 
            if (lstHormiguero.get(indexList).getId() == Idhormiga) { 
                hormiga = lstHormiguero.get(indexList); 
                break; 
            } 

        //validamos 
        if (aNativo == null || aGeno == null || hormiga == null || hormiga.getEstado() == "MUERTA") 
            return "Ups...! alimento u hormiga no son válidos"; 
            
        //inyectamos el genoAlimento a la ingestaNativa y se procede a alimenta a la hormiga 
        if( aNativo.inyectar(aGeno)) 
            lstHormiguero.set(indexList, hormiga.comer(aNativo)); 
        else 
            return hormiga.getTipo() + " NO alimentada"; 
            
        return lstHormiguero.get(indexList).getTipo() + " Alimentada"; 
    } 
}
