public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] lineArr = line.split(",?\\s+");
        String[] result = ReverseArray.reverse(lineArr);
        for(String word:result){
            System.out.println(word);
        }
    }
}
