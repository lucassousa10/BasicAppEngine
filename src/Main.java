import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;
import com.engine.ui.Button;

import java.awt.*;

public class Main extends AbstractApplication {

    Button button;

    public Main() {
        button = new Button("teste");
        button.rect.x = 100;
        button.rect.y = 100;
    }

    @Override
    public void update(Engine engine, float deltaTime) {
        button.update(engine, deltaTime);
    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        button.render(engine, renderer);
    }

    public static void main(String[] args) {
        Engine e = new Engine(new Main());
        e.setSize(800, 400);
        e.start();
    }
}
