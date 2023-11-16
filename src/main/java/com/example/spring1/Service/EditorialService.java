package com.example.spring1.Service;
import com.example.spring1.Repository.EditoriasRepository;
import com.example.spring1.entity.Autor;
import com.example.spring1.entity.Editorial;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {

    @Autowired
    EditoriasRepository editorialRepository;
    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepository.save(editorial);
    }

    public void modificalEditorial(String nombre, String id){
        Optional<Editorial> resp = editorialRepository.findById(id);

        if(resp.isPresent()){
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorialRepository.save(editorial);
        }
    }

    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepository.findAll();
        return editoriales;
    }




}
