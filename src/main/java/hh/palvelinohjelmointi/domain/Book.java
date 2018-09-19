package hh.palvelinohjelmointi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String authorName;
    private String bookName;
    private String isbn;
	private int publishYear;
	private Date dateAdded = new Date();


    public Book() {}

	public Book(String bookName, String authorName, String isbn, int publishYear) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.isbn = isbn;
		this.publishYear = publishYear;
		this.dateAdded = new Date();
	}

    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}	
	
    public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", authorName=" + authorName + ", bookName=" + bookName + ", isbn=" + isbn
				+ ", publishYear=" + publishYear + ", dateAdded=" + dateAdded + "]";
	}
}
