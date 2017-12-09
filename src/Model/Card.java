/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JLabel;
import controller.cardController;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class Card extends JLabel implements MouseListener {

    Icon faceIcon;
    Icon backIcon;
    boolean faceup = false;
    int num;
    int iconWidthHalf, iconHeightHalf;
    boolean mousePressOnMe = false;
    cardController controller;

    public Card(cardController controller, Icon face, Icon back, int num) {
        super(back);
        this.faceIcon = face;
        this.backIcon = back;
        this.controller = controller;
        this.num = num;
        this.addMouseListener(this);
        this.iconWidthHalf = face.getIconWidth() / 2;
        this.iconHeightHalf = back.getIconHeight() / 2;

    }

    public int getnum() {
        return num;
    }

    public boolean overIcon(int x, int y) {

        int disX = Math.abs(x - this.getWidth() / 2);
        int disY = Math.abs(x - this.getHeight() / 2);

        if (disX > this.iconWidthHalf || disY > this.iconHeightHalf) {
            return false;
        }
        return true;
    }

    public void turnUp() {

        if (this.faceup) {
            return;
        }

        this.faceup = this.controller.turnUp(this);

        if (this.faceup) {
            this.setIcon(this.faceIcon);

            ++controller.attempts;

        }

    }

    public void turnDown() {
        if (!this.faceup) {
            return;
        }

        this.setIcon(this.backIcon);
        this.faceup = false;

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (overIcon(arg0.getX(), arg0.getY())) {
            this.turnUp();

        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if (overIcon(arg0.getX(), arg0.getY())) {

            this.mousePressOnMe = true;

        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (this.mousePressOnMe) {
            this.mousePressOnMe = false;
            this.mouseClicked(arg0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        this.mousePressOnMe = false;
    }

}
