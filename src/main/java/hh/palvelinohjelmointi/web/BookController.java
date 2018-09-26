package hh.palvelinohjelmointi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.palvelinohjelmointi.domain.Book;
import hh.palvelinohjelmointi.domain.BookRepository;
import hh.palvelinohjelmointi.domain.Category;
import hh.palvelinohjelmointi.domain.CategoryRepository;
@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
    
	@RequestMapping (value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId,Model model){
		model.addAttribute("book", repository.findById(bookId));
		return "editbook";
	}
	
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }     
}
