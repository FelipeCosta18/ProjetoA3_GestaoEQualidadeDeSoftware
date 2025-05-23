package model;

public class Livro {
	
	public int id;
	public String titulo;
	public String autor;
	public Genero genero;
	public int quantidade;
	public String isbn;
	
	public Livro() {
		
	}

	public Livro(int id, String titulo, String autor, Genero genero, int quantidade, String isbn) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.quantidade = quantidade;
		this.isbn = isbn;
	}
}