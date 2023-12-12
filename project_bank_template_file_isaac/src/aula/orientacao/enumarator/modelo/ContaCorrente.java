package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContaCorrente implements Serializable, IConta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String numero;
	float saldo;
	LocalDateTime dataAbertura;
	boolean status;
	
	private ArrayList<Transacao> transacoes = new ArrayList<>();
	
	public ContaCorrente() {
	}
	
	public ContaCorrente(String numero) {
		super();
		this.numero = numero;
		this.saldo = 0f;
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
	}


	@Override
	public String toString() {
		return "Conta Corrente [ numero: " + numero + ", saldo: " + saldo + ", dataAbertura: " + dataAbertura + ", status: " + status + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(numero, other.numero);
	}
	
	
	public String getNumero() {
		return numero;
	}
	
	public void depositar(float quantia) {
		if(status && quantia > 0) {
			saldo += quantia;
			transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.CREDITO));
		}
			
		else
			System.out.println("Deposito não realizado");
	}
	
	public void sacar(float quantia) {
		if(status && quantia > 0 && quantia <= saldo) {
			saldo -= quantia;
			transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.DEBITO));
		}
		else
			System.out.println("Operacao nao pode ser realizada");
	}
	
	public void extrato(int year, Month month) {
		for(Transacao t : transacoes) {
			if(t.dataTransacao.getYear() == year && t.dataTransacao.getMonth() == month) {
				System.out.println(t);
			}
		}
	}
	
	public void transferir(float quantia, IConta contaDestino) {
		if(contaDestino instanceof ContaCorrente) {
			if (this.status && contaDestino.getSatus() && quantia > 0 && quantia <= this.saldo) {
				this.saldo -= quantia;
				contaDestino.setSaldo(contaDestino.getSaldo() + quantia);
				this.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, contaDestino));
				contaDestino.getTransacoes().add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, this));
			}else{
				System.out.println("Conta desabilitada!");
				}
		}else{
			if (this.getSatus() && contaDestino.getSatus() && quantia > 0 && getSaldo() >= (quantia+(quantia*IConta.TAXA_ADMINISTRATIVA))) {
				this.saldo -= (quantia+(quantia*IConta.TAXA_ADMINISTRATIVA));
				contaDestino.setSaldo(contaDestino.getSaldo() + quantia);
				this.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, contaDestino));
				contaDestino.getTransacoes().add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, this));
			}else{
				System.out.println("Conta desabilitada!");
				}
		}	
	}
	
//	public static void transferir(float quantia, ContaCorrente origem, ContaCorrente destino) {
//		if (origem.status && destino.status && quantia > 0 && quantia <= origem.saldo) {
//			origem.saldo -= quantia;
//			destino.saldo += quantia;
//			origem.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, destino));
//			destino.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, origem));
//		}
//	}


	@Override
	public boolean getSatus() {
		// TODO Auto-generated method stub
		return status;
	}


	@Override
	public float getSaldo() {
		// TODO Auto-generated method stub
		return this.saldo;
	}


	@Override
	public void setSaldo(float novoSaldo) {
		// TODO Auto-generated method stub
		this.saldo = novoSaldo;
	}


	@Override
	public List<Transacao> getTransacoes() {
		// TODO Auto-generated method stub
		return transacoes;
	}


	@Override
	public String getNumeroDaConta() {
		// TODO Auto-generated method stub
		return numero;
	}
	
}
