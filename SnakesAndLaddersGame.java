
public class SnakesAndLaddersGame {
    private Die die;
    private Player[] players;
    private int playerIndex;
    private GameBoard gameBoard;

    public SnakesAndLaddersGame(int upperBound, int lowerBound) {
        this.die = new Die(upperBound, lowerBound);
        players = new Player[5];
        playerIndex = 0;
        gameBoard = new GameBoard();
    }

    public SnakesAndLaddersGame() {
        this.die = new Die();
        players = new Player[5];
        playerIndex = 0;
        gameBoard = new GameBoard();
    }

    /**
     *
     * @param playerName a substring represents the name of the player that was inputted
     * @param strColor a substring represents the color of the player that was inputted
     * @return this function converts the player's color into object of type GamePiece and returns a new Player
     */
    private Player createPlayer(String playerName, String strColor){
        Color colorTemp = Color.valueOf(strColor);
        GamePiece gamePiece = new GamePiece(colorTemp);
        Player newPlayer = new Player(playerName,gamePiece);
        return newPlayer;
    }

    /**
     *
     * @param player single object of type player.
     * this function adds the player to the Players array and promote the playerIndex in the array by one step
     */
    private void addPlayers(Player player){
        players[playerIndex] = player;
        playerIndex++;
    }

    /**
     *
     * @param playerColor a substring represents the color of the player that was inputted
     * @param players an array of objects of type Player
     * @return this function checks whether the player's color (that was inputted) already exist in the array of players or not.
     * return false if the color is taken, otherwise true.
     */
    private boolean isColor (String playerColor,Player[] players) {
        Color colorTemp = Color.valueOf(playerColor);
        GamePiece gamePiece = new GamePiece(colorTemp);
        for (int i = 0; i < playerIndex; i++) {
            if (gamePiece.getColor().equals(players[i].getGamePiece().getColor())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param playerName a substring represents the name of the player that was inputted
     * @param players an array of objects of type Player
     * @return this function checks whether the player's name (that was inputted) already exist in the array of players or not.
     * return false if the name is taken, otherwise true.
     */
        private boolean isName (String playerName,Player[] players){
            for (int i=0;i<playerIndex;i++){
                if (playerName.equals(players[i].getName())){
                    return false;
                }
            }
            return true;
        }

    /**
     * checking all player's names length
     * @param players array of all players
     * @return the length of the longest name
     */

    private int maxName (Player[] players){
        int max = players[0].getName().length();
        for (int i=1;i<playerIndex;i++){
            if (players[i].getName().length()>max){
                max = players[i].getName().length();
            }
        }
        return max;
    }

    /**
     * sorting names by alphabet
     * @param maxLength the length of the longest name
     */

    private void sortNames (int maxLength){
        for (int i=0;i<playerIndex;i++) {
            for (int k = i + 1; k < playerIndex; k++) {
                for (int j = 0; j < maxLength; j++) {
                    if (players[i].getName().charAt(j) > players[k].getName().charAt(j)) {
                        Player temp = players[i];
                        players[i] = players[k];
                        players[k] = temp;
                        break;
                    }
                    else if (players[i].getName().charAt(j) < players[k].getName().charAt(j)){
                        break;
                    }
                    else if (players[i].getName().charAt(j) == players[k].getName().charAt(j)){
                        continue;
                    }
                }
            }
        }
    }

    /**
     * initializing the game
     */

    public void initializeGame() {
        System.out.println("Initializing the game...");

        String input = " ";
        while (!(input.equals("end"))) {
            input = Main.scanner.nextLine();
            if (input.equals("end")) {
                if (playerIndex > 1) { //if there are at least 2 players then the game initialization can be finished
                    break;
                } else {
                    System.out.println("Cannot start the game, there are less than two players!");
                    input = Main.scanner.nextLine();
                }
            }
            if (input.substring(0, 5).equals("add l")) { // input case: adding a ladder
                for (int i = 11; i < input.length(); i++) {
                    if (input.charAt(i) == 32) { //creating 2 relevant substrings by reaching the space element
                        String length = input.substring(11, i);
                        String squareNumber = input.substring(i + 1);
                        int intLength = Integer.parseInt(length);
                        int intSquareNumber = Integer.parseInt(squareNumber);
                        if ((intSquareNumber > 100) || (intSquareNumber < 1)) {
                            System.out.println("The square is not within the board's boundaries!");
                        } else if (intLength + intSquareNumber > 100) {
                            System.out.println("The ladder is too long!");
                        } else if (gameBoard.isLadderBase(intSquareNumber, gameBoard.getLadders()) != -1) {
                            System.out.println("This square already contains a bottom of a ladder!");
                        } else if (gameBoard.isSnakeHead(intSquareNumber, gameBoard.getSnakes()) != -1) {
                            System.out.println("This square contains a head of a snake!");
                        } else { // if none of the above is true then the ladder inputted is proper and will be added
                            Square squareBase = new Square(intSquareNumber);
                            Ladder ladder = new Ladder(intLength, squareBase);
                            gameBoard.addLadder(ladder);
                        }
                        break;
                    }
                }
            }

            if (input.substring(0, 5).equals("add s")) {

                for (int i = 10; i < input.length(); i++) { // input case: adding a snake
                    if (input.charAt(i) == 32) { //creating 2 relevant substrings by reaching the space element
                        String length = input.substring(10, i);
                        String squareNumber = input.substring(i + 1);
                        int intLength = Integer.parseInt(length);
                        int intSquareNumber = Integer.parseInt(squareNumber);
                        if ((intSquareNumber > 100) || (intSquareNumber < 1)) {
                            System.out.println("The square is not within the board's boundaries!");
                        } else if (intSquareNumber == 100) {
                            System.out.println("You cannot add a snake in the last square!");
                        } else if (intLength >= intSquareNumber) {
                            System.out.println("The snake is too long!");
                        } else if (gameBoard.isSnakeHead(intSquareNumber, gameBoard.getSnakes()) != -1) {
                            System.out.println("This square already contains a head of a snake !");
                        } else if (gameBoard.isLadderBase(intSquareNumber, gameBoard.getLadders()) != -1) {
                            System.out.println("This square contains a bottom of a ladder !");
                        } else { // if none of the above is true then the snake inputted is proper and will be added
                            Square squareHead = new Square(intSquareNumber);
                            Snake snake = new Snake(intLength, squareHead);
                            gameBoard.addSnake(snake);
                        }
                        break;
                    }
                }
            }


            if (input.substring(0, 5).equals("add p")) {
                for (int i = 11; i < input.length(); i++) { // input case: adding a player
                    if (input.charAt(i) == 32) { //creating 2 relevant substrings by reaching the space element
                        String playerName = input.substring(11, i);
                        String playerColor = input.substring(i + 1);
                        if (playerIndex == 5) {
                            System.out.println("The maximal number of players is five !");
                        } else if ((!isName(playerName, players)) && (!isColor(playerColor, players))) {
                            System.out.println("The name and the color are already taken!");
                        } else if (!isName(playerName, players)) {
                            System.out.println("The name is already taken!");
                        } else if (!isColor(playerColor, players)) {
                            System.out.println("The color is already taken!");
                        } else { // if none of the above is true then the player inputted is proper and will be added
                            Player player = createPlayer(playerName, playerColor);
                            addPlayers(player);
                        }
                        break;
                    }

                }
            }
        }

        int maxLength = maxName(players);
        sortNames(maxLength);

    }



    /**
     *
     * @param playerLocation an array represents of the players' location during the game
     * @return this function checks whether one of the players won the game. return true if there is a winner,
     * otherwise false
     */
    private boolean isWinner (int[] playerLocation){
        for (int i =0;i<playerIndex;i++){
            if (playerLocation[i]==100){
                return true;
            }
        }
        return false;
    }

    /**
     * This function adds a snake if relevant
     * @param player is current player number
     * @param snakeLocation is an indicator checking if there is a snake at place
     * @param playerLocation is current player location
     * @return updates snake head location
     */
    private int snake(int player,int snakeLocation,int [] playerLocation){
            while (snakeLocation!=-1){
                playerLocation[player] = playerLocation[player]-gameBoard.getSnakes()[snakeLocation].getLength();
                System.out.print(" -> "+playerLocation[player]);
                snakeLocation = gameBoard.isSnakeHead(playerLocation[player], gameBoard.getSnakes());

        }
        return snakeLocation;
    }

    /**
     * This function adds a lader if relevant
     * @param player is current player number
     * @param ladderLocation is an indicator checking if there is a ladder at place
     * @param playerLocation is current player location
     * @return updates ladder base location
     */
    private int ladder(int player,int ladderLocation,int [] playerLocation){
        while (ladderLocation!=-1){
            playerLocation[player] = playerLocation[player]+gameBoard.getLadders()[ladderLocation].getLength();
            System.out.print(" -> "+playerLocation[player]);
            ladderLocation = gameBoard.isLadderBase(playerLocation[player], gameBoard.getLadders());

        }
        return ladderLocation;
    }


    public String start(){
    int roundNumber = 0;
    int [] playerLocation = new int [playerIndex];
    //initialize each player starting location to 1
    for (int i =0;i<playerIndex;i++){
        playerLocation[i]=1;
    }
    String winnerPlayer = new String();
    while (!(isWinner(playerLocation))){ // in each iteration we check if there is a winner
    roundNumber++;
    System.out.println("------------------------- Round number " +roundNumber+ " -------------------------");
        for (int i =0;i<playerIndex;i++){ // a loop to describe each player's progress
            int previousPlayerLocation = playerLocation[i];
            int rolledNumber = this.die.roll();
            playerLocation[i] = playerLocation[i]+ rolledNumber;
            if (playerLocation[i]<1){  // if the die showed a negative number which puts the player in a negative location
                playerLocation[i]=1;
            }
            if (playerLocation[i]>100){ //if the player crossed the 100 square
                playerLocation[i]=playerLocation[i]-((playerLocation[i]-100)*2);
            }
            System.out.print(players[i].getName()+" rolled "+rolledNumber +". The path to the next square: "+ previousPlayerLocation+ " -> " +playerLocation[i]);
            int snakeLocation = gameBoard.isSnakeHead(playerLocation[i], gameBoard.getSnakes());
            int ladderLocation = gameBoard.isLadderBase(playerLocation[i], gameBoard.getLadders());
            while(snakeLocation!=-1 || ladderLocation!=-1) { //loop checking snakes and ladder
                snakeLocation = snake(i, snakeLocation, playerLocation);
                ladderLocation = ladder(i, ladderLocation, playerLocation);
                snakeLocation = gameBoard.isSnakeHead(playerLocation[i], gameBoard.getSnakes());
                ladderLocation = gameBoard.isLadderBase(playerLocation[i], gameBoard.getLadders());
            }


            if (playerLocation[i]==100){ //if during the current round one of the players reached the 100 square- break the loop
                winnerPlayer=players[i].getName();
                System.out.println(""); //for final space
                break;
            }
            System.out.println(""); //for spaces between players
        }
        System.out.println("\nPlayers positions on the board:");
        for(int j =0;j<playerIndex;j++){
            System.out.println(players[j].getName()+" is in square number "+playerLocation[j]);
        }
    }

    return winnerPlayer;
    }
}

