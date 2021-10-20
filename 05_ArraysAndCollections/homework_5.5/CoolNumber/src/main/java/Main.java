import java.util.*;

public class Main {


    public static void main(String[] args) {
        List<String> nomers = CoolNumbers.generateCoolNumbers();
        TreeMap<Long,String> findSpeed = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String findNomer = scanner.nextLine();

            if (findNomer.equals("QUIT")){
                break;
            }
            long nanoStart = System.nanoTime();
            boolean isFindNomer = CoolNumbers.bruteForceSearchInList(nomers, findNomer);
            long nanoStop = System.nanoTime();
            if (isFindNomer) {
                print("Поиск перебором найден, поиск занял", nanoStop - nanoStart);
            } else {
                print("Поиск перебором ненайден, поиск занял", nanoStop - nanoStart);
            }
            findSpeed.put(nanoStop - nanoStart,"Поиск перебором");

            Collections.sort(nomers);
            nanoStart = System.nanoTime();
            isFindNomer = CoolNumbers.binarySearchInList(nomers, findNomer);
            nanoStop = System.nanoTime();
            if (isFindNomer) {
                print("Бинарный поиск найден, поиск занял", nanoStop - nanoStart);
            } else {
                print("Бинарный поиск ненайден, поиск занял", nanoStop - nanoStart);
            }
            findSpeed.put(nanoStop - nanoStart,"Бинарный поиск");

            HashSet<String> hashSet = new HashSet<>(nomers);
            nanoStart = System.nanoTime();
            isFindNomer = CoolNumbers.searchInHashSet(hashSet, findNomer);
            nanoStop = System.nanoTime();

            if (isFindNomer) {
                print("Поиск по HashSet найден, поиск занял", nanoStop - nanoStart);
            } else {
                print("Поиск по HashSet ненайден, поиск занял", nanoStop - nanoStart);
            }
            findSpeed.put(nanoStop - nanoStart,"Поиск по HashSet");

            TreeSet<String> treeSet = new TreeSet<>(nomers);
            nanoStart = System.nanoTime();
            isFindNomer = CoolNumbers.searchInTreeSet(treeSet, findNomer);
            nanoStop = System.nanoTime();

            if (isFindNomer) {
                print("Поиск по TreeSet найден, поиск занял", nanoStop - nanoStart);
            } else {
                print("Поиск по TreeSet ненайден, поиск занял", nanoStop - nanoStart);
            }
            findSpeed.put(nanoStop - nanoStart,"Поиск по TreeSet");

            for (Long l:findSpeed.keySet()){
                print("Самый быстрый поиск: "+findSpeed.get(l), l);
                break;
            }
        }
    }


    static void print(String mess, long time) {
        System.out.println(mess + " " + time + " нс");
    }
}
