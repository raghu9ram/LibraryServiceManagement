package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Author
import example.micronaut.gorm.domain.Book
import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.model.BookModel
import grails.gorm.transactions.Transactional

import javax.inject.Singleton

@Singleton
@Transactional
class BookServiceImpl implements BookService{

    Book createBook(BookModel bookModel) {
        Book book = new Book(
                title: bookModel.title,
                publicationYear: bookModel.publicationYear,
                genre: bookModel.genre
        )

        if(bookModel.author) {
            Author author = Author.findById(bookModel.author.id)
            if(!author){
                throw new IllegalAccessException("Author not found")
            }
            book.author = author
        }
        book.save(flush: true)
        return book
    }

    BookModel getBook(Long id) {
        Book book = Book.get(id)
        if(!book) return null

        BookModel bookModel = new BookModel(
                title: book.title,
                publicationYear: book.publicationYear,
                genre: book.genre,
                author: book.author ? new AuthorModel(
                        name: book.author.name,
                        biography: book.author.biography
                ) : null
        )
        return bookModel
    }


    Book updateBook(Long id, BookModel bookModel){
        Book book = Book.get(id)
        if(!book) throw new IllegalArgumentException("Book not found")

        book.title = bookModel.title
        book.publicationYear = bookModel.publicationYear
        book.genre = bookModel.genre

        if(bookModel.author) {
            Author author = Author.findByName(bookModel.author.name)
            if (!author){
                throw new IllegalArgumentException("Author not found")
            }
            book.author = author
        }
        book.save(flush: true)
        return book
    }

    void deleteBook(Long id) {
        Book book = Book.get(id)
        if (!book) {
            throw new IllegalArgumentException("Book with ID ${id} not found")
        }

        book.delete(flush: true)
    }
}
