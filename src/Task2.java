import java.util.Arrays;


public class Task2 {
    public static void main(String[] args) {
        System.out.println(duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));

        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));

        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));

        System.out.println(equalToAvg(new float[]{1, 2, 3, 4, 5}));
        System.out.println(equalToAvg(new float[]{1, 2, 3, 4, 6}));


        System.out.println(Arrays.toString(indexMult(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(indexMult(new int[]{3, 3, -2, 408, 3, 31})));

        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));


        System.out.println(Tribonacci(11));

        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));

        System.out.println(botHelper("Hello, I’m under the water, please help me Calling for a staff member"));
        System.out.println(botHelper("Two pepperoni pizzas please"));

        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));

    }


    private static boolean duplicateChars(String word) {
        word = word.toLowerCase();
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            int count = 0;
            char letter = wordArray[i];
            for (int j = 0; j < wordArray.length; j++) {
                if (wordArray[j] == letter) {
                    count++;
                    if (count > 1) {
                        return true;
                    }
                }
            }

        }
        return false;

    }


    private static String getInitials(String name) {
        String[] words = name.split(" ");
        char first = words[0].charAt(0);
        char second = words[1].charAt(0);
        String initials = Character.toString(first) + Character.toString(second);
        return initials;
    }


    private static int differenceEvenOdd(int[] nums) {
        int Even_sum = 0;
        int Odd_sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num % 2 == 0) {
                Even_sum += num;
            } else {
                Odd_sum += num;
            }

        }
        return Math.abs(Even_sum - Odd_sum);

    }


    private static boolean equalToAvg(float[] nums) {
        float nums_sum = 0;
        float count = 0;
        float avg = 0;
        for (float num : nums) {
            nums_sum += num;
            count += 1;
        }
        avg = nums_sum / count;
        for (float num : nums) {
            if (num == avg) {
                return true;
            }

        }
        return false;
    }

    private static int[] indexMult(int[] nums) {
        int[] new_nums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            new_nums[i] = nums[i] * i;
        }
        return new_nums;
    }


    private static String reverse(String phrase) {
        char[] array = phrase.toCharArray();
        String result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            result = result + array[i];
        }
        return result;
    }


    private static int Tribonacci(int num) {
        //0 0 1 1 2 4 7 13 24 44 81
        int answer = 0;

        if (num == 1) {
            return 0;
        }
        if (num == 2) {
            return 0;
        }
        if (num == 3) {
            return 1;
        }

        if (num > 3) {

            for (int i = num - 1; i > num - 4; i--) {
                int addNum = Tribonacci(i);
                answer += addNum;

            }
            return answer;

        }
        return 0;
    }


    private static String pseudoHash(int num) {
        String[] dict = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        String answer = "";
        for (int i = 0; i < num; i++) {
            answer += dict[(int) Math.floor(Math.random() * dict.length)];
        }
        return answer;
    }


    private static String botHelper(String text) {
        text = text.toLowerCase();
        String[] text1 = text.split(" ");
        for (String word : text1){
            if (word.equals("help")){
                return "Calling for a staff member";
            }
        }
        return "Keep waiting";
    }


    private static boolean isAnagram(String word1, String word2){
        word1.toLowerCase();
        word2.toLowerCase();
        char[] word1_chars =  word1.toCharArray();
        Arrays.sort(word1_chars);
        char[] word2_chars =  word2.toCharArray();
        Arrays.sort(word2_chars);
        return Arrays.equals(word1_chars, word2_chars);

    }


}
