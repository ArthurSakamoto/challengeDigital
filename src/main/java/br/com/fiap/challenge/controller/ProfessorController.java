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

import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.service.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    @Cacheable("professor")
    public Page<Professor> index(@PageableDefault(size =7) Pageable pageable){
        return service.listaAll(pageable);

    }

    @PostMapping
    @CacheEvict(value = "professor", allEntries = true)
    public ResponseEntity<Professor> create(@RequestBody @Valid Professor professor){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.save(professor)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Professor> show(@PathVariable Long id){
        return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "professor", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Professor> optional = service.get(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    @CacheEvict(value = "professor", allEntries = true)
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody @Valid Professor newProfessor){
        Optional<Professor> optional = service.get(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        var professor = optional.get();
        BeanUtils.copyProperties(newProfessor, professor);
        professor.setId(id); 
        service.save(professor);

        return ResponseEntity.ok().build();
    }
    
}
