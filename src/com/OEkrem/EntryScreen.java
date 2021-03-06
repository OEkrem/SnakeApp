package com.OEkrem;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author OEkrem
 */
public class EntryScreen extends javax.swing.JFrame {
    
    public static String userName = "";
    public static boolean helpStatus = false;
    public static boolean soundStatus = true;
    

    /**
     * Creates new form GirisEkrani
     */
    public EntryScreen() {
        try {
            initComponents();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - getHeight()) / 2);
            setLocation(x, y);
            setTitle("Snake App");
            BufferedImage icon = ImageIO.read(new File("YilanOyunu/icons/yilanIcon.png"));
            setIconImage(icon);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (IOException ex) {
            Logger.getLogger(EntryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton_Basla = new javax.swing.JButton();
        jTextField_UserName = new javax.swing.JTextField();
        jCheckBox_HelpOnOff = new javax.swing.JCheckBox();
        jCheckBox_MusicOnOff = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("??sminizi Giriniz");

        jButton_Basla.setText("BA??LA");
        jButton_Basla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BaslaActionPerformed(evt);
            }
        });

        jTextField_UserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UserNameActionPerformed(evt);
            }
        });

        jCheckBox_HelpOnOff.setSelected(true);
        jCheckBox_HelpOnOff.setText("Oyunu oynarken yard??m istiyorum");

        jCheckBox_MusicOnOff.setSelected(true);
        jCheckBox_MusicOnOff.setText("M??zik A?? / Kapa");

        jLabel2.setText("Was made by OEkrem Y??ld??r??m - T??m haklar?? sakl??d??r @ 2020");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Basla, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jCheckBox_HelpOnOff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox_MusicOnOff)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton_Basla, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_HelpOnOff)
                    .addComponent(jCheckBox_MusicOnOff))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_BaslaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BaslaActionPerformed
        if(jTextField_UserName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "L??tfen isminizi giriniz", "Eksik Bilgi", 0);
        }
        else{
            userName = jTextField_UserName.getText();
            if(jCheckBox_HelpOnOff.isSelected()){
                helpStatus = true;
            }else{
                helpStatus = false;
            }
            if(jCheckBox_MusicOnOff.isSelected()){
                soundStatus = true;
            }else{
                soundStatus = false;
            }
            setVisible(false);
            GameScreen.Calistir();
        }
    }//GEN-LAST:event_jButton_BaslaActionPerformed

    private void jTextField_UserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UserNameActionPerformed
        if(jTextField_UserName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "L??tfen isminizi giriniz", "Eksik Bilgi", 0);
        }
        else{
            userName = jTextField_UserName.getText();
            if(jCheckBox_HelpOnOff.isSelected()){
                helpStatus = true;
            }else{
                helpStatus = false;
            }
            if(jCheckBox_MusicOnOff.isSelected()){
                soundStatus = true;
            }else{
                soundStatus = false;
            }
            setVisible(false);
            GameScreen.Calistir();
        }
    }//GEN-LAST:event_jTextField_UserNameActionPerformed

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
            java.util.logging.Logger.getLogger(EntryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Basla;
    private javax.swing.JCheckBox jCheckBox_HelpOnOff;
    private javax.swing.JCheckBox jCheckBox_MusicOnOff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField_UserName;
    // End of variables declaration//GEN-END:variables
}
