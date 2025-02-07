package AKUIC.AKCustomerControl;

import AKUIC.AKStyle;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class AKLabel extends JLabel {

    //Label para guardar etiquetas de texto
    public AKLabel() {
        customizeComponent();
    }

    public AKLabel(String text) {
        setText(text);
        customizeComponent();
    }

    private void customizeComponent() {
        setCustomizeComponent(getText(), AKStyle.RAFONT, AKStyle.RACOLOR_FONT_LIGHT, AKStyle.RAALIGNMENT_LEFT);
    }

    public void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        //setIcon(new ImageIcon(iconPath));
    }
}
