package testing;

import com.engine.Engine;
import com.engine.entities.ApplicationObject;
import com.ui.Button;
import com.ui.ClickListener;
import com.ui.UIApplication;

public class TestUI extends UIApplication {

    private Button button, button2;

    public TestUI() {
        button = new Button("bilu");
        button2 = new Button("bt 2");
        button2.getRect().y = button.getRect().height + 5;

        button.setClickListener(new ClickListener() {
            @Override
            public void onClick(ApplicationObject relatedObject) {
                ((Button) relatedObject).setText("rnd: " + (int) Math.floor(Math.random() * 200));
            }
        });

        button2.setClickListener(new ClickListener() {
            @Override
            public void onClick(ApplicationObject relatedObject) {
                ((Button) relatedObject).setText("outro! " + (int) Math.floor(Math.random() * 200));
            }
        });

        addObject(button);
        addObject(button2);
    }

    public static void main(String[] args) {
        Engine e = new Engine(new TestUI());
        e.setSize(200, 100);
        e.setScale(2f);
        e.start();
    }
}
