package com.biblioteca.config;

import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        // Seed only if collections are empty
        if (livroRepository.count() == 0) {
            livroRepository.save(new Livro("Dom Quixote", "Miguel de Cervantes", 1605, "Romance", 3));
            livroRepository.save(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, "Fantasia", 2));
            livroRepository.save(new Livro("1984", "George Orwell", 1949, "Ficção Científica", 4));
            livroRepository.save(new Livro("Clean Code", "Robert C. Martin", 2008, "Tecnologia", 2));
            livroRepository.save(new Livro("O Alquimista", "Paulo Coelho", 1988, "Romance", 5));
            System.out.println(">>> Livros de exemplo inseridos.");
        }

        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("Ana Souza", "2024001", "Rua das Flores, 10", "ALUNO"));
            usuarioRepository.save(new Usuario("Carlos Lima", "2024002", "Av. Central, 50", "ALUNO"));
            usuarioRepository.save(new Usuario("Prof. Marta Oliveira", "P001", "Campus Norte", "PROFESSOR"));
            usuarioRepository.save(new Usuario("João Funcionário", "F001", "Biblioteca Central", "FUNCIONARIO"));
            System.out.println(">>> Usuários de exemplo inseridos.");
        }
    }
}
