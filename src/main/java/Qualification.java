import java.util.ArrayList;
import java.util.List;

public enum Qualification {
    ZMC("ЗМС"), MCMK("МСMK"), MC("МС"), KMC("КМС"), R1("1 разряд"), R2("2 разряд"),
    R3("3 разряд"), JR1("1 юн.разряд"), JR2("2 юн.разряд"),JR3("3 юн.разряд"),WR("без разряда");

    private String title;

    Qualification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<String> getAllTitles() {
        Qualification[] arr = values();
        ArrayList<String> result =  new ArrayList<>();
        for(Qualification q:arr){
            result.add(q.title);
        }
        return result;
    }
}
