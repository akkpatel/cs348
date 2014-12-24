import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.*;
import java.io.Serializable;

public class SimpleDatabase implements Serializable{


 //declare public variables and constants here

 /*Your implementation starts here*/
  class newStudent implements Serializable{
    private String snum;
    private String name;
    private String slevel;
    private int age;
    private String deptid;
    public void setNum(String snum){
      this.snum = snum;
    }
    public void setName(String sname){
      this.name = sname;
    }
    public void setLevel(String slevel){
      this.slevel = slevel;
    }
    public void setAge(int age){
      this.age = age;
    }
    public void setDept(String deptid){
      this.deptid = deptid;
    }
    public boolean equals(Object o){
       newStudent x = (newStudent) o;
       if(x.snum.equals(snum)){
        // System.out.println("it works");
         return true;
       }else{
          // System.out.println("doesn't work");
           return false;
       }
    }
  }
  
  class newFaculty implements Serializable{
    
    private String fid;
    private String name;
    private String dept;
    public void setfid(String id){
      this.fid = id;
    }
    public void setName(String sname){
      this.name = sname;
    }
    public void setDept(String deptid){
      this.dept = deptid;
    }
    
  }
  
  class newClass implements Serializable{
    private String room;
    private String name;
    private String meet;
    private String fid;
    public void setfid(String id){
      this.fid = id;
    }
    public void setName(String sname){
      this.name = sname;
    }
    public void setRoom(String room){
      this.room = room;
    }
    public void setMeet(String meets){
      this.meet = meets;
    }
  }
  
  class newDept implements Serializable{
    private String location;
    private String name;
    private String deptid;
    public void setLoc(String loc){
      this.location = loc;
    }
    public void setName(String sname){
      this.name = sname;
    }
    public void setDept(String deptid){
      this.deptid = deptid;
    }
  }
  
  class newEnroll implements Serializable{
    private String snum;
    private String name;
    public void setNum(String snum){
      this.snum = snum;
    }
    public void setName(String name){
      this.name = name;
    }
  }
  class arrList implements Serializable{
   private ArrayList<newStudent> newS;
   private ArrayList<newDept> deptr;
   private ArrayList<newFaculty> facs;
   private ArrayList<newClass> nClass;
   private ArrayList<newEnroll> nEnroll;
   public void setStud(ArrayList<newStudent> newS){
     this.newS = newS;
   }
   public void setDept(ArrayList<newDept> deptr){
     this.deptr = deptr;
   }
   public void setFacs(ArrayList<newFaculty> facs){
     this.facs = facs;
   }
   public void setClass(ArrayList<newClass> nClass){
     this.nClass = nClass;
   }
   public void setEnr(ArrayList<newEnroll> nEnroll){
     this.nEnroll = nEnroll;
   }
   public ArrayList<newStudent> getStud(){
     return newS;
   }
   public ArrayList<newDept> getDept(){
     return deptr;
   }
   public ArrayList<newFaculty> getFacs(){
     return facs;
   }
   public ArrayList<newClass> getClasses(){
     return nClass;
   }
   public ArrayList<newEnroll> getEnroll(){
     return nEnroll;
   }
  }
 ArrayList<newStudent> newS = new ArrayList<newStudent>();
 ArrayList<newDept> deptr = new ArrayList<newDept>();
 ArrayList<newFaculty> facs = new ArrayList<newFaculty>();
 ArrayList<newClass> nClass = new ArrayList<newClass>();
 ArrayList<newEnroll> nEnroll = new ArrayList<newEnroll>();
 public boolean deptIdSearch(String id){
   for(int i = 0; i < deptr.size(); i++){
     if(deptr.get(i).deptid.equals(id)){
       return true;
     }
   }
   return false;
 }
 public boolean fidSearch(String fid){
   for(int i = 0; i < facs.size(); i++){
     if(facs.get(i).fid.equals(fid)){
       return true;
     }
   }
   return false;
 }
 public boolean nameSearch(String name){
   for(int i = 0; i < nClass.size(); i++){
     if(nClass.get(i).name.equals(name)){
       return true;
     }
   }
   return false;
 }
 public boolean enrollSearch(String snum, String name){
   for(int i = 0; i < nEnroll.size(); i++){
     if(nEnroll.get(i).snum.equals(snum)){
       if(nEnroll.get(i).name.equals(name)){
         return true;
       }
     }
   }
   return false;
 }
 public boolean snumSearch(String snum){
   for(int i = 0; i < newS.size(); i++){
     if(newS.get(i).snum.equals(snum)){
       return true;
     }
   }
   return false;
 }
 public void printAll(){
         System.out.println("Student ");
   for(int i = 0; i < newS.size(); i++){
        System.out.println("values: " + i + " " + newS.get(i).name + " " + newS.get(i).age);
   }
   System.out.println("Department ");
   for(int i = 0; i < deptr.size(); i++){
     System.out.println("Values: " + i + " " + deptr.get(i).name + " " + deptr.get(i).deptid);
   }
   System.out.println("Class ");
   for(int i = 0; i < nClass.size(); i++){
      System.out.println("values: " + i + " " + nClass.get(i).name + " " + nClass.get(i).fid);
   }
   System.out.println("Enroll ");
   for(int i = 0; i < nEnroll.size(); i++){
      System.out.println("values: " + i + " " + nEnroll.get(i).name + " " + nEnroll.get(i).snum);
   }
   System.out.println("Faculty ");
   for(int i = 0; i < facs.size(); i++){
      System.out.println("values: " + i + " " + facs.get(i).name + " " + facs.get(i).fid);
   }
    }
 public void addStudent( String snum, String sname, String slevel, int age, String deptid ) {
   newStudent stud = new newStudent();
   stud.setNum(snum);
   stud.setName(sname);
   stud.setLevel(slevel);
   stud.setAge(age);
   stud.setDept(deptid);
   if(deptIdSearch(deptid) == true){
     if(newS.contains(stud) == true){
       System.out.println("Error: Student already Exist!");
     }else{
       newS.add(stud);
     }
   }else{
     System.out.println("Error: Department does not exits!");
   }
 }
 
 public void addFaculty( String fid, String fname, String deptid ) {
   newFaculty fac = new newFaculty();
   fac.setfid(fid);
   fac.setName(fname);
   fac.setDept(deptid);
   if(deptIdSearch(deptid) == true){
     if(fidSearch(fid) == true){
       System.out.println("Error: Faculty already exists!");
     }else{
       facs.add(fac);
     }
   }else{
     System.out.println("Error: Department does not exist!");
   }
 }
 
 public void addClass( String name, String room, String meets_at, String fid ) {
   newClass classes = new newClass();
   classes.setName(name);
   classes.setRoom(room);
   classes.setMeet(meets_at);
   classes.setfid(fid);
   if(fidSearch(fid) == true){
     if(nameSearch(name) == true){
       System.out.println("Error: Class already exists!");
     }else{
       nClass.add(classes);
     }
   }else{
     System.out.println("Error: Faculty does not exists!");
   }
 }
 
 public void addDepartment( String deptid, String dname, String location ) {
   newDept dep = new newDept();
   dep.setDept(deptid);
   dep.setName(dname);
   dep.setLoc(location);
   if(deptIdSearch(deptid) == true){
     System.out.println("Error: Department already exists!");
   }else{
     deptr.add(dep);
   }
 }
 
 public void enroll( String snum, String name ) {
   newEnroll enrolled = new newEnroll();
   enrolled.setNum(snum);
   enrolled.setName(name);
   if(snumSearch(snum)==true && nameSearch(name)==true){
       if(enrollSearch(snum, name) == true){
         System.out.println("Error: Enrollment already exists!");
       }else{
         nEnroll.add(enrolled);
       }
   }else{
     System.out.println("Error: Missing entry!");
   }
 }
 
 public void getStudentsInDepartment( String deptid ) {
   for(int i = 0; i < newS.size(); i++){
     if(newS.get(i).deptid.equals(deptid)){
       System.out.println(newS.get(i).name + "," + newS.get(i).slevel + "," + newS.get(i).age);
     }
   }
 }
 
 public void getStudentsInClass( String name ) {
   for(int i = 0; i < nEnroll.size(); i++){
     if(nEnroll.get(i).name.equals(name)){
       for(int j = 0; j< newS.size(); j++){
         if(nEnroll.get(i).snum.equals(newS.get(j).snum)){
           System.out.println(newS.get(j).name + "," + newS.get(j).slevel + "," + newS.get(j).age);
         }
       }
     }
   }
 }
 
 public void getClassesForStudent( String snum ) {
   for(int i = 0; i < nEnroll.size(); i++){
     if(nEnroll.get(i).snum.equals(snum)){
       for(int j = 0; j < nClass.size(); j++){
         if(nEnroll.get(i).name.equals(nClass.get(j).name)){
           System.out.println(nClass.get(j).name + "," + nClass.get(j).room + "," + nClass.get(j).meet + "," + nClass.get(j).fid);
         }
       }
     }
   }
 }
 
 public void getClassesForFaculty( String fid ) {
   for(int i = 0; i < nClass.size(); i++){
     if(nClass.get(i).fid.equals(fid)){
       System.out.println(nClass.get(i).name);
     }
   }
 }
 
 public void deleteClass( String name ) {
   String fid = "";
   for(int i = 0; i < nClass.size(); i++){
     if(nClass.get(i).name.equals(name)){
       //System.out.println("the name are same");
       fid = nClass.get(i).fid;
       nClass.remove(i);
     }
   }
   for(int i = 0; i < nEnroll.size(); i++){
     if(nEnroll.get(i).name.equals(name)){
       //System.out.println("the name are same");
       nEnroll.remove(i);
     }
   }
   for(int i = 0; i < facs.size(); i++){
     if(facs.get(i).fid.equals(fid)){
       facs.remove(i);
     }
   }
 }
 
 public void save( String filename ) {
   arrList newList = new arrList();
   newList.setStud(newS);
   newList.setDept(deptr);
   newList.setFacs(facs);
   newList.setClass(nClass);
   newList.setEnr(nEnroll);
   String newFileName = filename + ".bin";
   try{
    // System.out.println("in Try");
     ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(newFileName));
     out.writeObject(newList);
    // System.out.println("write the object");
     out.flush();
     out.close();
   }catch(Exception e){
     e.printStackTrace();
   }
 }
 
 public void load( String filename ) {
   newS.clear();
   deptr.clear();
   facs.clear();
   nClass.clear();
   nEnroll.clear();
   arrList newList = new arrList();
   newList.setStud(newS);
   newList.setDept(deptr);
   newList.setFacs(facs);
   newList.setClass(nClass);
   newList.setEnr(nEnroll);
   String newFileName = filename + ".bin"; 
   try{
     FileInputStream fis = new FileInputStream(newFileName);
    // System.out.println("read file");
     ObjectInputStream ois = new ObjectInputStream(fis);
   //  System.out.println("object Streamed");
     newList = (arrList) ois.readObject();
    // System.out.println("read in the newList");
     ois.close();
     fis.close();
   }catch(Exception e){
     e.printStackTrace();
   }
   newS = newList.getStud();
   deptr = newList.getDept();
   facs = newList.getFacs();
   nClass = newList.getClasses();
   nEnroll = newList.getEnroll();
   //printAll();
 }
 
 public void help() {
   System.out.println("add_department DEPTID DNAME LOCATION");
   System.out.println("add_student SNUM SNAME SLEVEL AGE DEPTID");
   System.out.println("add_faculty FID FNAME DEPTID");
   System.out.println("add_class NAME ROOM MEETS_AT FID");
   System.out.println("enroll SNUM NAME");
   System.out.println("get_students_in_department DEPTID");
   System.out.println("get_students_in_class NAME");
   System.out.println("get_classes_for_student SNUM");
   System.out.println("get_classes_for_faculty FID");
   System.out.println("delete_class NAME");
   System.out.println("save FILENAME");
   System.out.println("load FILENAME");
   System.out.println("exit");
 }
  
   /*Your implementation ends here */
   
   
 public static void main( String [] args ) {
 
  SimpleDatabase sd = new SimpleDatabase();
  
  System.out.println( "Please enter a command.  Enter 'help' for a list of commands." );
  
  BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );

  String line = "";
  
  try {
   line = reader.readLine();
  }
  catch ( IOException e ) {
   e.printStackTrace();
  }
  
  
  
  String[] pieces = line.split( "\\s+" );
  String[] params;
  String command;
  
  if ( pieces.length > 0 ) {
   command = pieces[0];
   params = new String[pieces.length - 1];
   System.arraycopy( pieces, 1, params, 0, pieces.length - 1 );
  }
  else {
   command = "";
   params = null;
  }
  
  
  while ( !( command.equalsIgnoreCase( "exit" ) ) ) {
  
   if ( command.equalsIgnoreCase( "add_student" ) ) {
    if ( params.length != 5 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String snum = params[0];
     String sname = params[1];
     String slevel = params[2];
     int age = ( new Integer( params[3] ) ).intValue();
     String deptid = params[4];
     sd.addStudent( snum, sname, slevel, age, deptid );
    }
   }
   else if ( command.equalsIgnoreCase( "add_faculty" ) ) {
    if ( params.length != 3 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String fid = params[0];
     String fname = params[1];
     String deptid = params[2];
     sd.addFaculty( fid, fname, deptid );
    }
   }
   else if ( command.equalsIgnoreCase( "add_department" ) ) {
    if ( params.length != 3 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String deptid = params[0];
     String dname = params[1];
     String location = params[2];
     sd.addDepartment( deptid, dname, location );
    }
   }
   else if ( command.equalsIgnoreCase( "add_class" ) ) {
    if ( params.length != 4 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String name = params[0];
     String room = params[1];
     String meets_at = params[2];
     String fid = params[3];
     sd.addClass( name, room, meets_at, fid );
    }
   }
   else if ( command.equalsIgnoreCase( "enroll" ) ) {
    if ( params.length != 2 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String snum = params[0];
     String name = params[1];
     sd.enroll( snum, name );
    }
   }
   else if ( command.equalsIgnoreCase( "get_students_in_department" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String deptid = params[0];
     sd.getStudentsInDepartment( deptid );
    }
   }
   else if ( command.equalsIgnoreCase( "get_students_in_class" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String name = params[0];
     sd.getStudentsInClass( name );
    }
   }
   else if ( command.equalsIgnoreCase( "get_classes_for_student" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String snum = params[0];
     sd.getClassesForStudent( snum );
    }
   }
   else if ( command.equalsIgnoreCase( "get_classes_for_faculty" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String fid = params[0];
     sd.getClassesForFaculty( fid );
    }
   }
   else if ( command.equalsIgnoreCase( "delete_class" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String name = params[0];
     sd.deleteClass( name );
    }
   }
   else if ( command.equalsIgnoreCase( "save" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String filename = params[0];
     sd.save( filename );
    }
   }
   else if ( command.equalsIgnoreCase( "load" ) ) {
    if ( params.length != 1 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     String filename = params[0];
     sd.load( filename );
    }
   }
   else if ( command.equalsIgnoreCase( "help" ) ) {
    if ( params.length != 0 ) {
     System.out.println( "Incorrect number of parameters!" );
    }
    else {
     sd.help();
    }
   }
   else {
    System.out.println( "Invalid command!" );
   }
   
   try {
    line = reader.readLine();
   }
   catch ( IOException e ) {
    e.printStackTrace();
   }
   
   pieces = line.split( "\\s+" );
  
   if ( pieces.length > 0 ) {
    command = pieces[0];
    params = new String[pieces.length - 1];
    System.arraycopy( pieces, 1, params, 0, pieces.length - 1 );
   }
   else {
    command = "";
   }
  }
 
 }



}