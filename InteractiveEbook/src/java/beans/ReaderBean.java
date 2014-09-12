package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/**
 *
 * @author James
 */
@Named(value = "readerBean")
@ApplicationScoped
public class ReaderBean {

    /**
     * Creates a new instance of ReaderBean
     */
    public ReaderBean() {
    }
    
    public List<Chapter> getChapterList() {
        List<Chapter> chapters = new ArrayList<>();
        
        chapters.add(new Chapter(1, "Introduction to Computers, Programming, and Java"));
        chapters.add(new Chapter(2, "Elementary Programming"));
        chapters.add(new Chapter(3, "Selections"));
        chapters.add(new Chapter(4, "Mathematical Functions, Characters, and Strings"));
        chapters.add(new Chapter(5, "Loops"));
        chapters.add(new Chapter(6, "Methods"));
        chapters.add(new Chapter(7, "Single-Dimensional Arrays"));
        chapters.add(new Chapter(9, "Multidimensional Arrays"));
        chapters.add(new Chapter(10, "Objects and Classes"));
        chapters.add(new Chapter(11, "Object-Oriented Thinking"));
        chapters.add(new Chapter(12, "Inheritance and Polymorphism"));
        chapters.add(new Chapter(13, "Exception Handling and Text I/O"));
        chapters.add(new Chapter(14, "Abstract Classes and Interfaces"));
        chapters.add(new Chapter(15, "JavaFX Basics"));
        chapters.add(new Chapter(16, "Event-Driven Programming and Animations"));
        chapters.add(new Chapter(17, "JavaFX UI Controls and Multimedia"));
        chapters.add(new Chapter(18, "Binary I/O"));
        chapters.add(new Chapter(19, "Recursion"));
        chapters.add(new Chapter(20, "Generics"));
        chapters.add(new Chapter(21, "Lists, Stacks, Queues, and Priority Queues"));
        chapters.add(new Chapter(22, "Sets and Maps"));
        chapters.add(new Chapter(23, "Developing Efficient Algorithms"));
        chapters.add(new Chapter(24, "Sorting"));
        chapters.add(new Chapter(25, "Implementing Lists, Stacks, Queues, and Priority Queues"));
        chapters.add(new Chapter(26, "Binary Search Trees"));
        chapters.add(new Chapter(27, "AVL Trees"));
        chapters.add(new Chapter(28, "Hashing"));
        chapters.add(new Chapter(29, "Graphs and Applications"));
        chapters.add(new Chapter(30, "Weighted Graphs and Applications"));
        chapters.add(new Chapter(31, "Multithreading and Parallel Programming"));
        chapters.add(new Chapter(32, "Networking"));
        chapters.add(new Chapter(33, "Java Database Programming"));
        chapters.add(new Chapter(34, "JavaServer Faces"));
        chapters.add(new Chapter(35, "Advanced JavaFX"));
        chapters.add(new Chapter(36, "Advanced Java Database Programming"));
        chapters.add(new Chapter(37, "Internationalization"));
        chapters.add(new Chapter(38, "Servlets"));
        chapters.add(new Chapter(39, "JavaServer Pages"));
        chapters.add(new Chapter(40, "Web Services"));
        chapters.add(new Chapter(41, "2-4 Trees and B-Trees"));
        chapters.add(new Chapter(42, "Red-Black Trees"));
        chapters.add(new Chapter(43, "Testing Using JUnit"));
        
        return chapters;
    }
    
    public class Chapter {
        private int number;
        private String title;
        
        public Chapter(int number, String title) {
            this.number = number;
            this.title = title;
        }

        /**
         * @return the number
         */
        public int getNumber() {
            return number;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }
        
        
    }
}
