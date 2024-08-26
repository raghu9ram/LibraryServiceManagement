package example.micronaut.gorm.controller

import example.micronaut.gorm.domain.Book
import example.micronaut.gorm.model.BookModel
import example.micronaut.gorm.service.BookService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject

@Controller("/books")
class BookController {

    @Inject
    BookService bookService

    BookController(BookService bookService) {
        this.bookService = bookService
    }

    @Post
    Book create(@Body BookModel bookModel) {
        bookService.createBook(bookModel)
    }

    @Get("/{id}")
    BookModel get(@PathVariable Long id) {
        bookService.getBook(id)
    }

    @Put("/{id}")
    Book update(@PathVariable Long id, @Body BookModel bookModel){
        bookService.updateBook(id, bookModel)
    }


    @Delete("/{id}")
    HttpResponse<String> delete(@PathVariable Long id) {
        bookService.deleteBook(id)
        return HttpResponse.ok("Deleted Successfully")
    }
}
