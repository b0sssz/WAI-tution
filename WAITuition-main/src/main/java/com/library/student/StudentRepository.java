/**
 * Repository interface class for 'Book'
 */
package com.library.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Annotate as Repository class
@Repository
// We extend JpaRepository because we require...
// PagingAndSortingRepository for pagination
// CrudRepository for CRUD
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Annotate as query
    // We create a custom query to implement full text searches for columns...
    // isbn, title, genre
    // Here, nativeQuery = true means it is a native database query (MySQL)
    @Query(value = "SELECT * FROM student WHERE MATCH(email, name, gender) "
            + "AGAINST (?1)", nativeQuery = true)

    // Declare to used in service class
    public Page<Student> search(String keyword, Pageable pageable);
    public Long countById(Integer id);
}
