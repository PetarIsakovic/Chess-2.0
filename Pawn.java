import java.util.HashMap;

public class Pawn extends Piece{
    //used to check if the pawn can be enpasanted
    private boolean enpasantable;

    //this is the constructor for the Pawn class for initializing all instance variables in the Piece
    public Pawn(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
        this.enpasantable = false;
    }

    //getter method to check if the Pawn is enpasantable
    public boolean isEnpasantable(){
        return this.enpasantable;
    }

    //setter method to update if the pawn can be enpasanted
    public void setEnpasantable(boolean newEnpasantable){
        this.enpasantable = newEnpasantable;
    }
    
    //method used to get all of the possible moves that the Pawn can make
    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        //stores all of the possible moves of the pawn
        HashMap<String, String> moves = new HashMap<>();
        String move = "";
        //used to check if the current piece is white and if it is the white players turn
        if(super.getColor().equals("white") && turn.equals("white")){
            //checks if pawn can move one square forward
            move = super.getXPos() + "," + (super.getYPos()-1);
            if(!board.containsKey(move)){
                Piece removedPiece = null;
                if(board.containsKey(move)){
                    removedPiece = board.get(move);
                }
                
                addMove(move, "1 square forward", board, moves, turn, kingXPos, kingYPos);

                //checks if pawn can move two squares forward
                move = super.getXPos() + "," + (super.getYPos()-2);
                if(!board.containsKey(move) && super.getYPos() == 6){
                    removedPiece = null;
                    if(board.containsKey(move)){
                        removedPiece = board.get(move);
                    }
                    
                    //used to check if moving the spawn two squares forward will not leave the king in check
                    board.put(move, board.get(super.getXPos()+","+super.getYPos()));
                    board.remove(super.getXPos()+","+super.getYPos());
                    if(super.isKingSafe(board, turn, kingXPos, kingYPos)){    
                        moves.put(move, "2 squares forward");
                        this.enpasantable = true;
                    }
                    board.put(super.getXPos()+","+super.getYPos(), board.get(move));
                    board.remove(move);
                    if(removedPiece != null){
                        board.put(move, removedPiece);
                    }
                }
            }

            //check if pawn can enpasant to the right
            move = (super.getXPos()+1) + "," + (super.getYPos()-1);
            String enemyPawnPosition = (super.getXPos()+1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("black")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //check if pawn can capture on the right
            if(board.containsKey(move) && board.get(move).getColor().equals("black")){
                addMove(move, "captured on the right", board, moves, turn, kingXPos, kingYPos);
            }
            
            //check if pawn can enpasant to the left
            move = (super.getXPos()-1) + "," + (super.getYPos()-1);
            enemyPawnPosition = (super.getXPos()-1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("black")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //check if pawn can capture on the left
            if(board.containsKey(move) && board.get(move).getColor().equals("black")){
                addMove(move, "captured on the left", board, moves, turn, kingXPos, kingYPos);
            }
        }
        //used to check if the current piece is black and if it is the black players turn
        else if(super.getColor().equals("black") && turn.equals("black")){
            //checks to see if the pawn can move 1 square forward
            move = super.getXPos() + "," + (super.getYPos()+1);
            if(!board.containsKey(move)){
                addMove(move, move, board, moves, turn, kingXPos, kingYPos);
                //check if pawn can move two squares forward
                move = super.getXPos() + "," + (super.getYPos()+2);
                if(!board.containsKey(move) && super.getYPos() == 1){
                    Piece removedPiece = null;
                    if(board.containsKey(move)){
                        removedPiece = board.get(move);
                    }
                    //used to check if moving the spawn two squares forward will not leave the king in check
                    board.put(move, board.get(super.getXPos()+","+super.getYPos()));
                    board.remove(super.getXPos()+","+super.getYPos());
                    if(super.isKingSafe(board, turn, kingXPos, kingYPos)){
                        moves.put(move, move);
                        this.enpasantable = true;
                    }
                    board.put(super.getXPos()+","+super.getYPos(), board.get(move));
                    board.remove(move);
                    if(removedPiece != null){
                        board.put(move, removedPiece);
                    }
                }
            }

            //check if pawn can enpasant to the right
            move = (super.getXPos()+1) + "," + (super.getYPos()+1);
            String enemyPawnPosition = (super.getXPos()+1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("white")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //check if pawn can capture on the right
            if(board.containsKey(move) && board.get(move).getColor().equals("white")){
                addMove(move, "captured on the right", board, moves, turn, kingXPos, kingYPos);
            }
            
            //check if pawn can enpasant to the left
            move = (super.getXPos()-1) + "," + (super.getYPos()+1);
            enemyPawnPosition = (super.getXPos()-1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("white")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //check if pawn can capture on the left
            if(board.containsKey(move) && board.get(move).getColor().equals("white")){
                addMove(move, "captured on the left", board, moves, turn, kingXPos, kingYPos);
            }
        }
        return moves;
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