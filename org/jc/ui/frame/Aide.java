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
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

/**
 * Classe représentant la fenêtre d'aide de JContact.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Aide extends JFrame {
    
    /* Eléments graphiques. */

    private final JEditorPane zone_texte = new JEditorPane();
    private final JScrollPane scroll_texte = new JScrollPane(zone_texte);

    public Aide() {
        this.setTitle("Aide");
        this.setSize(500, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));
        this.getContentPane().add(scroll_texte);

        initContainer();

        this.setVisible(true);
        this.setResizable(false);
    }

    /* Méthode graphique. */
    
    private void initContainer() {
        zone_texte.setContentType("text/html");

        if (!lireFichier("/autres/aide.html").equals("-1")) {
            zone_texte.setText(lireFichier("/autres/aide.html"));
        } else {
            zone_texte.setText("<br />Erreur. Le fichier d'aide a été supprimé.");
        }

        zone_texte.setCaretPosition(0);
        zone_texte.setEditable(false);
    }

    /* Autre. */
    
    /**
     * Méthode permettant de lire un fichier qui se situe dans le ".jar" du logiciel.
     *
     * @param path_file Le chemin du fichier à lire. Le fichier doit se situer dans le ".jar".
     * 
     * @return Le contenu du fichier si la lecture s'est bien passée.<br />
     * <b>-1</b> sinon.
     */
    private String lireFichier(String path_file) {
        String contenu_fichier = "";

        try {
            int n;
            InputStream fis = Aide.this.getClass().getResourceAsStream(path_file);

            while ((n = fis.available()) > 0) {
                byte[] b = new byte[n];
                int result = fis.read(b);
                if (result == -1) {
                    break;
                }
                contenu_fichier = new String(b);
            }
        } catch (IOException err) {
            return "-1";
        }

        return contenu_fichier;
    }
}