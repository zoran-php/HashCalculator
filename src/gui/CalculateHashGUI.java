package gui;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.bind.DatatypeConverter;

public class CalculateHashGUI extends javax.swing.JFrame {

    File file = null;
    int bufferSize = 8192;
    
    public CalculateHashGUI() {
        initComponents();
        setDragAndDrop();
    }
    
    private void setFile(){
        String path = file.getName();
        label_filename.setText(path);
        long filesize = file.length();
        int maximum = (int) (filesize / bufferSize);
        if(filesize % bufferSize != 0){
            maximum++;
        }
        progress.setValue(0);
        progress.setMaximum(maximum);
    }
    
    private void setDragAndDrop(){
        Component c = this;
        log.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_REFERENCE);
                    List<File> fileList = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for(File f:fileList){
                        file = f;
                        break;
                    }
                    setFile();
                } catch (UnsupportedFlavorException | IOException ex) {
                    JOptionPane.showMessageDialog(c, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("/img/error.png"));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        btn_chooseFile = new javax.swing.JButton();
        label_filename = new javax.swing.JLabel();
        toolbar_progress = new javax.swing.JToolBar();
        cb_hashAlgorithm = new javax.swing.JComboBox<>();
        btn_calculate = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        scroll = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hash Calculator");
        setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
        setMinimumSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        toolbar.setFloatable(false);

        btn_chooseFile.setText("Choose file");
        btn_chooseFile.setFocusable(false);
        btn_chooseFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_chooseFile.setMaximumSize(new java.awt.Dimension(140, 30));
        btn_chooseFile.setMinimumSize(new java.awt.Dimension(140, 30));
        btn_chooseFile.setPreferredSize(new java.awt.Dimension(140, 30));
        btn_chooseFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chooseFileActionPerformed(evt);
            }
        });
        toolbar.add(btn_chooseFile);

        label_filename.setMaximumSize(new java.awt.Dimension(270, 25));
        label_filename.setMinimumSize(new java.awt.Dimension(270, 25));
        label_filename.setPreferredSize(new java.awt.Dimension(270, 25));
        toolbar.add(label_filename);

        getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

        toolbar_progress.setFloatable(false);

        cb_hashAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MD2", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-512" }));
        cb_hashAlgorithm.setMinimumSize(new java.awt.Dimension(100, 30));
        cb_hashAlgorithm.setPreferredSize(new java.awt.Dimension(100, 30));
        toolbar_progress.add(cb_hashAlgorithm);

        btn_calculate.setText("Calculate");
        btn_calculate.setFocusable(false);
        btn_calculate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_calculate.setMaximumSize(new java.awt.Dimension(66, 30));
        btn_calculate.setMinimumSize(new java.awt.Dimension(66, 30));
        btn_calculate.setPreferredSize(new java.awt.Dimension(66, 30));
        btn_calculate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calculateActionPerformed(evt);
            }
        });
        toolbar_progress.add(btn_calculate);

        progress.setMaximumSize(new java.awt.Dimension(230, 25));
        progress.setMinimumSize(new java.awt.Dimension(230, 25));
        progress.setPreferredSize(new java.awt.Dimension(230, 25));
        progress.setStringPainted(true);
        toolbar_progress.add(progress);

        getContentPane().add(toolbar_progress, java.awt.BorderLayout.PAGE_END);

        log.setEditable(false);
        log.setColumns(20);
        log.setLineWrap(true);
        log.setRows(5);
        log.setWrapStyleWord(true);
        scroll.setViewportView(log);

        getContentPane().add(scroll, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooseFileActionPerformed
        chooseFile();
    }//GEN-LAST:event_btn_chooseFileActionPerformed

    private void btn_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calculateActionPerformed
        calculateHash();
    }//GEN-LAST:event_btn_calculateActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculateHashGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new CalculateHashGUI().setVisible(true);
        });
    }
    
    private void chooseFile(){
        JFileChooser jfc = new JFileChooser(".");
        int r = jfc.showOpenDialog(this);
        if(r == JFileChooser.APPROVE_OPTION){
            file = jfc.getSelectedFile();
            setFile();
        }
    }
    
    private void calculateHash(){
        if(this.file != null){
            new Thread(() -> {
                try {
                    progress.setValue(0);
                    String algoritam = cb_hashAlgorithm.getSelectedItem().toString();
                    MessageDigest md = MessageDigest.getInstance(algoritam);
                    FileInputStream ulazniTokPodatakaDatoteke = new FileInputStream(file.getAbsoluteFile());
                    byte[] buffer = new byte[bufferSize];
                    int brojProcitanihBajtova;
                    while((brojProcitanihBajtova = ulazniTokPodatakaDatoteke.read(buffer)) != -1){
                        md.update(buffer, 0, brojProcitanihBajtova);
                        SwingUtilities.invokeLater(() -> {
                            progress.setValue(progress.getValue() + 1);
                        });
                    }
                    byte[] hesBajtovi = md.digest();
                    String hesVrednost = DatatypeConverter.printHexBinary(hesBajtovi).toLowerCase();
                    log.setText("File: " + file.getName()+"\n"+algoritam + ": " + hesVrednost);
                } catch (NoSuchAlgorithmException | IOException ex) {
                    Logger.getLogger(CalculateHashGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_calculate;
    private javax.swing.JButton btn_chooseFile;
    private javax.swing.JComboBox<String> cb_hashAlgorithm;
    private javax.swing.JLabel label_filename;
    private javax.swing.JTextArea log;
    private javax.swing.JProgressBar progress;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JToolBar toolbar_progress;
    // End of variables declaration//GEN-END:variables
}
