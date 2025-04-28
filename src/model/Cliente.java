package model;

public class Cliente {
	
	public int id;
	public String nome;
	public String email;
	public String cpf;
	public String endereco;
	public String contato;
	
	public Cliente() {
		
	}

	public Cliente(int id, String nome, String email, String cpf, String endereco, String contato) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
		this.contato = contato;
	}
}