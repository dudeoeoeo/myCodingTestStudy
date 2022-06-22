package daily;

public class C4 {
    public int solution(int X, int Y, int D) {
        if (X == Y) return 0;

        Y -= X;

        return Y % D <= 0 ? Y / D : Y / D + 1;
    }
}
