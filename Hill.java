import java.math.BigInteger;
import java.util.Arrays;

public class Hill {
    private static final int ASCII_ALPH_START = 65;
    private static final int ALPH_LEN = 26;

    public static void encrypt(String key, String message) {
        double[][] matrixKey = transformKeyIntoMatrix(key);

        if (!isMatrixCorrect(matrixKey))
            return;

        int[][] matrixMessage = transformMessageIntoMatrixes(message, matrixKey.length);
        double[][] encryptedMatrixMessage = new double[matrixMessage.length][matrixKey.length];
        for(int i = 0; i < matrixMessage.length; ++i)
            encryptedMatrixMessage[i] = multiplyMatrixesD(matrixMessage[i], matrixKey);

        String encryptedMessage = "";
        for(int i = 0; i < encryptedMatrixMessage.length; ++i)
            for(int j = 0; j < matrixKey.length; ++j)
                encryptedMessage += (char)(encryptedMatrixMessage[i][j] + ASCII_ALPH_START);

        System.out.println(encryptedMessage);
        
    }

    private static boolean isMatrixCorrect(double[][] matrixKey) {
        if (matrixKey.length == 1 && matrixKey[0][0] == 0) {
            System.out.println("Invalid matrix key, must be NxN!");
            return false;
        } else if (calculateDeterminant(matrixKey) == 0) {
            System.out.println("Inappriopriate matrix! Determinant must be different from zero.");
            return false;
        }
        return true;
    }

    private static double[][] transformKeyIntoMatrix(String key) {
        String[] matrixKey = key.split("-");
        int nLength = (int)Math.sqrt(matrixKey.length);
        if (nLength * nLength != matrixKey.length) {
            return new double[][]{{0}};
        }
        double[][] matrixIntKey = new double[nLength][nLength];
        for(int i = 0; i < matrixKey.length; ++i)
            matrixIntKey[i/nLength][i%nLength] = Double.parseDouble(matrixKey[i]);
        return matrixIntKey;
    }

    private static int[][] transformMessageIntoMatrixes(String message, int dimension) {
        while (message.length()%dimension != 0)
            message += "X";
        int messageLen = message.length();
        int[][] matrixes = new int[messageLen/dimension][dimension];
        
        for(int i = 0; i < messageLen; ++i)
            matrixes[i/dimension][i%dimension] = (int)message.charAt(i) - ASCII_ALPH_START;

        return matrixes;
    }

    public static void decrypt(String key, String message) {
        double[][] matrixKey = transformKeyIntoMatrix(key);
        double determinant = calculateDeterminant(matrixKey);

        if(!isMatrixCorrect(matrixKey))
            return;

        int[][] matrixMessage = transformMessageIntoMatrixes(message, matrixKey.length);
        double[][] inversedMatrixKey = invert(matrixKey);
        int inverseDeterminantModulo;
        try {
            inverseDeterminantModulo = inverseA((int) determinant);
        } catch (ArithmeticException e) {
            System.out.println("determinant^-1%" + ALPH_LEN + " is not possible.");
            return;
        }
        double[][] encryptedMatrixMessage = new double[matrixMessage.length][matrixKey.length];
        for(int i = 0; i < matrixMessage.length; ++i)
            encryptedMatrixMessage[i] = multiplyMatrixesD(matrixMessage[i], inversedMatrixKey);

        for(int i = 0; i < encryptedMatrixMessage.length; ++i)
            for(int j = 0; j < encryptedMatrixMessage[i].length; ++j) {
                encryptedMatrixMessage[i][j] = ((encryptedMatrixMessage[i][j] * inverseDeterminantModulo * determinant) % ALPH_LEN);
                encryptedMatrixMessage[i][j] = Math.round(encryptedMatrixMessage[i][j]);
                if (encryptedMatrixMessage[i][j] < 0)     
                    encryptedMatrixMessage[i][j] += ALPH_LEN;
                encryptedMatrixMessage[i][j] = encryptedMatrixMessage[i][j] % ALPH_LEN;
            }
        
        String decryptedMessage = "";
        for(int i = 0; i < encryptedMatrixMessage.length; ++i)
            for(int j = 0; j < matrixKey.length; ++j)
                decryptedMessage += (char)(encryptedMatrixMessage[i][j]  + 65);
        
        System.out.println(decryptedMessage);
    }

    private static double[] multiplyMatrixesD(int[] matrixNChars, double[][] matrixKey) {
        double[] multiplied = new double[matrixNChars.length];
        for(int i = 0; i < matrixNChars.length; ++i) {
            for(int j = 0; j < matrixNChars.length; ++j)
                multiplied[i] += (matrixNChars[j] * matrixKey[i][j]);
            multiplied[i] %= ALPH_LEN;
        }
        return multiplied;
    }

    private static double[][] invert(double[][] matrix) {
        double[][] unitMatrix = createUnitMatrix(matrix.length);
        double[][] invertedMatrix = new double[matrix.length][matrix.length];
        for(int i = 0; i < invertedMatrix.length; ++i)
            invertedMatrix[i] = Arrays.copyOf(matrix[i], matrix.length);
        double[] tempRow = new double[matrix.length];

        for(int row = 0; row < matrix.length; ++row) {
            tempRow = Arrays.copyOf(invertedMatrix[row], invertedMatrix.length);
            for(int col = 0; col < matrix.length; ++col) {
                tempRow[col] /= invertedMatrix[row][row];
                unitMatrix[row][col] /= invertedMatrix[row][row];
            }

            invertedMatrix[row] = Arrays.copyOf(tempRow, tempRow.length);
            for(int othersRow = 0; othersRow < matrix.length; othersRow++) {
                tempRow = Arrays.copyOf(invertedMatrix[othersRow], invertedMatrix.length);
                if(othersRow != row)
                    for(int col = 0; col< matrix.length; ++col) {
                        tempRow[col] = invertedMatrix[othersRow][col] - invertedMatrix[row][col] * invertedMatrix[othersRow][row];
                        unitMatrix[othersRow][col] = unitMatrix[othersRow][col] - unitMatrix[row][col] * invertedMatrix[othersRow][row];
                    }
                invertedMatrix[othersRow] = Arrays.copyOf(tempRow, tempRow.length);
            }
        }

        return unitMatrix;
    }

    private static double[][] createUnitMatrix(int n) {
        double[][] unitMatrix = new double[n][n];

        for (int i = 0; i < n; ++i) 
            unitMatrix[i][i] = 1;
        
        return unitMatrix;
    }


    private static double calculateDeterminant(double[][] matrix) {
        int sum = 0;
        if(matrix.length == 1)
            return matrix[0][0];
        else if(matrix.length == 2)
            return sumMatrix2x2(matrix);

        double[][] subMatrix = new double[matrix.length - 1][matrix.length - 1];

        int currRow = 0;
        int currCol = 0;
        for(int n = 0; n < matrix.length; ++n) {
            currRow = 0;
            currCol = 0;
            for(int m = 0; m < matrix.length * matrix.length; ++m)
                if(m/matrix.length != n/matrix.length && m%matrix.length != n%matrix.length) {
                    subMatrix[currRow][currCol] = matrix[m/matrix.length][m%matrix.length];
                    ++currCol;
                    if (currCol == subMatrix.length) {
                        currCol = 0;
                        ++currRow;
                    }
            }
            sum += matrix[0][n] * Math.pow(-1, n/matrix.length + n%matrix.length) * calculateDeterminant(subMatrix);
        }
        return sum;
    }

    private static double sumMatrix2x2(double[][] matrix) {
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
    }

    private static int inverseA(int a) {         
        BigInteger bi1, bi2;
        bi1 = new BigInteger(a + "");
        bi2 = new BigInteger(ALPH_LEN + "");
        return Integer.parseInt(bi1.modInverse(bi2) + "");
    }

    public static void main(String[] args) {
        //String matrix = "2-4-5-9-2-1-3-17-7";
        String matrix = "4-5-9-12-7-13-1-4-12-24-3-6-9-12-5-7";
        // encrypt(matrix, "ATTACKATDAWN");
        // decrypt(matrix, "PFOGOANPGXFX");
        encrypt(matrix, "ATTACKATDAWN");
        decrypt(matrix, "GGTLAMOLCRYU");
    }
}