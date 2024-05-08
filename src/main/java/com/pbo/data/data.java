package com.pbo.data;

public class data {
    private int id;
    private String email, pwd, nama, foto;

    public data(int id, String email, String pwd, String nama, String foto) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.nama = nama;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void empty() {
        this.id = -1;
        this.email = null;
        this.pwd = null;
        this.nama = null;
        this.foto = null;
    }
}
