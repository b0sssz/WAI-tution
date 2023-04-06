/**
 * Entity 'Book' class.
 * Columns - Id, title, isbn, genre
 */
package com.library.student;

import javax.persistence.*;

// Annotate POJO as entity to represent a database entity/object.
@Entity()
// Specify primary table for entity
@Table(name = "student")

public class Student {
    // Specify id as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    * @Column annotation
    * nullable = specify whether column can be empty/null
    * unique = specify if column content must be unique
    * length = specify column's amount of characters
    */

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String gender;

    /*
    * Column Id
     */
    // Getter for id
    public Integer getId() {
        return id;
    }

    // Setter for id
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     * Column email
     */
    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * Column name
     */
    // Getter for name
    public String getName() {
        return name;
    }
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Column gender
     */
    // Getter for gender
    public String getGender() {
        return gender;
    }
    // Setter for genre
    public void setGender(String gender) {
        this.gender = gender;
    }
}
