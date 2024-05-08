package com.pbo.data;

import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.pbo.App;
import com.pbo.service.connection;

public class dataControl {

    // tambah data ke database
    protected int createAkun(data akun) throws Exception {
        String query = "SELECT * FROM akun WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, akun.getEmail());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return 1;
                }
            }
        }
        query = "INSERT INTO akun (email, pwd, nama, foto) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.conn.prepareStatement(query);
            preparedStatement.setString(1, akun.getEmail());
            preparedStatement.setString(2, hashPass(akun.getPwd()));
            preparedStatement.setString(3, akun.getNama());
            preparedStatement.setString(4, akun.getFoto());
            preparedStatement.executeUpdate();
            getAkun(akun.getEmail(), akun.getPwd());
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
            return 2;
        }
    }

    // membaca data pada database berdasarkan email dan password
    protected Boolean getAkun(String email, String password)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String query = "SELECT * FROM akun WHERE email = ? AND pwd = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashPass(password));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    App.akun = new data(resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("pwd"),
                            resultSet.getString("nama"),
                            resultSet.getString("foto"));
                    return true;
                }
            }
        }
        System.err.println("Failed to get account");
        return false;
    }

    // update data pada database
    protected void updateAkun(data akun) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String query = "UPDATE akun SET nama = ?, pwd = ?, foto = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, akun.getNama());
            preparedStatement.setString(2, hashPass(akun.getPwd()));
            preparedStatement.setString(3, akun.getFoto());
            preparedStatement.setInt(4, akun.getId());
            preparedStatement.executeUpdate();
        }
    }

    // menghapus data dari database
    protected void deleteAkun(int id) throws SQLException {
        String query = "DELETE FROM akun WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private static final String predeterminedSalt = "PraktikumPBO";

    private String hashPass(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = predeterminedSalt.getBytes();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }
}
