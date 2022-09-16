package br.com.fiap.challenge.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.repository.AlunoRepository;
import br.com.fiap.challenge.repository.ProfessorRepository;

@Configuration
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    AlunoRepository repositoryA;

    @Autowired
    ProfessorRepository repositoryP;

    @Override
    public void run(String... args) throws Exception {

        repositoryA.saveAll(List.of(
            new Aluno("Marcos", "E.E.F.São Carlos ", "8-B", "Marcos@email.com", "senhateste"),
            new Aluno("Thais", "E.E.F.São Carlos ", "9-C", "Thais@email.com", "senhateste1"),
            new Aluno("João", "Colegio Catamarâ ", "1-B", "João@email.com", "senhateste2")
        ));
    
        repositoryP.saveAll(List.of(
            new Professor("João", "E.E.F.São Carlos", "Língua portuguesa", "8-B","Exercício de sujeito oculto", "pfJoão@email.com", "senhaprofessor"),
            new Professor("Alexandre", "E.E.F.São Carlos", "Química", "8-B", "Exercício de ligações químicas", "pfAlexandre@email.com", "senhaprofessor1"),
            new Professor("Sandra", "E.E.F.São Carlos", "Física", "8-B", "Exercício de grandezas fisicas", "pfSandra@email.com", "senhaprofessor2")
        ));

    }
    
}
