import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Piece {

    //holds the x position of the piece
    private int xPos;
    //holds the y position of the piece
    private int yPos;
    //holds the color of the chess piece (black/white) 
    private String color;
    //holds the buffered image of the chess piece
    private BufferedImage image;

    //constructor for the Piece so that when it is initialized these parameters are required
    public Piece(int xPos, int yPos, String color, String imageFilename){
        //initializes all of the instance variables
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color; 
        //used to initialize the buffered Image based on the filename given
        try {
            this.image = ImageIO.read(new File(imageFilename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //used to get the x position of the piece
    public int getXPos(){
        return this.xPos;
    }

    //used to get the y position of the piece
    public int getYPos(){
        return this.yPos;
    }

    //used to get the color of the piece
    public String getColor(){
        return this.color;
    }

    //used to get the image of the piece
    public BufferedImage getImage(){
        return this.image;
    }

    //used to update the x position of the piece
    public void setXPos(int newXPos){
        this.xPos = newXPos;
    }

    //used to update the y position of the piece
    public void setYPos(int newYPos){
        this.yPos = newYPos;
    }

    //used to get the possible moves that the piece can make
    public HashMap<String, String> getPossibleMoves(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        return null;
    }

    //used to check if the king is in check
    public boolean isKingSafe(HashMap<String, Piece> board, String turn, int kingXPos, int kingYPos){
        //looks for a check coming from the right
        for(int i = kingXPos+1; i < 8; i++){
            String piece = i + "," + kingYPos;
            if(board.containsKey(piece)){
                if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Rook || board.get(piece) instanceof Queen)){
                    return false;
                }
                else{
                    break;
                }
            }
        }

        //looks for a check coming from the left
        for(int i = kingXPos-1; i>=0; i--){
            String piece = i + "," + kingYPos;
            if(board.containsKey(piece)){
                if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Rook || board.get(piece) instanceof Queen)){
                    return false;
                }
                else{
                    break;
                }
            }
        }

        //looks for a check coming from below
        for(int i = kingYPos+1; i < 8; i++){
            String piece = kingXPos + "," + i;
            if(board.containsKey(piece)){
                if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Rook || board.get(piece) instanceof Queen)){
                    return false;
                }
                else{
                    break;
                }
            }
        }

        //looks for a check coming from above
        for(int i = kingYPos-1; i >= 0; i--){
            String piece = kingXPos + "," + i;
            if(board.containsKey(piece)){
                if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Rook || board.get(piece) instanceof Queen)){
                    return false;
                }
                else{
                    break;
                }
            }
        }

        //looks for a check coming from the bottom right
        for(int i = kingXPos+1; i < 8; i++){
            if(kingYPos + (i-kingXPos) < 8){
                String piece = i + "," + (kingYPos+(i-kingXPos));
                if(board.containsKey(piece)){
                    if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Bishop || board.get(piece) instanceof Queen)){
                        return false;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        //looks for a check coming from the top right
        for(int i = kingXPos+1; i < 8; i++){
            if(kingYPos - (i-kingXPos) >= 0){
                String piece = i + "," + (kingYPos-(i-kingXPos));
                if(board.containsKey(piece)){
                    if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Bishop || board.get(piece) instanceof Queen)){
                        return false;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        //looks for a check coming from the bottom left
        for(int i = kingXPos-1; i >= 0; i--){
            if(kingYPos + (kingXPos-i) < 8){
                String piece = i + "," + (kingYPos+(kingXPos-i));
                if(board.containsKey(piece)){
                    if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Bishop || board.get(piece) instanceof Queen)){
                        return false;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        //looks for a check coming from the top left
        for(int i = kingXPos-1; i >= 0; i--){
            if(kingYPos - (kingXPos-i) >= 0){
                String piece = i + "," + (kingYPos-(kingXPos-i));
                if(board.containsKey(piece)){
                    if(!board.get(piece).getColor().equals(turn) && (board.get(piece) instanceof Bishop || board.get(piece) instanceof Queen)){
                        return false;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        //check for pawn checks coming from white side
        if(turn.equals("black")){
            //check for bottom right pawn
            String piece = (kingXPos+1) + "," + (kingYPos+1);
            if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Pawn){
                return false;
            }
            //check for bottom left pawn
            piece = (kingXPos-1) + "," + (kingYPos+1);
            if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Pawn){
                return false;
            }
        }
        //check for pawn checks coming from black side
        else if(turn.equals("white")){
            //check for top right pawn
            String piece = (kingXPos+1) + "," + (kingYPos-1);
            if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Pawn){
                return false;
            }
            //check for top left pawn
            piece = (kingXPos-1) + "," + (kingYPos-1);
            if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Pawn){
                return false;
            }
        }

        //check for horse checks
        String piece = (kingXPos+2) + "," + (kingYPos-1);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos+2) + "," + (kingYPos+1);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos-2) + "," + (kingYPos-1);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos-2) + "," + (kingYPos+1);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos+1) + "," + (kingYPos-2);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos+1) + "," + (kingYPos-2);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos+1) + "," + (kingYPos+2);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos-1) + "," + (kingYPos-2);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        piece = (kingXPos-1) + "," + (kingYPos+2);
        if(board.containsKey(piece) && !board.get(piece).getColor().equals(turn) && board.get(piece) instanceof Knight){
            return false;
        }
        //means the king is safe
        return true;
    }

    //used to check if the piece is enpasantable
    public boolean isEnpasantable(){
        return false;
    }

    //used to update if the piece can be enpasanted
    public void setEnpasantable(boolean newEnpasantable){
        
    }

    //used to check if a piece has been moved
    public boolean getHasMoved(){
        return true;
    }
    //used to update weather or not a piece has been moved
    public void setHasMoved(boolean newHasMoved){
    }
    
}
