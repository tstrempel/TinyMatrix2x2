import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Matrix_2x2_double {
    private double c11_, c12_, c21_, c22_;

    public Matrix_2x2_double(double c11, double c12, double c21, double c22) {
        this.c11_ = c11;
        this.c12_ = c12;
        this.c21_ = c21;
        this.c22_ = c22;
    }

    public Matrix_2x2_double(String file) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader(file));
        List<String> values = new ArrayList<>();
        String lineFetched = null;
        String[] valuesArray;

        while (true) {
            lineFetched = buf.readLine();
            if (lineFetched == null) {
                break;
            } else {
                valuesArray = lineFetched.split("\t");
                for (String each : valuesArray) {
                    if (!"".equals(each)) {
                        values.add(each);
                    }
                }
            }
        }
        c11_ = Double.valueOf(values.get(0));
        c12_ = Double.valueOf(values.get(1));
        c21_ = Double.valueOf(values.get(2));
        c22_ = Double.valueOf(values.get(3));
        buf.close();
    }

    public void print(OutputStreamWriter os) throws IOException {
        os.write(c11_ + "\t" + c12_ + "\n" + c21_ + "\t" + c22_ + "\n");
    }

    public final short size() {
        return 2;
    }

    public final short rows() {
        return 2;
    }

    public final short columns() {
        return 2;
    }

    public final double get_element(int i, int j) {
        if (i == 0 && j == 0)
            return c11_;
        if (i == 0 && j == 1)
            return c12_;
        if (i == 1 && j == 0)
            return c21_;
        if (i == 1 && j == 1)
            return c22_;
        if (i > 1 | i < 0)
            throw new IndexOutOfBoundsException("Row index i > 1");
        if (j > 1 | j < 0)
            throw new IndexOutOfBoundsException("Column index j > 1");
        return 0.0; // should never happen; just to get rid of the warning
    }

    public final double determinant() {
        return c11_ * c22_ - c12_ * c21_;
    }

    public final double spur() {
        return c11_ + c22_;
    }

    public final boolean is_orthogonal() {
        return multiply(transpose()).is_identity();
    }

    public final boolean is_singular() {
        if (determinant() == 0.0)
            return true;
        else
            return false;
    }

    public final boolean is_symmetric() {
        if (c21_ == c12_)
            return true;
        else
            return false;
    }

    public final boolean is_diagonal() {
        if (c12_ == 0.0 && c21_ == 0.0)
            return true;
        else
            return false;
    }

    public final boolean is_upper_triangular() {
        if (c21_ == 0.0)
            return true;
        else
            return false;
    }

    public final boolean is_lower_triangular() {
        if (c12_ == 0.0)
            return true;
        else
            return false;
    }

    public final boolean is_identity() {
        if (c11_ == 1.0 && c22_ == 1.0 &&
                c12_ == 0.0 && c21_ == 0.0)
            return true;
        else
            return false;
    }

    public final Matrix_2x2_double transpose() {
        return new Matrix_2x2_double(c11_, c21_, c12_, c22_);
    }

    public final Matrix_2x2_double multiply_with_scalar(double s) {
        return new Matrix_2x2_double(c11_ * s, c12_ * s, c21_ * s, c22_ * s);
    }

    public final Matrix_2x2_double add_elementwise(final Matrix_2x2_double m) {
        return new Matrix_2x2_double(c11_ + m.c11_, c12_ + m.c12_, c21_ + m.c21_, c22_ + m.c22_);
    }

    public final Matrix_2x2_double multiply_elementwise(final Matrix_2x2_double m) {
        return new Matrix_2x2_double(c11_ * m.c11_, c12_ * m.c12_, c21_ * m.c21_, c22_ * m.c22_);
    }

    public final Matrix_2x2_double multiply(final Matrix_2x2_double m) {
        return new Matrix_2x2_double(c11_ * m.c11_ + c12_ * m.c21_,
                c11_ * m.c12_ + c12_ * m.c22_,
                c21_ * m.c11_ + c22_ * m.c21_,
                c21_ * m.c12_ + c22_ * m.c22_);
    }

    public final Matrix_2x2_double divide(final Matrix_2x2_double m) {
        return this.multiply(m.invert());
    }

    public final Matrix_2x2_double invert() {
        double d = determinant();
        if (d == 0.0)
            throw new ArithmeticException("Determinant is 0.0 --> matrix is singular, inverse matrix can't be computed");
        double r = 1.0 / d;
        return new Matrix_2x2_double(c22_ * r, -c12_ * r, -c21_ * r, c11_ * r);
    }

    public final boolean is_equal(final Matrix_2x2_double m) {
        if (c11_ == m.c11_ &&
                c12_ == m.c12_ &&
                c21_ == m.c21_ &&
                c22_ == m.c22_)
            return true;
        else
            return false;
    }

    public final Matrix_2x2_double symmetric_part() {
        return this.add_elementwise(transpose()).multiply_with_scalar(0.5);
    }

    public final Matrix_2x2_double antisymmetric_part() {
        return add_elementwise(transpose().multiply_with_scalar(-1.0)).multiply_with_scalar(0.5);
    }
}