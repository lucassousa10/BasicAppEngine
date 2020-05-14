import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;

import static com.engine.util.ExecutionTimeChecker.check;

public class Main extends AbstractApplication {

    float scale = 1;
    float timeBuffer = 0;

    @Override
    public void update(Engine engine, float deltaTime) {
        timeBuffer += deltaTime;
        if (timeBuffer >= 0.5f) {
            scale += 0.1;
            timeBuffer = 0;
        }
    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        //check(() ->
        renderer.drawText("ola, boa tarde", 50, 50, scale, Color.WHITE.getRGB());
        //, 1, true);
    }

    public static void main(String[] args) {
        Engine e = new Engine(new Main());
        e.setSize(400, 400);
        e.start();
    }
}
