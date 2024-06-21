import java.util.HashMap;

public class Knight extends Piece{
    //this is the constructor for the Knight class for initializing all instance variables in the Piece
    public Knight(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
    }

    //method used to get all of the possible moves that the Knight can make
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

    // helper method used to get all the possible moves that the Knight can make
    private void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){

        //every single if statement is used to check all 8 of the L's shapes that the horse can make
        String move = "";
        if(super.getXPos()+2 < 8 && super.getYPos()-1 >= 0){
            move = (super.getXPos()+2) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()+2 < 8 && super.getYPos()+1 < 8){
            move = (super.getXPos()+2) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()-2 >= 0 && super.getYPos()-1 >= 0){
            move = (super.getXPos()-2) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()-2 >= 0 && super.getYPos()+1 < 8){
            move = (super.getXPos()-2) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()+1 < 8 && super.getYPos()-2 >= 0){
            move = (super.getXPos()+1) + "," + (super.getYPos()-2);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()+1 < 8 && super.getYPos()+2 < 8){
            move = (super.getXPos()+1) + "," + (super.getYPos()+2);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()-1 >= 0 && super.getYPos()-2 >= 0){
            move = (super.getXPos()-1) + "," + (super.getYPos()-2);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
            }
        }

        if(super.getXPos()-1 >= 0 && super.getYPos()+2 < 8){
            move = (super.getXPos()-1) + "," + (super.getYPos()+2);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "L shape", board, moves, turn, kingXPos, kingYPos);
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