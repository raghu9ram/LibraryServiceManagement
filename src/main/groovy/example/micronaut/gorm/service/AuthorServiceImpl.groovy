package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Author
import example.micronaut.gorm.domain.Book
import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.model.BookModel
import grails.gorm.transactions.Transactional

import javax.inject.Singleton

@Singleton
@Transactional
class AuthorServiceImpl implements AuthorService {

    Author createAuthor(AuthorModel authorModel){
        Author author = new Author(
                name: authorModel.name,
                biography: authorModel.biography
        )
        authorModel.books.each { bookModel ->
            Book book = new Book(
                    title: bookModel.title,
                    publicationYear: bookModel.publicationYear,
                    genre: bookModel.genre,
                    author: author
            )
            author.books << book
        }
        author.save(flush:true)
        return author
    }


    AuthorModel getAuthor(Long id) {
        Author author = Author.get(id)
        if(!author) return  null

        AuthorModel authorModel = new AuthorModel(
                name: author.name,
                biography: author.biography,
                books: author.books.collect { book ->
                    new BookModel(
                            title: book.title,
                            publicationYear: book.publicationYear,
                            genre: book.genre,
                    )
                }
        )
        return authorModel
    }
    Author updateAuthor(Long id, AuthorModel authorModel){
        Author author = Author.get(id)
        if(!author) throw new IllegalArgumentException("Author Not Found")

        author.name = authorModel.name
        author.biography = authorModel.biography

        author.books.clear()
        authorModel.books?.each { bookModel ->
            Book book = new Book(
                    title: bookModel.title,
                    publicationYear: bookModel.publicationYear,
                    genre: bookModel.genre,
                    author: author
            )
            author.books << book
        }
        author.save(flush: true)
        return author
    }


    void deleteAuthor(Long id) {
        Author author = Author.get(id)
        if (!author) {
            throw new IllegalArgumentException("Author with ID ${id} not found")
        }

        author.delete(flush: true)
    }
}
