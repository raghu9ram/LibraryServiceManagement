package example.micronaut.gorm.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import example.micronaut.gorm.domain.Book
import grails.gorm.annotation.Entity

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
class AuthorModel {

    String name
    String biography

    static constraints = {
        name nullable: false
        biography nullable: true
    }

    static mapping = {
        books fetch: 'join'
    }

    @JsonManagedReference
    static hasMany = [books:BookModel]
    List<Book> books = []

}
