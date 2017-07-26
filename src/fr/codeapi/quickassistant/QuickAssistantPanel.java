package fr.codeapi.quickassistant;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbPreferences;

final class QuickAssistantPanel extends javax.swing.JPanel {

	private Object[] type;
	private ArrayList<Object[]> data;
	private final QuickAssistantOptionsPanelController controller;

	QuickAssistantPanel(QuickAssistantOptionsPanelController controller) {
		this.controller = controller;
		initComponents();
		type = new Object[]{
			"Création fichier",
			"Ouvrir projet"
		};
		data = new ArrayList<Object[]>();
		// TODO listen to changes in form fields and call controller.changed()
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        mainTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Type", "Prefix", "Argument", "Parent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(mainTable);

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(QuickAssistantPanel.class, "QuickAssistantPanel.addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(editButton, org.openide.util.NbBundle.getMessage(QuickAssistantPanel.class, "QuickAssistantPanel.editButton.text")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(deleteButton, org.openide.util.NbBundle.getMessage(QuickAssistantPanel.class, "QuickAssistantPanel.deleteButton.text")); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
		CommandDialog d = new CommandDialog(null, true);
		d.panel = this;
		d.setCombo(type);
		d.setLocationRelativeTo(null);
		d.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
		if (mainTable.getSelectedRow()>-1) {
			CommandDialog d = new CommandDialog(null, true);
			d.panel = this;
			d.setCombo(type);
			d.setLocationRelativeTo(null);
			d.loadData(mainTable.getSelectedRow(), data.get(mainTable.getSelectedRow()));
			d.setVisible(true);
		}
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (mainTable.getSelectedRow()>-1) {
		DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
			model.removeRow(mainTable.getSelectedRow());	
			data.remove(mainTable.getSelectedRow());
		}
    }//GEN-LAST:event_deleteButtonActionPerformed

	public int getRefType(String nomType) {
		for (int i = 0; i < type.length; i++) {
			if (nomType.equals(type[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public void addData(int ref, String nom, int selectedType, String prefix, String argument, String parent) {
		DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
		if (ref==-1) {
			data.add(new Object[]{nom, type[selectedType], prefix, argument, parent});
			model.addRow(data.get(data.size()-1));
		}
		else {
			data.set(ref,new Object[]{nom, type[selectedType], prefix, argument, parent});
			model.removeRow(ref);
			model.insertRow(ref, data.get(ref));
		}
	}
	
	void load() {
		DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
		model.setNumRows(0);
		int nb = Integer.parseInt(NbPreferences.forModule(QuickAssistantPanel.class).get("nbCommand", "-1"));
		for (int i = 0; i < nb; i++) {
			String nom = getFieldData("nom"+i);
			int selectedType = Integer.parseInt(getFieldData("type"+i));
			String prefix = getFieldData("prefix"+i);
			String argument = getFieldData("argument"+i);
			String parent = getFieldData("parent"+i);
			data.add(new Object[]{nom, type[selectedType], prefix, argument, parent});
			model.addRow(data.get(data.size()-1));
		}
	}
	
	private String getFieldData(String name) {
		return NbPreferences.forModule(QuickAssistantPanel.class).get(name, "");
	}
	private void setFieldData(String name, String value) {
		NbPreferences.forModule(QuickAssistantPanel.class).put(name, value);
	}

	void store() {
		NbPreferences.forModule(QuickAssistantPanel.class).put("nbCommand", String.valueOf(mainTable.getRowCount()));
		int i = 0;
		for (Object[] data1 : data) {
			setFieldData("nom"+i, (String)data1[0]);
			setFieldData("type"+i, String.valueOf(getRefType((String)data1[1])));
			setFieldData("prefix"+i, (String)data1[2]);
			setFieldData("argument"+i, (String)data1[3]);
			setFieldData("parent"+i, (String)data1[4]);
			i++;
		}
	}

	boolean valid() {
		// TODO check whether form is consistent and complete
		return true;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTable;
    // End of variables declaration//GEN-END:variables
}
