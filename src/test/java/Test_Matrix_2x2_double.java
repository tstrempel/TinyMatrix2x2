import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.Test;
// import org.junit.jupiter.api.Test;

public class Test_Matrix_2x2_double {
    @Test
    public void testConstructorsAndIsEqual() throws IOException, ClassNotFoundException {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1.0, 2.0, 3.0, 4.0);
        OutputStream oStream = new FileOutputStream("test.mat");
        OutputStreamWriter out = new OutputStreamWriter(oStream, "UTF-8");
        m1.print(out);
        out.close();

        Matrix_2x2_double m2 = new Matrix_2x2_double("test.mat");

        assertTrue(m1.is_equal(m2));

    }

    @Test
    public void testSizeRowsColumnsGetElement() {
        Matrix_2x2_double m = new Matrix_2x2_double(1.0, 2.0, 3.0, 4.0);
        assertEquals(2, m.size());
        assertEquals(2, m.rows());
        assertEquals(2, m.columns());
        assertEquals(1, m.get_element(0, 0));
        assertEquals(2, m.get_element(0, 1));
        assertEquals(3, m.get_element(1, 0));
        assertEquals(4, m.get_element(1, 1));
    }

    @Test
    public void testDeterminant() {
        Matrix_2x2_double m = new Matrix_2x2_double(1.0, 2.0, 3.0, 4.0);
        assertEquals(-2, m.determinant());
    }

    @Test
    public void testSpur() {
        Matrix_2x2_double m = new Matrix_2x2_double(1.0, 2.0, 3.0, 4.0);
        assertEquals(5, m.spur());
    }


    @Test
    public void testIsOrthogonal() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(0, 1, 1, 0);
        assertTrue(m1.is_orthogonal());
        Matrix_2x2_double m2 = new Matrix_2x2_double(1.0, 2.0, 3.0, 4.0);
        assertFalse(m2.is_orthogonal());
        Matrix_2x2_double m3 = new Matrix_2x2_double(1.0, 2.0, 2.0, 4.0);
        assertFalse(m3.is_orthogonal());
    }

    @Test
    public void testIsSingular() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(0, 1, 1, 0);
        assertFalse(m1.is_singular());
        Matrix_2x2_double m2 = new Matrix_2x2_double(1, 2, 2, 4);
        assertTrue(m2.is_singular());
        Matrix_2x2_double m3 = new Matrix_2x2_double(1, 2, 3, 4);
        assertFalse(m3.is_singular());
    }

    @Test
    public void testIsSymmetric() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(0, 1, 1, 0);
        assertTrue(m1.is_symmetric());
        Matrix_2x2_double m2 = new Matrix_2x2_double(1, 2, 3, 4);
        assertFalse(m2.is_symmetric());
    }

    @Test
    public void testIsDiagonal() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(0, 1, 1, 0);
        assertFalse(m1.is_diagonal());
        Matrix_2x2_double m2 = new Matrix_2x2_double(1, 0, 0, 1);
        assertTrue(m2.is_diagonal());
    }

    @Test
    public void testIsUpperTriangular() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 1, 0, 1);
        assertTrue(m1.is_upper_triangular());
        Matrix_2x2_double m2 = new Matrix_2x2_double(0, 0, 1, 0);
        assertFalse(m2.is_upper_triangular());
    }

    @Test
    public void testIsLowerTriangular() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 0, 1, 1);
        assertTrue(m1.is_lower_triangular());
        Matrix_2x2_double m2 = new Matrix_2x2_double(0, 1, 0, 0);
        assertFalse(m2.is_lower_triangular());
    }

    @Test
    public void testIsIdentity() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 0, 0, 1);
        assertTrue(m1.is_identity());
        Matrix_2x2_double m2 = new Matrix_2x2_double(0, 1, 0, 0);
        assertFalse(m2.is_identity());
    }

    @Test
    public void testTransposeUsingIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(1, 3, 2, 4);
        assertTrue(m1.transpose().is_equal(m2));

    }

    @Test
    public void testMultiplyWithScalarUsingIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(2, 4, 6, 8);
        assertTrue(m1.multiply_with_scalar(2.0).is_equal(m2));
    }

    @Test
    public void testAddElementwiseUsingIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(-1, -2, -3, -4);
        Matrix_2x2_double m3 = new Matrix_2x2_double(0, 0, 0, 0);
        assertTrue(m1.add_elementwise(m2).is_equal(m3));
    }

    @Test
    public void testMultiplyElementwise() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(1.0, 1.0 / 2.0, 1.0 / 3.0, 1.0 / 4.0);
        Matrix_2x2_double m3 = new Matrix_2x2_double(1, 1, 1, 1);
        assertTrue(m1.multiply_elementwise(m2).is_equal(m3));
    }

    @Test
    public void testMultiplyUsingIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(4, 3, 2, 1);
        Matrix_2x2_double m3 = new Matrix_2x2_double(8, 5, 20, 13);
        assertTrue(m1.multiply(m2).is_equal(m3));
    }

    @Test
    public void testDivideUsingMultiplyAndIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = new Matrix_2x2_double(4, 3, 2, 1);
        Matrix_2x2_double m3 = new Matrix_2x2_double(8, 5, 20, 13);
        assertTrue(m3.divide(m2).is_equal(m1));
    }

    @Test
    public void testInvertUsingMultiplyAndIdentity() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = m1.invert();
        assertTrue(m1.multiply(m2).is_identity());
    }

    @Test
    public void testSymmetricPartAndAntisymmetricPartUsingAddElementwiseAndIsEqual() {
        Matrix_2x2_double m1 = new Matrix_2x2_double(1, 2, 3, 4);
        Matrix_2x2_double m2 = m1.symmetric_part();
        Matrix_2x2_double m3 = m1.antisymmetric_part();
        assertTrue(m2.add_elementwise(m3).is_equal(m1));
    }
}
