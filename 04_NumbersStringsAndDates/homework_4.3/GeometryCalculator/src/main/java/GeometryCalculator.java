public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.abs(radius) *  Math.abs(radius) *  Math.PI;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return  ( 4.0 / 3.0 ) * Math.PI * Math.pow( Math.abs(radius), 3 );
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        return a < b + c && b < a + c && c < a + b;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (isTrianglePossible(a,b,c)) {
            double p1 = (a + b + c) / 2;
            return Math.sqrt(p1 * (p1 - a) * (p1 - b) * (p1 - c));
        }
        return -1;
    }
}
