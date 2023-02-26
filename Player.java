

public class Player {
    private String name;
    private GamePiece gamePiece;

    public Player(String playerName, GamePiece playerGamePiece) {
        name = playerName;
        gamePiece = playerGamePiece;

    }

    public String getName() {
        return name;
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }


}
