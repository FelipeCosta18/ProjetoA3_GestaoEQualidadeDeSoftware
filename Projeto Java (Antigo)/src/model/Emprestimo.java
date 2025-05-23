package model;

public class Emprestimo {
	
	public int id;
	public Cliente cliente;
	public Livro livro;
	public String dataEmprestimo;
	public String dataDevolucao;
	
	public Emprestimo() {
		
	}

	public Emprestimo(int id, Cliente cliente, Livro livro, String dataEmprestimo, String dataDevolucao) {
		this.id = id;
		this.cliente = cliente;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}
}