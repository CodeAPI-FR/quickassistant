package fr.codeapi.quickassistant;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;


/**
 *
 * @author thomas
 */
public class CommandDialog extends javax.swing.JDialog {
	
	public QuickAssistantPanel panel;
	private int originRef=-1;
	/**
	 * Creates new form CommandDialog
	 */
	public CommandDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}
	
	public void setCombo(Object[] data) {
		typeCB.setModel(new DefaultComboBoxModel(data));
	}
	public void loadData(int ref, Object[] data) {
		originRef = ref;
		nomTF.setText((String)data[0]);
		typeCB.setSelectedIndex(panel.getRefType((String)data[1]));
		prefixTF.setText((String)data[2]);
		argumentTF.setText((String)data[3]);
		parentTF.setText((String)data[4]);
	} 

	public void setSelectedAttribute(String name) {
		argumentTF.setText(name);
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomTF = new javax.swing.JTextField();
        nomLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        typeCB = new javax.swing.JComboBox();
        argumentLabel = new javax.swing.JLabel();
        argumentTF = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        prefixLabel = new javax.swing.JLabel();
        prefixTF = new javax.swing.JTextField();
        parentLabel = new javax.swing.JLabel();
        parentTF = new javax.swing.JTextField();
        openButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nomTF.setText(org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.nomTF.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(nomLabel, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.nomLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(typeLabel, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.typeLabel.text")); // NOI18N

        typeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Voir code" }));
        typeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCBActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(argumentLabel, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.argumentLabel.text")); // NOI18N

        argumentTF.setText(org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.argumentTF.text")); // NOI18N
        argumentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                argumentTFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(prefixLabel, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.prefixLabel.text")); // NOI18N

        prefixTF.setText(org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.prefixTF.text")); // NOI18N
        prefixTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixTFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(parentLabel, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.parentLabel.text")); // NOI18N

        parentTF.setText(org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.parentTF.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(openButton, org.openide.util.NbBundle.getMessage(CommandDialog.class, "CommandDialog.openButton.text")); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(cancelButton)
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prefixLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(parentLabel)
                                    .addGap(35, 35, 35))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(argumentLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeLabel)
                            .addComponent(nomLabel))
                        .addGap(44, 44, 44)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(argumentTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(prefixTF)
                    .addComponent(parentTF)
                    .addComponent(typeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomLabel))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prefixTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefixLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(argumentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(argumentLabel)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parentLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void argumentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_argumentTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_argumentTFActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
		panel.addData(originRef,nomTF.getText(), typeCB.getSelectedIndex(), prefixTF.getText(), argumentTF.getText(), parentTF.getText());
		dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void prefixTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prefixTFActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed

		switch (typeCB.getSelectedIndex()) {
			case 0:
				TemplateDialog d = new TemplateDialog(null, true);
				d.command = this;
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				break;
			case 1:
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				Integer op = jf.showOpenDialog(this);
				if (op==JFileChooser.APPROVE_OPTION) {
					argumentTF.setText(jf.getSelectedFile().getAbsolutePath());
				}
				break;
		}
    }//GEN-LAST:event_openButtonActionPerformed

    private void typeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCBActionPerformed
        switch (typeCB.getSelectedIndex()) {
			case 0:
				//Bouton Argument
				openButton.setVisible(true);
				//Parent
				parentLabel.setVisible(true);
				parentTF.setVisible(true);
				break;
			case 1:
				//Bouton Argument
				openButton.setVisible(true);
				//Parent
				parentLabel.setVisible(false);
				parentTF.setVisible(false);
				break;
		}
    }//GEN-LAST:event_typeCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel argumentLabel;
    private javax.swing.JTextField argumentTF;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JTextField nomTF;
    private javax.swing.JButton okButton;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel parentLabel;
    private javax.swing.JTextField parentTF;
    private javax.swing.JLabel prefixLabel;
    private javax.swing.JTextField prefixTF;
    private javax.swing.JComboBox typeCB;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
