import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.List;

public class Help {
    public static void createText(String textForText, int x, int y, List<Node> elements){
        Text text = new Text();
        text.setText(textForText);
        text.setLayoutX(x);
        text.setLayoutY(y);
        elements.add(text);
    }
}
