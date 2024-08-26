package example.micronaut.gorm.controller

import example.micronaut.gorm.domain.Author
import example.micronaut.gorm.model.AuthorModel
import example.micronaut.gorm.service.AuthorService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.inject.Inject
import java.nio.file.Path

@Controller("/authors")
class AuthorController {

    @Inject
    AuthorService authorService

    AuthorController(AuthorService authorService){
        this.authorService = authorService
    }

    @Post
    Author create(@Body AuthorModel authorModel){
        authorService.createAuthor(authorModel)
    }

    @Get("/{id}")
    AuthorModel get(@PathVariable Long id) {
        authorService.getAuthor(id)
    }

    @Put("/{id}")
    Author update(@PathVariable Long id, @Body AuthorModel authorModel) {
        authorService.updateAuthor(id, authorModel)
    }

    @Delete("/{id}")
    HttpResponse<String> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id)
        return HttpResponse.ok("Author Deleted Successfully")
    }
}
