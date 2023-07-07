package com.development.scut_cdd.ServerLayer;

import Model.Entity.ServerAction;

public class ServerDataGenerator {
    GameRoomData gameRoomData;

    public ServerDataGenerator(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }

    public String gen_Initial_Game(){
        gameRoomData.setRoundCount(0);
        gameRoomData.setPassCount(0);
        gameRoomData.setServerAction(new ServerAction(ServerAction.ACTION_TYPE_Init_GAME));
        return gameRoomData.toJson();
    }
}
