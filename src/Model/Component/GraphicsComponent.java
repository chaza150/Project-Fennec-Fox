package Model.Component;

import java.awt.*;
import java.util.function.Consumer;

public class GraphicsComponent extends Component {

    public GraphicsComponent(int zIndex, Consumer<Graphics> painter){
        super(ComponentType.GRAPHICS);

        setProperty("zIndex", zIndex);
        setProperty("painter", painter);
    }
}
