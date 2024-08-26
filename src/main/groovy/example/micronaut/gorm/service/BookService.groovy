package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Book
import example.micronaut.gorm.model.BookModel

interface BookService {
    Book createBook(BookModel bookModel)
    BookModel getBook(Long id)
    Book updateBook(Long id, BookModel bookModel)
    void deleteBook(Long id)
//    List<Book> listBooks(String genre)
}
