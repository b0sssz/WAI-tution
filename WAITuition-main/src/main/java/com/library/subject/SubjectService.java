/**
 * Service class & business logics for 'Book'
 */
package com.library.subject;

import com.library.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Annotate as Service class
@Service
public class SubjectService {

    // Specify amount of search results to show per page
    public static final int SEARCH_RESULT_PER_PAGE = 10;

    // Inject instance of BookRepository as repo
    @Autowired
    private SubjectRepository repo;

    // Define pagination to be used in controllers
    public Page<Subject> search(String keyword, int pageNum) {
        // Interface for pagination, use PageRequest
        Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);
        return repo.search(keyword, pageable);
    }

    // Define list of student to be used in controllers
    public List<Subject> listAll() {
        // Return all entries as List, use findAll() from CrudRepository
        return (List<Subject>) repo.findAll();
    }

    // Define student saving to be used in controllers
    public void save(Subject subject) {
        // Save book
        repo.save(subject);
    }

    // Define getting book by id to be used in controller
    // Throws BookNotFoundException if book is not found...
    // so we can do something about it later
    public Subject get(Integer id) throws SubjectNotFoundException {
        // Declare container object and attempt to retrieve entity Book by id
        Optional<Subject> result = repo.findById(id);
        // Book found
        if (result.isPresent()) {
            // Get the book
            return result.get();
        }
        // Book not found, throw BookNotFoundException
        throw new SubjectNotFoundException();
    }

    // Define deleting book by id to be used in controller
    // Like previously, we throw BookNotFoundException if book is not found
    public void delete(Integer id) throws SubjectNotFoundException {
        // Use countById, as declared in BookRepository class
        // We find out if a book exists for that id
        Long count = repo.countById(id);
        // Book does not exist
        if (count == null || count == 0) {
            // Throw BookNotFoundException
            throw new SubjectNotFoundException();
        }
        // Exception not thrown, book must exist...
        // delete it by its id using deleteById by CrudRepository
        repo.deleteById(id);
    }
}
