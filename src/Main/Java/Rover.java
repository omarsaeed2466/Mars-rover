package Main.Java;

public class Rover {
    private Position position ;
    private Plateau plateau ;
    private Heading heading ;
    private String name ;

    public Rover(String name) {
        this.name = name;
    }
    public void dropRover(Plateau plateau , String args){
        String[] parts = args.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Heading heading = ToHeading(parts[2].toCharArray()[0]);
        dropRover(plateau,new Position(x,y),heading);
    }

    private void dropRover(Plateau plateau, Position position, Heading heading) {
        if (!position.IsOnPlateau(plateau)){
            throw new PositionNotOnPlateauException(plateau,position);
        }if (plateau.)
    }
}
