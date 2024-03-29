/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
/**
 *
 * @author Lucas Renan Maues Nunes
 */
public class TelaOs extends javax.swing.JFrame {
    // frameworks do pacote java.sql
    // inicializando a variável conexao
    Connection conexao = null;
    // variáveis especiais de apoio à conexão com o BD      
    PreparedStatement pst = null;
    // objeto matriz que recebe o resultado do comando SQL
    ResultSet rs = null;
    
    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();
        
        // executa o metodo conector
        // recebe a string de conexao ou NULL
        conexao = ModuloConexao.conector();
    }
    
    // Metodo que vai inserir uma nova OS
    private void auxAdd() {
        String sql = "INSERT INTO tbos (equipamento,defeito,servico,tecnico,valor,idcli) VALUES (?,?,?,?,?,?)";
        
        try {
            // Preparando a conexao com o BD
            pst = conexao.prepareStatement(sql);
            
            // Atribuindo os campos do formulario às posições do sql
            pst.setString(1, txtOsEquip.getText());
            pst.setString(2, txtOsDefeito.getText());
            pst.setString(3, txtOsServico.getText());
            pst.setString(4, txtOsTecnico.getText());
            pst.setString(5, txtOsValor.getText());
            pst.setString(6, txtOsIdCliente.getText());
            
            // validade os campos
            if ((txtOsEquip.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty()) || (txtOsServico.getText().isEmpty()) || (txtOsTecnico.getText().isEmpty()) || (txtOsIdCliente.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // executa o comando SQL
                int adicionado = pst.executeUpdate(); // retorna um valor numerico diferente de ZERO
                
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço adicionado com sucesso");
                    
                    // limpando os campos de entrada
                    txtOsEquip.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCliente.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // MÉTODO PARA ADICIONAR OS
    public void adicionar() {
        // Instrução SQL para buscar a chave primaria da tabela de clentea
        String sql = "SELECT idcli FROM tbclientes WHERE idcli = ?";
        
        try {
            // Prepara a conexao com o BD
            pst = conexao.prepareStatement(sql);
            
            // Atribui o id do campo do form à consulta sql da tabela de clientes 
            pst.setString(1, txtOsIdCliente.getText());
            
            // Retorna a consulta no ResultSet
            rs = pst.executeQuery();
            
            if (rs.next()) {// Valida a existencia de um cliente cadastrado na tabela de clientes
                auxAdd(); // chama o metodo que vai fazer a inserção da OS
            } else {
                JOptionPane.showMessageDialog(null, "O cliente com ID "+txtOsIdCliente.getText()+" ainda não foi registrado");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void consultar() {
        // Consulta SQL
        String sql = "SELECT * FROM tbos WHERE os=?";
        
        try {
            // Prepara a conexao com o BD
            pst = conexao.prepareStatement(sql);
            
            // Atribui o id os na consulta sql
            pst.setString(1, txtOsId.getText());
            
            // executa a consulta sql e atribui ao ResultSet
            rs = pst.executeQuery();
            
            // valida a existencia da OS
            if (rs.next()) {
                txtOsData.setText(rs.getString(2));
                txtOsEquip.setText(rs.getString(3));
                txtOsDefeito.setText(rs.getString(4));
                txtOsServico.setText(rs.getString(5));
                txtOsTecnico.setText(rs.getString(6));
                txtOsValor.setText(rs.getString(7));
                txtOsIdCliente.setText(rs.getString(8));
            } else {
                JOptionPane.showMessageDialog(null, "Ordem de serviço não existe");
                
                // limpando os campos do formulários
                txtOsData.setText(null);
                txtOsEquip.setText(null);
                txtOsDefeito.setText(null);
                txtOsServico.setText(null);
                txtOsTecnico.setText(null);
                txtOsValor.setText(null);
                txtOsIdCliente.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // METODO PARA ALTERAR A OS
    private void auxAlt() {
        String sql = "UPDATE tbos SET equipamento=?, defeito=?, servico=?, tecnico=?, valor=?, idcli=? WHERE os=?";
        
        try {
            // Prepara a conexão com o BD
            pst = conexao.prepareStatement(sql);
            
            // Atribuindo os campos do formulario às posições do sql
            pst.setString(1, txtOsEquip.getText());
            pst.setString(2, txtOsDefeito.getText());
            pst.setString(3, txtOsServico.getText());
            pst.setString(4, txtOsTecnico.getText());
            pst.setString(5, txtOsValor.getText());
            pst.setString(6, txtOsIdCliente.getText());
            pst.setString(7, txtOsId.getText());
            
            // validade os campos
            if ((txtOsId.getText().isEmpty()) ||(txtOsEquip.getText().isEmpty()) || (txtOsDefeito.getText().isEmpty()) || (txtOsServico.getText().isEmpty()) || (txtOsTecnico.getText().isEmpty()) || (txtOsIdCliente.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // executa o comando SQL
                int adicionado = pst.executeUpdate(); // retorna um valor numerico diferente de ZERO
                
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço adicionado com sucesso");
                    
                    // limpando os campos de entrada
                    txtOsEquip.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCliente.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // MÉTODO PARA BUSAR PELA CHAVE ESTRANGEIRA
    private void alterar() {
        // Instrução SQL para buscar a chave primaria da tabela de clentea
        String sql = "SELECT idcli FROM tbclientes WHERE idcli = ?";
        
        try {
            // Prepara a conexao com o BD
            pst = conexao.prepareStatement(sql);
            
            // Atribui o id do campo do form à consulta sql da tabela de clientes 
            pst.setString(1, txtOsIdCliente.getText());
            
            // Retorna a consulta no ResultSet
            rs = pst.executeQuery();
            
            if (rs.next()) {// Valida a existencia de um cliente cadastrado na tabela de clientes
                auxAlt(); // chama o metodo que vai fazer a alteração dodos da OS
            } else {
                JOptionPane.showMessageDialog(null, "O cliente com ID "+txtOsIdCliente.getText()+" ainda não foi registrado");
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // MÉTODO PARA DELETAR UMA OS
    private void remover() {
        // pede confirmação para remover usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            // Comando sql para deletar OS
            String sql = "DELETE FROM tbos WHERE os=?";
            
            try {
                // Prepara a conexão com o bd
                pst = conexao.prepareStatement(sql);
                
                // Atribui o campo os do formulario ao comando sql
                pst.setString(1, txtOsId.getText());
                
                // executa a consulta e atribui seu retirno à variavel apagado
                int apagado = pst.executeUpdate();
                
                // Testa se a remoção foi feita
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço removido com sucesso");
                    
                    // limpando os campos do formulário
                    txtOsEquip.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCliente.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtOsId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtOsEquip = new javax.swing.JTextField();
        txtOsDefeito = new javax.swing.JTextField();
        txtOsServico = new javax.swing.JTextField();
        txtOsTecnico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        txtOsIdCliente = new javax.swing.JTextField();
        txtOsData = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jLabel1.setText("OS");

        jLabel2.setText("Equipamento");

        jLabel3.setText("Defeito");

        jLabel4.setText("Serviço");

        jLabel5.setText("Técnico");

        jLabel6.setText("Id Cliente");

        txtOsId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsIdActionPerformed(evt);
            }
        });

        jLabel7.setText("Data");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        jButton1.setToolTipText("Adicionar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        jButton2.setToolTipText("Consultar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        jButton3.setToolTipText("Alterar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        jButton4.setToolTipText("Remover");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setPreferredSize(new java.awt.Dimension(80, 80));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtOsDefeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsDefeitoActionPerformed(evt);
            }
        });

        jLabel8.setText("Valor");

        txtOsValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsValorActionPerformed(evt);
            }
        });

        txtOsData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtOsData.setForeground(new java.awt.Color(51, 51, 51));
        txtOsData.setText("________________");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOsServico)
                            .addComponent(txtOsDefeito)
                            .addComponent(txtOsEquip)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtOsIdCliente)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)))
                                        .addGap(55, 55, 55)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtOsId, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(txtOsData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsData))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOsDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtOsServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(223, 145, 700, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtOsIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsIdActionPerformed

    private void txtOsDefeitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsDefeitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsDefeitoActionPerformed

    private void txtOsValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsValorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Realizando uma consulta
        consultar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Adicioando ordem de serviço
        adicionar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Alterar os dados da ordem de servico
        alterar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Remove uma ordem de serviço
        remover();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaOs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaOs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaOs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaOs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtOsData;
    private javax.swing.JTextField txtOsDefeito;
    private javax.swing.JTextField txtOsEquip;
    private javax.swing.JTextField txtOsId;
    private javax.swing.JTextField txtOsIdCliente;
    private javax.swing.JTextField txtOsServico;
    private javax.swing.JTextField txtOsTecnico;
    private javax.swing.JTextField txtOsValor;
    // End of variables declaration//GEN-END:variables
}
