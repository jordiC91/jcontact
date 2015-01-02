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

package org.jc.model;

import java.io.*;

/**
 * Classe représentant un contact.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Contact {

    private String nom;

    private String prenom;

    private String num_tel;

    private int age;

    public Contact(String nom, String prenom, String num_tel, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.age = age;
    }

    /* Accesseurs. */
    
    /**
     * Méthode permettant de récupérer le nom du contact.
     *
     * @return Le prénom du contact.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode permettant de récupérer le prénom du contact.
     *
     * @return Le nom du contact.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Méthode permettant de récupérer le numéro de téléphone du contact.
     *
     * @return Le numéro de téléphone du contact.
     */
    public String getNumTel() {
        return this.num_tel;
    }

    /**
     * Méthode permettant de récupérer l'age du contact.
     *
     * @return L'age du contact.
     */
    public int getAge() {
        return this.age;
    }

    /* Setters. */
    
    /**
     * Méthode permettant de modifier le nom du contact.<br />
     * La validité de l'argument est testée.
     *
     * @param nom Le nouveau nom du contact.
     */
    public void setNom(String nom) {
        if (nom == null); else if (nom.length() > 0) {
            this.nom = nom;
        }
    }

    /**
     * Méthode permettant de modifier le prénom du contact.<br />
     * La validité de l'argument est testée.
     *
     * @param prenom Le nouveau prénom du contact.
     */
    public void setPrenom(String prenom) {
        if (prenom == null); else if (prenom.length() > 0) {
            this.prenom = prenom;
        }
    }

    /**
     * Méthode permettant de modifier le numéro de téléphone du contact.<br />
     * La validité de l'argument est testée.
     *
     * @param num_tel Le nouveau numéro de téléphone du contact.
     */
    public void setNumTel(String num_tel) {
        if (num_tel == null); else if (num_tel.length() > 0) {
            this.num_tel = num_tel;
        }
    }

    /**
     * Méthode permettant de modifier l'âge du contact.<br />
     * La validité de l'argument est testée.
     *
     * @param age Le nouvel âge du contact.
     */
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    /* Autres. */
    
    /**
     * Méthode permettant d'enregistrer un contact.<br />
     * Le contact est enregistré dans le répertoire "JContact/contacts/".
     *
     * @return <b>true</b> si le contact a bien été enregistré.<br
     * /><b>false</b> sinon.
     */
    public boolean enregistrerContact() {
        FileOutputStream flux_fichier = null;
        String path_file = "JContact/contacts/" + this.nom + " " + this.prenom;

        try {
            flux_fichier = new FileOutputStream(new File(path_file));
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            flux_fichier.write(this.toString().getBytes());
            flux_fichier.flush();
            flux_fichier.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Méthode permettant de récupérer le contact sous la forme d'une chaîne de caractères.
     *
     * @return Une représentation du contact sous la forme d'une chaîne de caract?res.
     */
    @Override
    public String toString() {
        return nom + "-" + prenom + "-" + num_tel + "-" + age;
    }

    /**
     * Méthode permettant de comparer 2 contacts.
     *
     * @param contact Le contact ? comparer.
     * 
     * @return <b>true</b> si les deux contacts sont égaux.<br />
     * <b>false</b>* sinon.
     */
    public boolean equals(Contact contact) {
        return (this.nom == contact.nom) && (this.prenom == contact.prenom) && (this.num_tel == contact.num_tel) && (this.age == contact.age);
    }
}
