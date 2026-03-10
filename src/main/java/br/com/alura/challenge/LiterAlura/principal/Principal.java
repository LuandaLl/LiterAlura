package br.com.alura.challenge.LiterAlura.principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.challenge.LiterAlura.model.Autor;
import br.com.alura.challenge.LiterAlura.model.Livro;
import br.com.alura.challenge.LiterAlura.repository.AutorRepository;
import br.com.alura.challenge.LiterAlura.repository.LivroRepository;
import br.com.alura.challenge.LiterAlura.service.ConsumoApi;
import br.com.alura.challenge.LiterAlura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    @Autowired
    private LivroRepository livroRepositorio;

    @Autowired
    private AutorRepository autorRepositorio;

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ---------------------------
                    ESCOLHA O NÚMERO DA SUA OPÇÃO:
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos num determinado ano
                    5- Listar livros num determinado idioma
                    0- Sair
                    ---------------------------
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroWeb();
                case 2 -> listarLivrosRegistados();
                case 3 -> listarAutoresRegistados();
                case 4 -> listarAutoresVivosNoAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("A fechar a aplicação...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro que deseja procurar:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        var dados = conversor.obterDados(json, RespostaApi.class);

        Optional<DadosLivro> livroBuscado = dados.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst();

        if (livroBuscado.isPresent()) {
            DadosLivro dadosLivro = livroBuscado.get();
            
            // Verifica se o livro já está no banco
            if (livroRepositorio.findByTituloContainingIgnoreCase(dadosLivro.titulo()).isPresent()) {
                System.out.println("Erro: Não se pode registar o mesmo livro mais do que uma vez.");
            } else {
                // Lógica para salvar Autor e Livro (considerando apenas o primeiro autor)
                var dadosAutor = dadosLivro.autores().get(0);
                Autor autor = autorRepositorio.findByNomeContainingIgnoreCase(dadosAutor.nome())
                        .orElseGet(() -> autorRepositorio.save(new Autor(dadosAutor)));

                Livro livro = new Livro(dadosLivro, autor);
                livroRepositorio.save(livro);
                System.out.println(livro);
            }
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listarLivrosRegistados() {
        List<Livro> livros = livroRepositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistados() {
        List<Autor> autores = autorRepositorio.findAll();
        autores.forEach(a -> {
            System.out.println("Autor: " + a.getNome());
            System.out.println("Ano de nascimento: " + a.getAnoNascimento());
            System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
            System.out.print("Livros: [");
            a.getLivros().forEach(l -> System.out.print(l.getTitulo() + ", "));
            System.out.println("]\n");
        });
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("Digite o ano que deseja pesquisar:");
        var ano = leitura.nextInt();
        List<Autor> autores = autorRepositorio.buscarAutoresVivosNoAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado nesse ano.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = leitura.nextLine();
        List<Livro> livros = livroRepositorio.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}