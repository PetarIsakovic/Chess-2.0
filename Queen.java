import java.util.HashMap;

public class Queen extends Piece{

    //this is the constructor for the Queen class for initializing all instance variables in the Piece
    public Queen(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
    }

    //method used to get all of the possible moves that the Queen can make
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

    // helper method used to get all the possible moves that the Queen can make
    private void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){

        //used to check if the queen can slide to the bottom right
        for(int i = super.getXPos()+1; i < 8; i++){
            if(super.getYPos()+(i-super.getXPos()) < 8){
                String move = (i) + "," + (super.getYPos()+(i-super.getXPos()));
                if(board.containsKey(move)){
                    if(board.get(move).getColor().equals(enemy)){
                        addMove(move, "slide to the bottom right", board, moves, turn, kingXPos, kingYPos);
                    }
                    break;
                }
                else{
                    addMove(move, "slide to the bottom right", board, moves, turn, kingXPos, kingYPos);
                }
            }
        }

        //used to check if the queen can slide to the top right
        for(int i = super.getXPos()+1; i < 8; i++){
            if(super.getYPos()-(i-super.getXPos()) >= 0){
                String move = (i) + "," + (super.getYPos()-(i-super.getXPos()));
                if(board.containsKey(move)){
                    if(board.get(move).getColor().equals(enemy)){
                        addMove(move, "slide to the top right", board, moves, turn, kingXPos, kingYPos);
                    }
                    break;
                }
                else{
                    addMove(move, "slide to the top right", board, moves, turn, kingXPos, kingYPos);
                }
            }
        }

        //used to check if the queen can slide to the bottom left
        for(int i = super.getXPos()-1; i >= 0; i--){
            if(super.getYPos()+(super.getXPos()-i) < 8){
                String move = (i) + "," + (super.getYPos()+(super.getXPos()-i));
                if(board.containsKey(move)){
                    if(board.get(move).getColor().equals(enemy)){
                        addMove(move, "slide to the bottom left", board, moves, turn, kingXPos, kingYPos);
                    }
                    break;
                }
                else{
                    addMove(move, "slide to the bottom left", board, moves, turn, kingXPos, kingYPos);
                }
            }
        }

        //used to check if the queen can slide to the top left
        for(int i = super.getXPos()-1; i >= 0; i--){
            if(super.getYPos()-(super.getXPos()-i) >= 0){
                String move = (i) + "," + (super.getYPos()-(super.getXPos()-i));
                if(board.containsKey(move)){
                    if(board.get(move).getColor().equals(enemy)){
                        addMove(move, "slide to the top left", board, moves, turn, kingXPos, kingYPos);
                    }
                    break;
                }
                else{
                    addMove(move, "slide to the top left", board, moves, turn, kingXPos, kingYPos);
                }
            }
        }

        //used to check if the queen can slide to the right
        for(int i = super.getXPos()+1; i < 8; i++){
            String move = (i) + "," + (super.getYPos());
            if(board.containsKey(move)){
                if(board.get(move).getColor().equals(enemy)){
                    addMove(move, "slide to the right", board, moves, turn, kingXPos, kingYPos);
                }
                break;
            }
            else{
                addMove(move, "slide to the right", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //used to check if the queen can slide to the left
        for(int i = super.getXPos()-1; i >= 0; i--){
            String move = (i) + "," + (super.getYPos());
            if(board.containsKey(move)){
                if(board.get(move).getColor().equals(enemy)){
                    addMove(move, "slide to the left", board, moves, turn, kingXPos, kingYPos);
                }
                break;
            }
            else{
                addMove(move, "slide to the left", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //used to check if the queen can slide up
        for(int i = super.getYPos()-1; i >= 0; i--){
            String move = (super.getXPos()) + "," + (i);
            if(board.containsKey(move)){
                if(board.get(move).getColor().equals(enemy)){
                    addMove(move, "slide up", board, moves, turn, kingXPos, kingYPos);
                }
                break;
            }
            else{
                addMove(move, "slide up", board, moves, turn, kingXPos, kingYPos);
            }
        }

        //used to check if the queen can slide down
        for(int i = super.getYPos()+1; i < 8; i++){
            String move = (super.getXPos()) + "," + (i);
            if(board.containsKey(move)){
                if(board.get(move).getColor().equals(enemy)){
                    addMove(move, "slide down", board, moves, turn, kingXPos, kingYPos);
                }
                break;
            }
            else{
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