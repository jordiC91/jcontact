/*
 * Copyright 2012 Jordi CHARPENTIER jordi.charpentier@gmail.com
 * 
 * This file is part of JContact.

 * JContact is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * JContact is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with JContact. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jc.ui.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jc.utils.FileUtils;

/**
 * Classe représentant la fenêtre d'informations de JChat.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class JContact extends JFrame {

    /* Eléments graphiques. */
    
    private final JPanel container = new JPanel();

    private final JPanel container_haut = new JPanel();
    private final JLabel lab_logo = new JLabel(new ImageIcon(getClass().getResource("/images/icones/icone_grande.png")));
    private final Box box = Box.createVerticalBox();
    private final JLabel lab_version = new JLabel();
    private final JLabel lab_auteur = new JLabel("Auteur : Jordi CHARPENTIER");
    private final JLabel lab_contact = new JLabel("Contact : contact@jordi-charpentier.com");

    private final JEditorPane zone_texte = new JEditorPane();
    private final JScrollPane scroll_texte = new JScrollPane(zone_texte);
    private final JButton bout_ok = new JButton("OK");

    public JContact() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("JContact");
        this.getContentPane().add(container);

        initContainers();

        this.setVisible(true);
        this.setResizable(false);
    }

    /* Méthode graphique. */
    
    private void initContainers() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createVerticalStrut(5));

        container.add(container_haut);
        container_haut.add(lab_logo);
        lab_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        container_haut.add(box);
        box.add(lab_version);
        
        try {
            if (!FileUtils.lireFichier("/version", JContact.class).equals("-1")) {
                lab_version.setText("JContact : " + FileUtils.lireFichier("/version", JContact.class));
            } else {
                lab_version.setText("Il manque le fichier de version.");
            }
        } catch (IOException ex) {
            Logger.getLogger(JContact.class.getName()).log(Level.SEVERE, null, ex);
        }

        lab_version.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(lab_auteur);
        lab_auteur.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(lab_contact);
        lab_contact.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.add(scroll_texte);
        scroll_texte.setMaximumSize(new Dimension(250, 250));
        scroll_texte.setAlignmentX(Component.CENTER_ALIGNMENT);

        zone_texte.setContentType("text/html");
        zone_texte.setText("<font face=\"Arial\"<center>"
                + "<h3>GNU General Public Licence</h3></center></font>"
                + "<font face=\"Arial\" size=3>This program is free software; "
                + "you can redistribute it and/or modify it under the terms of "
                + "the GNU General Public License as published by the Free "
                + "Software Foundation; either version 3 of the License, or (at your "
                + "option) any later version. <br><br> This program is distributed "
                + "in the hope that it will be useful, but WITHOUT ANY WARRANTY; "
                + "without even the implied warranty of MERCHANTABILITY or FITNESS "
                + "FOR A PARTICULAR PURPOSE. See the GNU General Public License for "
                + "more details. <br><br> You should have received a copy of the GNU"
                + " General Public License along with this program; if not, write"
                + " to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.</font>");

        zone_texte.setCaretPosition(0);
        zone_texte.setEditable(false);

        container.add(Box.createVerticalStrut(5));

        container.add(bout_ok);
        bout_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        bout_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        container.add(Box.createVerticalStrut(5));
    }
}