package mx.itesm.team4.box2d;

import mx.itesm.team4.enums.UserDataType;

public class GroundUserData extends UserData{
    public GroundUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.GROUND;
    }
}
