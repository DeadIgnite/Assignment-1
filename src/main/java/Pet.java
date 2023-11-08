import java.io.Serializable;

/*
    Author: Cody Zellmer-Johnson
    Class: CSC 422
    Assignment: Version Control Pet Database
    Release: 1
 */
public class Pet implements Serializable {
    private String name;
    private int age;
    public Pet(String name, int age) {this.name = name; this.age = age;}
    // getters and setters
    public String getName() {return this.name;}
    public int getAge() {return this.age;}
    public void setName(String name) {this.name = name;}
    public void setAge(int age) {this.age = age;}
}
