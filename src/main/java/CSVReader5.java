import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader5 {
    public static List<Jump> readJumps(String fileName) throws IOException {
        au.com.bytecode.opencsv.CSVReader reader = new au.com.bytecode.opencsv.CSVReader(new FileReader(fileName));
        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        //Read CSV line by line and use the string array as you want
        String[] subStr;
        ArrayList<Jump> jumps = new ArrayList<Jump>();
        Jump jump = new Jump();
        for(int i = 1; i < allRows.size(); i++){
            jump = new Jump();
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
      //  System.out.println("q");
        return jumps;
    }
}
