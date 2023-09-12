/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.foobcryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author lakshya
 */
public class hybrid_decryption extends javax.swing.JFrame {
    String input,output,aes_key,private_key;
    File file;
    
    public static void decrypt_hybrid(String inputFile, String outputFile, String aes_keyFile, String privateKeyFile) throws Exception{
        // Read the encrypted AES key and IV
    FileInputStream keyIn = new FileInputStream(aes_keyFile);
    byte[] encryptedAESKey = new byte[256];
    keyIn.read(encryptedAESKey);
    byte[] iv = new byte[16];
    keyIn.read(iv);
    keyIn.close();

    // Decrypt the AES key and IV using the RSA private key
    FileInputStream privateKeyFileStream = new FileInputStream(privateKeyFile);
    byte[] privateKeyBytes = privateKeyFileStream.readAllBytes();
    privateKeyFileStream.close();
    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey rsaPrivateKey = keyFactory.generatePrivate(privateKeySpec);

    Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    rsaCipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

    byte[] decryptedAESKey = rsaCipher.doFinal(encryptedAESKey);
    SecretKeySpec aesKeySpec = new SecretKeySpec(decryptedAESKey, "AES");

    // Decrypt the file using the AES key and IV
    Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    aesCipher.init(Cipher.DECRYPT_MODE, aesKeySpec, new IvParameterSpec(iv));

    FileInputStream inFile = new FileInputStream(inputFile);
    FileOutputStream outFile = new FileOutputStream(outputFile);
    byte[] buffer = new byte[4096];
    int bytesRead;

    while ((bytesRead = inFile.read(buffer)) != -1) {
        byte[] outputBytes = aesCipher.update(buffer, 0, bytesRead);
        outFile.write(outputBytes);
    }

    byte[] outputBytes = aesCipher.doFinal();
    outFile.write(outputBytes);

    inFile.close();
    outFile.close();

    JOptionPane.showMessageDialog(null, "File Decrypted Successfully");
}
            
    public hybrid_decryption() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileLBL = new javax.swing.JLabel();
        fileBTN = new javax.swing.JButton();
        keyLBL = new javax.swing.JLabel();
        keyBTN = new javax.swing.JButton();
        decryptBTN = new javax.swing.JButton();
        selectedFileLBL = new javax.swing.JLabel();
        keyFileLBL = new javax.swing.JLabel();
        backBTN = new javax.swing.JButton();
        privateKeySelectLBL = new javax.swing.JLabel();
        privateKeyLBL = new javax.swing.JLabel();
        privateKeyBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileLBL.setText("File");

        fileBTN.setText("SELECT");
        fileBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileBTNActionPerformed(evt);
            }
        });

        keyLBL.setText("AES Key");

        keyBTN.setText("SELECT");
        keyBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyBTNActionPerformed(evt);
            }
        });

        decryptBTN.setText("DECRYPT");
        decryptBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptBTNActionPerformed(evt);
            }
        });

        selectedFileLBL.setText("No file selected");

        keyFileLBL.setText("No file selected");

        backBTN.setText("BACK");
        backBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBTNActionPerformed(evt);
            }
        });

        privateKeySelectLBL.setText("No file selected");

        privateKeyLBL.setText("Private Key");

        privateKeyBTN.setText("SELECT");
        privateKeyBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateKeyBTNActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setText("HYBRID DECRYPTION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backBTN))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileLBL)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(privateKeyLBL)
                                        .addGap(39, 39, 39))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(keyLBL)
                                        .addGap(53, 53, 53)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(keyBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(privateKeyBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(keyFileLBL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectedFileLBL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fileBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(privateKeySelectLBL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(decryptBTN)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileLBL)
                    .addComponent(fileBTN))
                .addGap(7, 7, 7)
                .addComponent(selectedFileLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyLBL)
                    .addComponent(keyBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyFileLBL)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(privateKeyLBL)
                    .addComponent(privateKeyBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(privateKeySelectLBL)
                .addGap(18, 18, 18)
                .addComponent(decryptBTN)
                .addGap(31, 31, 31)
                .addComponent(backBTN))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void decryptBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptBTNActionPerformed
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Decrypted File");

        int userSelection = fileChooser.showSaveDialog(this);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            File toSave = fileChooser.getSelectedFile();
            output = toSave.getAbsolutePath();
            try {
                decrypt_hybrid(input, output, aes_key, private_key);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Save cancelled");
        }
    }//GEN-LAST:event_decryptBTNActionPerformed

    private void fileBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileBTNActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        
        int result = fileChooser.showOpenDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            input = file.getAbsolutePath();
            
            if(file == null){
                JOptionPane.showMessageDialog(null,"No file selected, please select a file!");
            }
            else{
                selectedFileLBL.setText(file.getName());
            }
            
        }
        else if(result == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null,"File selection cancelled!");
                    }
        else if(result == JFileChooser.ERROR_OPTION){
            JOptionPane.showMessageDialog(null,"Error selecting file");
            
        }
    }//GEN-LAST:event_fileBTNActionPerformed

    private void keyBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyBTNActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        
        int result = fileChooser.showOpenDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            aes_key = file.getAbsolutePath();
            
            if(file == null){
                JOptionPane.showMessageDialog(null,"No key selected, please select a key!");
            }
            else{
                keyFileLBL.setText(file.getName());
            }
            
        }
        else if(result == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null,"Key selection cancelled!");
                    }
        else if(result == JFileChooser.ERROR_OPTION){
            JOptionPane.showMessageDialog(null,"Error selecting Key");
            
        }
    }//GEN-LAST:event_keyBTNActionPerformed

    private void backBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBTNActionPerformed
        decryption_type dt = new decryption_type();
        dt.show();
        this.setVisible(false);
    }//GEN-LAST:event_backBTNActionPerformed

    private void privateKeyBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privateKeyBTNActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        
        int result = fileChooser.showOpenDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            private_key = file.getAbsolutePath();
            
            if(file == null){
                JOptionPane.showMessageDialog(null,"No Private Key selected, please select a Private Key!");
            }
            else{
                privateKeySelectLBL.setText(file.getName());
            }
            
        }
        else if(result == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null,"Private Key selection cancelled!");
                    }
        else if(result == JFileChooser.ERROR_OPTION){
            JOptionPane.showMessageDialog(null,"Error selecting Private Key");
            
        }
    }//GEN-LAST:event_privateKeyBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(hybrid_decryption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hybrid_decryption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hybrid_decryption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hybrid_decryption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hybrid_decryption().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBTN;
    private javax.swing.JButton decryptBTN;
    private javax.swing.JButton fileBTN;
    private javax.swing.JLabel fileLBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton keyBTN;
    private javax.swing.JLabel keyFileLBL;
    private javax.swing.JLabel keyLBL;
    private javax.swing.JButton privateKeyBTN;
    private javax.swing.JLabel privateKeyLBL;
    private javax.swing.JLabel privateKeySelectLBL;
    private javax.swing.JLabel selectedFileLBL;
    // End of variables declaration//GEN-END:variables
}