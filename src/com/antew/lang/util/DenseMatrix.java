package com.antew.lang.util;

import com.antew.lang.Matrix;

public class DenseMatrix implements Matrix {
    
    protected int rows;
    protected int columns;
    protected double[][] array;
    
    public DenseMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        array = new double[rows][columns];
    }
   
    
    @Override
    public double get(int i, int j) {
        return array[i][j];
    }

    @Override
    public void put(int i, int j, double d) {
        array[i][j] = d;
        
    }

    @Override
    public Matrix transpose() {
        DenseMatrix temp = new DenseMatrix(columns, rows);
        for (int i = 0; i < temp.rows; ++i)
            for (int j = 0; j < temp.columns; ++j)
                temp.array[i][j] = array[j][i];
        
        return temp;
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        DenseMatrix temp = (DenseMatrix) matrix;
        
        if (columns != temp.rows)
            throw new IllegalArgumentException("Matrices have incompatible sizes!");
        
        DenseMatrix result = new DenseMatrix(rows, temp.columns);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < temp.columns; ++j) {
                double sum = 0;
                
                for (int k = 0; k < columns; ++k)
                    sum += array[i][k] * temp.array[k][j];  // "Data Structures and Algorithms in Java" shows addition here, but that is incorrect...
                
                result.array[i][j] = sum;
                    
            }
        }
        
        return result;
    }

    @Override
    public Matrix add(Matrix matrix) {
        DenseMatrix temp = (DenseMatrix) matrix;
        
        if (columns != temp.rows)
            throw new IllegalArgumentException("Matrices have incompatible sizes!");
        
        DenseMatrix result = new DenseMatrix(rows, temp.columns);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < temp.columns; ++j) {
                double sum = 0;
                
                for (int k = 0; k < columns; ++k)
                    sum += array[i][k] + temp.array[k][j];
                
                result.array[i][j] = sum;
                    
            }
        }
        return result;
    }

}
