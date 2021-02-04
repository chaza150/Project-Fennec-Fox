package Model.Component;

public class TimingComponent extends Component {

    public TimingComponent() {
        super(ComponentType.TIMING);

        this.setProperty("deltaTime", 0);
    }

}
