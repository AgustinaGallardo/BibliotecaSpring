package com.example.spring1.Service;

import com.example.spring1.Exepciones.MiException;
import com.example.spring1.Repository.AutorRepository;
import com.example.spring1.Repository.EditoriasRepository;
import com.example.spring1.Repository.LibroRepository;
import com.example.spring1.entity.Autor;
import com.example.spring1.entity.Editorial;
import com.example.spring1.entity.Libro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditoriasRepository editorialRepository;
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        validar(isbn,  titulo,  idAutor,  idEditorial,  ejemplares);
        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepository.save(libro);
    }

    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList<>();
        libros = libroRepository.findAll();
        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
        validar(isbn,  titulo,  idAutor,  idEditorial,  ejemplares);

        Optional<Libro> resp = libroRepository.findById(isbn);
        Optional<Editorial> respEditorial = editorialRepository.findById(idEditorial);
        Optional<Autor> respAutor = autorRepository.findById(idAutor);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if(respAutor.isPresent()){
            autor = respAutor.get();
        }
        if(respEditorial.isPresent()){
            editorial = respEditorial.get();
        }
        if(resp.isPresent()){
            Libro libro = resp.get();
            libro.setTitulo(titulo);
            libro.setEditorial(editorial);
            libro.setAutor(autor);
            libro.setEjemplares(ejemplares);
            libroRepository.save(libro);
        }
    }

    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{

        if(isbn == null){
            throw new MiException("El ISBN no puede ser nulo");
        }

        if(idAutor == null){
            throw new MiException("El Autor no puede ser nulo");
        }

        if(idEditorial == null){
            throw new MiException("La editorial no puede ser nula");
        }

        if(titulo == null || titulo.isEmpty()){
            throw new MiException("El titulo no puede ser nulo");
        }

        if(ejemplares == null){
            throw new MiException("Los ejemplares no puede ser nulo");
        }
    }

}
