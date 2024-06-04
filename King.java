import java.util.HashMap;

public class King extends Piece{

    private boolean hasMoved;

    public King(int xPos, int yPos, String color, String image){
        super(xPos, yPos, color, image);
        this.hasMoved = false;
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

    public boolean getHasMoved(){
        return this.hasMoved;
    }

    public void setHasMoved(boolean newHasMoved){
        this.hasMoved = newHasMoved;
    }

    public void checkMoves(HashMap<String, Piece> board, HashMap<String, String> moves, String enemy, String turn, int kingXPos, int kingYPos){

        //HE CANT BE IN CHECK
        if(!hasMoved && super.isKingSafe(board, turn, kingXPos, kingYPos)){
            String move = (super.getXPos()+2) + "," + (super.getYPos());
            String positionOfRook = (super.getXPos()+3) + "," + (super.getYPos());
            if(board.containsKey(positionOfRook) && board.get(positionOfRook) instanceof Rook && !board.get(positionOfRook).getHasMoved()){
                String checkSquare1 = (super.getXPos()+1) + "," + (super.getYPos());
                String checkSquare2 = (super.getXPos()+2) + "," + (super.getYPos());
                if(!board.containsKey(checkSquare1) && !board.containsKey(checkSquare2) && super.isKingSafe(board, turn, kingXPos+1, kingYPos) && super.isKingSafe(board, turn, kingXPos+2, kingYPos)){
                    moves.put(move, "castle right");
                }
            }

            move = (super.getXPos()-2) + "," + (super.getYPos());
            positionOfRook = (super.getXPos()-4) + "," + (super.getYPos());
            if(board.containsKey(positionOfRook) && board.get(positionOfRook) instanceof Rook && !board.get(positionOfRook).getHasMoved()){
                String checkSquare1 = (super.getXPos()-1) + "," + (super.getYPos());
                String checkSquare2 = (super.getXPos()-2) + "," + (super.getYPos());
                String checkSquare3 = (super.getXPos()-3) + "," + (super.getYPos());
                if(!board.containsKey(checkSquare1) && !board.containsKey(checkSquare2) && !board.containsKey(checkSquare3) && super.isKingSafe(board, turn, kingXPos-1, kingYPos) && super.isKingSafe(board, turn, kingXPos-2, kingYPos)){
                    moves.put(move, "castle left");
                }
            }
        }

        if(super.getYPos() >= 0){
            String move = (super.getXPos()) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move up 1 square", board, moves, turn, kingXPos, kingYPos-1);
            }
        }

        if(super.getYPos() < 8){
            String move = (super.getXPos()) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move down 1 square", board, moves, turn, kingXPos, kingYPos+1);
            }
        }

        if(super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos());
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move left 1 square", board, moves, turn, kingXPos-1, kingYPos);
            }
        }

        if(super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos());
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move right 1 square", board, moves, turn, kingXPos+1, kingYPos);
            }
        }

        if(super.getYPos() >= 0 && super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move top left 1 square", board, moves, turn, kingXPos-1, kingYPos-1);
            }
        }

        if(super.getYPos() >= 0 && super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos()-1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move top right 1 square", board, moves, turn, kingXPos+1, kingYPos-1);
            }
        }

        if(super.getYPos() < 8 && super.getXPos() >= 0){
            String move = (super.getXPos()-1) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move bottom left 1 square", board, moves, turn, kingXPos-1, kingYPos+1);
            }
        }

        if(super.getYPos() < 8 && super.getXPos() < 8){
            String move = (super.getXPos()+1) + "," + (super.getYPos()+1);
            if(!board.containsKey(move) || board.get(move).getColor().equals(enemy)){
                addMove(move, "move bottom right 1 square", board, moves, turn, kingXPos+1, kingYPos+1);
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
