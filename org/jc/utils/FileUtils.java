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

package org.jc.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe représentant une classe permettant de faciliter la manipulation de fichier.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class FileUtils {
    
    /**
     * Constructeur par défaut de la classe.<br /> 
     * Il est déclaré privé pour emp?cher l'instanciation de la classe.
     */
    private FileUtils() {
        
    }
    
    /**
     * Méthode permettant de lire un fichier dans le <b>.jar</b> de l'application et de renvoyer son contenu.
     * 
     * @param pathFile Le chemin du fichier ? lire dans le <b>.jar</b>.
     * @param c La classe qui permettra "d'accéder" aux données dans le <b>.jar</b>.
     * 
     * @return Le contenu du fichier, ou une chaine vide si le fichier donné en argument n'existe pas.
     * 
     * @throws IOException Si la lecture du fichier échoue.
     */
    public static String lireFichier(String pathFile, Class c) throws IOException {
        String contenuFichier = "";

        int n;
        InputStream fis = c.getClass().getResourceAsStream(pathFile);

        while ((n = fis.available()) > 0) {
            byte[] b = new byte[n];
            int result = fis.read(b);

            if (result == -1) {
                break;
            }

            contenuFichier = new String(b);
        }

        return contenuFichier;
    }

    /**
     * Méthode permettant d'écrire un texte dans un fichier.
     *
     * @param contenu Le contenu ? écrire dans le fichier.
     * @param pathFichier L'adresse du fichier.
     *
     * @throws IOException Si la lecture du fichier échoue.
     */
    public static void ecrireTexteFichier(String contenu, String pathFichier) throws IOException {
        FileWriter lu = new FileWriter(pathFichier);
        
        try (BufferedWriter out = new BufferedWriter(lu)) {
            out.write(contenu);
        }
    }
}
