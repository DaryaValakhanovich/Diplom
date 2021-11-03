import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader5 {
   /* public static List<Jump> readJumps(String fileName) throws IOException {
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
            jump.setFz(Double.parseDouble (row[5]));
            jump.setMx(Double.parseDouble (row[6]));
            jump.setMy(Double.parseDouble (row[7]));
            jump.setMz(Double.parseDouble (row[8]));
            jump.setCoPx(Double.parseDouble (row[9]));
            jump.setCoPy(Double.parseDouble (row[10]));
            jumps.add(jump);
            // System.out.println(jump.toString());
        }
      //  System.out.println("q");
        return jumps;
    }*/

    public static ArrayList<Double> cutFz(ArrayList<Double> arr) {
        Double nearIzoline = average(arr);
        int countHead = 0;
        int countTail = arr.size()- 1;
        for (int i = 0; arr.get(i) < nearIzoline; i++){
            countHead++;
        }
        for (int i = arr.size()-1; arr.get(i) < nearIzoline; i--){
            countTail--;
        }
        ArrayList<Double> result = new ArrayList<>();
        for(int i = countHead; i <= countTail; i++){
            result.add(arr.get(i));
        }
        return result;
    }
    private static Double average(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    public static double[] findMas(ArrayList<Double> list){
        ArrayList<Integer> counts = new ArrayList<>();
        ArrayList<Double> edges = new ArrayList<>();
        double leftBorder = list.stream().min(Double::compare).get();
        double finalBorder = list.stream().max(Double::compare).get();
        double step = (finalBorder - leftBorder) / Math.sqrt(list.size());
        edges.add(leftBorder);
        double rightBorder;
        int count = 0;
        while(leftBorder < finalBorder) {
            rightBorder = leftBorder + step;
            edges.add(rightBorder);
            for (Double aDouble : list) {
                if (leftBorder <= aDouble && aDouble < rightBorder) {
                    count++;
                }
            }
            counts.add(count);
            count = 0;
            leftBorder = rightBorder;
        }
        int indexOfMaxValue = counts.indexOf(counts.stream().max(Integer::compare).get());
        Double izoline = edges.get(++indexOfMaxValue);
        double g = 9.81;
        double P = izoline;
        System.out.println(indexOfMaxValue);
        double mass=Math.round(P/g);
        System.out.println(P/g);
        double[] mas = new double[2];
        mas[0] = mass;
        mas[1] = izoline;
        return mas;
    }

    public static ArrayList<Double> readFz(String fileName) throws IOException {
        au.com.bytecode.opencsv.CSVReader reader = new au.com.bytecode.opencsv.CSVReader(new FileReader(fileName));
        List<String[]> allRows = reader.readAll();
        ArrayList<Double> fz = new ArrayList<>();
        String[] row = allRows.get(0);
        int column = 0;
        for (int i = 0;i<row.length;i++){
            if(row[i].equals("Fz")){
                column = i;
            }
        }
        for(int i = 1; i < allRows.size(); i++){
            row = allRows.get(i);
            fz.add(Double.parseDouble (row[column]));
        }
        return fz;
    }

}
