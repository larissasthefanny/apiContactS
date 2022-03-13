package apicontact.com.br.ApiContact.controller;

import apicontact.com.br.ApiContact.model.Contact;
import apicontact.com.br.ApiContact.repository.ContactRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll(){
        List<Contact> contact = new ArrayList<>();
        contact = repository.findAll();
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping(path ={"/{id}"})
    public ResponseEntity<Optional<Contact>> getById(@PathVariable Integer id){
        Optional<Contact> contact;
        try{
            contact = repository.findById(id);
            return new ResponseEntity<Optional<Contact>>(contact, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Contact>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<Optional<Contact>> deleteById(@PathVariable Integer id){
        try{
            repository.deleteById(id);
            return new ResponseEntity<Optional<Contact>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Contact>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path ={"/{id}"})
    public ResponseEntity<Contact> update(@PathVariable Integer id, @RequestBody Contact newContact){
        return repository.findById(id)
            .map(contact -> {
                contact.setName(newContact.getName());
                contact.setEmail(newContact.getEmail());
                contact.setPhone(newContact.getPhone());
                Contact contactUpdated = repository.save(contact);
                return ResponseEntity.ok().body(contactUpdated);
            }).orElse(ResponseEntity.notFound().build());
    }
}
