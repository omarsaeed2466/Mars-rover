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

    public void dropRover(Plateau plateau, Position position, Heading heading) {
        if (!position.IsOnPlateau(plateau)){
            throw new PositionNotOnPlateauException(plateau,position);
        }if (plateau.isOccupied(position)){
            throw new RuntimeException("Already occupied by a rover!");
        }
        this.plateau = plateau;
        this.position = position;
        this.heading = heading;

        plateau.addRover(this);
    }


    public void dropRover(Plateau plateau , int posX , int posY , char heading){
        dropRover(plateau,new Position(posX,posY),ToHeading(heading));
    }





         public String reportStatus(){
StringBuilder sb = new StringBuilder(name);
sb.append("");
sb.append(reportStatus());
return sb.toString();
}


    public String reportPosition() {
        if (position == null || heading == null) {
            return "Not dropped yet.";
        }
        return position.toString() + " " + FromHeading(heading);
    }


    public boolean hasPosition(Position pos) {
        return position.isEqual(pos);
    }



    public void processInstruction(Instructions[] instruction){
        for (Instructions i : instruction){
            processInstruction(i);
        }
    }
    private void processInstruction(Instructions instruction){
        if (position == null || heading == null) {
            throw new NotDroppedException();
        }

        switch (instruction) {
            case LEFT: turnLeft(); break;
            case MOVE: moveForward(); break;
            case RIGHT: turnRight(); break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void turnLeft() {
        switch (heading) {
            case EAST: heading = Heading.NORTH; break;
            case NORTH: heading = Heading.WEST; break;
            case SOUTH: heading = Heading.EAST; break;
            case WEST: heading = Heading.SOUTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void turnRight() {
        switch (heading) {
            case EAST: heading = Heading.SOUTH; break;
            case NORTH: heading = Heading.EAST; break;
            case SOUTH: heading = Heading.WEST; break;
            case WEST: heading = Heading.NORTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void moveForward() {
        Position newPosition = position.moveForward(heading);
        if (!newPosition.IsOnPlateau(plateau)) {
            throw new PositionNotOnPlateauException(plateau, newPosition);
        }
        position = newPosition;
    }
    private static Heading ToHeading(char heading) {
        switch (heading) {
            case 'N': return Heading.NORTH;
            case 'W': return Heading.WEST;
            case 'S': return Heading.SOUTH;
            case 'E': return Heading.EAST;
            default: throw new RuntimeException("Unsupported character '" + heading + "'!");
        }
    }
    private static char FromHeading(Heading heading) {
        switch (heading) {
            case NORTH: return 'N';
            case WEST: return 'W';
            case SOUTH: return 'S';
            case EAST: return 'E';
            default: throw new RuntimeException("Unsupported heading '" + heading + "'!");
        }
    }
}
