package com.example.simon.gadgeothek;

import java.io.Serializable;

public class UserRegistrationData implements Serializable {

    private String name;
    private String email;
    private String adresse;
    private String passwort;
    private String matrikelnummer;

    public UserRegistrationData() {
    }

    public UserRegistrationData(String name, String email, String adresse, String passwort, String matrikelnummer) {
        this.name = name;
        this.email = email;
        this.adresse = adresse;
        this.passwort = passwort;
        this.matrikelnummer = matrikelnummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    public void setMatrikelnummer(String matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }
}
