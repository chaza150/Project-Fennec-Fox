package Model.Component;

public class VelocityComponent extends Component{

    public VelocityComponent(float x, float y){
        super(ComponentType.VELOCITY);

        this.setProperty("x", x);
        this.setProperty("y", y);

        this.setPrerequisites(ComponentType.POSITION);
    }
}
