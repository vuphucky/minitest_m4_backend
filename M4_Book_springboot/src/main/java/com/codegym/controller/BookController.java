package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.IBookService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private IBookService bookService;
//    @Autowired
//    private ICategoryService categoryService;

//    @ModelAttribute("category")
//    public Iterable<Category> listCategories() {
//        return categoryService.findAll();
//    }
//
//    @GetMapping
//    public ModelAndView listBooks(){
//        ModelAndView modelAndView = new ModelAndView("/book/list");
//        modelAndView.addObject("book",bookService.findAll());
//        return modelAndView;
//    }
//
//    @GetMapping("/create")
//    public ModelAndView showCreateForm(){
//        ModelAndView modelAndView = new ModelAndView("/book/create");
//        modelAndView.addObject("book", new Book());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveBook(@ModelAttribute("book") Book book){
//        bookService.save(book);
//        ModelAndView modelAndView = new ModelAndView("/book/create");
//        modelAndView.addObject("book", new Book());
//        modelAndView.addObject("message", "Create book success fully");
//        return modelAndView;
//    }
//     @GetMapping("/update/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id){
//         Optional<Book> book = bookService.findById(id);
//         if (book.isPresent()){
//             ModelAndView modelAndView = new ModelAndView("/book/update");
//             modelAndView.addObject("book",book.get());
//             return modelAndView;
//         } else {
//             return new ModelAndView("/error_404");
//         }
//     }
//    @PostMapping("/update")
//    public ModelAndView updateBook(@ModelAttribute("book") Book book){
//        bookService.save(book);
//        ModelAndView modelAndView = new ModelAndView("/book/update");
//        modelAndView.addObject("book", book);
//        modelAndView.addObject("message", "book update successfully");
//        return modelAndView;
//    }
//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteform(@PathVariable Long id){
//        Optional<Book> book = bookService.findById(id);
//        if (book.isPresent()){
//            ModelAndView modelAndView = new ModelAndView("/book/delete");
//            modelAndView.addObject("book",book.get());
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error_404");
//        }
//    }
//    @PostMapping("/delete")
//    public String deleteBook(@ModelAttribute("book") Book book){
//        bookService.delete(book.getId());
//        return "redirect:/books";
//    }
@GetMapping
public ResponseEntity<Iterable<Book>> getAllBook() {
    List<Book> books = (List<Book>) bookService.findAll();
    return new ResponseEntity<>(books, HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional <Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isPresent()) {
            bookService.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
    Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isPresent()) {
            bookService.delete(id);
            return new ResponseEntity<>(bookOptional.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
