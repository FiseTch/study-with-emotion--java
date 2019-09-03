package com.szkingdom.util;


import java.util.Scanner;

/**
 * 矩阵工具类
 * <p>
 * m*n -- m行n列
 *
 * @author Tongch
 * @version 1.0
 * @time 2019/2/19 14:59
 */
public class MatrixUtil {
    
    
    public static double[][] staticMatrix = null;
    
    
    public static void main(String[] args) throws Exception {
        System.out.println("欢迎进入矩阵计算器，请选择要进行的操作:\n" +
                "1.相加\n" +
                "2.相减\n" +
                "3.相乘\n" +
                "4.反转\n"
        );
        System.out.println("请输入要进行操作的序号:");
        Scanner sc = new Scanner(System.in);
        handleMatrix(sc.nextInt());
        sc.close();
    }
    
    
    public static void handleMatrix(int order) throws Exception {
        double[][] matrix1 = null;
        double[][] matrix2 = null;
        switch (order) {
            case 1:
                matrix1 = inputMatrix(1);
                matrix2 = inputMatrix(2);
                showMatrix(addMatrix(matrix1, matrix2), "当前计算结果为：");
                break;
            case 2:
                matrix1 = inputMatrix(1);
                matrix2 = inputMatrix(2);
                showMatrix(subMatrix(matrix1, matrix2), "当前计算结果为：");
                break;
            case 3:
                matrix1 = inputMatrix(1);
                matrix2 = inputMatrix(2);
                showMatrix(multiplyMatrix(matrix1, matrix2), "当前计算结果为：");
                break;
            case 4:
                matrix1 = inputMatrix(1);
                showMatrix(revertMatrix(matrix1), "当前计算结果为：");
                break;
        }
        
    }
    
    
    /**
     * 矩阵的输入
     *
     * @return
     */
    public static double[][] inputMatrix(int number) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第 " + number +
                " 个矩阵的行数(按回车结束)：");
        int row = sc.nextInt();
        System.out.println("请输入第 " + number +
                " 个矩阵的列数(按回车结束)：");
        int column = sc.nextInt();
        
        double[][] inputMatrix = new double[row][column];
        
        for (int i = 0; i < row; i++) {
            System.out.println("请输入第 " + (i + 1) + " 行的数据");
            for (int j = 0; j < column; j++) {
                System.out.println("请输入( " + (i + 1) + " , " + (j + 1) + " )的数据: ");
                inputMatrix[i][j] = sc.nextInt();
            }
        }
        showMatrix(inputMatrix, "");
        return inputMatrix;
    }
    
    /**
     * 对矩阵的校验
     *
     * @param matrix1
     * @return
     * @throws Exception
     */
    public static boolean checkRevertMatrix(double[][] matrix1) throws Exception {
        if (matrix1 == null) {
            throw new Exception("矩阵为空");
        }
        return true;
    }
    
    
    /**
     * 转置矩阵
     *
     * @param matrix1
     * @return
     */
    public static double[][] revertMatrix(double[][] matrix1) throws Exception {
        
        if (checkRevertMatrix(matrix1)) {
            int row = getMatrixRow(matrix1);
            int column = getMatrixColumn(matrix1);
            
            double[][] resultMatrix = new double[column][row];
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[j][i] = matrix1[i][j];
                }
            }
            return resultMatrix;
        }
        return staticMatrix;
    }
    
    
    /***
     * 矩阵相乘时的校验
     *
     *  todo:矩阵为0时没有校验
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static boolean checkMultiplyMatrix(double[][] matrix1, double[][] matrix2) throws Exception {
        
        if (matrix1 == null) {
            throw new Exception("矩阵1 为空");
        }
        
        if (matrix2 == null) {
            throw new Exception("矩阵2 为空");
        }
        
        if (getMatrixColumn(matrix1) != getMatrixRow(matrix2)) {
            throw new Exception("矩阵1的列数与矩阵2的行数不一致，无法相乘");
        }
        
        return true;
    }
    
    
    /**
     * 矩阵相乘
     *
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2) throws Exception {
        
        if (checkMultiplyMatrix(matrix1, matrix2)) {
            
            int row = getMatrixRow(matrix1);
            int column = getMatrixColumn(matrix2);
            double[][] resultMatrix = new double[row][column];
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    double sum = 0;
                    for (int k = 0; k < getMatrixColumn(matrix1); k++) {
                        sum += matrix1[i][k] * matrix2[k][j];
                    }
                    resultMatrix[i][j] = sum;
                }
            }
            return resultMatrix;
        }
        return staticMatrix;
    }
    
    
    /**
     * 对矩阵相加时的校验
     * <p>
     * todo:矩阵为0时没有校验
     *
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static boolean checkAddMethod(double[][] matrix1, double[][] matrix2) throws Exception {
        if (matrix1 == null) {
            throw new Exception("矩阵1为空");
        }
        
        if (matrix2 == null) {
            throw new Exception("矩阵2为空");
        }
        
        if (getMatrixRow(matrix1) != getMatrixRow(matrix2)) {
            throw new Exception("两个矩阵的行数不相同，无法相加");
        }
        
        if (getMatrixColumn(matrix1) != getMatrixColumn(matrix2)) {
            throw new Exception("两个矩阵的列数不相同，无法相加");
        }
        
        
        return true;
    }
    
    
    /**
     * 矩阵的相加
     *
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static double[][] addMatrix(double[][] matrix1, double[][] matrix2) throws Exception {
        
        if (checkAddMethod(matrix1, matrix2)) {
            int row = getMatrixRow(matrix1);
            int column = getMatrixColumn(matrix1);
            double[][] resultMatrix = new double[row][column];
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            
            return resultMatrix;
        }
        return staticMatrix;
    }
    
    
    /**
     * 矩阵的相减
     *
     * @param matrix1
     * @param matrix2
     * @return
     */
    public static double[][] subMatrix(double[][] matrix1, double[][] matrix2) throws Exception {
        
        if (checkAddMethod(matrix1, matrix2)) {
            int row = getMatrixRow(matrix1);
            int column = getMatrixColumn(matrix1);
            double[][] resultMatrix = new double[row][column];
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
            
            return resultMatrix;
        }
        return staticMatrix;
    }
    
    
    /**
     * 获取矩阵的列数
     *
     * @param matrix1
     * @return
     */
    public static int getMatrixColumn(double[][] matrix1) {
        return matrix1.length == 0 ? 0 : matrix1[0].length;
    }
    
    
    /**
     * 获取矩阵的行数
     *
     * @param matrix1
     * @return
     */
    public static int getMatrixRow(double[][] matrix1) {
        return matrix1.length == 0 ? 0 : matrix1.length;
    }
    
    
    /**
     * 打印矩阵
     *
     * @param matrix1
     * @param message
     */
    public static void showMatrix(double[][] matrix1, String message) {
        
        int row = getMatrixRow(matrix1);
        int column = getMatrixColumn(matrix1);
        
        message = (message == null || message == "") ? "当前矩阵为 ： " : message;
        
        System.out.println(message);
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(matrix1[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
}
