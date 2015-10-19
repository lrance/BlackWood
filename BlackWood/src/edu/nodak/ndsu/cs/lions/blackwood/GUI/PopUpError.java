/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PopUpError.java
 *
 * Created on May 3, 2010, 3:02:08 PM
 */

package edu.nodak.ndsu.cs.lions.blackwood.GUI;

/**
 *
 * @author Evan Pederson
 */
public class PopUpError extends javax.swing.JPanel {

    /** Creates new form PopUpError  */
    public PopUpError() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorLabelOne = new javax.swing.JLabel();
        errorLabelTwo = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        errorLabelOne.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        errorLabelOne.setForeground(new java.awt.Color(255, 0, 0));
        errorLabelOne.setText("You must enter in information in the required fields");

        errorLabelTwo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        errorLabelTwo.setText("\"*\" Indicates a required field");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(errorLabelOne))
                    .add(layout.createSequentialGroup()
                        .add(115, 115, 115)
                        .add(errorLabelTwo))
                    .add(layout.createSequentialGroup()
                        .add(152, 152, 152)
                        .add(okButton)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(errorLabelOne)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(errorLabelTwo)
                .add(18, 18, 18)
                .add(okButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        System.exit(0);
}//GEN-LAST:event_okButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabelOne;
    private javax.swing.JLabel errorLabelTwo;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

}
