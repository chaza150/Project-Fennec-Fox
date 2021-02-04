package Model.Component;

public class PositionComponent extends Component{

    public PositionComponent(float x, float y){
        super(ComponentType.POSITION);

        this.setProperty("x", x);
        this.setProperty("y", y);
    }
}
