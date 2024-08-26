package example.micronaut.gorm.service

import example.micronaut.gorm.domain.Author
import example.micronaut.gorm.model.AuthorModel

interface AuthorService {
    def createAuthor(AuthorModel authorModel)
    def getAuthor(Long id)
    def updateAuthor(Long id, AuthorModel authorModel)
    void deleteAuthor(Long id)
//    List<Author> listAuthors()
}
