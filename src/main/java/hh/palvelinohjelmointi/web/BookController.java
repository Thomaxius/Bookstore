package hh.palvelinohjelmointi.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.domain.Book;
import hh.palvelinohjelmointi.domain.BookRepository;
import hh.palvelinohjelmointi.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	// Login page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
  
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book){
    	bookRepository.save(book);
        return "redirect:booklist";
    }
    
      // REST
      @RequestMapping(value="/books", method = RequestMethod.GET)
      public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    

      // REST
     @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
     public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }
      
	@RequestMapping (value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId,Model model){
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editbook";
	}
	
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }     
}
