package com.example.spring1.Repository;

import com.example.spring1.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query(" SELECT l FROM Libro l WHERE l.titulo =:titulo")
    public Libro bucarPorTitulo(@Param("titulo") String titulo);

    @Query ("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);





}
