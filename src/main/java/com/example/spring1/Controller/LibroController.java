package com.example.spring1.Controller;
import com.example.spring1.Exepciones.MiException;
import com.example.spring1.Service.AutorService;
import com.example.spring1.Service.EditorialService;
import com.example.spring1.Service.LibroService;
import com.example.spring1.entity.Autor;
import com.example.spring1.entity.Editorial;
import com.example.spring1.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService = new LibroService();
    @Autowired
    private AutorService autorService = new AutorService();
    @Autowired
    private EditorialService editorialService = new EditorialService();
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();

        modelo.addAttribute("autores",autores);
        modelo.addAttribute("editoriales",editoriales);

        return "libro_from.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor,@RequestParam String idEditorial, ModelMap modelo){

        try{
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("Exito", "El libvro fue cargado correctamente");

        }catch (MiException ex){
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();

            modelo.addAttribute("autores",autores);
            modelo.addAttribute("editoriales",editoriales);

            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros = libroService.listarLibros();
        modelo.addAttribute("libro", libros);
        return "libros_list.html";
    }


}
