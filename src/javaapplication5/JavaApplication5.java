
package javaapplication5;

import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;





class   MarksComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) 
        {
        return a.tot > b.tot ? -1 : a.tot == b.tot ? 0 : 1;
    }

   
}


public class JavaApplication5 {
    
    static int tot_stud;
    
    public static ArrayList PrintAverage(CSVReader csvReader) throws IOException
    {
        
         
        ArrayList stud_data = new ArrayList();

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
              
              stud_data.add(new Student(row[0],row[1],row[2],row[3],row[4],tot,avrg));
        }
        
        tot_stud=count;
        
        avg1/=count;
        avg2/=count;
        avg3/=count;
        
        System.out.println("AVERAGE");
        System.out.println("The average for subject 1 is : "+avg1);
        System.out.println("The average for subject 2 is : "+avg2);
        System.out.println("The average for subject 3 is : "+avg3);
        
        csvReader.close();
        
        return stud_data;
        
        
    }
    
    public static void Data_Sort(CSVWriter csvWriter, ArrayList stud_data) throws IOException
    {
        Collections.sort(stud_data,new MarksComparator());
        
        for (Iterator it = stud_data.iterator(); it.hasNext();) 
        {
            Student d = (Student) it.next();
            csvWriter.writeAll(d.ToList());
            
        } 
        
        csvWriter.close();
    }
    
    public static void TopN(CSVReader csvReadr,int n) throws IOException
    {
        String rows[];
        
        int count=0;
        
        System.out.println("The top "+n+" are");
        
        while((rows = csvReadr.readNext()) != null) 
        {
             if(count<n)
                 System.out.println(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]+" "+rows[4]+" "+rows[5]+" "+
                         rows[6]);
             count++;
             
             if(count>n)
                 break;
        }
        csvReadr.close();
    }
    
    public static void BotN(CSVReader csvRead, int n) throws IOException
    {
      int count=0;
      
      String row[];
        
        System.out.println("The bottom "+n+" are");
        
        count=0;
        
        while((row = csvRead.readNext()) != null) 
        {
             if(count>=(tot_stud-n))
                 System.out.println(row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]+" "+row[5]+" "+
                         row[6]);
             count++;
        }
        
       csvRead.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException  
    {
        String in = args[0];
        String out= args[1];
        int topn=Integer.parseInt(args[2]);
        int botn=Integer.parseInt(args[3]);
        
        
        CSVReader csvReader = new CSVReader(new FileReader(in));  //Initializing the reader and writer files.
        CSVWriter csvWriter = new CSVWriter(new FileWriter(out));
        
        ArrayList stud_data;
        stud_data=PrintAverage(csvReader); //Function to print average
        Data_Sort(csvWriter,stud_data);   //Function to sort the file
        
        
        CSVReader csvReadr = new CSVReader(new FileReader(out));
        TopN(csvReadr,topn);//Function to print top N student
        
        CSVReader csvRead = new CSVReader(new FileReader(out));
        BotN(csvRead,botn);//Function to print bottom N students
    } 
}     
    

    
  
        
