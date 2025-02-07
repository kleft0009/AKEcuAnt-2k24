package AKUIC.AKCustomerControl;

import AKUIC.AKStyle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class AKLabelLink extends AKLabel implements MouseListener {

    AKLabelLink(String text) {
        super(text);
        setPersonalizacion();
    }

    AKLabelLink(String text, String iconPath) {
        super(text);
        setPersonalizacion();
        setIcon(new ImageIcon(iconPath));
    }

    void setPersonalizacion() {
        addMouseListener(this);
        setOpaque(false);
        setForeground(AKStyle.RACOLOR_FONT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(AKStyle.RACURSOR_HAND);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(AKStyle.RACURSOR_DEFAULT);
    }
}
