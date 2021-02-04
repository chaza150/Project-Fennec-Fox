package Model.Component;

public enum ComponentType {
    NONE,
    POSITION, // x, y
    VELOCITY, // x, y
    ACCELERATION, //
    GRAPHICS, // zIndex, painter
    PLAYER_CONTROL,
    TIMING, // deltaTime
    ANIMATION, // activeTransition
    COLLIDER // collisionCheck, collisionResponse

}