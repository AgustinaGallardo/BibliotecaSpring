package com.example.spring1.Service;

import com.example.spring1.Exepciones.MiException;
import com.example.spring1.Repository.AutorRepository;
import com.example.spring1.entity.Autor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepository.save(autor);
    }

    private void validar(String nombre) throws MiException {
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("El nombre no puede ser nulo");
        }
    }

    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList<>();
        autores = autorRepository.findAll();
        return autores;
    }
    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException{

        validar(nombre);

        Optional<Autor> respuesta = autorRepository.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepository.save(autor);
        }
    }

    public Autor getOne(String id){
            return autorRepository.getReferenceById(id);
    }





}
