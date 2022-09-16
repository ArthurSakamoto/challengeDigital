package br.com.fiap.challenge.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.service.AlunoService;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    @Cacheable("aluno")
    public Page<Aluno> index(@PageableDefault(size =10) Pageable pageable){
        return service.listAll(pageable);

    }

    @PostMapping
    @CacheEvict(value = "aluno", allEntries = true)
    public ResponseEntity<Aluno> create(@RequestBody @Valid Aluno aluno){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.save(aluno)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Aluno> show(@PathVariable Long id){
        return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "aluno", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Aluno> optional = service.get(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    @CacheEvict(value = "aluno", allEntries = true)
    public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody @Valid Aluno newAluno){
        Optional<Aluno> optional = service.get(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        var aluno = optional.get();
        BeanUtils.copyProperties(newAluno, aluno);
        aluno.setId(id); 
        service.save(aluno);

        return ResponseEntity.ok().build();
    }
    
}
