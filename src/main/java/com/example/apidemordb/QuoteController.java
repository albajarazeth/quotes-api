package com.example.apidemordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/quote")
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createQuote(@RequestBody Quote quote){
        quoteRepository.save(quote);
        return ResponseEntity.ok("Quote successfully created "+ quote.getId());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quote>> getAllQuotes(){
        List<Quote> quotes = quoteRepository.findAll();
        return ResponseEntity.ok(quotes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable Long id){
        Optional<Quote> quote = quoteRepository.findById(id);
        if(quote.isPresent()){
            quoteRepository.deleteById(id);
            return ResponseEntity.ok("Quote successfully deleted with ID " + id);
        }
        else{
            return ResponseEntity.status(404).body("Quote not found with ID " + id);

        }
    }

}
