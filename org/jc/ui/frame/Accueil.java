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

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

/**
 * Classe représentant la fenêtre d'accueil de JContact.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */

public class Accueil extends JFrame {

    /* Barre de menu. */
    
    private final JMenuBar barre_menu = new JMenuBar();
    private final JMenu menu_fichier = new JMenu("Fichier");
    private final JMenuItem sous_menu_export = new JMenuItem("Exporter les contacts");
    private final JMenuItem sous_menu_quitter = new JMenuItem("Quitter");
    private final JMenu menu_aide = new JMenu("Aide");
    private final JMenuItem sous_menu_aide = new JMenuItem("Aide");
    private final JMenuItem sous_menu_logiciel = new JMenuItem("Logiciel");

    /* Fenêtre. */
    
    private final JPanel container = new JPanel();
    private final JButton bout_nouv_contact = new JButton("Nouveau contact");
    private JList liste_contact;
    private JScrollPane scroll_liste;

    /* Popup menu. */
    
    private JPopupMenu pop_up_menu;
    private final JMenuItem pop_up_modifier_contact = new JMenuItem("Voir/Modifier");
    private final JMenuItem pop_up_supprimer_contact = new JMenuItem("Supprimer");

    /* Autre. */
    
    private int compteur_fenetre = 0;

    public Accueil() {
        this.setTitle("JContact");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(barre_menu);
        this.setFocusable(true);
        this.requestFocus();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("content/images/icones/icone.png")));
        this.getContentPane().add(container);

        initBarreMenu();
        initContainers();
        initPopupMenu();
        setListener();

        this.setVisible(true);
        this.setResizable(false);
    }

    /* Méthodes graphiques. */
    
    private void initBarreMenu() {
        barre_menu.add(menu_fichier);
        menu_fichier.add(sous_menu_export);
        menu_fichier.add(sous_menu_quitter);
        barre_menu.add(menu_aide);
        menu_aide.add(sous_menu_aide);
        menu_aide.add(sous_menu_logiciel);
    }

    private void initContainers() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(5));
        container.add(bout_nouv_contact);
        bout_nouv_contact.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        bout_nouv_contact.setIcon(new ImageIcon(getClass().getResource("/content/images/nouveau.png")));
        container.add(Box.createVerticalStrut(5));

        if ((new File("JContact/contacts").list() == null) || (new File("JContact/contacts").list().length == 0)) {
            liste_contact = new JList();
        } else {
            liste_contact = new JList(new File("JContact/contacts").list());
        }

        liste_contact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste_contact.setLayoutOrientation(JList.VERTICAL);
        liste_contact.setVisibleRowCount(-1);

        scroll_liste = new JScrollPane(liste_contact);
        scroll_liste.setMaximumSize(new Dimension(200, 300));
        scroll_liste.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(scroll_liste);
        container.add(Box.createVerticalStrut(10));
    }

    private void initPopupMenu() {
        pop_up_menu = new JPopupMenu("Menu");
        pop_up_menu.add(pop_up_modifier_contact);
        pop_up_menu.add(pop_up_supprimer_contact);
    }

    private void setListener() {
        /* Barre de menu. */

        sous_menu_export.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // TODO
                }
            }
        });
        
        sous_menu_quitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    dispose();
                }
            }
        });

        sous_menu_aide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Aide a = new Aide();
                }
            }
        });

        sous_menu_logiciel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    JContact jc = new JContact();
                }
            }
        });

        /* Menu Popup. */
        pop_up_modifier_contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (compteur_fenetre == 0) {
                        compteur_fenetre++;
                        CreerContact nouv_contact = new CreerContact(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[0], lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[1]);

                        try {
                            nouv_contact.setNom(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[0]);
                            nouv_contact.setPrenom(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[1]);
                            nouv_contact.setNumTel(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[2]);
                            nouv_contact.setAge(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[3]);

                            nouv_contact.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    compteur_fenetre--;
                                    liste_contact.setListData(new File("JContact/contacts").list());
                                }

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    compteur_fenetre--;
                                    liste_contact.setListData(new File("JContact/contacts").list());
                                }
                            });
                        } catch (NullPointerException a) {
                            nouv_contact.dispose();
                            JOptionPane.showMessageDialog(null, "Ce fichier n'est pas un contact, ou il a été modifié.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Il y a déj? une fen?tre ouverte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pop_up_supprimer_contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (new File("JContact/contacts/" + (String) liste_contact.getSelectedValue()).delete()) {
                        liste_contact.setListData(new File("JContact/contacts").list());
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du fichier " + (String) liste_contact.getSelectedValue() + ". Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        /* Autre. */
        bout_nouv_contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compteur_fenetre == 0) {
                    compteur_fenetre++;

                    new CreerContact().addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            compteur_fenetre--;
                            liste_contact.setListData(new File("JContact/contacts").list());
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            compteur_fenetre--;
                            liste_contact.setListData(new File("JContact/contacts").list());
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Il y a déjà une fen?tre ouverte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        liste_contact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (((String) liste_contact.getSelectedValue()) != null) {
                        if (compteur_fenetre == 0) {
                            compteur_fenetre++;
                            CreerContact nouv_contact = new CreerContact(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[0], lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[1]);

                            try {
                                nouv_contact.setNom(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[0]);
                                nouv_contact.setPrenom(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[1]);
                                nouv_contact.setNumTel(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[2]);
                                nouv_contact.setAge(lireFichier("JContact/contacts/" + (String) liste_contact.getSelectedValue()).split("-")[3]);
                            } catch (NullPointerException a) {
                                nouv_contact.dispose();
                                JOptionPane.showMessageDialog(null, "Ce fichier n'est pas un contact, ou il a été modifié.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }

                            nouv_contact.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    compteur_fenetre--;
                                    liste_contact.setListData(new File("JContact/contacts").list());
                                }

                                @Override
                                public void windowClosed(WindowEvent e) {
                                    compteur_fenetre--;
                                    liste_contact.setListData(new File("JContact/contacts").list());
                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, "Il y a déj? une fen?tre ouverte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if ((SwingUtilities.isRightMouseButton(e)) && (((String) liste_contact.getSelectedValue()) != null)) {
                    pop_up_menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /* Autre. */
    
    private String lireFichier(String path_file) {
        String contenu_fichier = "";

        try {
            FileInputStream fis = new FileInputStream(path_file);
            int n;

            while ((n = fis.available()) > 0) {
                byte[] b = new byte[n];
                int result = fis.read(b);
                if (result == -1) {
                    break;
                }
                contenu_fichier = new String(b);
            }
        } catch (IOException err) {
            return "";
        }

        return contenu_fichier;
    }

}
