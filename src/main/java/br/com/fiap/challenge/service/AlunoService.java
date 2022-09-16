package br.com.fiap.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Page<Aluno> listAll(Pageable pageable){
        return repository.findAll(pageable);
        
    }

    public Aluno save(Aluno aluno){
        return repository.save(aluno);
    }

    public Optional<Aluno> get(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
