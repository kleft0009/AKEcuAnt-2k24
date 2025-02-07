package AKUIC.AKCustomerControl;

import AKUIC.AKStyle;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * Clase AKLabel que extiende JLabel para personalizar etiquetas de texto con
 * estilos predefinidos.
 */
public class AKLabel extends JLabel {

    /**
     * Constructor por defecto de AKLabel. Inicializa la etiqueta con los
     * estilos predeterminados de AKStyle.
     */
    public AKLabel() {
        customizeComponent();
    }

    /**
     * Constructor que inicializa AKLabel con un texto específico.
     *
     * @param text Texto a mostrar en la etiqueta.
     */
    public AKLabel(String text) {
        setText(text);
        customizeComponent();
    }

    /**
     * Aplica estilos predeterminados a la etiqueta.
     */
    private void customizeComponent() {
        setCustomizeComponent(getText(), AKStyle.RAFONT, AKStyle.RACOLOR_FONT_LIGHT, AKStyle.RAALIGNMENT_LEFT);
    }

    /**
     * Permite personalizar la etiqueta con texto, fuente, color y alineación
     * específicos.
     *
     * @param text Texto a mostrar en la etiqueta.
     * @param font Fuente a aplicar al texto.
     * @param color Color del texto.
     * @param alignment Alineación horizontal del texto dentro de la etiqueta.
     */
    public void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        //setIcon(new ImageIcon(iconPath)); // Comentado para posible implementación futura.
    }
}
