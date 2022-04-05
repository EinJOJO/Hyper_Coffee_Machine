public class Main {

    public static void main(String[] args) {
    // write your program here
        int i = 0;
        for (Secret secret : Secret.values()) {
            if (secret.name().startsWith("STAR")) {
                i++;
            }
        }
        System.out.println(i);
    }
}

