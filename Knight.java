import java.util.HashMap;

public class Knight extends Piece{
    public Knight(int xPos, int yPos, String color, String image){
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