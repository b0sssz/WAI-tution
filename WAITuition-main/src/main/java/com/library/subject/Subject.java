/**
 * Entity 'Book' class.
 * Columns - Id, title, isbn, genre
 */
package com.library.subject;

import javax.persistence.*;

// Annotate POJO as entity to represent a database entity/object.
@Entity()
// Specify primary table for entity
@Table(name = "subject")

public class Subject {
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
    private String subject_name;

    @Column(length = 100, nullable = false)
    private String date;

    @Column(length = 40, nullable = false)
    private String time;

    @Column(length = 40, nullable = false)
    private int total_stud;

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
     * Column subject_name
     */
    // Getter for subject_name
    public String getSubject_name() {
        return subject_name;
    }

    // Setter for subject_name
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    /*
     * Column name
     */
    // Getter for date
    public String getDate() {
        return date;
    }
    // Setter for date
    public void setDate(String date) {
        this.date = date;
    }

    /*
     * Column time
     */
    // Getter for time
    public String getTime() {
        return time;
    }
    // Setter for time
    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTotal_stud() {
        return total_stud;
    }
    // Setter for total_stud
    public void setTotal_stud(Integer total_stud) {
        this.total_stud = total_stud;
    }
}
