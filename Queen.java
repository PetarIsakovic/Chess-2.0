import java.util.HashMap;

public class Queen extends Piece{

    //this is the constructor for the Queen class for initializing all instance variables in the Piece
    public Queen(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
    }

    
    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        HashMap<String, String> moves = new HashMap<>();

        if(super.getColor().equals("white") && turn.equals("white")){
            checkMoves(board, moves, "black", turn, kingXPos, kingYPos);
            
        }
        else if(super.getColor().equals("black") && turn.equals("black")){
            checkMoves(board, moves, "white", turn, kingXPos, kingYPos);
        }
        return moves;
    }

    public void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){

        //bottom right
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

        //top right
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

        //bottom left
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

        //top left
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