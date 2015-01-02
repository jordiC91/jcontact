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
 * Classe repr�sentant un contact.
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
     * M�thode permettant de r�cup�rer le nom du contact.
     *
     * @return Le pr�nom du contact.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * M�thode permettant de r�cup�rer le pr�nom du contact.
     *
     * @return Le nom du contact.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * M�thode permettant de r�cup�rer le num�ro de t�l�phone du contact.
     *
     * @return Le num�ro de t�l�phone du contact.
     */
    public String getNumTel() {
        return this.num_tel;
    }

    /**
     * M�thode permettant de r�cup�rer l'age du contact.
     *
     * @return L'age du contact.
     */
    public int getAge() {
        return this.age;
    }

    /* Setters. */
    
    /**
     * M�thode permettant de modifier le nom du contact.<br />
     * La validit� de l'argument est test�e.
     *
     * @param nom Le nouveau nom du contact.
     */
    public void setNom(String nom) {
        if (nom == null); else if (nom.length() > 0) {
            this.nom = nom;
        }
    }

    /**
     * M�thode permettant de modifier le pr�nom du contact.<br />
     * La validit� de l'argument est test�e.
     *
     * @param prenom Le nouveau pr�nom du contact.
     */
    public void setPrenom(String prenom) {
        if (prenom == null); else if (prenom.length() > 0) {
            this.prenom = prenom;
        }
    }

    /**
     * M�thode permettant de modifier le num�ro de t�l�phone du contact.<br />
     * La validit� de l'argument est test�e.
     *
     * @param num_tel Le nouveau num�ro de t�l�phone du contact.
     */
    public void setNumTel(String num_tel) {
        if (num_tel == null); else if (num_tel.length() > 0) {
            this.num_tel = num_tel;
        }
    }

    /**
     * M�thode permettant de modifier l'�ge du contact.<br />
     * La validit� de l'argument est test�e.
     *
     * @param age Le nouvel �ge du contact.
     */
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    /* Autres. */
    
    /**
     * M�thode permettant d'enregistrer un contact.<br />
     * Le contact est enregistr� dans le r�pertoire "JContact/contacts/".
     *
     * @return <b>true</b> si le contact a bien �t� enregistr�.<br
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
     * M�thode permettant de r�cup�rer le contact sous la forme d'une cha�ne de caract�res.
     *
     * @return Une repr�sentation du contact sous la forme d'une cha�ne de caract?res.
     */
    @Override
    public String toString() {
        return nom + "-" + prenom + "-" + num_tel + "-" + age;
    }

    /**
     * M�thode permettant de comparer 2 contacts.
     *
     * @param contact Le contact ? comparer.
     * 
     * @return <b>true</b> si les deux contacts sont �gaux.<br />
     * <b>false</b>* sinon.
     */
    public boolean equals(Contact contact) {
        return (this.nom == contact.nom) && (this.prenom == contact.prenom) && (this.num_tel == contact.num_tel) && (this.age == contact.age);
    }
}
