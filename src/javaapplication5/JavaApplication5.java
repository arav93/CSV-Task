
package javaapplication5;

import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;




class   MarksComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
        return a.tot > b.tot ? -1 : a.tot == b.tot ? 0 : 1;
    }

   
}


public class JavaApplication5 {
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException  
    {
        CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\ARAVIND\\Downloads\\input.csv"));
        CSVWriter csvWriter = new CSVWriter(new FileWriter("C:\\Users\\ARAVIND\\Downloads\\output.csv"));
     
        
        ArrayList peps = new ArrayList();

        String[] row = null;
        
        float avg1=0,avg2=0,avg3=0,avg;
        int count=0;//To get the total number of students
        int tot=0;// To calculate the total in each case
        
        while((row = csvReader.readNext()) != null) 
        {
              tot=0;
              count++;
              
              avg1+=Integer.parseInt(row[2]);
              avg2+=Integer.parseInt(row[3]);
              avg3+=Integer.parseInt(row[4]);
              
              tot+=Integer.parseInt(row[2]);
              tot+=Integer.parseInt(row[3]);
              tot+=Integer.parseInt(row[4]);
              
              avg=tot/3;
              
              String total = Integer.toString(tot);
              String avrg = Float.toString(avg);
              
              peps.add(new Student(row[0],row[1],row[2],row[3],row[4],tot,avrg));
        }
        
         Collections.sort(peps,new MarksComparator());
        
        for (Iterator it = peps.iterator(); it.hasNext();) {
            Student d = (Student) it.next();
            csvWriter.writeAll(d.ToList());
        }
      
        csvReader.close();
       
        csvWriter.close();
        
        
        //To pront the top 5
        
        
        CSVReader csvReadr = new CSVReader(new FileReader("C:\\Users\\ARAVIND\\Downloads\\output.csv"));
        
        
        String rows[];
        
        int icount=0;
        
        System.out.println("The top 5 are");
        
        while((rows = csvReadr.readNext()) != null) 
        {
             if(icount<5)
                 System.out.println(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]+" "+rows[4]+" "+rows[5]+" "+
                         rows[6]);
             icount++;
             
             if(icount>5)
                 break;
        }
        csvReadr.close();
        
        //To print the bottom 2
        
        
        String irow[];
        
        CSVReader csvRead = new CSVReader(new FileReader("C:\\Users\\ARAVIND\\Downloads\\output.csv"));
        System.out.println("The bottom 2 are");
        
        icount=0;
        
        while((irow = csvRead.readNext()) != null) 
        {
             if(icount>=count-2)
                 System.out.println(irow[0]+" "+irow[1]+" "+irow[2]+" "+irow[3]+" "+irow[4]+" "+irow[5]+" "+
                         irow[6]);
             icount++;
        }
        
       csvRead.close();
       
        //To find the average
        
        avg1/=count;
        avg2/=count;
        avg3/=count;
        
        System.out.println("AVERAGE");
        System.out.println("The average for subject 1 is : "+avg1);
        System.out.println("The average for subject 2 is : "+avg2);
        System.out.println("The average for subject 3 is : "+avg3);
} 
}     
    

    
  
        