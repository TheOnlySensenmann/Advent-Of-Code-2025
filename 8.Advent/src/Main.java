import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input_example.txt");
        Scanner inputReader = new Scanner(inputFile);


        ArrayList<Position> input =  new ArrayList<>();
        while (inputReader.hasNextLine()) {
            String[] inputLine = inputReader.nextLine().split(",");
            Position p =  new Position();
            p.setX(Integer.parseInt(inputLine[0]));
            p.setY(Integer.parseInt(inputLine[1]));
            p.setZ(Integer.parseInt(inputLine[2]));
            input.add(p);
        }

        TreeSet<Relation> relations = new TreeSet<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                relations.add(new Relation(input.get(i), input.get(j)));
            }
        }



        int indexCounter = 0;
        TreeSet<Relation> newRelations = new TreeSet<>(relations);
        for (Relation relation : relations) {
            if(indexCounter>=10){
                newRelations.remove(relation);
            }
            indexCounter++;
        }
        relations = newRelations;


        ArrayList<HashSet<Position>> circuits = new ArrayList<>();


        while(!relations.isEmpty()) {
            HashSet<Position> circuit = new HashSet<>();
            circuit.add(relations.getFirst().end);
            circuit.add(relations.getFirst().start);
            while(addRelation(circuit, relations)){

            }
            circuits.add(circuit);
        }


        int i = 0;



        circuits.sort(new Comparator<HashSet<Position>>() {
            @Override
            public int compare(HashSet<Position> a, HashSet<Position> b) {
                return Integer.compare(b.size(), a.size());
            }
        });



        int res = circuits.get(0).size()*circuits.get(1).size()*circuits.get(2).size();
        System.out.println(res);

    }

    private static boolean addRelation(HashSet<Position> circuit, TreeSet<Relation> relations) {
        boolean added = false;
        TreeSet<Relation> newRelations = (TreeSet<Relation>) relations.clone();
        for(Relation relation : newRelations){
            if(isInCircuit(circuit, relation)){
                circuit.add(relation.end);
                circuit.add(relation.start);
                relations.remove(relation);
                added = true;
            }
        }
        return added;
    }

    private static boolean isInCircuit(HashSet<Position> circuit, Relation r){
        for(Position p : circuit){
            if(r.end.equals(p) || r.start.equals(p)){
                return true;
            }
        }
        return false;
    }


}