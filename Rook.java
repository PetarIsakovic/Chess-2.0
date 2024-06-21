import java.util.HashMap;

public class Rook extends Piece{

    private boolean hasMoved;
    
    //this is the constructor for the rook class for initializing all instance variables in the Piece
    public Rook(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
        this.hasMoved = false;
    }

    //used to get the possible moves that the rook can make
    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        //holds the possible moves that the rook can make
        HashMap<String, String> moves = new HashMap<>();

        //checks to see if the rook is white and if its the white players turn
        if(super.getColor().equals("white") && turn.equals("white")){
            //updates the moves hashmap to hold all of the legal moves for the white rook
            checkMoves(board, moves, "black", turn, kingXPos, kingYPos);
            
        }
        //checks to see if the rook is black and if its the black players turn
        else if(super.getColor().equals("black") && turn.equals("black")){
            //updates the moves hashmap to hold all of the legal moves for the black rook
            checkMoves(board, moves, "white", turn, kingXPos, kingYPos);
        }
        //returns all of the legal moves for that rook
        return moves;
    }

    //used to check if the rook has moved (getter method)
    public boolean getHasMoved(){
        return this.hasMoved;
    }

    //used to update weather or not the rook has moved (setter method)
    public void setHasMoved(boolean newHasMoved){
        this.hasMoved = newHasMoved;
    }

    //used to find all of the possible moves that the rook can make
    private void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){
        //loops through all of the squares to the right of the rook
        for(int i = super.getXPos()+1; i < 8; i++){
            //stores the x,y coordinate of the current square/tile in the loop in a string format
            String move = (i) + "," + (super.getYPos());
            //checks to see if a piece is located on this square/tile
            if(board.containsKey(move)){
                //checks to see if the current piece selected is a enemy piece
                if(board.get(move).getColor().equals(enemy)){
                    //adds the move to the hashmap
                    addMove(move, "slide to the left", board, moves, turn, kingXPos, kingYPos);
                }
                //exits the for loop
                break;
            }
            //means there is no piece located on this square/tile
            else{
                //adds the move to the hashmap
                addMove(move, "slide to the right", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //loops through all of the squares to the left of the rook
        for(int i = super.getXPos()-1; i >= 0; i--){
            //stores the x,y coordinate of the current square/tile in the loop in a string format
            String move = (i) + "," + (super.getYPos());
            //checks to see if a piece is located on this square/tile
            if(board.containsKey(move)){
                //checks to see if the current piece selected is a enemy piece
                if(board.get(move).getColor().equals(enemy)){
                    //adds the move to the hashmap
                    addMove(move, "slide to the left", board, moves, turn, kingXPos, kingYPos);
                }
                //exits the loop
                break;
            }
            else{
                //adds the move to the hashmap
                addMove(move, "slide to the left", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //loops through all of the squares above the rook
        for(int i = super.getYPos()-1; i >= 0; i--){
            //stores the x,y coordinate of the current square/tile in the loop in a string format
            String move = (super.getXPos()) + "," + (i);
            //checks to see if a piece is located on this square/tile
            if(board.containsKey(move)){
                //checks to see if the current piece selected is a enemy piece
                if(board.get(move).getColor().equals(enemy)){
                    //adds the move to the hashmap
                    addMove(move, "slide up", board, moves, turn, kingXPos, kingYPos);
                }
                //exits the loop
                break;
            }
            else{
                //adds the move to the hashmap
                addMove(move, "slide up", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //loops through all of the squares below the rook
        for(int i = super.getYPos()+1; i < 8; i++){
            //stores the x,y coordinate of the current square/tile in the loop in a string format
            String move = (super.getXPos()) + "," + (i);
            //checks to see if a piece is located on this square/tile
            if(board.containsKey(move)){
                //checks to see if the current piece selected is a enemy piece
                if(board.get(move).getColor().equals(enemy)){
                    //adds the move to the hashmap
                    addMove(move, "slide down", board, moves, turn, kingXPos, kingYPos);
                }
                //exits the for loop
                break;
            }
            else{
                //adds the move to the hashmap
                addMove(move, "slide down", board, moves, turn, kingXPos, kingYPos);
            }
        }
    }
    //helper method used to see if the move that can be made by the piece will ensure the kings saftey
    private void addMove(String move, String typeOfMove, HashMap<String, Piece> board, HashMap<String, String> moves, String turn, int kingXPos, int kingYPos){
        //stores the piece that will be moved
        Piece removedPiece = null;
        //checks to see if the board contains the move that the user can make
        if(board.containsKey(move)){
            //gets the piece that will be moved
            removedPiece = board.get(move);
        }
        //places the piece in its new position
        board.put(move, board.get(super.getXPos()+","+super.getYPos()));
        //removes the old position piece
        board.remove(super.getXPos()+","+super.getYPos());
        //checks to see if the king is safe
        if(super.isKingSafe(board, turn, kingXPos, kingYPos)){
            //means the move is valid
            moves.put(move, typeOfMove);
        }
        //resets the board changes
        board.put(super.getXPos()+","+super.getYPos(), board.get(move));
        board.remove(move);
        if(removedPiece != null){
            board.put(move, removedPiece);
        }
    }
}