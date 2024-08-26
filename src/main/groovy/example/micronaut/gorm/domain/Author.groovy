package example.micronaut.gorm.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import grails.gorm.annotation.Entity

@Entity
class Author {
    String name
    String biography

    static constraints = {
        name nullable: false
        biography nullable: true
    }

    @JsonManagedReference
    static hasMany = [books:Book]

    static mapping = {
        books fetch: 'join'
    }

//    static Author findByName(String name) {
//        if (name == null) {
//            return null
//        }
//        return Author.createCriteria().get {
//            eq('name', name)
//        }
//    }
}
