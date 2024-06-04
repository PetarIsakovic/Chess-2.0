import java.util.HashMap;

public class Pawn extends Piece{
    //used to check if the pawn can be enpasanted
    private boolean enpasantable;

    public Pawn(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
        this.enpasantable = false;
    }

    public boolean isEnpasantable(){
        return this.enpasantable;
    }

    public void setEnpasantable(boolean newEnpasantable){
        this.enpasantable = newEnpasantable;
    }

    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        HashMap<String, String> moves = new HashMap<>();
        String move = "";
        if(super.getColor().equals("white") && turn.equals("white")){
            //check if pawn can move one square forward
            move = super.getXPos() + "," + (super.getYPos()-1);
            if(!board.containsKey(move)){
                Piece removedPiece = null;
                if(board.containsKey(move)){
                    removedPiece = board.get(move);
                }
                
                addMove(move, "1 square forward", board, moves, turn, kingXPos, kingYPos);

                //check if pawn can move two squares forward
                move = super.getXPos() + "," + (super.getYPos()-2);
                if(!board.containsKey(move) && super.getYPos() == 6){
                    removedPiece = null;
                    if(board.containsKey(move)){
                        removedPiece = board.get(move);
                    }
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

            //capture on the right
            if(board.containsKey(move) && board.get(move).getColor().equals("black")){
                addMove(move, "captured on the right", board, moves, turn, kingXPos, kingYPos);
            }
            
            //check if pawn can enpasant to the left
            move = (super.getXPos()-1) + "," + (super.getYPos()-1);
            enemyPawnPosition = (super.getXPos()-1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("black")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //capture on the left
            if(board.containsKey(move) && board.get(move).getColor().equals("black")){
                addMove(move, "captured on the left", board, moves, turn, kingXPos, kingYPos);
            }
            
        }
        else if(super.getColor().equals("black") && turn.equals("black")){
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

            //capture on the right
            if(board.containsKey(move) && board.get(move).getColor().equals("white")){
                addMove(move, "captured on the right", board, moves, turn, kingXPos, kingYPos);
            }
            
            //check if pawn can enpasant to the left
            move = (super.getXPos()-1) + "," + (super.getYPos()+1);
            enemyPawnPosition = (super.getXPos()-1) + "," + super.getYPos();
            if(!board.containsKey(move) && board.containsKey(enemyPawnPosition) && board.get(enemyPawnPosition).isEnpasantable() && board.get(enemyPawnPosition).getColor().equals("white")){
                addMove(move, enemyPawnPosition + ":enpasanted", board, moves, turn, kingXPos, kingYPos);
            }

            //capture on the left
            if(board.containsKey(move) && board.get(move).getColor().equals("white")){
                addMove(move, "captured on the left", board, moves, turn, kingXPos, kingYPos);
            }
        }
        return moves;
    }

    public void addMove(String move, String typeOfMove, HashMap<String, Piece> board, HashMap<String, String> moves, String turn, int kingXPos, int kingYPos){
        Piece removedPiece = null;
        if(board.containsKey(move)){
            removedPiece = board.get(move);
        }
        board.put(move, board.get(super.getXPos()+","+super.getYPos()));
        board.remove(super.getXPos()+","+super.getYPos());
        if(super.isKingSafe(board, turn, kingXPos, kingYPos)){
            moves.put(move, typeOfMove);
        }
        board.put(super.getXPos()+","+super.getYPos(), board.get(move));
        board.remove(move);
        if(removedPiece != null){
            board.put(move, removedPiece);
        }
    }
}