package view;

import java.util.List;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import model.Cliente;
import model.Emprestimo;
import model.Genero;
import model.Livro;

public class TelaMain {

	public static void main(String[] args) {
		List<Genero> generos = GeneroDAO.getGeneros();
		List<Livro> livros = LivroDAO.getLivros();
		List<Cliente> clientes = ClienteDAO.getClientes();
		List<Emprestimo> emprestimos = EmprestimoDAO.getEmprestimos();
		
		int opcao;
		
		do {
			opcao = menuInicial();
			
			switch(opcao) {
				case 5:
					break;
			
				case 1:
					int opcaoGenero;
					do {
						opcaoGenero = menuGenero();
						
						if(opcaoGenero == 1) {
							cadastrarGenero();
						}
						
						if(opcaoGenero == 2) {
							excluirGenero();
						}
						
						if(opcaoGenero == 3) {
							editarGenero();
						}
								
						if(opcaoGenero == 4) {
							generos = GeneroDAO.getGeneros();
							listarGeneros(generos);
						}
					
					} while (opcaoGenero != 5);
					break;
				
				case 2:
					int opcaoLivro;
					do {
						opcaoLivro = menuLivro();
						
						if(opcaoLivro == 1) {
							generos = GeneroDAO.getGeneros();
							
							if(generos.size() == 0) {
								JOptionPane.showMessageDialog(null, "É necessário cadastrar um Gênero primeiro!");
								cadastrarGenero();
								generos = GeneroDAO.getGeneros();
							}
							cadastrarLivro(generos);
						}
						
						if(opcaoLivro == 2) {
							excluirLivro();
						}
						
						if(opcaoLivro == 3) {
							editarLivro();
						}
						
						if(opcaoLivro == 4) {
							livros = LivroDAO.getLivros();
							listarLivros(livros);
						}
					
					} while (opcaoLivro != 5);
					break;
				
				case 3:
					int opcaoCliente;
					do {
						opcaoCliente = menuCliente();
						
						if(opcaoCliente == 1) {
							cadastrarCliente();
						}
						
						if(opcaoCliente == 2) {
							excluirCliente();
						}
						
						if(opcaoCliente == 3) {
							editarCliente();
						}
						
						if(opcaoCliente == 4) {
							clientes = ClienteDAO.getClientes();
							listarClientes(clientes);
						}
						
					} while (opcaoCliente != 5);
					break;
				
				case 4:
					int opcaoEmprestimo;
					do {
						opcaoEmprestimo = menuEmprestimo();
						
						if(opcaoEmprestimo == 1) {
							clientes = ClienteDAO.getClientes();
							livros = LivroDAO.getLivros();
							
							if(clientes.size() == 0) {
								JOptionPane.showMessageDialog(null, "É necessário cadastrar um Cliente primeiro!");
								cadastrarCliente();
								clientes = ClienteDAO.getClientes();
							}
							
							if(livros.size() == 0) {
								JOptionPane.showMessageDialog(null, "É necessário cadastrar um Livro primeiro!");
								cadastrarLivro(generos);
								livros = LivroDAO.getLivros();
							}
							
							cadastrarEmprestimo(clientes, livros);
						}
						
						if (opcaoEmprestimo == 2) {
							excluirEmprestimo();
						}
						
						if (opcaoEmprestimo == 3) {
							editarEmprestimo();
						}
						
						if(opcaoEmprestimo == 4) {
							emprestimos = EmprestimoDAO.getEmprestimos();
							listarEmprestimos(emprestimos);
						}
						
					} while (opcaoEmprestimo != 5);
					break;

				default:
					JOptionPane.showMessageDialog(null, "Opção inválida!");
					break;
			}
		
		} while (opcao != 5);
	}
	
	public static int menuInicial() {
		String texto = "========  BIBLIOTECA  ========\n\n" 
				+ "1 - Gênero\n" 
				+ "2 - Livro\n"
				+ "3 - Cliente\n"
				+ "4 - Empréstimo\n"
				+ "5 - Sair\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static int menuGenero() {
		String texto = "========  GÊNERO  ========\n\n" 
				+ "1 - Cadastrar Gênero\n" 
				+ "2 - Excluir Gênero\n"
				+ "3 - Editar Gênero\n"
				+ "4 - Listar Gêneros\n"
				+ "5 - Voltar\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void cadastrarGenero() {	
		String nome = JOptionPane.showInputDialog("Digite o nome do Gênero: ");
		
		if(!nome.isEmpty()) {
			Genero novoGenero = new Genero(0, nome);
			GeneroDAO.cadastrar(novoGenero);
		}
	}
	
	public static void listarGeneros(List<Genero> generos) {
		String texto = "Gêneros cadastrados:\n";
		
		if(generos.size() == 0) {
			texto += "\nNenhum Gênero cadastrado.";
		}
		else {
			for(Genero gen : generos) {
				texto += "\n" + gen.id + " - " + gen.nome;
			}
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public static void excluirGenero() {
		List<Genero> generos = GeneroDAO.getGeneros();
		
		String texto = "Gêneros cadastrados:\n";
		
		if(generos.size() == 0) {
			texto += "\nNenhum Gênero cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Genero gen : generos) {
				texto += "\n" + gen.id + " - " + gen.nome;
			}
			texto += "\n\nDigite o id do Gênero que deseja excluir: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
				
			int idGen = Integer.valueOf(idDigitado);
			GeneroDAO.excluir(idGen);
		}
	}
	
	public static void editarGenero() {
		List<Genero> generos = GeneroDAO.getGeneros();
		
		String texto = "Gêneros cadastrados:\n";
		
		if(generos.size() == 0) {
			texto += "\nNenhum Gênero cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Genero gen : generos) {
				texto += "\n" + gen.id + " - " + gen.nome;
			}
			texto += "\n\nDigite o id do Gênero que deseja editar: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
			int idGen = Integer.valueOf(idDigitado);
			
			String nomeAtual = null;
		    for (Genero gen : generos) {
		        if (gen.id == idGen) {
		            nomeAtual = gen.nome;
		        }
		    }
			
			String novoNome = JOptionPane.showInputDialog("Nome atual: " + nomeAtual + "\n\nDigite o novo nome do Gênero: ");
			GeneroDAO.editar(idGen, novoNome);
		}
	}
	
	public static int menuLivro() {
		String texto = "========  LIVRO  ========\n\n" 
				+ "1 - Cadastrar Livro\n" 
				+ "2 - Excluir Livro\n"
				+ "3 - Editar Livro\n"
				+ "4 - Listar Livros\n"
				+ "5 - Voltar\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void cadastrarLivro(List<Genero> generos) {
		String titulo = JOptionPane.showInputDialog("Digite o título do Livro: ");
		String autor = JOptionPane.showInputDialog("Digite o autor do Livro: ");
		
		String texto = "Gêneros cadastrados:\n";
		for(Genero gen : generos) {
			texto += "\n" + gen.id + " - " + gen.nome;
		}
		
		texto += "\n\nDigite o id do Gênero deste Livro: ";
		
		String idDigitado = JOptionPane.showInputDialog(texto);
		int idGen = Integer.valueOf(idDigitado);
		
		Genero genSelecionado = null;
		for(Genero gen : generos) {
			if(gen.id == idGen) {
				genSelecionado = gen;
			}
		}
		String qtdDigitada = JOptionPane.showInputDialog("Digite a quantidade do Livro: ");
		int qtd = Integer.valueOf(qtdDigitada);
		String isbn = JOptionPane.showInputDialog("Digite o ISBN do Livro: ");
		
		Livro novoLivro = new Livro(0, titulo, autor, genSelecionado, qtd, isbn);
		LivroDAO.cadastrar(novoLivro);
	}
	
	public static void listarLivros(List<Livro> livros) {
		String texto = "Livros cadastrados:\n";
		
		if(livros.size() == 0) {
			texto += "\nNenhum Livro cadastrado.";
		}
		else {
			for(Livro liv : livros) {
				texto += "\n" + liv.id + " - " + liv.titulo
						+ "\nAutor: " + liv.autor
						+ "\nGênero: " + liv.genero.nome
						+ "\nQuantidade: " + liv.quantidade
						+ "\nISBN: " + liv.isbn
						+ "\n---------------------------------------";
			}
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public static void excluirLivro() {
		List<Livro> livros = LivroDAO.getLivros();
		
		String texto = "Livros cadastrados:\n";
		
		if(livros.size() == 0) {
			texto += "\nNenhum Livro cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Livro liv : livros) {
				texto += "\n" + liv.id + " - " + liv.titulo
				+ "\nAutor: " + liv.autor
				+ "\nGênero: " + liv.genero.nome
				+ "\n---------------------------------------";
			}
			texto += "\n\nDigite o id do Livro que deseja excluir: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
				
			int idLiv = Integer.valueOf(idDigitado);
			LivroDAO.excluir(idLiv);
		}
	}
	
	public static int menuAlterarLivro() {
		String texto = "1 - Alterar Título\n"
				+ "2 - Alterar Autor\n"
				+ "3 - Alterar Gênero\n"
				+ "4 - Alterar Quantidade\n"
				+ "5 - Alterar ISBN\n"
				+ "6 - Sair\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void editarLivro() {
		List<Livro> livros = LivroDAO.getLivros();
		
		String texto = "Livros cadastrados:\n";
		
		if(livros.size() == 0) {
			texto += "\nNenhum Livro cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Livro liv : livros) {
				texto += "\n" + liv.id + " - " + liv.titulo
						+ "\nAutor: " + liv.autor
						+ "\nGênero: " + liv.genero.nome
						+ "\n---------------------------------------";
			}
			texto += "\n\nDigite o id do Livro que deseja editar: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
			int idLiv = Integer.valueOf(idDigitado);
			
			int opcao;
			do {
				opcao = menuAlterarLivro();
				
				if(opcao == 1) {
					String tituloAtual = null;
				    for (Livro liv : livros) {
				        if (liv.id == idLiv) {
				            tituloAtual = liv.titulo;
				        }
				    }
					
				    String novoTitulo = JOptionPane.showInputDialog("Título atual: " + tituloAtual + "\n\nDigite o novo título do Livro: ");
					LivroDAO.editarTitulo(idLiv, novoTitulo);
				}
				
				if(opcao == 2) {
					String autorAtual = null;
				    for (Livro liv : livros) {
				        if (liv.id == idLiv) {
				            autorAtual = liv.autor;
				        }
				    }
					
					String novoAutor = JOptionPane.showInputDialog("Autor atual: " + autorAtual + "\n\nDigite o novo autor do Livro: ");
					LivroDAO.editarAutor(idLiv, novoAutor);
				}
				
				if(opcao == 3) {
					List<Genero> generos = GeneroDAO.getGeneros();
					
					texto = "Gêneros cadastrados:\n";

					for(Genero gen : generos) {
						texto += "\n" + gen.id + " - " + gen.nome;
					}
					texto += "\n\nDigite o id do novo Gênero do Livro: ";
					
					String idGenero = JOptionPane.showInputDialog(texto);
					int idGen = Integer.valueOf(idGenero);
					LivroDAO.editarGenero(idLiv, idGen);
				}
				
				if(opcao == 4) {
					int qtdAtual = 0;
				    for (Livro liv : livros) {
				        if (liv.id == idLiv) {
				            qtdAtual = liv.quantidade;
				        }
				    }
					
					String quantidadeDigitada = JOptionPane.showInputDialog("Quantidade atual: " + qtdAtual + "\n\nDigite a nova quantidade do Livro: ");
					int quantidade = Integer.valueOf(quantidadeDigitada);
					LivroDAO.editarQuantidade(idLiv, quantidade);
				}
				
				if(opcao == 5) {
					String isbnAtual = null;
				    for (Livro liv : livros) {
				        if (liv.id == idLiv) {
				            isbnAtual = liv.isbn;
				        }
				    }
					
					String novoISBN = JOptionPane.showInputDialog("ISBN atual: " + isbnAtual + "\n\nDigite o novo ISBN do Livro: ");
					LivroDAO.editarISBN(idLiv, novoISBN);
				}
				
			} while (opcao != 6);
		}
	}
	
	public static int menuCliente() {
		String texto = "========  CLIENTE  ========\n\n" 
				+ "1 - Cadastrar Cliente\n" 
				+ "2 - Excluir Cliente\n"
				+ "3 - Editar Cliente\n"
				+ "4 - Listar Clientes\n"
				+ "5 - Voltar\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void cadastrarCliente() {	
		String nome = JOptionPane.showInputDialog("Digite o nome do Cliente: ");
		String email = JOptionPane.showInputDialog("Digite o email do Cliente: ");
		String cpf = JOptionPane.showInputDialog("Digite o CPF do Cliente: ");
		String endereco = JOptionPane.showInputDialog("Digite o endereço do Cliente: ");
		String contato = JOptionPane.showInputDialog("Digite o número de telefone do Cliente: ");
	
		Cliente novoCliente= new Cliente(0, nome, email, cpf, endereco, contato);
		ClienteDAO.cadastrar(novoCliente);
	}
	
	public static void excluirCliente() {
		List<Cliente> clientes = ClienteDAO.getClientes();
		
		String texto = "Clientes cadastrados:\n";
		
		if(clientes.size() == 0) {
			texto += "\nNenhum Cliente cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Cliente cli : clientes) {
				texto += "\n" + cli.id + " - " + cli.nome
						+ "\nCPF: " + cli.cpf
						+ "\n---------------------------------------";
			}
			texto += "\n\nDigite o id do Cliente que deseja excluir: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
				
			int idCli = Integer.valueOf(idDigitado);
			ClienteDAO.excluir(idCli);
		}
	}
	
	public static void listarClientes(List<Cliente> clientes) {
		String texto = "Clientes cadastrados:\n";
		
		if(clientes.size() == 0) {
			texto += "\nNenhum Cliente cadastrado.";
		}
		else {
			for(Cliente cli : clientes) {
				texto += "\n" + cli.id + " - " + cli.nome
						+ "\nCPF: " + cli.cpf
						+ "\nEndereço: " + cli.endereco
						+ "\nEmail: " + cli.email
						+ "\nContato: " + cli.contato
						+ "\n---------------------------------------";
			}
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public static int menuAlterarCliente() {
		String texto = "1 - Alterar Nome\n"
				+ "2 - Alterar Email\n"
				+ "3 - Alterar CPF\n"
				+ "4 - Alterar Endereço\n"
				+ "5 - Alterar Contato\n"
				+ "6 - Sair\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void editarCliente() {
		List<Cliente> clientes = ClienteDAO.getClientes();
		
		String texto = "Clientes cadastrados:\n";
		
		if(clientes.size() == 0) {
			texto += "\nNenhum Cliente cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Cliente cli : clientes) {
				texto += "\n" + cli.id + " - " + cli.nome
						+ "\nCPF: " + cli.cpf
						+ "\n---------------------------------------";
			}
			texto += "\n\nDigite o id do Cliente que deseja editar: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
			int idCli = Integer.valueOf(idDigitado);
			
			int opcao;
			do {
				opcao = menuAlterarCliente();
				
				if(opcao == 1) {
					String nomeAtual = null;
				    for (Cliente cli : clientes) {
				        if (cli.id == idCli) {
				            nomeAtual = cli.nome;
				        }
				    }
					
				    String novoNome = JOptionPane.showInputDialog("Nome atual: " + nomeAtual + "\n\nDigite o novo nome do Cliente: ");
					ClienteDAO.editarNome(idCli, novoNome);
				}
				
				if(opcao == 2) {
					String emailAtual = null;
				    for (Cliente cli : clientes) {
				        if (cli.id == idCli) {
				            emailAtual = cli.email;
				        }
				    }
					
					String novoEmail = JOptionPane.showInputDialog("Email atual: " + emailAtual + "\n\nDigite o novo email do Cliente: ");
					ClienteDAO.editarEmail(idCli, novoEmail);
				}
				
				if(opcao == 3) {
					String cpfAtual = null;
				    for (Cliente cli : clientes) {
				        if (cli.id == idCli) {
				            cpfAtual = cli.cpf;
				        }
				    }
					
					String novoCPF = JOptionPane.showInputDialog("CPF atual: " + cpfAtual + "\n\nDigite o novo CPF do Cliente: ");
					ClienteDAO.editarCPF(idCli, novoCPF);
				}
				
				if(opcao == 4) {
					String enderecoAtual = null;
				    for (Cliente cli : clientes) {
				        if (cli.id == idCli) {
				            enderecoAtual = cli.endereco;
				        }
				    }
					
					String novoEndereco = JOptionPane.showInputDialog("Endereço atual: " + enderecoAtual + "\n\nDigite o novo endereço do Cliente: ");
					ClienteDAO.editarEndereco(idCli, novoEndereco);
				}
				
				if(opcao == 5) {
					String contatoAtual = null;
				    for (Cliente cli : clientes) {
				        if (cli.id == idCli) {
				            contatoAtual = cli.contato;
				        }
				    }
					
					String novoContato = JOptionPane.showInputDialog("Contato atual: " + contatoAtual + "\n\nDigite o novo número de telefone do Cliente: ");
					ClienteDAO.editarContato(idCli, novoContato);
				}
				
			} while (opcao != 6);
		}
	}
	
	public static int menuEmprestimo() {
		String texto = "========  EMPRÉSTIMO  ========\n\n" 
				+ "1 - Cadastrar Empréstimo\n" 
				+ "2 - Excluir Empréstimo\n"
				+ "3 - Editar Empréstimo\n"
				+ "4 - Listar Empréstimos\n"
				+ "5 - Voltar\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void cadastrarEmprestimo(List<Cliente> clientes, List<Livro> livros) {
		String texto = "Clientes cadastrados:\n";
		for(Cliente cli : clientes) {
			texto += "\n" + cli.id + " - " + cli.nome
			+ "\nCPF: " + cli.cpf
			+ "\n---------------------------------------";
		}
		
		texto += "\n\nDigite o id do Cliente deste Empréstimo: ";
		
		String idDigitadoCliente = JOptionPane.showInputDialog(texto);
		int idCli = Integer.valueOf(idDigitadoCliente);
		
		Cliente cliSelecionado = null;
		for(Cliente cli : clientes) {
			if(cli.id == idCli) {
				cliSelecionado = cli;
			}
		}
		
		texto = "Livros cadastrados:\n";
		for(Livro liv : livros) {
			texto += "\n" + liv.id + " - " + liv.titulo
					+ "\nAutor: " + liv.autor
					+ "\nGênero: " + liv.genero.nome
					+ "\n---------------------------------------";
		}
		
		texto += "\n\nDigite o id do Livro deste Empréstimo: ";
		
		String idDigitadoLivro = JOptionPane.showInputDialog(texto);
		int idLiv = Integer.valueOf(idDigitadoLivro);
		
		Livro livSelecionado = null;
		for(Livro liv : livros) {
			if(liv.id == idLiv) {
				livSelecionado = liv;
			}
		}
		
		String dataEmprestimo = JOptionPane.showInputDialog("Digite a data inicial do Empréstimo: ");
		String dataDevolucao = JOptionPane.showInputDialog("Digite a data de devolução do Empréstimo: ");
		
		Emprestimo novoEmprestimo = new Emprestimo(0, cliSelecionado, livSelecionado, dataEmprestimo, dataDevolucao);
		EmprestimoDAO.cadastrar(novoEmprestimo);
	}
	
	public static void excluirEmprestimo() {
		List<Emprestimo> emprestimos = EmprestimoDAO.getEmprestimos();
		
		String texto = "Empréstimos cadastrados:\n";
		
		if(emprestimos.size() == 0) {
			texto += "\nNenhum Empréstimo cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Emprestimo emp : emprestimos) {
				texto += "\nID: " + emp.id
				+ "\nCliente - Nome: " + emp.cliente.nome + " / CPF: " + emp.cliente.cpf
				+ "\nLivro: " + emp.livro.titulo
				+ "\nData de Empréstimo: " + emp.dataEmprestimo
				+ "\nData de Devolução: " + emp.dataDevolucao
				+ "\n-------------------------------------------------------------";
			}
			texto += "\n\nDigite o id do Empréstimo que deseja excluir: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
				
			int idEmp = Integer.valueOf(idDigitado);
			EmprestimoDAO.excluir(idEmp);
		}
	}
	
	public static void listarEmprestimos(List<Emprestimo> emprestimos) {
		String texto = "Empréstimos cadastrados:\n";
		
		if(emprestimos.size() == 0) {
			texto += "\nNenhum Empréstimo cadastrado.";
		}
		else {
			for(Emprestimo emp : emprestimos) {
				texto += "\nID: " + emp.id
						+ "\nCliente - Nome: " + emp.cliente.nome + " / CPF: " + emp.cliente.cpf
						+ "\nLivro: " + emp.livro.titulo
						+ "\nData de Empréstimo: " + emp.dataEmprestimo
						+ "\nData de Devolução: " + emp.dataDevolucao
						+ "\n-------------------------------------------------------------";
			}
		}
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public static int menuAlterarEmprestimo() {
		String texto = "1 - Alterar Cliente\n"
				+ "2 - Alterar Livro\n"
				+ "3 - Alterar Data de Empréstimo\n"
				+ "4 - Alterar Data de Devolução\n"
				+ "5 - Sair\n\n"
				+ "Escolha uma opção: ";
		
		int opcao = -1;
		String opcaoDigitada = JOptionPane.showInputDialog(texto);
		
		if(!opcaoDigitada.isEmpty()) {
			opcao = Integer.valueOf(opcaoDigitada);
		}
		
		return opcao;
	}
	
	public static void editarEmprestimo() {
		List<Emprestimo> emprestimos = EmprestimoDAO.getEmprestimos();
		
		String texto = "Empréstimos cadastrados:\n";
		
		if(emprestimos.size() == 0) {
			texto += "\nNenhum Empréstimo cadastrado.";
			
			JOptionPane.showMessageDialog(null, texto);
		}
		else {
			for(Emprestimo emp : emprestimos) {
				texto += "\nID: " + emp.id
						+ "\nCliente - Nome: " + emp.cliente.nome + " / CPF: " + emp.cliente.cpf
						+ "\nLivro: " + emp.livro.titulo
						+ "\nData de Empréstimo: " + emp.dataEmprestimo
						+ "\nData de Devolução: " + emp.dataDevolucao
						+ "\n-------------------------------------------------------------";
			}
			texto += "\n\nDigite o id do Empréstimo que deseja editar: ";
			String idDigitado = JOptionPane.showInputDialog(texto);
			int idEmp = Integer.valueOf(idDigitado);
			
			int opcao;
			do {
				opcao = menuAlterarEmprestimo();
					
				if(opcao == 1) {
					List<Cliente> clientes = ClienteDAO.getClientes();
					
					texto = "Clientes cadastrados:\n";

					for(Cliente cli : clientes) {
						texto += "\n" + cli.id + " - " + cli.nome
								+ "\nCPF: " + cli.cpf
								+ "\n---------------------------------------";
					}
					texto += "\n\nDigite o id do novo Cliente do Empréstimo: ";
					
					String idCliente = JOptionPane.showInputDialog(texto);
					int idCli = Integer.valueOf(idCliente);
					EmprestimoDAO.editarCliente(idEmp, idCli);
				}
				
				if(opcao == 2) {
					List<Livro> livros = LivroDAO.getLivros();
					
					texto = "Livros cadastrados:\n";

					for(Livro liv : livros) {
						texto += "\n" + liv.id + " - " + liv.titulo
								+ "\nAutor: " + liv.autor
								+ "\nGênero: " + liv.genero.nome
								+ "\n---------------------------------------";
					}
					texto += "\n\nDigite o id do novo Livro do Empréstimo: ";
					
					String idLivro = JOptionPane.showInputDialog(texto);
					int idLiv = Integer.valueOf(idLivro);
					EmprestimoDAO.editarLivro(idEmp, idLiv);
				}
				
				if(opcao == 3) {
					String dataEmprestimoAtual = null;
				    for (Emprestimo emp : emprestimos) {
				        if (emp.id == idEmp) {
				        	dataEmprestimoAtual = emp.dataEmprestimo;
				        }
				    }
					
					String novaDataEmprestimo = JOptionPane.showInputDialog("Data de Empréstimo atual: " + dataEmprestimoAtual + "\n\nDigite a nova data de Empréstimo: ");
					EmprestimoDAO.editarDataEmprestimo(idEmp, novaDataEmprestimo);
				}
				
				if(opcao == 4) {
					String dataDevolucaoAtual = null;
				    for (Emprestimo emp : emprestimos) {
				        if (emp.id == idEmp) {
				        	dataDevolucaoAtual = emp.dataDevolucao;
				        }
				    }
					
					String novaDataDevolucao = JOptionPane.showInputDialog("Data de Devolução atual: " + dataDevolucaoAtual + "\n\nDigite a nova data de Devolução: ");
					EmprestimoDAO.editarDataDevolucao(idEmp, novaDataDevolucao);
				}
				
			} while (opcao != 5);
		}
	}
}