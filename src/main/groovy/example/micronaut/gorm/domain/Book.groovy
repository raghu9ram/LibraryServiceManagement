package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import grails.gorm.annotation.Entity

@Entity
class Book {
    String title
    Integer publicationYear
    String genre
    @JsonBackReference
    Author author

    static constraints = {
        title nullable: false
        publicationYear nullable: false
        genre nullable: true
        author nullable: false
    }

    static belongsTo = [author:Author]
}
