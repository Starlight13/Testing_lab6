import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparer {

    private String expectedCSV;
    private String resultCSV;
    private String logFile;

    public Comparer(String expectedCsv, String resultCSV, String logFile) {
        this.expectedCSV = expectedCsv;
        this.resultCSV = resultCSV;
        this.logFile = logFile;
    }

    public boolean compareCSV() {
        fileWorker.writeToFile('\n' +"==============================================", logFile);
        fileWorker.writeToFile("\nDate: " + new Date() + " \n", logFile);
        List<String> exp = fileWorker.readLines(expectedCSV);
        List<String> res = fileWorker.readLines(resultCSV);
        int unequalRows  = 0;
        boolean isEqual;

        for (int i = 0; i < exp.size(); i++) {
            if (res.size() - 1 < i) {
                break;
            } else {
                String result = diff(exp.get(i), res.get(i)).second;
                if (!result.isEmpty()) {
                    unequalRows++;
                    fileWorker.writeToFile("Difference in id "+getId(exp.get(i))+ ".\n \tExpected : " +exp.get(i)+". \n \tResult : " + res.get(i) + ".\n\tDifference: " + result, logFile);
                }
            }
        }
        int expSize = exp.size();
        exp.removeAll(res);
        if(exp.size() == 0) {
            fileWorker.writeToFile("Result is equal to expected", logFile);
            isEqual = true;
        }else {
            fileWorker.writeToFile("Some resulting rows were not as expected", logFile);
            isEqual = false;
        }

        fileWorker.writeToFile("Total rows in expected csv : " + expSize, logFile);
        fileWorker.writeToFile("Total rows in resulting csv : " + res.size(), logFile);
        fileWorker.writeToFile("Total unequal rows : " + unequalRows, logFile);
        fileWorker.writeToFile('\n' +"==============================================", logFile);
        return isEqual;
    }

    private String getId(String row) {
        StringBuffer total = new StringBuffer();
        String[] n = row.split(""); //array of strings
        for (int i = 0; i < n.length; i++) {
            if ((n[i].matches("[0-9]+"))) {// validating numbers
                total.append(n[i]); //appending
            } else return total.toString();
        }
        return total.toString();
    }

    public static Pair<String> diff(String a, String b) {
        return diffHelper(a, b, new HashMap<Long, Pair<String>>());
    }


    private static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        long key = ((long) a.length()) << 32 | b.length();
        if (!lookup.containsKey(key)) {
            Pair<String> value;
            if (a.isEmpty() || b.isEmpty()) {
                value = new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                value = diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    value = new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    value = new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
            lookup.put(key, value);
        }
        return lookup.get(key);
    }

    public static class Pair<T> {
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public final T first, second;

        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
