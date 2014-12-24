package university;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessCommandServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
        Key universityKey = KeyFactory.createKey("University", "Purdue");
        
      /*you don't need to worry about the variable below, this gets the value of the 
       * string entered in the text area as defined in the university.jsp file
       */
        String content = req.getParameter("command");
        
        
        /*This String array contains the individual elements of the 
        command entered in the text area, e.g. if commandEls[0] gives "add_department", 
        commandEls[1] gives the department id, commandEls[2] gives the department name
        and commandEls[3] gives the department location*/ 
        String [] commandEls = content.split(" ");
        
        /*This string contains the results to display to the user once a command is entered.
         * For a query, it should list the results of the query. 
         * For an insertion, it should either contain an error message or 
         * the message "Command executed successfully!"*/
        String results = null;
        String test = null;
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        /*your implementation starts here*/
        if ( commandEls[0].equals( "add_student" ) ) {
        	String snum = commandEls[1];
        	String sname = commandEls[2];
        	String slevel = commandEls[3];
        	String age = commandEls[4];
        	boolean flag = false;
        	String checking= "";
        	//add a student record with the given fields to the datastore, don't forget to check for duplicate record
        	Key newkey = makeKey(snum, 1);
        	Query query = new Query(newkey);
        	PreparedQuery pq = datastore.prepare(query);
        	for(Entity sol : pq.asIterable()){
        		checking = (String) sol.getProperty("snum");
        		if(checking.equals(snum)){
        			flag = true;
        		}
        	}
        	//System.out.println("value of flag: " + flag);
        	if(!(flag)){
        	//	System.out.println("inEntity true");
        		Entity stud = new Entity(newkey);
        		stud.setProperty("snum", snum);
				stud.setProperty("sname",sname);
				stud.setProperty("slevel", slevel);
				stud.setProperty("age", age);
				datastore.put(stud);
				results = "Command executed successfully";
        	}else{
        		results = "Error: Student already exists!";
        	}
        	
        	//set the value of the "results" String
        	
        }
        else if ( commandEls[0].equals( "add_faculty" ) ) {
        	String fid = commandEls[1];
        	String fname = commandEls[2];
        	String checking = "";
        	boolean flag = false;
        	//add a faculty record with the given fields to the datastore, don't forget to check for duplicate record
        	Key facKey = makeKey(fid, 2);
        	Query facQuery = new Query(facKey);
        	PreparedQuery facPq = datastore.prepare(facQuery);
        	for(Entity facsol : facPq.asIterable()){
        		checking = (String) facsol.getProperty("fid");
        	//	System.out.println("checking: " + checking);
        		if(checking.equals(fid)){
        			flag = true;
        		}
        	}
        	if(!(flag)){
    			Entity faculty = new Entity(facKey);
    			faculty.setProperty("fid", fid);
    			faculty.setProperty("fname", fname);
    			datastore.put(faculty);
    			results = "Command executed successfully";
        	}else{
        		results = "Error: Faculty already exists!";
        	}
        	
        	//set the value of the "results" String
        		
        }
        else if ( commandEls[0].equals( "add_class" ) ) {
        	String cname = commandEls[1];
        	String room = commandEls[2];
        	String meets_at = commandEls[3];
        	String fid = commandEls[4];
        	String checking = "";
        	boolean flag = false;
        	Key classKey = makeKey(cname, 3);
        	//add a class record with the given fields to the datastore, don't forget to check for duplicate record
        	Query query = new Query(classKey);
        	PreparedQuery pq = datastore.prepare(query);
        	for(Entity sol : pq.asIterable()){
        		checking = (String) sol.getProperty("cname");
        	//	System.out.println("checking: " + checking + " cname: " + cname);
        		if(checking.equals(cname)){
        			flag = true;
        		}
        	}
        	if(!(flag)){
        		Entity classEnt = new Entity(classKey);
        		classEnt.setProperty("cname", cname);
        		classEnt.setProperty("room", room);
        		classEnt.setProperty("meets_at", meets_at);
        		classEnt.setProperty("fid", fid);
        		datastore.put(classEnt);
        		results = "Command executed successfully";
        	}else{
        		results = "Error: Class already exists!";
        	}
        	
        	//set the value of the "results" String
            	
        }
        else if ( commandEls[0].equals( "enroll" ) ) {
        	String snum = commandEls[1];
        	String cname = commandEls[2];
        	
        	//System.out.println("in enroll");
        	String concat = snum+cname;
        	String checking = "";
        	boolean flag = false;
        	//add an enrollment record with the given fields to the datastore, don't forget to check for duplicate record
        	Key enrKey = makeKey(concat, 4);
        	Query query = new Query(enrKey);
        	PreparedQuery pq = datastore.prepare(query);
        	//System.out.println("query created");
        	for(Entity sol : pq.asIterable()){
        	//	System.out.println("in for loop");
        		checking = (String) sol.getProperty("snum");
        		checking += (String) sol.getProperty("cname");
        	//	System.out.println("checking: " + checking + " concat: " + concat);
        		if(checking.equals(concat)){
        			flag = true;
        		}
        	}
        	
        	if(!(flag)){
        		Entity enrEnt = new Entity(enrKey);
        		enrEnt.setProperty("snum", snum);
        		enrEnt.setProperty("cname", cname);
        		datastore.put(enrEnt);
        		results = "Command executed successfully";
        	}else{
        		results = "Error: Enrollment already exists!";
        	}
        	
        	//set the value of the "results" String
        	
        }
        
        else if ( commandEls[0].equals( "get_classes_for_student" ) ) {
        	String snum = commandEls[1];
        //	System.out.println("in class student");
        	//query the datastore for the list of classes the student identified by snum takes, append each record to "results"
        	
        	String enrSnum = "";
        	String enrCname = "";
        	String clCname = "";
        	String clRoom = "";
        	String clMeet = "";
        	String clFid = "";		
        			
        	Query new_qu = new Query("Enroll");
        	PreparedQuery new_pq = datastore.prepare(new_qu);
        	
        	Query cl_qu = new Query("Class"); 
        	PreparedQuery cl_pq = datastore.prepare(cl_qu);
        	results = "";
        	for(Entity newSol : new_pq.asIterable()){
        		//System.out.println("in first loop");
        		enrSnum = (String) newSol.getProperty("snum");
        		enrCname = (String) newSol.getProperty("cname");
        		System.out.println("enrsnum: " + enrSnum);
        		if(enrSnum.equals(snum)){
        			//System.out.println("in if loop");
        			for(Entity clSol : cl_pq.asIterable()){
        			//	System.out.println("in second loop");
        				clCname = (String) clSol.getProperty("cname");
        				System.out.println("enrCname:  " + enrCname + "  " + "clCname: " + clCname);
        				clRoom = (String) clSol.getProperty("room");
        				clMeet = (String) clSol.getProperty("meets_at");
        				clFid = (String) clSol.getProperty("fid");
        				if(clCname.equals(enrCname)){
        				//	System.out.println("in second if loop");
        					results += clCname + ", " + clRoom + ", " + clMeet + ", " + clFid + ";" ; 
        				}
        			}
        		}
        	}

        }
        
        else if ( commandEls[0].equals( "get_instructors_for_student" ) ) {
        	String snum = commandEls[1];
        	
        	String enrSnum = "";
        	String enrCname = "";
        	String clCname = "";
        	String clFid = "";
        	String facFid = "";
        	String facFname = "";
        	//query the datastore for the list of instructors of the classes that the student identified by "snum" takes, 
        	//append each record to "results"
        	Query new_qu = new Query("Enroll");
        	PreparedQuery new_pq = datastore.prepare(new_qu);
        	
        	Query cl_qu = new Query("Class"); 
        	PreparedQuery cl_pq = datastore.prepare(cl_qu);
        	
        	Query fac_qu = new Query("Faculty");
        	PreparedQuery fac_pq = datastore.prepare(fac_qu);
        	results = "";
        	for(Entity newSol : new_pq.asIterable()){
        		System.out.println("in first loop");
        		enrSnum = (String) newSol.getProperty("snum");
        		enrCname = (String) newSol.getProperty("cname");
        //		System.out.println("enrsnum: " + enrSnum);
        		if(enrSnum.equals(snum)){
        			for(Entity clSol : cl_pq.asIterable()){
          //  				System.out.println("in second loop");
            				clCname = (String) clSol.getProperty("cname");
            				clFid = (String) clSol.getProperty("fid");
            //				System.out.println("enrCname:  " + enrCname + "  " + "clCname: " + clCname);
            				if(clCname.equals(enrCname)){
            					for(Entity facSol : fac_pq.asIterable()){
            						facFid = (String) facSol.getProperty("fid");
            						facFname = (String) facSol.getProperty("fname");
            						if(facFid.equals(clFid)){
            							results += facFname + "; " ;
            						}
            					}
            				}
        			}
        		}
        	}
        }
        resp.sendRedirect( "/university.jsp?universityName=Purdue&display=" + results );
    }
    public Key makeKey(String snum, int i){
    	Key key = null;
    	if(i == 1){
    	//	System.out.println("student");
    		key = KeyFactory.createKey("Student", snum);
    		return key;
    	}
    	if(i == 2){
    	//	System.out.println("faculty key");
    		key = KeyFactory.createKey("Faculty", snum);
    		return key;
    	}
    	if(i == 3){
    	//	System.out.println("class key");
    		key = KeyFactory.createKey("Class", snum);
    		return key;
    	}
    	if(i == 4){
    	//	System.out.println("enroll key");
    		key = KeyFactory.createKey("Enroll", snum);
    		return key;
    	}
    	else{
    	//	System.out.println("in else");
    		return key;
    	}
    }
    
}
