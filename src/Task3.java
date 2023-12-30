import java.util.ArrayList;
import java.util.Arrays;


public class Task3 {
    public static void main(String[] args) {
        //1
        System.out.println(replaceVovels("apple"));
        System.out.println(replaceVovels("Even if you did this task not by yourself, you have to understand every single line of code"));
        System.out.println("---------------------------");
        //2
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println("---------------------------");
        //3
        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1));
        System.out.println("---------------------------");
        //4
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));
        System.out.println("---------------------------");
        //5
        System.out.println(countRoots(new int[]{1, -3, 2}));
        System.out.println(countRoots(new int[]{2, 5, 2}));
        System.out.println(countRoots(new int[]{1, -6, 9}));
        System.out.println("---------------------------");
        //6
        String[][] products_shops_1 = {{"Apple", "Shop1", "Shop2", "Shop3", "Shop4"}, {"Banana", "Shop2", "Shop3", "Shop4"}, {"Orange", "Shop1", "Shop3", "Shop4"}, {"Pear", "Shop2", "Shop4"}};
        String[][] products_shops_2 = {{"Fridge", "Shop2", "Shop3"}, {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"}, {"Laptop", "Shop3", "Shop4"}, {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}};
        System.out.println(Arrays.toString(salesData(products_shops_1)));
        System.out.println(Arrays.toString(salesData(products_shops_2)));
        System.out.println("---------------------------");
        //7
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println("---------------------------");
        //8
        System.out.println(waveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[]{1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[]{1, 2, -6, 10, 3}));

        System.out.println("---------------------------");
        //9
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));
        System.out.println("---------------------------");
        //10
        int[][] matrix1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 29, 10}, {5, 5, 5, 5, 35}, {7, 4, 3, 14, 2}, {1, 0, 11, 10, 1}};
        int[][] matrix2 = {{6, 4, 19, 0, 0}, {81, 25, 3, 1, 17}, {48, 12, 60, 32, 14}, {91, 47, 16, 65, 217}, {5, 73, 0, 4, 21}};

        //Вывод
        for (int i = 0; i < dataScience(matrix1).length; i++) {
            System.out.println(Arrays.toString(dataScience(matrix1)[i]));
        }

        System.out.println();

        for (int i = 0; i < dataScience(matrix2).length; i++) {
            System.out.println(Arrays.toString(dataScience(matrix2)[i]));
        }
    }


    private static String replaceVovels(String text){
        text = text.toLowerCase();
        String[] vovelsArray = {"a", "e", "i", "o", "u", "y"};
        for (String vovel : vovelsArray){
            text = text.replace(vovel, "*");
        }
        return text;
    }


    private static String stringTransform(String text) {
        String newText = "";
        for (int i = 0; i < text.length()-1; i++){
            char letter1, letter2;
            letter1 = text.charAt(i);
            letter2 = text.charAt(i+1);
//            System.out.println(letter1);
//            System.out.println(letter2);

            if (letter1 == letter2){
                newText += "Double" + Character.toUpperCase(letter1);
                i++;
                }

            else {
                newText += letter1;
            }
        }
        char lastLetter1, lastLetter2;
        lastLetter1 = text.charAt(text.length()-1);
        lastLetter2 = text.charAt(text.length()-2);

        if (lastLetter1 != lastLetter2){
            newText += lastLetter1;
        }
        return newText;
    }


    private static boolean doesBlockFit(int a, int b, int c, int w, int h){
        return (a * b <= w * h || b * c <= w * h || a * c <= w * h);
    }


    private static boolean numCheck(int num){
        int summ = 0;
        String strNum = Integer.toString(num);
        for (int i = 0; i < strNum.length(); i++){
            int intDigit = Character.getNumericValue(strNum.charAt(i));
            summ += intDigit * intDigit;
        }
        return (summ % 2 == num % 2);
    }


    private static int countRoots(int[] nums){
        int count = 0;
        double D = nums[1]*nums[1] - 4 * nums[0] * nums[2] * 1.0;
        double x1 = (-1 * nums[1] + Math.sqrt(D))/(2.0*nums[0]);
        double x2 = (-1 * nums[1] - Math.sqrt(D))/(2.0*nums[0]);
        if (D > 0) {
            if (x1 % 1 == 0) {
                count++;
            }
            if (x2 % 1 == 0) {
                count++;
            }
        }
        else if (D == 0){
            if (x1 % 1 == 0){
                count++;
            }
        }
        return count;
    }


    private static String[] salesData(String[][] products_shops) {
        ArrayList<String> products = new ArrayList<String>();
        int max_count = 0;
        for (String[] product_shop : products_shops){
            if (product_shop.length-1 > max_count){
                if (max_count != 0) {
                    products.clear();
                }
                max_count = product_shop.length - 1;
                products.add(product_shop[0]);
            }
            else if (product_shop.length-1 == max_count) {
                products.add(product_shop[0]);
            }
        }
        String[] answer = new String[products.size()];
        for (int i = 0; i < products.size(); i++){
            answer[i] = products.get(i);
        }
        return answer;
    }


    private static boolean validSplit(String text){
        boolean flag = false;
        int fallCount = 0;
        String[] words = text.split(" ");
        char[] starts = new char[words.length];
        char[] ends = new char[words.length];

        for (int s = 0; s < words.length; s++){
            starts[s] = words[s].charAt(0);
            ends[s] = words[s].charAt(words[s].length()-1);
        }
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(starts));
        System.out.println(Arrays.toString(ends));
        for (int i = 0; i < words.length; i++){
            for (int j = 0; j < ends.length; j++){
                if (ends[j] == starts[i]){
//                    System.out.println(ends[j]);
//                    System.out.println(starts[i]);
                    ends[j] = ' ';
                    starts[i] = ' ';
                    flag = true;
                    break;
                }
            }
            if (!flag){
                fallCount++;
            }
        }

        System.out.println(fallCount);

        return (fallCount <= 1 ? true : false);
    }


    private static boolean waveForm(int[] nums){
        String current, previous = null;
        int num1, num2;
        for (int i = 0; i < nums.length-1; i++){
            num1 = nums[i];
            num2 = nums[i+1];

            current = num2 > num1 ? "up" : "down";

            if (previous == current){
                return false;
            }
            previous = current;
        }
        return true;
    }


    private static char commonVovel(String text){
        text = text.toLowerCase();
        int max_count = 0;
        int count;
        char max_vovel = ' ';
        char[] vovels = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (char vovel : vovels){
            count = 0;
            for (int i = 0; i < text.length(); i++){
                if (text.charAt(i) == vovel){
                    count++;
                }
            }
            if (count > max_count){
                max_count = count;
                max_vovel = vovel;
            }
        }
        return max_vovel;
    }


    private static int[][] dataScience(int[][] matrix){
        int[][] newMatrix = matrix;
        int summ;

        for (int i = 0; i < matrix[0].length; i++){
            summ = 0;

            for (int j = 0; j < matrix[0].length; j++){
                if (j != i) {
                    summ += matrix[j][i];
                    //System.out.println("+ " + matrix[j][i]);
                }
            }
            //System.out.println("summ: " + summ);
            newMatrix[i][i] = (int) Math.round(1.0*summ/(matrix[0].length-1));
        }

        return newMatrix;
    }
}