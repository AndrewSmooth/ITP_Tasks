import java.util.ArrayList;
import java.util.*;

public class Task5 {

    public static void main (String[] args) {

        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB") );
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));

        System.out.println(spiderVsFly("H3", "E2").toString());
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));

        System.out.println(digitsCount(121317) );
        System.out.println(digitsCount(544) );
        System.out.println(digitsCount(12345) );


        String[] s1 = {"cat", "create", "sat"};
        String[] s2 = {"trance", "recant"};
        String[] s3 = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
        System.out.println(totalPoints(s1, "caster"));
        System.out.println(totalPoints(s2, "recant"));
        System.out.println(totalPoints(s3, "tossed"));

        int[] q1 = {1, 2, 3, 4, 5};
        int[] q2 = {1, 6, 5, 4, 8, 2, 3, 7};

        System.out.println(sumsUp(q1));
        System.out.println(sumsUp(q2));

        String[] w1 = {"95%", "83%", "90%", "87%", "88%", "93%"};
        String[] w2 = {"10%"};
        String[] w3 = {"53%", "79%"};

        System.out.println(takeDownAverage(w1));
        System.out.println(takeDownAverage(w2));
        System.out.println(takeDownAverage(w3));

        System.out.println(caesarCipher("encode", "hello world", 3));
        System.out.println(caesarCipher("decode", "almost last task!", 4));

        System.out.println(setSetup(5, 3) );
        System.out.println(setSetup(7, 3) );



        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome") );
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));

        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    //1
    public static boolean sameLetterPattern(String a, String b) {
        int x = b.charAt(0) - a.charAt(0); //x - насколько первые символы далеко друг от друга
        for (int i = 1; i < a.length(); i++) { //разница между соответствующими символами в шаблоне должна быть одинаковая
            if ((b.charAt(i) - a.charAt(i)) != x) {
                return false;//если нет, возвращаем false
            }
        }
        return true;
    }

    //2
    public static StringBuilder spiderVsFly (String a, String b) {
        int spider_x = a.charAt(0) - 'A'; // x - координата по радиалу 0-7
        int spider_y = a.charAt(1) - '0'; // y - координата по кольцу 0-4
        int fly_x = b.charAt(0) - 'A';
        int fly_y = b.charAt(1) - '0';

        int cnt = 0;

        StringBuilder answer = new StringBuilder();

        while (true) {
            //добавляем координаты в ответ
            char c1 = (char)(65 + spider_x); //65 - 'A' в числовом виде
            char c2 = Integer.toString(spider_y).charAt(0);
            answer.append(c1);
            answer.append(c2);



            //Если координаты паука совпадают с координатами мухи, то выходим из цикла
            if (spider_x == fly_x && spider_y == fly_y) {
                break;
            }
            answer.append("-");

            //Начнем строить алгоритм из центра
            //Если паук в центре паутины, то ему остается только двигаться дальше
            if (spider_y == 0) {
                spider_y = 1; //вперед на 1 кольцо
                spider_x = fly_x; //по радиалам можно сразу двигаться по направлению к мухе
            }

            //Если расстояние между пауком и мухой по радиалам больше 2, то двигаем паука по кольцам к центру
            else if (dist(spider_x, fly_x) > 2) { //dist - расстояние между двумя точками
                spider_y--;
                if (spider_y == 0) { //Если попадает в центр, то ставим по радиалам 0
                    spider_x = 0;
                }
            }

            //Если муха дальше по кольцам, двигаем паука к ней
            else if (spider_y < fly_y) {
                spider_y++;
            }

            //Если паук и муха на одном кольце, то выбираем кратчайший путь по радиалам: пойти напрямую или через точку A(начало "оси радиалов")
            else if (spider_y == fly_y) {
                if (Math.abs(spider_x - fly_x) < (7 - spider_x) + fly_x) { //Если ближе пойти напрямую
                    if (spider_x < spider_y) { //определяем в какую сторону пойдет паук: по часовой или против
                        spider_x++; //по часовой
                    }
                    else {
                        spider_x--; //против
                    }
                }
                else { //Если ближе пойти через точку А
                    spider_x = (spider_x + 1) % 8; //определяем координату по радиалам, если сделать 1 шаг к точке А
                }
            }

            //Остается случай, когда муха ближе к центру по кольцам, чем паук
            else {
                spider_y--;  //паук движется к центру
            }

            cnt += 1; //Счетчик шагов +1

            if (cnt > 40) { // При слишком большом пути останавливаем цикл
                break;
            }

        }

        return answer;
    }


    public static int dist(int spider_x, int fly_x) {
        return Math.min(Math.abs(spider_x - fly_x), (8 - spider_x) + fly_x); //минимальный путь: через А или не через А
    }


    //3
    public static int digitsCount (long n) {
        if (n < 10) { //проверка что пришло одноразрадное число
            return 1;
        }
        else {
            return 1 + digitsCount(n/10);
        }
    }


    //4
    public static int totalPoints (String[] words, String word) { //если в новом слове есть 6 букв: подсчитываем и +50. Если нет, но оно содержит буквы из прошлых слов, то +очки
        int[] wordCount = new int[26]; //массив с буквами нового слова
        for (int i = 0; i < 26; i++) {
            wordCount[i] = 0;
        }
        for (int i = 0; i < word.length(); i++) {
            int ind = word.charAt(i) - 'a';
            wordCount[ind] += 1;
        }
        int answer = 0;
        for (String s : words) {
            int[] nCount = new int[26];
            for (int i = 0; i < 26; i++) { //массив с буквами найденных слов
                nCount[i] = 0;
            }
            for (int i = 0; i < s.length(); i++) {
                int ind = s.charAt(i) - 'a';
                nCount[ind] += 1;
            }
            int count = 0;
            boolean is_include_in_answer = true;
            for (int i = 0; i < 26; i++) {
                //System.out.print(i);
                //System.out.print(wordCount[i]);
                //System.out.println(nCount[i]);
                if (nCount[i] > wordCount[i]) { //Если слово из текста не содержит буквы из нового слова, то неверно
                    is_include_in_answer = false;
                }
                count += nCount[i]; //скольцо букв в слове
            }
            if (is_include_in_answer) {
                answer += (count - 2);
                if (count >= 6) {
                    answer += 50;
                }
            }
        }
        return answer;
    }


    //5
    public static ArrayList< ArrayList<Integer> > sumsUp (int[] arr) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();

        for (int i = 1; i < arr.length; i++) { //В этом цикле i - каждая цифра в массиве
            for (int j = i-1; j >= 0; j--) { //j - перебираются все цифры до i
                if (arr[i] + arr[j] == 8) { // если в сумме 8, подходит.
                    ArrayList<Integer> a1 = new ArrayList<Integer>();
                    if (arr[j] <= arr[i]) { //Добавляем в порядке возрастания
                        a1.add(arr[j]);
                        a1.add(arr[i]);
                    }
                    else {
                        a1.add(arr[i]);
                        a1.add(arr[j]);
                    }
                    answer.add(a1);
                }
            }
        }
        return answer;
    }


    //6
    public static String takeDownAverage (String[] arr) {//S+x/n+1 = S/n - 5
        int n = arr.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i].replaceAll("%", "");
            sum += Integer.parseInt(arr[i]);
        }
        int ans = (int)Math.round((sum / n) -  (5 * (n+1))) ;//по формуле

        return Integer.toString(ans) + "%";
    }


    //7
    public static String caesarCipher(String type, String s, int shift) {
        int tp = 0;
        if (type.equals("encode")) tp = 1;
        else tp = -1;

        s = s.toUpperCase();
        char[] ch = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            char c = ch[i];
            int x = c - 'A'; //числовое отображение символа

            if (x >= 0 && x <= 25) { //Если x буква латинского алфавита
                int p = (65 + x + shift * tp);  //Сдвиг

                if (p > 90) { //Если вышли за границы алфавита (Z=90), то отматываем назад
                    p = (p - 25 - 1);
                }
                else if (p < 65) { //Если вышли за границы алфавита (A=65), то идем вперед
                    p = (25 + p + 1);
                }
                ch[i] = (char)p; //Меняем букву в массиве на другую
            }
            else {
                ch[i] = c; //Если x не буква, оставляем
            }
        }
        return new String(ch);
    }


    //8
    public static int setSetup (int n, int k) {
        if (k == 0) {
            return 1;
        }
        else {
            return n * setSetup(n-1, k-1); //таким образом часть числителя и знаменатель сокращаются
        }
    }


    //9
    public static String timeDifference (String cityA, String timeA, String cityB) {

        HashMap<String, int[] > cityDifference = new HashMap<>();
        HashMap<String, Integer> months = new HashMap<>();
        HashMap<String, Integer> daysInMonths = new HashMap<>();

        String[] monthsArray = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        cityDifference.put("Los Angeles", new int[] {-8, 0});
        cityDifference.put("New York", new int[] {-5, 0});
        cityDifference.put("Caracas", new int[] {-4, 30});
        cityDifference.put("Buenos Aires", new int[] {-3, 0});
        cityDifference.put("London", new int[] {0, 0});
        cityDifference.put("Rome", new int[] {1, 0});
        cityDifference.put("Moscow", new int[] {3, 0});
        cityDifference.put("Tehran", new int[] {3, 30});
        cityDifference.put("New Delhi", new int[] {5, 30});
        cityDifference.put("Beijing", new int[] {8, 0});
        cityDifference.put("Canberra", new int[] {10, 0});

        daysInMonths.put("January", 31);
        daysInMonths.put("February", 28);
        daysInMonths.put("March", 31);
        daysInMonths.put("April", 30);
        daysInMonths.put("May", 31);
        daysInMonths.put("June", 30);
        daysInMonths.put("July", 31);
        daysInMonths.put("August", 31);
        daysInMonths.put("September", 30);
        daysInMonths.put("October", 31);
        daysInMonths.put("November", 30);
        daysInMonths.put("December", 31);

        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 20);
        months.put("November", 11);
        months.put("December", 12);

        // time[0] - Месяц, time[1] - Число месяца
        // time[2] - Год, time[3] - Время
        String[] time = timeA.split(" ");
        time[1] = time[1].replaceAll(",", "");

        // Получаем разницу часовых поясов
        int[] timeInCityA = cityDifference.get(cityA);
        int[] timeInCityB = cityDifference.get(cityB);

        // Получаем прибавку ко времени
        int[] timeDifference = new int[] {timeInCityB[0] - timeInCityA[0],
                timeInCityB[1] - timeInCityA[1]};

        // Получаем из времени time[3] часы и минуты
        String[] currentTimeInString = time[3].split(":");
        int[] currentTime = {Integer.parseInt(currentTimeInString[0]),
                Integer.parseInt(currentTimeInString[1])};

        // newTime - Время с учетом смены часовых поясов
        // newTime[0] - Часы
        // newTime[1] - Минуты
        int[] newTime = new int[] {currentTime[0] + timeDifference[0],
                currentTime[1] + timeDifference[1]};

        int month = months.get(time[0]);
        int date = Integer.parseInt(time[1]);
        int year = Integer.parseInt(time[2]);

        if (newTime[1] < 0) { //если минуты отрицательные
            newTime[1] = 60 + newTime[1];
            newTime[0] -= 1;

            if (newTime[0] < 0) { //Если часы отрицательные
                newTime[0] = 24 - newTime[0];
                date -= 1;

                if (date == 0) { //Если день = 0
                    month -= 1;
                    if (month >= 1) { //если месяц в нормальном виде
                        date = daysInMonths.get(monthsArray[month - 1]);
                    }
                    else {
                        year -= 1;
                        month = 12;
                        date = 31;
                    }
                }
            }
        }

        if (newTime[1] >= 60) { //если минуты больше 60
            newTime[1] = newTime[1] % 60;
            newTime[0] += 1;
        }

        if (newTime[0] >= 24) { //Если часы больше 24
            newTime[0] -= 24;
            date += 1;
            if (date > daysInMonths.get(monthsArray[month - 1])) { //Если день превышает колилчество дней в этом месяце
                month += 1;
                date = 1;
                if (month > 11) {
                    month = 1;
                    year += 1;
                }
            }
        }

        StringBuilder ans = new StringBuilder(); //собираем ответ
        ans.append(Integer.toString(year));
        ans.append("-");
        ans.append(Integer.toString(month));
        ans.append("-");
        ans.append(Integer.toString(date));
        ans.append(" ");

        if (newTime[0] <= 9) {
            ans.append("0");
        }
        ans.append(newTime[0]);
        ans.append(":");
        if (newTime[1] <= 9) {
            ans.append("0");
        }
        ans.append(newTime[1]);

        return ans.toString();
    }


    //10
    public static boolean isNew (int a) {
        char[] digs = Integer.toString(a).toCharArray();
        for (int i = 1; i < digs.length; i++) {
            if (digs[i] < digs[i-1] && digs[i] != '0') { //если в числе есть цифра, которая меньше предыдущей цифры и != 0, то число не новое
                return false;
            }
        }
        return true;
    }
}