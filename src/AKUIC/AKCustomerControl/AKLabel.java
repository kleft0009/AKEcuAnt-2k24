package UIC.CustomerControl;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import UIC.RAStyle;

public class RALabel extends JLabel{
    public RALabel(){
        customizeComponent();
    }
    public RALabel(String text){
        setText(text);
        customizeComponent();
    }
    private void customizeComponent(){
        setCustomizeComponent(getText(), RAStyle.RAFONT, RAStyle.RACOLOR_FONT_LIGHT, RAStyle.RAALIGNMENT_LEFT);
    }
    public void setCustomizeComponent(String text, Font  font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(false);
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(alignment);
        //setIcon(new ImageIcon(iconPath));
    }
}