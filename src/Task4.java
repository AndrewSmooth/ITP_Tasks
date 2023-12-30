import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Task4 {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(generateBrackets(1));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(binarySystem(3));
        System.out.println(binarySystem(4));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(countLetters("aaabbcdd"));
        System.out.println(countLetters("vvvvaajaaaaa"));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(convertToNum(("eight")));
        System.out.println(convertToNum(("five hundred sixty seven")));
        System.out.println(convertToNum(("thirty one")));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));

        System.out.println("-------------------------------");
        System.out.println();



        int[][] input1 = {{1, 3, 1},{1, 5, 1},{4, 2, 1}};
        System.out.println(shortestWay(input1));// ➞ 7   1+3+1+1+1=7

        int[][] input2 = {{2, 7, 3},{1, 4, 8},{4, 5, 9}};
        System.out.println(shortestWay(input2));// ➞ 21

        System.out.println("-------------------------------");
        System.out.println();


        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        System.out.println("-------------------------------");
        System.out.println();

        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));



    }
public static String nonRepeatable(String str) {
    if (str.length() == 0) {
        return "";
    }
    char firstChar = str.charAt(0);

    String remaining = nonRepeatable(str.substring(1)).replaceAll(String.valueOf(firstChar), "");
    return firstChar + remaining;
}

    private static void addBracket(ArrayList list, int leftRem, int rightRem, char[] str, int count) {
        if (leftRem < 0 || rightRem < leftRem) return; // некорректное состояние

        if (leftRem == 0 && rightRem == 0) { //нет больше левых скобок
            String s = String.copyValueOf(str);
            list.add(s);
        } else {
            // Добавляем левую скобку, если остались любые левые скобки
            if (leftRem > 0) {
                str[count] = '(';
                addBracket(list, leftRem - 1, rightRem, str, count + 1);
            }

            // Добавляем правую скобку, если выражение верно
            if (rightRem > leftRem) {
                str[count] = ')';
                addBracket(list, leftRem, rightRem - 1, str, count + 1);
            }
        }
    }

    private static ArrayList generateBrackets(int count) {
        char[] str = new char[count * 2];
        ArrayList list = new ArrayList();
        addBracket(list, count, count, str, 0);
        return list;
    }


    private static void findBinarySystem(int n, ArrayList<String> result, String currentString) {
        if (n == 0) {
            result.add(currentString);
        }
        if ((n > 0) && (currentString.length() == 0)) {
            findBinarySystem(n - 1, result, currentString + "0");
            findBinarySystem(n - 1, result, "1");
        } else if ((n > 0) && (String.valueOf(currentString.charAt(currentString.length() - 1)).equals("1"))) {
            findBinarySystem(n - 1, result, currentString + "0");
            findBinarySystem(n - 1, result, currentString + "1");
        } else if ((n > 0) && (String.valueOf(currentString.charAt(currentString.length() - 1)).equals("0"))) {
            findBinarySystem(n - 1, result, currentString + "1");
        }
    }

    private static ArrayList<String> binarySystem(int num){
        ArrayList<String> result = new ArrayList<String>();
        findBinarySystem(num, result, "");
        return result;
    }


    private static String alphabeticRow(String word) {
        String maxLenString = "";
        String currentMaxString = "";
        String currentMinString = "";
        boolean isIncreasing;
        for (int i = 0; i < word.length() - 1; i++) {
            char left = word.charAt(i);
            char right = word.charAt(i + 1);
            int leftNum = Character.getNumericValue(left);
            int rightNum = Character.getNumericValue(right);

            if (Math.abs(leftNum - rightNum)==1) { //уже есть проверка на алфавитный порядок
                isIncreasing = leftNum < rightNum;

                if (isIncreasing) {
                    currentMaxString += left;
                } else currentMinString += left;

                if (i == word.length() - 2) {
                    currentMaxString += right;
                    currentMinString += right;
                    if (currentMaxString.length() > maxLenString.length()){
                        maxLenString = currentMaxString;
                    }
                    if(currentMinString.length() > maxLenString.length()){
                        maxLenString = currentMinString;
                    }
                }
            }

            else {
                if (currentMaxString.length() != 0){
                    currentMaxString += left;
                }
                if (currentMinString.length() != 0){
                    currentMinString += left;
                }

                if (currentMaxString.length() > maxLenString.length()){
                    maxLenString = currentMaxString;
                }
                if(currentMinString.length() > maxLenString.length()){
                    maxLenString = currentMinString;
                }
                currentMaxString = "";
                currentMinString = "";
            }


//            System.out.println("left: " + left + leftNum + " right: " + right + rightNum);
//            System.out.println("currentMaxString: " + currentMaxString);
//            System.out.println("currentMinString: " + currentMinString);
//            System.out.println("MaxLenString: " + maxLenString);
//            System.out.println();
        }
        return maxLenString;
    }

    private static String countLetters(String word) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                result.append(word.charAt(i - 1));
                result.append(count);
                count = 1;
            }
        }

        result.append(word.charAt(word.length() - 1));
        result.append(count);

        for (int i = 1; i < result.length() - 2; i += 2) {
            for (int j = i + 2; j < result.length(); j += 2) {
                if (result.charAt(j) < result.charAt(i) || (result.charAt(j) == result.charAt(i) &&
                        result.charAt(j - 1) < result.charAt(i - 1))) {
                    char tempChar = result.charAt(i-1);
                    int tempCount = Integer.parseInt(String.valueOf(result.charAt(i)));

                    result.setCharAt(i-1, result.charAt(j-1));
                    result.setCharAt(i, result.charAt(j));
                    result.setCharAt(j-1, tempChar);
                    result.setCharAt(j, (char)(tempCount + '0'));
                }
            }
        }
        return result.toString();
    }


    private static int convertToNum(String inputStr) {
        String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] tenToNineteen = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] hundredsAndThousands = {"hundred", "thousand"};

        int result = 0;
        int currentResult = 0;

        String[] words = inputStr.split(" ");

        for (String word : words) {
            for (int i = 0; i < digits.length; i++) {
                if (word.equals(digits[i])) {
                    currentResult += i;
                    break;
                }
            }

            for (int i = 1; i < tens.length; i++) {
                if (word.equals(tens[i])) {
                    currentResult += i * 10;
                    break;
                }
            }

            for (int i = 0; i < tenToNineteen.length; i++) {
                if (word.equals(tenToNineteen[i])) {
                    currentResult += i + 10;
                    break;
                }
            }

            for (int i = 0; i < hundredsAndThousands.length; i++) {
                if (word.equals(hundredsAndThousands[i])) {
                    if (i == 0){
                        result += currentResult * 100;
                    }
                    else if (i == 1){
                        result += currentResult * 1000;
                    }
                    currentResult = 0;
                    break;
                }
            }
        }
        result += currentResult;
        return result;
    }


    private static String uniqueSubstring(String numStr){
        StringBuilder currentString = new StringBuilder();
        String maxString = "";

        for (int i = 1; i < numStr.length(); i++){
            if (numStr.charAt(i) == numStr.charAt(i-1) || currentString.indexOf(String.valueOf(numStr.charAt(i))) != -1){
                if (currentString.indexOf(String.valueOf(numStr.charAt(i-1))) == -1){
                    currentString.append(numStr.charAt(i-1));
                }

                if (currentString.length() > maxString.length()) {
                    maxString = currentString.toString();
                }

                currentString.delete(0, currentString.length());
               }
            else {
                if (currentString.indexOf(String.valueOf(numStr.charAt(i-1))) == -1){
                    currentString.append(numStr.charAt(i-1));
                }

            }
            }
        return maxString;
    }


    public static void countShortestWay(int[][] inputWay, HashMap<String, Integer> resultMap, int curX, int curY, int curLen){
        int n = inputWay.length - 1;//записываем n
        //inputWay[curY][curX] в начале они 0 0
        if((curX==0)&&(curY==0)){//если только запустили метод и curLen не указан
            curLen = inputWay[0][0];
        }
        if((curX==n)&&(curY==n)){
            int curRes = resultMap.get("res");
            curRes = curRes > curLen? curLen : curRes;
            resultMap.put("res",curRes);
            return;
        }
        if(curX==n){
            countShortestWay(inputWay,resultMap,curX,(curY+1),(curLen+inputWay[curY+1][curX])); //curLen меняется при вызове следующего метода в рекурсии
        } else if (curY==n){
            countShortestWay(inputWay,resultMap,(curX+1),curY,(curLen+inputWay[curY][curX+1]));
        } else {
            countShortestWay(inputWay,resultMap,(curX+1),curY,(curLen+inputWay[curY][curX+1]));
            countShortestWay(inputWay,resultMap,curX,(curY+1),(curLen+inputWay[curY+1][curX]));
        }

    }


    private static int shortestWay(int[][] input) {
        HashMap<String, Integer> shortestWayMap = new HashMap<>();
        shortestWayMap.put("res", 10000000);
        countShortestWay(input, shortestWayMap, 0,0,0);
        return shortestWayMap.get("res");
    }


    private static String numericOrder(String text){
       String[] words = text.split(" ");
       String[] words_sorted = new String[words.length];
       String answer = "";

       for (String word : words){
           for (int i = 0; i < word.length(); i++){
               if (Character.isDigit(word.charAt(i))){
                   int num = Character.getNumericValue(word.charAt(i));
                   words_sorted[num-1] = word.substring(0, i) + word.substring(i+1, word.length());
                   break;
               }
           }
       }

       for (String el : words_sorted){
           answer += el + " ";
       }

        return answer;
    }


    public static int switchNums(int num1, int num2) {
        StringBuilder resultString = new StringBuilder();

        for(int i = 0; i < String.valueOf(num2).length(); i++){
            char curChar = String.valueOf(num2).charAt(i);
            int curInt = Integer.parseInt(Character.toString(curChar));

            char maxDigit = '0'; // Переменная для хранения максимальной цифры из num1

            for (int j = 0; j < String.valueOf(num1).length(); j++) {//ищем максимальную цифру
                char currentChar = String.valueOf(num1).charAt(j);
                int currentDigit = Character.getNumericValue(currentChar);
                if (currentDigit > Character.getNumericValue(maxDigit)) {
                    maxDigit = currentChar;
                }
            }
            //проверяем больше ли она, чем та что в num2, если нет, ничего не меняем
            //Если да, заменяем её в num2 и убираем из num1
            if(curInt < Character.getNumericValue(maxDigit)){
                resultString.append(maxDigit);
                num1 = Integer.valueOf(String.valueOf(num1).replaceFirst(String.valueOf(maxDigit), "")); // Удаляем максимальную цифру из строки
            } else {
                resultString.append(curChar);
            }
        }
        return (int) Integer.valueOf(resultString.toString());
    }
}








