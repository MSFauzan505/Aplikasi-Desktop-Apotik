/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apotik;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JDesktopPane;

public class background extends JDesktopPane {

    private Image image;

    public background() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {

            image = new javax.swing.ImageIcon(getClass().getResource("bg.jpg")).getImage();
            if (g != null) {
                g.drawImage(image,
                        (this.getSize().width - image.getWidth(null)) / 2,
                        (this.getSize().height - image.getHeight(null)) / 2,
                        null);
            }
        } catch (NullPointerException npe) {
            System.out.println("Can't find images !!");
        }
    }
}
