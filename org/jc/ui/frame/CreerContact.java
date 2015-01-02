/*
 * Copyright 2012 Jordi CHARPENTIER jordi.charpentier@gmail.com
 * 
 * This file is part of JContact.
 *
 * JContact is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * JContact is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JContact. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jc.ui.frame;

import org.jc.model.Contact;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import org.jc.ui.custom.JTextFieldPerso;

/**
 * Classe représentant la fenêtre de création de contact de JContact.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class CreerContact extends JFrame {

    /* Fenêtre. */
    
    private JPanel container = new JPanel();
    private JLabel lab_nom = new JLabel("Nom");
    private JTextFieldPerso ta_nom = new JTextFieldPerso(40);
    private JLabel lab_prenom = new JLabel("Prénom");
    private JTextFieldPerso ta_prenom = new JTextFieldPerso(40);
    private JLabel lab_num_tel = new JLabel("Numéro de téléphone");
    private JTextFieldPerso ta_num_tel = new JTextFieldPerso(10);
    private JLabel lab_age = new JLabel("Age");
    private JTextFieldPerso ta_age = new JTextFieldPerso(3);

    private JPanel container_bas = new JPanel();
    private JButton bout_valider = new JButton("Valider");
    private JButton bout_annuler = new JButton("Annuler");

    /* Popup menu. */
    
    private final JPopupMenu pop_up_menu1 = new JPopupMenu("Menu");
    private final JMenuItem pop_up_couper1 = new JMenuItem("Couper");
    private final JMenuItem pop_up_copier1 = new JMenuItem("Copier");
    private final JMenuItem pop_up_coller1 = new JMenuItem("Coller");

    private final JPopupMenu pop_up_menu2 = new JPopupMenu("Menu");
    private final JMenuItem pop_up_couper2 = new JMenuItem("Couper");
    private final JMenuItem pop_up_copier2 = new JMenuItem("Copier");
    private final JMenuItem pop_up_coller2 = new JMenuItem("Coller");

    private final JPopupMenu pop_up_menu3 = new JPopupMenu("Menu");
    private final JMenuItem pop_up_couper3 = new JMenuItem("Couper");
    private final JMenuItem pop_up_copier3 = new JMenuItem("Copier");
    private final JMenuItem pop_up_coller3 = new JMenuItem("Coller");

    private final JPopupMenu pop_up_menu4 = new JPopupMenu("Menu");
    private final JMenuItem pop_up_couper4 = new JMenuItem("Couper");
    private final JMenuItem pop_up_copier4 = new JMenuItem("Copier");
    private final JMenuItem pop_up_coller4 = new JMenuItem("Coller");

    /* Listener. */
    
    private ActionsListener actionslistener = new ActionsListener();

    /* Autres. */
    
    private String nom;
    private String prenom;

    public CreerContact() {
        this.setTitle("JContact");
        this.setSize(250, 250);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.requestFocus();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));

        this.setLayout(new BorderLayout());
        this.add(container, BorderLayout.CENTER);
        this.add(container_bas, BorderLayout.SOUTH);

        initContainers();
        initPopUpMenu();
        setListener();
        setIconPopuMenu();

        this.setVisible(true);
        this.setResizable(false);
    }

    public CreerContact(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;

        ta_nom.setEditable(false);
        ta_prenom.setEditable(false);

        this.setTitle("JContact");
        this.setSize(250, 250);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.requestFocus();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));

        this.setLayout(new BorderLayout());
        this.add(container, BorderLayout.CENTER);
        this.add(container_bas, BorderLayout.SOUTH);

        initContainers();
        initPopUpMenu();
        setListener();
        setIconPopuMenu();

        this.setVisible(true);
        this.setResizable(false);
    }

    /* Méthodes graphiques. */
    
    private void initContainers() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(5));
        container.add(lab_nom);
        lab_nom.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(ta_nom);
        ta_nom.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta_nom.setMaximumSize(new Dimension(130, 25));

        container.add(Box.createVerticalStrut(5));
        container.add(lab_prenom);
        lab_prenom.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(ta_prenom);
        ta_prenom.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta_prenom.setMaximumSize(new Dimension(130, 25));

        container.add(Box.createVerticalStrut(5));
        container.add(lab_num_tel);
        lab_num_tel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(ta_num_tel);
        ta_num_tel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta_num_tel.setMaximumSize(new Dimension(130, 25));

        container.add(Box.createVerticalStrut(5));
        container.add(lab_age);
        lab_age.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(ta_age);
        ta_age.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta_age.setMaximumSize(new Dimension(130, 25));
        container.add(Box.createVerticalStrut(5));

        container_bas.add(bout_valider);
        container_bas.add(bout_annuler);
    }

    private void initPopUpMenu() {
        pop_up_menu1.add(pop_up_couper1);
        pop_up_menu1.add(pop_up_copier1);
        pop_up_menu1.add(pop_up_coller1);

        pop_up_menu2.add(pop_up_couper2);
        pop_up_menu2.add(pop_up_copier2);
        pop_up_menu2.add(pop_up_coller2);

        pop_up_menu3.add(pop_up_couper3);
        pop_up_menu3.add(pop_up_copier3);
        pop_up_menu3.add(pop_up_coller3);

        pop_up_menu4.add(pop_up_couper4);
        pop_up_menu4.add(pop_up_copier4);
        pop_up_menu4.add(pop_up_coller4);
    }

    private void setListener() {
        /* Menu popup. */

        ta_nom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if ((SwingUtilities.isRightMouseButton(e)) && (ta_nom.isEditable())) {
                    pop_up_menu1.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        ta_prenom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if ((SwingUtilities.isRightMouseButton(e)) && (ta_prenom.isEditable())) {
                    pop_up_menu2.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        ta_num_tel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    pop_up_menu3.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        ta_age.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    pop_up_menu4.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        pop_up_couper1.addActionListener(actionslistener);
        pop_up_copier1.addActionListener(actionslistener);
        pop_up_coller1.addActionListener(actionslistener);
        pop_up_couper2.addActionListener(actionslistener);
        pop_up_copier2.addActionListener(actionslistener);
        pop_up_coller2.addActionListener(actionslistener);
        pop_up_couper3.addActionListener(actionslistener);
        pop_up_copier3.addActionListener(actionslistener);
        pop_up_coller3.addActionListener(actionslistener);
        pop_up_couper4.addActionListener(actionslistener);
        pop_up_copier4.addActionListener(actionslistener);
        pop_up_coller4.addActionListener(actionslistener);

        /* Boutons. */
        bout_valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if ((ta_nom.getText().length() > 0) && (ta_prenom.getText().length() > 0) && (ta_num_tel.getText().length() > 0) && (ta_age.getText().length() > 0)) {
                    if ((new File("JContact/contacts/" + nom + " " + prenom).exists()) && (nom != null) && (prenom != null)) {
                        if (new Contact(ta_nom.getText(), ta_prenom.getText(), ta_num_tel.getText(), Integer.parseInt(ta_age.getText())).enregistrerContact()) {
                            JOptionPane.showMessageDialog(null, "Contact modifié.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    } else if ((!new File("JContact/contacts/" + ta_nom.getText() + " " + ta_prenom.getText()).exists()) && (nom == null) && (prenom == null)) {
                        if (new Contact(ta_nom.getText(), ta_prenom.getText(), ta_num_tel.getText(), Integer.parseInt(ta_age.getText())).enregistrerContact()) {
                            JOptionPane.showMessageDialog(null, "Contact créé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la création du contact. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ce contact existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Au moins un des champs a mal été rempli.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bout_annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                testQuitter();
            }
        });
    }

    private void setIconPopuMenu() {
        pop_up_couper1.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/couper.png")));
        pop_up_copier1.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/copier.png")));
        pop_up_coller1.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/coller.png")));

        pop_up_couper2.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/couper.png")));
        pop_up_copier2.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/copier.png")));
        pop_up_coller2.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/coller.png")));

        pop_up_couper3.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/couper.png")));
        pop_up_copier3.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/copier.png")));
        pop_up_coller3.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/coller.png")));

        pop_up_couper4.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/couper.png")));
        pop_up_copier4.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/copier.png")));
        pop_up_coller4.setIcon(new ImageIcon(getClass().getResource("/images/popup_menu/coller.png")));
    }

    public void setNom(String nom) {
        ta_nom.setText(nom);
    }

    public void setPrenom(String prenom) {
        ta_prenom.setText(prenom);
    }

    public void setNumTel(String num_tel) {
        ta_num_tel.setText(num_tel);
    }

    public void setAge(String age) {
        ta_age.setText(age);
    }

    /* Autre. */
    private void testQuitter() {
        boolean resultat = false;

        if ((ta_nom.getText().length() > 0) || (ta_prenom.getText().length() > 0) || (ta_num_tel.getText().length() > 0)) {
            resultat = true;
        }

        if (resultat) {
            if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment annuler ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
            }
        } else {
            dispose();
        }
    }

    /* Listener. */
    private class ActionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((e.getSource() == pop_up_couper1) && (!pop_up_menu1.isVisible())) {
                ta_nom.cut();
            } else if ((e.getSource() == pop_up_copier1) && (!pop_up_menu1.isVisible())) {
                ta_nom.copy();
            } else if ((e.getSource() == pop_up_coller1) && (!pop_up_menu1.isVisible())) {
                ta_nom.paste();
            } else if ((e.getSource() == pop_up_couper2) && (!pop_up_menu2.isVisible())) {
                ta_prenom.cut();
            } else if ((e.getSource() == pop_up_copier2) && (!pop_up_menu2.isVisible())) {
                ta_prenom.copy();
            } else if ((e.getSource() == pop_up_coller2) && (!pop_up_menu2.isVisible())) {
                ta_prenom.paste();
            } else if ((e.getSource() == pop_up_couper3) && (!pop_up_menu2.isVisible())) {
                ta_num_tel.cut();
            } else if ((e.getSource() == pop_up_copier3) && (!pop_up_menu2.isVisible())) {
                ta_num_tel.copy();
            } else if ((e.getSource() == pop_up_coller3) && (!pop_up_menu2.isVisible())) {
                ta_num_tel.paste();
            } else if ((e.getSource() == pop_up_couper4) && (!pop_up_menu2.isVisible())) {
                ta_age.cut();
            } else if ((e.getSource() == pop_up_copier4) && (!pop_up_menu2.isVisible())) {
                ta_age.copy();
            } else if ((e.getSource() == pop_up_coller4) && (!pop_up_menu2.isVisible())) {
                ta_age.paste();
            }
        }
    }
}
