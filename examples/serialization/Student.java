import java.io.Serializable;

public class Student  implements Serializable
{
 private int id;
 /*transient*/ private String name;

 public Student(int id, String name) {
  this.id = id;
  this.name = name;
 }

 public int getID () {return this.id;}
 public String getName() {return this.name;}

}