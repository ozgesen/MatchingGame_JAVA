/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Card;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author asus
 */
public class cardController extends JLabel implements ActionListener {

    private Vector turnedCards;
    private Timer turnDownTimer;
    private final int turnDownDelay = 2000;
    public static int attempts;
    public String ss;

    public cardController() {

        attempts = 0;

        this.turnedCards = new Vector(2);
        this.turnDownTimer = new Timer(this.turnDownDelay, this);
        this.turnDownTimer.setRepeats(false);

    }

    private boolean doAddCard(Card card) {

        this.turnedCards.add(card);
        if (this.turnedCards.size() == 2) {
            Card otherCard = (Card) this.turnedCards.get(0);
            if (otherCard.getnum() == card.getnum()) {
                this.turnedCards.clear();
            } else {
                this.turnDownTimer.start();
            }
        }
        return true;
    }

    public boolean turnUp(Card card) {

        if (this.turnedCards.size() < 2) {
            return doAddCard(card);
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < this.turnedCards.size(); i++) {
            Card card = (Card) this.turnedCards.get(i);
            card.turnDown();
        }

        this.turnedCards.clear();

    }

    public String set_label() {
       ss = "Attempts : " + Integer.toString(attempts);

        return ss;
    }
    public void clear_attemp() {
        attempts = 0;
    }
    

}
