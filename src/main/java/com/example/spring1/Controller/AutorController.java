package com.example.spring1.Controller;

import com.example.spring1.Exepciones.MiException;
import com.example.spring1.Service.AutorService;
import com.example.spring1.entity.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    AutorService autorService = new AutorService();

    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            autorService.crearAutor(nombre);
        } catch (MiException e) {
            throw new RuntimeException(e);
         }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Autor> autores  = autorService.listarAutores();
        modelo.addAttribute("autores",autores);

        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor",autorService.getOne(id));
        return "autor_modificar.html";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
        try{
            autorService.modificarAutor(nombre, id);
            return "redirect:../lista";
        }catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
            }
        }




}
