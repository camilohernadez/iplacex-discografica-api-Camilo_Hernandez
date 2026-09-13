package org.iplacex.Discografia.Discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.Discografia.Artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping ("/api")
public class DiscoController {
    @Autowired
    private IArtistaRepository artisrepo;
    @Autowired
    private IDiscoRepository disrepo;

    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandlePostDiscoRequest(@RequestBody Disco disco){
        
        if (!artisrepo.existsById(disco.idArtista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Disco temp = disrepo.save(disco);
        return new ResponseEntity<>(temp,HttpStatus.CREATED);   
    }
    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest(){
        
        List<Disco> discos = disrepo.findAll();
        
        return new ResponseEntity<>(discos,HttpStatus.OK); 
    }
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleGetDiscoRequest(@PathVariable("id") String id){
        
        Optional <Disco> temp = disrepo.findById(id);
        
        if (!temp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(), HttpStatus.OK);
    }
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id){
        List<Disco> discos = disrepo.findDiscosByIdArtista(id);

        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
