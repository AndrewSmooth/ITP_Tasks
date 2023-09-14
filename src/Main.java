public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World111111!");
        //1
        System.out.println("---Task 1---");
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        //2
        System.out.println("---Task 2---");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        //3
        System.out.println("---Task 3---");
        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));
        //4
        System.out.println("---Task 4---");
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        //5
        System.out.println("---Task 5---");
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));
        //6
        System.out.println("---Task 6---");
        System.out.println(howManyItems(22, 1.4f, 2));
        System.out.println(howManyItems(45, 1.8f, 1.9f));
        System.out.println(howManyItems(100, 2, 2));
        //7
        System.out.println("---Task 7---");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        //8
        System.out.println("---Task 8---");
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));
        //9
        System.out.println("---Task 9---");
        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250));
        //10
        System.out.println("---Task 10---");
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));
    }

    public static float convert(int x){
        return x * 3.785f;
    }

    public static int fitCalc(int minutes, int power){
        return minutes * power;
    }

    public static int containers(int boxes, int bags, int barrels){
        return boxes*20 + bags* 50 + barrels*100;
    }

    public static String triangleType(int x, int y, int z){
        if(x > (y + z) || y > (x + z) || z > (x + y)){
            return "not a triangle";
        }
        else if(x == y & y == z){
            return "isosceles";
        }
        else if(x == y || x == z || y == z) {
            return "equilateral";
        }

        else return "different-sided";
    }

    public static int ternaryEvaluation(int x, int y){
        return x>y? x : y;
    }

    public static int howManyItems(float n, float w, float h){
        return (int) (n/(w*h*2));
    }

    public static int factorial(int x){
        int answer = 1;
        for(int i = 2; i<=x; i++){
            answer *= i;
        }
        return answer;
    }

    public static int gcd(int x, int y){
        int answer = 1;
        int i = 1;
        while(i < ternaryEvaluation(x, y)){
            if(x % i == 0 & y % i == 0){
                answer = i;
            }
            i++;
        }
        return answer;
    }

    public static int ticketSaler(int tickets, int price){
        return (int) (tickets * price/1.38888889f);
    }

    public static int tables(int students, int tables){
        int studentsLeft = students - 2*tables;
        if(studentsLeft <= 0){
            return 0;
        }
        return (int) Math.ceil(studentsLeft/2f);
    }
}
