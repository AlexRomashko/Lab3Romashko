package Romashko3B.var18;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    //параметры вычисления по схеме Горнера
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 2;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;

        double result = coefficients[0];
        for (int i = 1; i < coefficients.length; ++i) {
            result = result * x + coefficients[i];
        }

        switch (col) {
            case 0:
// Если запрашивается значение 1-го столбца, то это X
                return x;

            case 1: {
// Если запрашивается значение 2-го столбца, то это значение многочлена
                return result;
            }
            default:
                return 0.0;
        }
    }


    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            default:
// Название 2-го столбца
                return "Значение многочлена";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}
