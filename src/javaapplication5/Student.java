/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARAVIND
 */
class Student {
        
    String roll;
    String name;
    String sub1;
    String sub2;
    String sub3;
    int tot;
    String avg;
    
    public Student(String roll1,
    String name1,
    String sub11,
    String sub21,
    String sub31,
    int tot1,
    String avg1) 
    {
      roll=roll1;
      name=name1;
      sub1=sub11;
      sub2=sub21;
      sub3=sub31;
      tot=tot1;
      avg=avg1;
    }
    
    public List ToList()
    {
       List<String[]> da = new ArrayList<String[]>();
       String tota= Integer.toString(tot);
       da.add(new String[]{roll,name,sub1,sub2,sub3,tota,avg});
       return da;
    }
    
}
