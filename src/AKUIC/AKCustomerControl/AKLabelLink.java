package AKUIC.AKCustomerControl;

import AKUIC.AKStyle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 * Clase AKLabelLink que extiende AKLabel e implementa MouseListener. Representa
 * una etiqueta personalizada con comportamiento de enlace, cambiando el cursor
 * al pasar el mouse sobre ella.
 */
public class AKLabelLink extends AKLabel implements MouseListener {

    /**
     * Constructor que inicializa el AKLabelLink con un texto.
     *
     * @param text Texto que mostrará la etiqueta.
     */
    AKLabelLink(String text) {
        super(text);
        setPersonalizacion();
    }

    /**
     * Constructor que inicializa el AKLabelLink con un texto y un ícono.
     *
     * @param text Texto que mostrará la etiqueta.
     * @param iconPath Ruta del icono que se mostrará junto al texto.
     */
    AKLabelLink(String text, String iconPath) {
        super(text);
        setPersonalizacion();
        setIcon(new ImageIcon(iconPath));
    }

    /**
     * Configura la apariencia y el comportamiento del AKLabelLink. Se agrega un
     * MouseListener y se define el color de la fuente.
     */
    void setPersonalizacion() {
        addMouseListener(this);
        setOpaque(false);
        setForeground(AKStyle.RACOLOR_FONT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Método no implementado
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Método no implementado
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Método no implementado
    }

    /**
     * Cambia el cursor a mano cuando el mouse entra en la etiqueta.
     *
     * @param e Evento del mouse.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(AKStyle.RACURSOR_HAND);
    }

    /**
     * Restaura el cursor a su estado predeterminado cuando el mouse sale de la
     * etiqueta.
     *
     * @param e Evento del mouse.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(AKStyle.RACURSOR_DEFAULT);
    }
}
