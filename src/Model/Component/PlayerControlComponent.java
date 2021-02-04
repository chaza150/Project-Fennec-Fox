package Model.Component;

public class PlayerControlComponent extends Component {

    public PlayerControlComponent() {
        super(ComponentType.PLAYER_CONTROL);
        this.setPrerequisites(ComponentType.POSITION, ComponentType.VELOCITY);
    }

}
