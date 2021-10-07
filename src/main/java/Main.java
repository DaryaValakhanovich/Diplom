import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException, NoSuchMethodException, SQLException, ClassNotFoundException {
     //       String csv = "data.csv";
   //        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        //Create record
    //       String [] record = "4,David,Miller,Australia,30".split(",");
        //Write the record to file
    //      writer.writeNext(record);
        //close the writer
      //     writer.close();


        //  String csv = "data.csv";
        //    CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        //    String [] record = "3,David,Feezor,USA,40".split(",");
        //   writer.writeNext(record);
        //    writer.close();


        //Build reader instance
      // CSVReader reader = new CSVReader(new FileReader("DropJump.csv"));
        //Read all rows at once
     //   List<String[]> allRows = reader.readAll();
        //Read CSV line by line and use the string array as you want
       // for(String[] row : allRows){
     //       System.out.println(Arrays.toString(row));
     //  List<Jump> jumps = readJumps("Countermovement jump.csv");
Connection c = ConnectionManager.getConnection();
       }

    public static List<Jump> readJumps(String fileName) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        //Read CSV line by line and use the string array as you want
        String[] subStr;
        ArrayList<Jump> jumps = new ArrayList<Jump>();
        Jump jump = new Jump();
        for(int i = 1; i < allRows.size(); i++){
            String[] row = allRows.get(i);
            jump.setTimestamp(Double.parseDouble (row[0]));
            jump.setSync(Double.parseDouble (row[1]));
            jump.setAux(Double.parseDouble (row[2]));
            jump.setFx(Double.parseDouble (row[3]));
            jump.setFy(Double.parseDouble (row[4]));
            jump.setMx(Double.parseDouble (row[5]));
            jump.setMy(Double.parseDouble (row[6]));
            jump.setMz(Double.parseDouble (row[7]));
            jump.setCoPx(Double.parseDouble (row[8]));
            jump.setCoPy(Double.parseDouble (row[9]));
            jumps.add(jump);
           // System.out.println(jump.toString());
        }
        return jumps;
    }
}
