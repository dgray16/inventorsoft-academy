package com.inventorsoft.domain.service;

import com.inventorsoft.domain.dictionary.Nationality;
import com.inventorsoft.domain.model.Author;
import com.inventorsoft.domain.repository.AuthorRepository;
import com.inventorsoft.domain.service.base.GeneralService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService extends GeneralService<Author, Integer> {

    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository repository) {
        super(repository);
        this.authorRepository = repository;
    }

    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author createTestAuthor() {
        Author author = new Author();

        author.setBirthday(LocalDate.ofYearDay(1995, 29));
        author.setFirstName("Vova");
        author.setLastName("Perebykivskyi");
        author.setNationality(Nationality.UKRAINIAN);

        return authorRepository.save(author);
    }

}
