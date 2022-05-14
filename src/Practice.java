public class Practice {
    public static void main(String[] args) {
        String s = "Say hello, plz say something";
        Practice p = new Practice();
        //System.out.println(p.reverse(s));
        System.out.println(Math.sqrt(100));
        for(int i = 2; i <= 100; i++) {
            System.out.printf("Math.sqrt(100)  %d = %d \n", i, ((int)Math.sqrt(100) % i));
        }
    }

    public String reverse(String str) {
        String s_builder = new StringBuilder(str).reverse().toString();

        char [] arr = str.toCharArray();

        StringBuilder sb = new StringBuilder();

        for(int i = arr.length-1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
        return String.valueOf(sb);
    }
}
