package string;

public class KthGrammar {

    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        return (~K & 1) ^ kthGrammar(N-1, (K+1)/2);
    }

    public static void main(String[] args) {
        KthGrammar kthGrammar = new KthGrammar();
        System.out.println(kthGrammar.kthGrammar(2,2));
    }

}
