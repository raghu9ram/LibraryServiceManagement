package example.micronaut.gorm.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonInclude
import example.micronaut.gorm.domain.Author
import grails.gorm.annotation.Entity

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
class BookModel {

    String title
    Integer publicationYear
    String genre
    @JsonBackReference
    AuthorModel author

    static constraints = {
        title nullable: false
        publicationYear nullable: false
        genre nullable: true
        author nullable: false
    }

    static belongsTo = [author:AuthorModel]

}
