import java.io.*;


class Persist{
 public static void main(String args[]) throws Exception{
  
  Student s1 = new Student(4413,"John_Lee");

  FileOutputStream fout=new FileOutputStream("resultFile.txt");
  ObjectOutputStream out=new ObjectOutputStream(fout);

  out.writeObject(s1);
  out.flush();

  System.out.println("writting success");
 }

}