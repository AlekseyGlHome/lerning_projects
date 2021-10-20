import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        String[] chars = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        ArrayList<String> nomerDB = new ArrayList<>();

        for (int region = 1; region <= 199; region++) {
            for (String aChar : chars) {
                for (int j = 1; j <= 10; j++) {
                    nomerDB.add(String.format("%s%03d%s%02d", aChar, j, aChar + aChar, region));
                }
                for (int j = 111; j <= 999; j += 111) {
                    nomerDB.add(String.format("%s%03d%s%02d", aChar, j, aChar + aChar, region));
                }
            }
            for (int i = 111; i <= 999; i += 111) {
                for (String aChar : chars) {
                    for (String aChar1 : chars) {
                        for (String aChar2 : chars) {
                            nomerDB.add(String.format("%s%03d%s%02d", aChar, i, aChar1 + aChar2, region));
                        }
                    }
                }
            }
        }
        return nomerDB;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        return list.contains(number);
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        return Collections.binarySearch(sortedList, number) >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
