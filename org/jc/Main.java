package org.jc;

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

import java.io.File;
import java.io.IOException;

import javax.swing.*;
import org.jc.ui.frame.Accueil;

/**
 * Classe englobant la méthode <b>main</b> du programme.<br/>
 * Elle permet de lancer JContact.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        
        /* Le code suivant permet d'utiliser le LaF du système sur lequel JContact est lancé. */

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            /* On ignore cette exception. */
        }

        initFiles(); 
        
        Accueil accueil = new Accueil();  
    }

    private static void initFiles() {
        if (!new File("JContact").isDirectory()) {
            new File("JContact").mkdir();
        }

        if (!new File("JContact/contacts").isDirectory()) {
            new File("JContact/contacts").mkdir();
        }

        if (!new File("JContact/user.properties").isFile()) {
            try {
                new File("JContact/user.properties").createNewFile();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la création du fichier \"user.properties\". Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
