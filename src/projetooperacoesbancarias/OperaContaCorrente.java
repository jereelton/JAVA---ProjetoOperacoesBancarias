/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetooperacoesbancarias;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jereelton
 */
public class OperaContaCorrente {
    
    private final ArrayList<String> extratoConta;
    private String saldoConta = "0";
    
    private String tipoOperacaoConta;
    private String valorOperacaoConta;
    private String saldoOperacaoConta;
    
    FormataMoeda formatoMoeda = new FormataMoeda();

    public OperaContaCorrente() {
        this.extratoConta = new ArrayList<>();
    }
    
    public void depositarValor(String valor) {
    
        try {
        
            this.somarValores(valor);
            this.extratoConta.add("Deposito:" + valor + ":" + this.formatoMoeda.formatar(this.saldoConta) );
        
        } catch(Exception e) {
        
            JOptionPane.showMessageDialog(null, "Informe o valor para depositar");
            
        }
    
    }
    
    public void resgatarValor(String valor) {
    
        try {
            
            if( this.subtrairValores(valor) ) {
            
                this.extratoConta.add("Resgate:" + valor + ":" + this.formatoMoeda.formatar(this.saldoConta) );
            
            }
        
        } catch(Exception e) {
        
            JOptionPane.showMessageDialog(null, "Informe o valor para resgatar");
            
        }
        
    }
    
    private void somarValores(String valor) {
    
        BigDecimal big1 = new BigDecimal(this.saldoConta);
        BigDecimal big2 = new BigDecimal(valor.replaceAll("[^0-9]", "").replaceAll("^[0]",""));
        
        this.saldoConta = big1.add(big2).toString().replaceAll("[^0-9]", "").replaceAll("^[0]", "");
        
    }
    
    private boolean subtrairValores(String valor) {
    
        BigDecimal big1 = new BigDecimal(this.saldoConta);
        BigDecimal big2 = new BigDecimal(valor.replaceAll("[^0-9]", "").replaceAll("^[0]",""));
        
        if( big2.compareTo(big1) == 1 ) {
        
            JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
            this.extratoConta.add("Resgate Bloqueado:" + this.formatoMoeda.formatar(big2.toString() ) + ":" + this.formatoMoeda.formatar(this.saldoConta) );
            return false;
            
        }
        
        this.saldoConta = big1.subtract(big2).toString().replaceAll("[^0-9]", "").replaceAll("^[0]","");
        
        return true;
        
    }
    
    public void configurarExtratoConta(String stringExtrato) {
    
        String[] splitExtratoConta = stringExtrato.split(":");
        this.tipoOperacaoConta     = splitExtratoConta[0];
        this.valorOperacaoConta    = splitExtratoConta[1];
        this.saldoOperacaoConta    = splitExtratoConta[2];
        
    }
    
   public ArrayList<String> retornarExtratoConta() {
   
        return this.extratoConta;
       
   }
    
    public String retornarSaldoConta() {
    
        return this.formatoMoeda.formatar(this.saldoConta);
        
    }
    
    public String tipoOperacaoConta() {
        
        return this.tipoOperacaoConta;
    }
    
    public String valorOperacaoConta() {
    
        return this.valorOperacaoConta;
    }
    
    public String saldoOperacaoConta() {
    
        return this.saldoOperacaoConta;
    }
    
}
