import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Task6 {

    public static void main (String[] args) {


        //1
        System.out.println("1----------------");
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth") );


        //2
        System.out.println();
        System.out.println("2----------------");
        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));

        //3
        System.out.println();
        System.out.println("3----------------");
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh") );
        System.out.println(nicoCipher("mubashirhassan", "crazy"));

        //4
        System.out.println();
        System.out.println("4----------------");
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));

        //5
        System.out.println();
        System.out.println("5----------------");
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));

        //6
        System.out.println();
        System.out.println("6----------------");
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));

        //7
        System.out.println();
        System.out.println("7----------------");
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));

        //8
        System.out.println();
        System.out.println("8----------------");
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)"));
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));
        System.out.println(generateNonconsecutive("(3) + (5 + (10))"));
        System.out.println(generateNonconsecutive("1 / 0"));
        System.out.println(generateNonconsecutive("1 + *"));
        System.out.println(generateNonconsecutive("12 + () + 6"));
        System.out.println(generateNonconsecutive("850 + (980 * ( 650)))"));
        System.out.println(generateNonconsecutive("oh no, I am probably not a math expression"));
        System.out.println(generateNonconsecutive(""));

        //9
        System.out.println();
        System.out.println("9----------------");
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println(isValid("aabbcc"));

        //10
        System.out.println();
        System.out.println("10----------------");
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
    }


    //1
    public static String hiddenAnagram (String text, String temp) {

        StringBuilder ntemp = new StringBuilder();
        temp = temp.toLowerCase();
        for (int i = 0; i < temp.length(); i++) { //Записываем все буквы из второй строчки
            char c = temp.charAt(i);
            if ('a' <= c && c <= 'z') {
                ntemp.append(c);
            }
        }
        temp = ntemp.toString();

        int len = temp.length();
        int n = text.length();

        StringBuilder ntext = new StringBuilder();
        text = text.toLowerCase();
        for (int i = 0; i < n; i++) { //Записываем все буквы из первой строчки
            char c = text.charAt(i);
            if ('a' <= c && c <= 'z') {
                ntext.append(c);
            }
        }
        text = ntext.toString();
        //System.out.println(text);
        n = text.length();

        int[] tempCount = new int[26];
        for (int i = 0; i < len; i++) { //Записываем в массив сколько раз во 2 строке встречается каждая буква
            int ind = temp.charAt(i) - 'a';
            if (0 <= ind && ind < 26) {
                tempCount[ind] += 1;
            }
        }
        String ans = "notfound";
        for (int l = 0; l + len <= n; l++) { //записываем в массив сколько раз в 1 строке встречается каждая буква
            int r = l + len; //r - количество букв в 1 строке. l - разница кол бувкв в строках
//            System.out.println(text.substring(l, r));
//            System.out.println(temp);
            int[] rayCount = new int[26];
            for (int j = l; j < r; j++) {
                int ind = text.charAt(j) - 'a';
                rayCount[ind] += 1;
            }

            boolean isAns = true;
            for (int i = 0; i < 26; i++) { //проверяем, что все буквы второй строки содержатся в первой
                if (tempCount[i] != rayCount[i]) {
                    isAns = false;
                    //System.out.println((char)(i+65));
                }
            }
            if (isAns) {
                ans = text.substring(l, r); //Сохраняем подходящую нам подстроку из первой строки
                break;
            }
        }

        return ans;
    }


    //2
    public static ArrayList<String> collect (String a, int p) {
        if (a.length() < p) {
            return new ArrayList<String>(); //Если строка меньше указанной длины, возвращаем пустой массив. Это одно из требований
        }
        else {
            ArrayList<String> f = collect(a.substring(p, a.length()), p); //рекурсивно вызываем функцию, в которую передаём обрезанную строку
            f.add(a.substring(0, p));
            Collections.sort(f); //сортируем
            return f;
        }
    }


    //3
    public static String nicoCipher (String message, String key) {

        char[] copyKey = key.toCharArray(); //сортируем ключ
        Arrays.sort(copyKey);

        int cnt = 0;
        // Проверяем,что в ключе все символы различны
        HashMap<Character, Integer> mp = new HashMap<>();
        for (char c : copyKey) {
            if (!mp.containsKey(c)) {
                mp.put(c, cnt);
                cnt++;
            }
            else {
                return "Key is not valid";
            }
        }

        int[] positions = new int[key.length()]; //mp - хэшмап со всеми символами ключа
//        System.out.println(positions.length);
        for (int i = 0; i < key.length(); i++) { //key - несортированный ключ
            positions[i] = mp.get(key.charAt(i)); //назначаем номера буквам из ключа
        }


        ArrayList<ArrayList<Character> > cipher = new ArrayList<ArrayList<Character> >(); //создаем массив с массивами
        for (int i = 0; i < positions.length; i++) {
            cipher.add(new ArrayList<>());
        }

        int new_length = (message.length() / key.length() + 1) * (key.length()); //Новая длина закодированного слова (кол-во строк * кол. столбцов)
        int new_size = new_length / (key.length()); //кол-во строк
        //System.out.println(new_size);

        for (int i = 0; i < new_length; i++) { //
            if (i < message.length()) {
                cipher.get(positions[i % positions.length]).add(message.charAt(i)); //каждому элементу "таблицы" соответствует символ сообщения
            }
            else {
                cipher.get(positions[i % positions.length]).add(' '); //если буквы закончились, добавляем пустую строку
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < new_size; i++) { //сортируем столбцы по назначенным номерам и выводим ответ
            for (int j = 0; j < positions.length; j++) {//i - строки, j - столбцы
                ans.append(cipher.get(j).get(i));
            }
        }

        return ans.toString();
    }


    //4
    public static int[] twoProduct(int[] array, int num) {
        int gap = 100000000;
        int[] out = new int[2];
        for (int i = 0; i < array.length; i++) { //ищем подходящие нам пары
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] * array[j] == num) {//Если произвдение равно числу, подходит
                    if (j - i < gap) { //учитываем, что промежуток минимален
                        out[1] = array[j];
                        out[0] = array[i];
                        gap = j - i;
                    }
                }
            }
        }
        if (out[0] == 0) {
            return new int[0]; //Если нет совпадений - возвращаем пустой массив
        } else {
            return out;
        }
    }


    //5
    public static int[] isExact(int num) {
        if (num == 2) {
            return new int[] {2, 2}; //2 - минимальная граница и минимальный делитель
        }
        for (int i = num / 2; i > 2; i--) {
            if (num % i == 0) { // Проверяем все делители num
                int[] array = isExact(num/ i); //проверка второго делителя на верхнюю границу
//                System.out.println(Arrays.toString(array));
//                System.out.println(num/i);
                if (Arrays.equals(array, new int[0])) {//в таком случае массив не должен быть пустой
                    continue;
                }
                if (array[1] == i - 1) { //Если первый делитель больше то возвращаем первый делитель
                    return new int[] {num, i};
                }
            }
        }
        return new int[0];
    }


    //6
    public static long gcd (long a, long b) { //НОД
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

    public static String fractions (String s) {
        String[] x = s.split("\\("); // "(." - специальные символы в регулярных выражениях. Перед ними ставим "\\"
        String[] xx = x[0].split("\\."); // x - первая часть числа с точкой, вторая без. xx - первое число без точки
        //x[0] - число без периода
        //x[1] - период
        //xx[0] - целая часть числа без периода
        //xx[1] - дробная часть числа без периода

        //System.out.println("**********************");
        //System.out.println("x: " + Arrays.toString(x));

        String x0 = xx[0];    //x0 - целая часть числа без периода
        String x1;
        if (xx.length == 1) { //Если дробной части нет, то x1=0
            x1 = "0";
        }
        else {                //Если есть, то x1 - дробная часть числа без периода
            x1 = xx[1];
        }
        String x2 = x[1].substring(0, x[1].length()-1); // x2 - период

        StringBuilder val1 = new StringBuilder();

        for (int i = 0; i < x2.length(); i++) { //Составляем строку из 9 и 0.
                                                // 9 - кол.во цифр в периоде
            val1.append("9");                   // 0 - кол.во цифр в дробной части числа без периода
        }
        for (int i = 0; i < x[0].length()-2; i++) {
            val1.append("0");
        }

        //System.out.println("val1: " + val1);

        long val1Int = Long.parseLong(val1.toString()); //Переводим получившуюся строку в число
        long val2Int = (long) Math.pow(10, x1.length());  //10 в степени кол-ва цифр в дробной части числа без периода

        //System.out.println("val2Int: " + val2Int);
        //0.19 * 999900 * 100 + 19 * 999900 + 2367 * 100
        long A = Long.parseLong(x0) * val1Int * val2Int + Long.parseLong(x1) * val1Int + Long.parseLong(x2) * val2Int;
        long B = val1Int * val2Int;

//        System.out.println(A + " " + B);

        long g = gcd(A, B); //Находим нод 2х чисел и делим
        A /= g;//числитель
        B /= g;//знаменатель

        return Long.toString(A) + "/" + Long.toString(B);
    }


    //7
    public static String pilish_string (String s) {
        StringBuilder cur, ans = new StringBuilder();
        String pi = "314159265358979";
        char[] pis = pi.toCharArray();
        int[] pisInts = new int[pis.length];
        for (int i = 0; i < pis.length; i++) { //заполняем массив с числами
            pisInts[i] = pis[i] - '0';
        }

        int cnt = 0;
        for (int p : pisInts) { //Проходимся по массиву цифр числа пи
            for (int i = 0; i < p; i++) { //Перебираем числа меньшие цифры
                if (cnt >= s.length() && !s.isEmpty()) { //Если мы вышли за пределы строки, добавляем последний элемент
                    ans.append(s.charAt(s.length()-1));
                }
                else if(!s.isEmpty()) { //добавляем в ответ символ строки, равный счету
                    ans.append(s.charAt(cnt)); //В первый раз добавит 3 буквы
                }
                cnt++; //После добавления буквы, увеличиваем счёт
            }

            if (cnt >= s.length()) { //Если букв больше, чем символов в тексте, выходим из цикла
                break;
            }
            ans.append(" "); //Приводим к стороковому типу
        }
        return ans.toString();
    }


    //8
    public static String generateNonconsecutive (String op) {

        try {
            return generateNonconsecutiveTwin1(op);
        }
        catch (CustomMathException e) {
            return e.getMessage();
        }
    }

    public static String generateNonconsecutiveTwin1 (String op) throws CustomMathException {

        // В отдельной функции парсим выражение в динамический массив
        // И в ней проверяем строку на корректность
        int priority = 0;
        boolean isFirst = true;
        String opc = "";
        ArrayList<String> opAr = new ArrayList<>();
        for (int i = 0; i < op.length(); i++) { //проходимся по элементам строки
            char c = op.charAt(i);
            if (c == '(') { //Если скобки, увеличиваем приоритет
                priority++;
                opAr.add(Character.toString(c));
                isFirst = true;
            }
            else if (c == ')') {
                // Проверка на то, чтобы была парвильная скобочная последовательность
                if (priority == 0) {
                    throw(new CustomMathException("Not a valid bracket sequence"));
                }
                priority--;
                opAr.add(Character.toString(c));
            }
            else if ('0' <= c && c <= '9') {  //Если цифра, то она и все цифры подряд после неё сохраняются в str
                StringBuilder str = new StringBuilder();
                while (i < op.length() && '0' <= op.charAt(i) && op.charAt(i) <= '9') {
                    str.append(op.charAt(i));
                    i++;
                }
                i--;
                if (isFirst && opc.equals("-")) { //Если скобки и вычитание, добавляем opc в opAr и обнуляем
                    opAr.add(opc + str.toString());
                    opc = "";
                }
                else {
                    opAr.add(str.toString()); //Иначе просто добавляем
                }
                isFirst = false;
            }
            else if (is_operation(c)) { //Если операция
                if (isFirst && c == '-') { //Если вычитание, opc = -
                    opc = "-";
                }
                else { //Если нет, то добавляем символ в opAr, обнуляем opc
                    opAr.add(Character.toString(c));
                    opc = "";
                }
            }
            // Некорректные символы
            else if (c != ' ') {
                //System.out.println(ERROR + " Not a valid characters.");
                //return ERROR + " Not a valid characters.";
                throw new CustomMathException("Not a valid characters.");
            }
        }

        if (priority != 0) { //Если скобка не закрылась ошибка
            throw new CustomMathException("Not a valid bracket sequence");
        }
        else if (opAr.isEmpty()) { //Если массив opAR пустой, значит выражение неверно. Ошибка
            throw new CustomMathException("Empty math expression");
        }

        // У нас есть массив из частей выражения. Проверим на корректность

        for (int i = 0; i < opAr.size(); i++) {
            // Пустые скобки
            if (i < opAr.size()-1 && opAr.get(i).equals("(") && opAr.get(i+1).equals(")")) {
                throw new CustomMathException("Empty parentheses.");
            }
            // Две подряд идущие математические операции
            if (i < opAr.size()-1 && is_operation(opAr.get(i)) && is_operation(opAr.get(i+1))) {
                throw new CustomMathException("Two consecutive operations.");
            }
            // Два подряд идущих числа
            if (i < opAr.size()-1 && is_number(opAr.get(i)) && is_number(opAr.get(i+1))) {
                throw new CustomMathException("Two consecutive numbers.");
            }
            // После закрытой скобки идет число(без операции между ними)
            if (i < opAr.size()-1 && opAr.get(i).equals(")") && is_number(opAr.get(i+1))) {
                throw new CustomMathException("Missed operation");
            }
            // Перед открывающей скобкой идет число без операции между ними
            if (i < opAr.size()-1 && is_number(opAr.get(i)) && opAr.get(i+1).equals("(")) {
                throw new CustomMathException("Missed operation");
            }
        }

        // Проверим деление на ноль
        for (int i = 1; i < opAr.size() - 1; i++) {

            if (is_operation(opAr.get(i)) && is_number(opAr.get(i-1)) && is_number(opAr.get(i+1))) {
                try{
                    String result = calculate(opAr.get(i-1), opAr.get(i), opAr.get(i+1));
                }
                catch (CustomMathException e) {
                    throw e;
                }
            }
        }


        return arrayStringToString(generateNonconsecutiveTwin(opAr));
    }

    public static ArrayList<String> generateNonconsecutiveTwin(ArrayList<String> opAr) {

        // Считаем приоритет текущих операций и приоритет
        int priority = 0;

        for (String s : opAr) {
//            System.out.print(s);
//            System.out.print("  ");
        }
//        System.out.println();

        // База рекурсии - в массиве opAr один элемент, который
        // и есть результат вычисления всего выражения
        if (opAr.size() == 1) {
            return opAr;
        }
        // Еще один исключительный случай - пустая строка
        boolean isFinded = false;

        // Сначала проверим, есть ли у нас такие скобки, что внутри них всего одно число
        for (int i = 0; i < opAr.size(); i++) {
            String s = opAr.get(i);
            if (i + 2 < opAr.size() && s.equals("(") && opAr.get(i+2).equals(")")) {
                isFinded = true;
                opAr.remove(i);
                opAr.remove(i+1);
                break;
            }
        }
        if (isFinded) {
            return generateNonconsecutiveTwin(opAr);
        }




        //Теперь уже пытаемся посчитать операцию с максимальным приоритетом.
        double priorCnt = 0, maxPriorCnt = 0;
        double[] priors = new double[opAr.size()];
        for (int i = 0; i < opAr.size(); i++) {
            if (opAr.get(i).equals("(")) {
                priorCnt += 1;
            }
            else if (opAr.get(i).equals(")")) {
                priorCnt -= 1;
            }
            else if (is_operation(opAr.get(i))) {
                priors[i] = priorCnt + priorityOfOp(opAr.get(i));
                maxPriorCnt = Math.max(maxPriorCnt, priors[i]);
            }
        }

        for (int i = 1; i < opAr.size() - 1; i++) {
            if (is_operation(opAr.get(i)) && priors[i] == maxPriorCnt) {
                // Нашли операцию, которую необходимо вычислить
                // Берем эту опреацию, числа справа и слева и все считаем.
                try {
                    String result = calculate(opAr.get(i-1), opAr.get(i), opAr.get(i+1));

                    opAr.set(i, result);
                    opAr.remove(i-1);
                    opAr.remove(i);
                    break;
                }
                catch(CustomMathException e) {
                    return new ArrayList<>();
                }
            }
        }


        return generateNonconsecutiveTwin(opAr);
    }

    public static boolean is_operation(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    public static boolean is_operation(String c) {
        return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"));
    }

    public static boolean is_number (String c) {
        return ('0' <= c.charAt(0) && c.charAt(0) <= '9');
    }

    public static double priorityOfOp (String s) {
        if (s.equals("*") || s.equals("/")) {
            return 0.7;
        }
        else {
            return 0.3;
        }
    }

    public static String calculate  (String num1, String op, String num2) throws CustomMathException {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int n3 = 0;
        if (op.equals("+")) {
            n3 = n1 + n2;
        }
        else if (op.equals("-")) {
            n3 = n1 - n2;
        }
        else if (op.equals("*")) {
            n3 = n1 * n2;
        }
        else if (op.equals("/")) {
            if (n2 == 0) throw new CustomMathException("Zero exception");
            n3 = n1 / n2;
        }

        return Integer.toString(n3);
    }

    public static String arrayStringToString (ArrayList<String> a) {
        StringBuilder x = new StringBuilder();
        for (String s : a) {
            x.append(s);
        }
        return x.toString();
    }


    //9
    public static String isValid(String s) {
//        System.out.println(s);
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        boolean used = false;
        int a1 = 0, a2 = 0, n = 0; //n - кол.во различных символов a2 - максимум повторений a1 - минимум
        int cnt1 = 0, cnt2 = 0, curCnt = 1; //cnt1 - сколько символов повторяются cnt2 - сколько одиночных символов
        for (int i = 1; i < cs.length; i++) { //Проходим по символам строки
            if (cs[i] != cs[i-1]) { //Если два идущих подряд символа не равны
                if (a1 == 0) {
                    a1 = curCnt;
                    cnt1++;
                }
                else if (curCnt != a1 && a2 == 0) {
                    a2 = curCnt;
                    cnt2++;
                }
                else {
                    if (curCnt == a1) cnt1++;
                    else if (curCnt == a2) cnt2++;
                    else return "NO";
                }
                n++;
                curCnt = 1;
            }
            else {
                curCnt++;
            }
//            System.out.println("a1 a2 cnt1 cnt2 n curCnt");
//            System.out.println(a1 + "   " + a2 + "   " + cnt1 + "   " + cnt2 + "   " + n + "   " + curCnt);
        }
        n++;
        if (curCnt == a1) cnt1++;
        else if (curCnt == a2) cnt2++;
        else return "NO";
        if ((cnt1 == n-1 || cnt2 == n-1) && Math.abs(a1-a2) == 1) { //если при удалении 1 буквы условие выполняется
            return "YES";
        }
        else if (a2 == 0) {
            return "YES";
        }
        else {
            return "NO";
        }
    }


    //10
    public static String findLCS (String a, String b) {

        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
        int[][] dpi = new int[n+1][m+1];
        int[][] dpj = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dpi[i][j] = -1;
                dpj[i][j] = -1;
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i <= n; i++) { //у каждой ячейки ссылка на ячейку до неё
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) { //если символы на 2х позициях равны, топрибавляем ответ на 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dpi[i][j] = i - 1;
                    dpj[i][j] = j - 1;
                }
                if (dp[i][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    dpi[i][j] = i;
                    dpj[i][j] = j - 1;
                }
                if (dp[i][j] < dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                    dpi[i][j] = i- 1;
                    dpj[i][j] = j;
                }
            }
        }
        //System.out.print(a);
        //System.out.print(" ");
        //System.out.println(b);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //System.out.print(dp[i][j]);
                //System.out.print(" ");
            }
            //System.out.println();
        }
        int x = n, y = m; //восстанавливаем ответ
        StringBuilder ans = new StringBuilder();
        while (true) {
            int nx = dpi[x][y];
            int ny = dpj[x][y];
            if (nx == -1 || ny == -1) break; //ячейки, которые остались -1, там нет правльного ответа
            if (x - nx == y - ny) { //добавляем ли мы символ (1==1 тогда добавляем (если пошли по диагонали, тогда добавляем)
                ans.append(a.charAt(x-1));
            }
            x = nx;
            y = ny;
            //System.out.print(x);
            //System.out.println(y);
        }

        String answer = ans.reverse().toString(); //переаорачиваем, т.к. добавляли с конца
        return answer;
    }

}