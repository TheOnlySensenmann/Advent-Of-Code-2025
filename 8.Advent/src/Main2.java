import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main2{
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
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
        TreeSet<Relation> afterOneThousandRelations = new TreeSet<>(relations);
        TreeSet<Relation> newRelations = new TreeSet<>(relations);
        for (Relation relation : relations) {
            if(indexCounter>=1000){
                newRelations.remove(relation);
                afterOneThousandRelations.add(relation);
            }
            indexCounter++;
        }


        ArrayList<HashSet<Position>> circuits = new ArrayList<>();


        while(!newRelations.isEmpty()) {
            HashSet<Position> circuit = new HashSet<>();
            circuit.add(newRelations.getFirst().end);
            circuit.add(newRelations.getFirst().start);
            while(addRelation(circuit, newRelations, input)){

            }
            circuits.add(circuit);
        }
        for(Position p: input){
            HashSet<Position> temp = new HashSet<>();
            temp.add(p);
            circuits.add(temp);
        }





        circuits.sort((a, b) -> Integer.compare(b.size(), a.size()));



        Relation last = new Relation();
        while(circuits.size()!=1){
            for(Relation relation: afterOneThousandRelations){
                if(merge(relation, circuits)){
                    last = relation;
                }
            }

            System.out.println((long) last.end.getX()*last.start.getX());
        }
        System.out.println((long)last.end.getX()*last.start.getX());







    }

    private static boolean merge(Relation relation, ArrayList<HashSet<Position>> circuits){
            for(int i = 0; i < circuits.size(); i++){
                for(int j = 0; j < circuits.size(); j++){
                    if(i == j){
                        continue;
                    }
                    if(circuits.get(i).contains(relation.end) && circuits.get(j).contains(relation.start)){
                        circuits.get(i).addAll(circuits.get(j));
                        circuits.remove(j);
                        return true;
                    }
                }
            }
        return false;
    }

    private static boolean addRelation(HashSet<Position> circuit, TreeSet<Relation> relations, ArrayList<Position> input) {
        boolean added = false;
        TreeSet<Relation> newRelations = (TreeSet<Relation>) relations.clone();
        for(Relation relation : newRelations){
            if(isInCircuit(circuit, relation)){
                circuit.add(relation.end);
                circuit.add(relation.start);
                relations.remove(relation);
                input.remove(relation.end);
                input.remove(relation.start);
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