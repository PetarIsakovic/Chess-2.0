import java.util.HashMap;

public class King extends Piece{
    //used to store if the king has moved
    private boolean hasMoved;

    //this is the constructor for the King class for initializing all instance variables in the Piece
    public King(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
        this.hasMoved = false;
    }
    

    //method used to get all of the possible moves that the Bishop can make
    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        //stores all of the moves that the bishop can make
        HashMap<String, String> moves = new HashMap<>();
        //checks to see if the piece is white and if its the white players turn
        if(super.getColor().equals("white") && turn.equals("white")){
            checkMoves(board, moves, "black", turn, kingXPos, kingYPos);
        }
        //checks to see if the piece is black and if its the black players turn
        else if(super.getColor().equals("black") && turn.equals("black")){
            checkMoves(board, moves, "white", turn, kingXPos, kingYPos);
        }
        //returns all of the moves
        return moves;
    }

    //getter method to see if the king has moved
    public boolean getHasMoved(){
        return this.hasMoved;
    }

    //setter method to update if the king has moved
    public void setHasMoved(boolean newHasMoved){
        this.hasMoved = newHasMoved;
    }

    // helper method used to get all the possible moves that the King can make
    private void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){

        //Checks to see if the king can castle at all (meaning we are checking if it is in check)
        if(!hasMoved && super.isKingSafe(board, turn, kingXPos, kingYPos)){
            String move = (super.getXPos()+2) + "," + (super.getYPos());
            String positionOfRook = (super.getXPos()+3) + "," + (super.getYPos());
            //checks to see if we can castle right
            if(board.containsKey(positionOfRook) && board.get(positionOfRook) instanceof Rook && !board.get(positionOfRook).getHasMoved()){
                String checkSquare1 = (super.getXPos()+1) + "," + (super.getYPos());
                String checkSquare2 = (super.getXPos()+2) + "," + (super.getYPos());
                if(!board.containsKey(checkSquare1) && !board.containsKey(checkSquare2) && super.isKingSafe(board, turn, kingXPos+1, kingYPos) && super.isKingSafe(board, turn, kingXPos+2, kingYPos)){
                    moves.put(move, "castle right");
                }
            }
            move = (super.getXPos()-2) + "," + (super.getYPos());
            positionOfRook = (super.getXPos()-4) + "," + (super.getYPos());
            //checks to see if we can castle left
            if(board.containsKey(positionOfRook) && board.get(positionOfRook) instanceof Rook && !board.get(positionOfRook).getHasMoved()){
                String checkSquare1 = (super.getXPos()-1) + "," + (super.getYPos());
                String checkSquare2 = (super.getXPos()-2) + "," + (super.getYPos());
                String checkSquare3 = (super.getXPos()-3) + "," + (super.getYPos());
                if(!board.containsKey(checkSquare1) && !board.containsKey(checkSquare2) && !board.containsKey(checkSquare3) && super.isKingSafe(board, turn, kingXPos-1, kingYPos) && super.isKingSafe(board, turn, kingXPos-2, kingYPos)){
                    moves.put(move, "castle left");
                }
            }
        }

        //checks to see if we can move one square up
        if(super.getYPos() >= 0){
            String move = (super.getXPos()) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move up 1 square", board, moves, turn, kingXPos, kingYPos-1);
            }
        }

        //checks to see if we can move one square down
        if(super.getYPos() < 8){
            String move = (super.getXPos()) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move down 1 square", board, moves, turn, kingXPos, kingYPos+1);
            }
        }

        //checks to see if we can move square left
        if(super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos());
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move left 1 square", board, moves, turn, kingXPos-1, kingYPos);
            }
        }

        //checks to see if we can move one square right
        if(super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos());
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move right 1 square", board, moves, turn, kingXPos+1, kingYPos);
            }
        }

        //checks to see if we can move 1 square top left
        if(super.getYPos() >= 0 && super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move top left 1 square", board, moves, turn, kingXPos-1, kingYPos-1);
            }
        }

        //checks to see if we can move top right 1 square
        if(super.getYPos() >= 0 && super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move top right 1 square", board, moves, turn, kingXPos+1, kingYPos-1);
            }
        }

        //checks to see if we can move bottom left 1 square
        if(super.getYPos() < 8 && super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move bottom left 1 square", board, moves, turn, kingXPos-1, kingYPos+1);
            }
        }

        //checks to see if we can move bottom right 1 square
        if(super.getYPos() < 8 && super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move bottom right 1 square", board, moves, turn, kingXPos+1, kingYPos+1);
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
