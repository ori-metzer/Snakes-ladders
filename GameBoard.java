

public class GameBoard {
    private Ladder[] ladders;
    private Snake[] snakes;
    private int ladderIndex;
    private int snakeIndex;
    private int boardSize;



    public GameBoard (){
        boardSize = 100;
        ladders = new Ladder[boardSize];
        snakes = new Snake[boardSize];
        ladderIndex = 0;
        snakeIndex = 0;
    }

    public Ladder[] getLadders() {
        return ladders;
    }

    public Snake[] getSnakes() {
        return snakes;
    }


    /**
     *
     * @param ladder a single object of type Ladder
     *  this function adds the ladder to the Ladders array and promote the ladderIndex in the array by one step
     */
    public void addLadder(Ladder ladder) {
        ladders[ladderIndex] = ladder;
        ladderIndex++;
    }

    /**
     *
     * @param snake a single object of type Snake
     * this function adds the snake to the snakes array and promote the snakeIndex in the array by one step
     */
    public void addSnake(Snake snake) {
        snakes[snakeIndex] = snake;
        snakeIndex++;
    }

    /**
     *
     * @param squareNumber the number of square of the ladder's base inputted
     * @param ladders an array of ladders which already exist
     * @return this func checks if the square already contains a ladder's base. if so, then it returns the index of
     * the existing ladder, otherwise returns -1
     */
    public int isLadderBase(int squareNumber, Ladder[] ladders) {
        for (int i = 0; i < ladderIndex; i++) {
            if (squareNumber == ladders[i].getSquareNumber().getSquareNumber()) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param squareNumber the number of square of the snake's head inputted
     * @param snakes an array of snakes which already exist
     * @return this func checks if the square already contains a snake's head. if so, then it returns the index of
     * the existing snake, otherwise returns -1
     */
    public int isSnakeHead(int squareNumber, Snake[] snakes) {
        for (int i = 0; i < snakeIndex; i++) {
            if (squareNumber == snakes[i].getSquareNumber().getSquareNumber()) {
                return i;
            }
        }
        return -1;
    }





}
