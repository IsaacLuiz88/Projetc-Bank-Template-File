package aula.orientacao.enumarator.modelo;

import java.time.Month;
import java.util.List;

public interface IConta {
	
	float TAXA_ADMINISTRATIVA = 0.05f;
	
	public void depositar(float quantia);
	
	public void sacar(float quantia);
	
	public void extrato(int year, Month month);
	
	public void transferir(float quantia, IConta contaDestino);
	
	public boolean getSatus();
	
	public float getSaldo();
	
	public void setSaldo(float novoSaldo);
	
	public List<Transacao> getTransacoes();
	
	public String getNumeroDaConta();
	
}
