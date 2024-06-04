import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class Main {
    //fps of the game
    final int FPS = 60;
    //width of the screen
    final int WIDTH = 640;
    //heights of the screen
    final int HEIGHT = 640;
    //game title name
    final String TITLE = "Chess Final Project";

    //holds the size of each square's width/length
    final int sizeOfSquares = 80;
    //holds the coordinates of each piece as well as the physical Piece as well
    HashMap<String, Piece> board = new HashMap<>();

    //this object will be used when actually drawing to the screen (using Drawing.java class)
    Drawing drawing;

    //used to store where the player last clicked their mouse (x and y position)
    int clickXPos;
    int clickYPos;

    //used to store where the player last hovered their mouse (x and y position)
    int hoveringXPos;
    int hoveringYPos;

    //used to store where the player last dragged their mouse (x and y position)
    int draggingXPos;
    int draggingYPos;

    //used to check if a piece has been selected (if a user clicked on it)
    boolean pieceSelected = false;

    //used to check if the user is currently hovering their mouse over a piece
    boolean hovering = false;

    //used to check if the user is current dragging their mouse while holding the click button
    boolean mouseIsBeingDragged = false;

    //used to store the possible move that a piece can make
    HashMap<String, String> possibleMoves;

    int blackKingXPos = 4;
    int blackKingYPos = 0;

    int whiteKingXPos = 4;
    int whiteKingYPos = 7;

    boolean mousePressed = false;
    
    boolean mouseReleased = false;

    MouseEvent holder;

    //used to store what players turn it is
    String turn = "white";

    public Main() {
        // initialize anything you need to before we see it on the screen
        start();
        // create the screen and show it
        drawing = new Drawing(TITLE, WIDTH, HEIGHT, FPS, this);
        // make sure key and mouse events work
        // this is like an actionListener on buttons except it's the keyboard and mouse
        drawing.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        drawing.addMouseListener(m);
        drawing.addMouseMotionListener(m);
        drawing.addMouseWheelListener(m);
        // start the game loop
        drawing.loop();
    }

    //used when initially starting the program
    public void start() {
        //placing every black piece onto the board (except the pawns)
        board.put("0,0", new Rook(0, 0, "black",  "Images/BlackRook.png"));
        board.put("1,0", new Knight(1, 0, "black",  "Images/BlackKnight.png"));
        board.put("2,0", new Bishop(2, 0, "black",  "Images/BlackBishop.png"));
        board.put("3,0", new Queen(3, 0, "black",  "Images/BlackQueen.png"));
        board.put("4,0", new King(4, 0, "black",  "Images/BlackKing.png"));
        board.put("5,0", new Bishop(5, 0, "black",  "Images/BlackBishop.png"));
        board.put("6,0", new Knight(6, 0, "black",  "Images/BlackKnight.png"));
        board.put("7,0", new Rook(7, 0, "black",  "Images/BlackRook.png"));

        //placing every white piece onto the board (except the pawns)
        board.put("0,7", new Rook(0, 7, "white",  "Images/WhiteRook.png"));
        board.put("1,7", new Knight(1, 7, "white",  "Images/WhiteKnight.png"));
        board.put("2,7", new Bishop(2, 7, "white",  "Images/WhiteBishop.png"));
        board.put("3,7", new Queen(3, 7, "white",  "Images/WhiteQueen.png"));
        board.put("4,7", new King(4, 7, "white",  "Images/WhiteKing.png"));
        board.put("5,7", new Bishop(5, 7, "white",  "Images/WhiteBishop.png"));
        board.put("6,7", new Knight(6, 7, "white",  "Images/WhiteKnight.png"));
        board.put("7,7", new Rook(7, 7, "white",  "Images/WhiteRook.png"));

        //used to loop through all 8 pawns that will be placed for both plack and white
        for(int i = 0; i < 8; i++){
            //places the pawns onto the board
            board.put(i + ",1", new Pawn(i, 1, "black",  "Images/BlackPawn.png"));
            board.put(i+ ",6", new Pawn(i, 6, "white",  "Images/WhitePawn.png"));
        }

        //initializes the possible moves HashMap
        possibleMoves = new HashMap<>();
    }

    //NOT NEEDED CODE REMOVE LATER!!!!!!
    public void loop() {
        if(mousePressed){
            //holds the position of where the mouse clicked in a string format (x, y)
            String position = clickXPos+","+clickYPos;

            //used to check if the position that the user clicked on one of their sides pieces
            if(board.containsKey(position) && board.get(position).getColor().equals(turn)){
                //means a valid piece was selected
                pieceSelected = true;

                if(turn.equals("white")){
                    //retrieves all of the possible moves of that piece
                    possibleMoves = board.get(position).getPossibleMoves(board, turn, whiteKingXPos, whiteKingYPos);
                }
                else if(turn.equals("black")){
                    possibleMoves = board.get(position).getPossibleMoves(board, turn, blackKingXPos, blackKingYPos);
                }
                //printing the possible moves of that piece (for debugging)
                System.out.println(possibleMoves);
            }
            //means that a invalid piece was selected (or no piece at all)
            else{
                pieceSelected = false;
            }
            mousePressed = false;
        }
        if(mouseReleased){
            //checks to see if the mouse was just dragging a piece
            if(mouseIsBeingDragged){
                //holds the coordinates of where the piece was just released on the board
                String coordinateOfRelease = holder.getX()/sizeOfSquares + "," + holder.getY()/sizeOfSquares;
                //holds the coordinate where the piece was just picked up
                String coordinateOfPickUp = clickXPos + "," + clickYPos;

                //checks if the possible moves that the player can make with the piece that was selected contains the coordinates of where the piece was released
                if(possibleMoves.containsKey((coordinateOfRelease))){
                    //checks to see if that current move that the player is making an enpasant move
                    if(possibleMoves.get(coordinateOfRelease).contains("enpasanted")){
                        //holds the coordinates of the piece that is being enpasanted
                        String coordinatesOfRemoval = possibleMoves.get(coordinateOfRelease).substring(0, 3);
                        //removes the enpasanted piece (the pawn) from the board
                        board.remove(coordinatesOfRemoval);
                    }

                    //checks if the piece moved was a king
                    if(board.get(coordinateOfPickUp) instanceof King){
                        //means the king has moved
                        board.get(coordinateOfPickUp).setHasMoved(true);
                        if(turn.equals("white")){
                            whiteKingXPos = holder.getX()/sizeOfSquares;
                            whiteKingYPos = holder.getY()/sizeOfSquares;
                        }
                        else if(turn.equals("black")){
                            blackKingXPos = holder.getX()/sizeOfSquares;
                            blackKingYPos = holder.getY()/sizeOfSquares;
                        }
                        if(possibleMoves.get(coordinateOfRelease).equals("castle right")){
                            String coordOfRook = (board.get(coordinateOfPickUp).getXPos()+3) + "," + board.get(coordinateOfPickUp).getYPos();
                            Piece rook = board.get(coordOfRook);
                            rook.setXPos(board.get(coordinateOfPickUp).getXPos()+1);
                            rook.setYPos(board.get(coordinateOfPickUp).getYPos());
                            board.put(rook.getXPos() + "," + rook.getYPos(), rook);
                            board.remove(coordOfRook);
                        }
                        else if(possibleMoves.get(coordinateOfRelease).equals("castle left")){
                            String coordOfRook = (board.get(coordinateOfPickUp).getXPos()-4) + "," + board.get(coordinateOfPickUp).getYPos();
                            Piece rook = board.get(coordOfRook);
                            rook.setXPos(board.get(coordinateOfPickUp).getXPos()-1);
                            rook.setYPos(board.get(coordinateOfPickUp).getYPos());
                            board.put(rook.getXPos() + "," + rook.getYPos(), rook);
                            board.remove(coordOfRook);
                        }
                    }

                    //checks if the piece moved was a rook
                    if(board.get(coordinateOfPickUp) instanceof Rook){
                        //means the rook has moved
                        board.get(coordinateOfPickUp).setHasMoved(true);
                    }

                    //places the piece on the new position on the board
                    board.put(coordinateOfRelease, board.get(coordinateOfPickUp));
                    //removes the old position of the piece on the board
                    board.remove(coordinateOfPickUp);

                    //updates the actual x position and y position of the piece's object
                    board.get(coordinateOfRelease).setXPos(holder.getX()/sizeOfSquares);
                    board.get(coordinateOfRelease).setYPos(holder.getY()/sizeOfSquares);
                    
                    //checks to see if the current players turn was white
                    if(turn.equals("white")){
                        //changes the turn to black
                        turn = "black";
                        //loops through all of the pieces on the board
                        for(String key : board.keySet()){
                            //checks to see if any of the black pieces were enpasantable
                            if(board.get(key).getColor().equals("black") && board.get(key).isEnpasantable()){
                                //means that this specific black piece is no longer enpasantable
                                board.get(key).setEnpasantable(false);
                            }
                        }
                    }
                    //checks to see if the current players turn was black
                    else if(turn.equals("black")){
                        //changes the turn to white
                        turn = "white";
                        //loops through all of the pieces on the board
                        for(String key : board.keySet()){
                            //checks to see if any of the white pieces were enpasantable
                            if(board.get(key).getColor().equals("white") && board.get(key).isEnpasantable()){
                                //means that the specific white piece is no longer enpasantable
                                board.get(key).setEnpasantable(false);
                            }
                        }
                    }
                }
            }
            //sets the users cursor to the DEFAULT_CURSOR style
            drawing.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            //resets the possible moves that the user could make with that piece
            possibleMoves = new HashMap<>();
            //means a piece is no longer being selected
            pieceSelected = false;
            //means the mouse is no longer being dragged
            mouseIsBeingDragged = false;
            mouseReleased = false;
        }
    }
    //used when drawing anything to the screen
    public void paintComponent(Graphics2D g) {
        //loops through all of the columns of the board
        for(int i = 0; i < 8; i++){
            //loops through all of the rows of the board
            for(int j = 0; j < 8; j++){
                //checks if the current square on the board has a piece that has been clicked on by the user
                if(i == clickYPos && j == clickXPos && pieceSelected){
                    g.setColor(new Color(32, 2, 133, 143));
                }
                //checks if the current square on the board has a piece that is being hovered on by the user
                else if(i == hoveringYPos && j == hoveringXPos && hovering){
                    g.setColor(new Color(233, 111, 3, 143));
                }
                else{
                    //used to alternate between dark and light squares on the board
                    if(i % 2 == 0){
                        if(j % 2 == 0){
                            g.setColor(new Color(240,218,181));
                        }
                        else{
                            g.setColor(new Color(181,135,99));
                        }
                    }
                    else{
                        if(j % 2 != 0){
                            g.setColor(new Color(240,218,181));
                        }
                        else{
                            g.setColor(new Color(181,135,99));
                        }
                    }
                }
                //used to draw the square/tile of the board onto the screen
                g.fillRect(j*sizeOfSquares, i*sizeOfSquares, sizeOfSquares, sizeOfSquares);

                //gets the coordinate of the current square/tile (x, y)
                String coordinate = j + "," + i;
                //checks to see if that current (x,y) coordinate can be found in the possible moves hashmap
                if(possibleMoves.containsKey(coordinate)){
                    //draws a square with a special colour for the user to understand the possible moves that the piece can make
                    g.setColor(new Color(2, 133,181, 143));
                    g.fillRect(j*sizeOfSquares, i*sizeOfSquares, sizeOfSquares, sizeOfSquares);
                }
            }
        }

        // loops through each key in the hashmap of the board (each key represents the coordinates of the piece)
        for (Map.Entry<String, Piece> entry : board.entrySet()) {
            String key = entry.getKey();
            //checks to see if the piece has not been clicked on by the player and the mouse is not being dragged
            if (!(key.equals(clickXPos + "," + clickYPos) && mouseIsBeingDragged)) {
                //draws the piece on its title
                g.drawImage(board.get(key).getImage(), board.get(key).getXPos() * sizeOfSquares, board.get(key).getYPos() * sizeOfSquares, sizeOfSquares, sizeOfSquares, null);
            }
        }
        //checks to see if the mouse is being dragged 
        if (mouseIsBeingDragged) {
            //checks to see if the player clicked on a piece
            if(board.get(clickXPos + "," + clickYPos) != null){
                //draws that piece onto the position of the users mouse (where they are dragging their mouse)
                g.drawImage(board.get(clickXPos + "," + clickYPos).getImage(), draggingXPos - sizeOfSquares / 2, draggingYPos - sizeOfSquares / 2, sizeOfSquares, sizeOfSquares, null);
            }
        }
    }

    //used to control the Mouse actions of the chess game
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {
            //gets the x and y position of where the mouse was clicked on the chess board
            clickXPos = e.getX()/sizeOfSquares;
            clickYPos = e.getY()/sizeOfSquares;
            mousePressed = true;
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {
            mouseReleased = true;
            holder = e;
        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }
 
 
        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {
            //holds the current position of where the mouse is being moved (x and y coords)
            hoveringXPos = e.getX()/sizeOfSquares;
            hoveringYPos = e.getY()/sizeOfSquares;

            //stores the mouses position in a x,y format string
            String position = hoveringXPos + "," + hoveringYPos;

            //checks to see if the board contains a piece at that specific position being hovered
            if(board.containsKey(position)){
                //used to update the cursor type of the user to HAND_CURSOR
                drawing.setCursor(new Cursor(Cursor.HAND_CURSOR));
                //means the user is currently hovering a piece
                hovering = true;
            }
            //means the user is not hovering a piece
            else{
                //used to update the cursor type to the DEFAULT_CURSOR
                drawing.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                //means the user is not currently hovering a piece
                hovering = false;
            }
        }

        // if the mouse is being dragged
        @Override
        public void mouseDragged(MouseEvent e) {
            // checks to see if a valid piece was selected
            if(pieceSelected){
                //holds the current dragging x position
                draggingXPos = e.getX();
                //holds the current dragging y position
                draggingYPos = e.getY();
                //means the mouse is currently being dragged
                mouseIsBeingDragged = true;
            }
        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            // determine which key was pressed
            int key = e.getKeyCode();

        }
    }

    // the main method that launches the game when you hit run
    public static void main(String[] args) {
        Main game = new Main();
    }
}