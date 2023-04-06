/**
 * Controller class for Admin
 */
package com.library.user.auth;

import com.library.student.Student;
import com.library.student.StudentNotFoundException;
import com.library.student.StudentService;
import com.library.subject.Subject;
import com.library.subject.SubjectNotFoundException;
import com.library.subject.SubjectService;
import com.library.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Annotate as controller
@Controller
public class AdminController {

    // Inject instance of UserService
    @Autowired
    private UserService userService;

    // Inject instance of studentService
    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    // Map HTTP GET requests for '/admin/student'
    @GetMapping("/admin/student")
    public String adminStudent(Model model) {
        // Use listAll() from studentService service class...
        // to get a List of student
        List<Student> listStudents = studentService.listAll();
        // Store List of student in liststudent attribute, so we can populate it in Thymeleaf
        model.addAttribute("listStudent", listStudents);
        // Get size of List of student, so that we know how many entries in the entity
        model.addAttribute("totalItems", listStudents.size());

        // Return templates/admin/student.html
        return "admin/student";
    }

    // Map HTTP POST requests for "/admin/student/add"
    @PostMapping("/admin/student/save")
    // This url accepts POST requests to "save" the book
    public String saveStudent(Student student) {
        // Saves the POSTed book entity using instance of studentService
        studentService.save(student);

        // Return a redirect to "/admin/student?add"
        return "redirect:/admin/student?add";
    }

    // Map HTTP GET requests for '/admin/student/add'
    @GetMapping("/admin/student/add")
    public String adminAddBook(Model model) {
        // Return template file with add book form, that's it.
        // Return templates/admin/addStudent.html
        return "admin/addStudent";
    }

    // Map HTTP GET requests for '/admin/student/edit/{id}'
    @GetMapping("/admin/student/edit/{id}")
    public String adminEditBook(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Get the book by its id
            Student student = studentService.get(id);
            // Store the book entity in attribute to be used in Thymeleaf
            model.addAttribute("student", student);
            // Customise a page title, so that we can dynamically display...
            // book ID in Thymeleaf
            model.addAttribute("pageTitle", "Edit student (ID: " + id + ")");

            // Return templates/admin/edit_student.html
            return "admin/edit_student";
            // Book not found
        } catch (StudentNotFoundException e) {
            // Return redirect to list of student with parameter to show error
            return "redirect:/admin/student?notExist";
        }
    }
    // Map HTTP GET requests for '/admin/student/delete/{id}'
    @GetMapping("/admin/student/delete/{id}")
    public String adminDeleteBook(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Delete the book by its id
            studentService.delete(id);

            // Success, redirect to list of student with a param for message
            return "redirect:/admin/student?deleted={id}";
            // Book not found
        } catch (StudentNotFoundException e) {
            // Redirect to list of student with a param for message
            return "redirect:/admin/student?notExist";
        }
    }

    // Map HTTP GET requests for '/admin/student/search'
    @GetMapping("/admin/student/search")
    // User will request page with keyword parameter, so we accept keyword as parameter
    public String search(String keyword, Model model) {
        // Call searchByPage with search keyword, model container, and result page 1
        // We assume if user arrives to this url, they are on result page 1
        return searchByPage(keyword, model, 1);
    }

    // Map HTTP GET requests for '/admin/student/search/page/{pageNum}'
    @GetMapping("/admin/student/search/page/{pageNum}")
    public String searchByPage(String keyword, Model model,
                               @PathVariable(name = "pageNum") int pageNum) {

        // Get current page search results
        Page<Student> result = studentService.search(keyword, pageNum);

        // Store contents of current page results in List
        List<Student> listResult = result.getContent();

        // Add attributes to model container so that we can use it in Thymeleaf
        // Get pages total amount of search results
        model.addAttribute("totalPages", result.getTotalPages());
        // Get total amount of results from search
        model.addAttribute("totalItems", result.getTotalElements());
        // Get the current page number
        model.addAttribute("currentPage", pageNum);

        // Start counter to ensure only specified amount of results are displayed per page
        long startCount = (pageNum - 1) * StudentService.SEARCH_RESULT_PER_PAGE + 1;
        // Get current start counter value
        model.addAttribute("startCount", startCount);

        // Determine when to stop displaying results.
        long endCount = startCount + StudentService.SEARCH_RESULT_PER_PAGE - 1;
        // We are probably on last page of search, but...
        // endCount cannot exceed total amount of results from search
        if (endCount > result.getTotalElements()) {
            // set counter to end at total amount of results
            endCount = result.getTotalElements();
        }

        // Store as attribute to be used in Thymeleaf
        // Get endCount value as attribute
        model.addAttribute("endCount", endCount);
        // Get List of search results for current page
        model.addAttribute("listResult", listResult);
        // Get keyword used for search
        model.addAttribute("keyword", keyword);

        // Return templates/admin/searchStudent.html
        return "admin/searchStudent";
    }
//________________________________________________________________________________________________________________________________________________________________
    // Map HTTP GET requests for '/admin/student'
    @GetMapping("/admin/subject")
    public String adminSubject(Model model) {
        // Use listAll() from studentService service class...
        // to get a List of student
        List<Subject> listResultSub= subjectService.listAll();
        // Store List of student in liststudent attribute, so we can populate it in Thymeleaf
        model.addAttribute("listSubject", listResultSub);
        // Get size of List of student, so that we know how many entries in the entity
        model.addAttribute("totalItems", listResultSub.size());

        // Return templates/admin/student.html
        return "admin/subject";
    }

    // Map HTTP POST requests for "/admin/student/add"
    @PostMapping("/admin/subject/save")
    // This url accepts POST requests to "save" the book
    public String saveSubject(Subject subject) {
        // Saves the POSTed book entity using instance of studentService
        subjectService.save(subject);

        // Return a redirect to "/admin/student?add"
        return "redirect:/admin/subject?add";
    }

    // Map HTTP GET requests for '/admin/student/add'
    @GetMapping("/admin/subject/add")
    public String adminAddSubject(Model model) {
        // Return template file with add book form, that's it.
        // Return templates/admin/addStudent.html
        return "admin/addSubject";
    }

    // Map HTTP GET requests for '/admin/subject/edit/{id}'
    @GetMapping("/admin/subject/edit/{id}")
    public String adminEditSubject(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Get the book by its id
            Subject subject = subjectService.get(id);
            // Store the book entity in attribute to be used in Thymeleaf
            model.addAttribute("subject", subject);
            // Customise a page title, so that we can dynamically display...
            // book ID in Thymeleaf
            model.addAttribute("pageTitle", "Edit subject (ID: " + id + ")");

            // Return templates/admin/edit_student.html
            return "admin/editSubject";
            // Book not found
        } catch (SubjectNotFoundException e) {
            // Return redirect to list of student with parameter to show error
            return "redirect:/admin/subject?notExist";
        }
    }
    // Map HTTP GET requests for '/admin/student/delete/{id}'

    @GetMapping("/admin/subject/delete/{id}")
    public String adminDeleteSubject(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Delete the book by its id
            subjectService.delete(id);

            // Success, redirect to list of student with a param for message
            return "redirect:/admin/subject?deleted={id}";
            // Book not found
        } catch (SubjectNotFoundException e) {
            // Redirect to list of student with a param for message
            return "redirect:/admin/subject?notExist";
        }
    }



    // Map HTTP GET requests for '/admin/student/search'
    @GetMapping("/admin/subject/subjectSearch")
    // User will request page with keyword parameter, so we accept keyword as parameter
    public String searchsub(String keyword, Model models) {
        // Call searchByPage with search keyword, model container, and result page 1
        // We assume if user arrives to this url, they are on result page 1
        return searchByPage2(keyword, models, 1);
    }

    // Map HTTP GET requests for '/admin/student/search/page/{pageNum}'
    @GetMapping("/admin/subject/subjectSearch/page/{pageNum}")
    public String searchByPage2(String keyword, Model model,
                               @PathVariable(name = "pageNum") int pageNum) {

        // Get current page search results
        Page<Subject> result = subjectService.search(keyword, pageNum);

        // Store contents of current page results in List
        List<Subject> listResult = result.getContent();

        // Add attributes to model container so that we can use it in Thymeleaf
        // Get pages total amount of search results
        model.addAttribute("totalPages", result.getTotalPages());
        // Get total amount of results from search
        model.addAttribute("totalItems", result.getTotalElements());
        // Get the current page number
        model.addAttribute("currentPage", pageNum);

        // Start counter to ensure only specified amount of results are displayed per page
        long startCount = (pageNum - 1) * SubjectService.SEARCH_RESULT_PER_PAGE + 1;
        // Get current start counter value
        model.addAttribute("startCount", startCount);

        // Determine when to stop displaying results.
        long endCount = startCount + SubjectService.SEARCH_RESULT_PER_PAGE - 1;
        // We are probably on last page of search, but...
        // endCount cannot exceed total amount of results from search
        if (endCount > result.getTotalElements()) {
            // set counter to end at total amount of results
            endCount = result.getTotalElements();
        }

        // Store as attribute to be used in Thymeleaf
        // Get endCount value as attribute
        model.addAttribute("endCount", endCount);
        // Get List of search results for current page
        model.addAttribute("listResult", listResult);
        // Get keyword used for search
        model.addAttribute("keyword", keyword);

        // Return templates/admin/searchStudent.html
        return "admin/searchSubject";
    }


}
