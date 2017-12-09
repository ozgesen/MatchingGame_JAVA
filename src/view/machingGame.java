/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Card;
import controller.cardController;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author asus
 */
public class machingGame extends JLabel implements ActionListener {

    public JFrame mainFrame;
    private Container mainContentPane;
    private ImageIcon cardIcon[];
    private String cardType = "default";
    private cardController controller;
    JMenuBar menuBar;
    Card newCard;
    JLabel lbl;

    private static void newMenuItem(String text, JMenu menu, ActionListener listener) {
        JMenuItem newItem = new JMenuItem(text);
        newItem.setActionCommand(text);
        newItem.addActionListener(listener);
        menu.add(newItem);
    }

    public machingGame() {
        this.controller = new cardController();
        this.mainFrame = new JFrame("Matching Game");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(400, 550);
        this.mainContentPane = this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
        lbl = new JLabel("Attempts : 0");

        //Mneu Bar
        menuBar = new JMenuBar();
        this.mainFrame.setJMenuBar(menuBar);
        //Game Menu
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        newMenuItem("New Game", gameMenu, this);
        newMenuItem("Exit", gameMenu, this);
        //CardType
        JMenu cardType = new JMenu("Card Type");
        menuBar.add(cardType);
        newMenuItem("Cars", cardType, this);
        newMenuItem("Flowers", cardType, this);

        //AboutMenu
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);

        newMenuItem("About", aboutMenu, this);
        menuBar.add(aboutMenu);

        this.cardIcon = loadCardIcons(); //Load cards

    }

    private ImageIcon[] loadCardIcons() {

        ImageIcon icon[] = new ImageIcon[9];

        for (int i = 0; i < 9; i++) {

            String fileName = "images/" + cardType + "/card" + i + ".jpg";
            icon[i] = new ImageIcon(fileName);

        }
        return icon;
    }

    public JPanel makeCards() {
        controller.clear_attemp();
        JPanel panel = new JPanel(new GridLayout(4, 4));
        ImageIcon backIcon = this.cardIcon[8];

        int cardsToAdd[] = new int[16];
        for (int i = 0; i < 8; i++) {
            cardsToAdd[2 * i] = i;
            cardsToAdd[2 * i + 1] = i;
        }
        randomsizeCardArray(cardsToAdd);

        for (int i = 0; i < cardsToAdd.length; i++) {
            int num = cardsToAdd[i];
            newCard = new Card(controller, this.cardIcon[num], backIcon, num);
            panel.add(newCard);

            mainFrame.add(lbl);
            newCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {
                    String yazi = controller.set_label();
                    lbl.setText(yazi);
                }});}
    return panel;
    }

    private void randomsizeCardArray(int[] t) {
        Random randomsizer = new Random();

        for (int i = 0; i < t.length; i++) {  //degistir
            int d = randomsizer.nextInt(t.length);
            int s = t[d];
            t[d] = t[i];
            t[i] = s;

        }
    }

    public void newGame() {

        this.mainContentPane.removeAll();
        lbl.setText("Attempts : 0");
        this.mainContentPane.add(makeCards());

        this.mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            newGame();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Cars")) {
            cardType = "cars";
            this.cardIcon = loadCardIcons();
            newGame();
        }
        if (e.getActionCommand().equals("Flowers")) {
            cardType = "flowers";
            this.cardIcon = loadCardIcons();
            newGame();
        }

    }

}
