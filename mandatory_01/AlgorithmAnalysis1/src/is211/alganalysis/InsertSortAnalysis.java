/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is211.alganalysis;

/**
 *
 * @author evenal
 */
public class InsertSortAnalysis extends EmpiricalAnalysis {

    public InsertSortAnalysis() {
        super("Insertion sort");
    }



    protected void run(int[] shuffledData) {
        insertSort(shuffledData);   
    }

    public void insertSort(int[] data) {
        int n = data.length;

        //outer loop picks the next value to insert
        for (int i = 1; i < n; i++) {
            //inner loop inserts it
            int nextval = data[i];
            int j = i-1;
//            while (j >= 0 && data[j] > nextval) {
             while (j >= 0 && super.greaterThan(data[j], nextval)) {
                data[j + 1] = data[j];
// Add value to super Assign.
                super.assign(j);
                j--;              
            }
            data[j+1] = nextval;
        }
    }

    // Not in use
    private static void pp(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println();
        System.out.flush();
    }
}
