/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandatory_01.code;

/**
 *
 * @author evenal
 */
public class BubbleSortAnalysis extends EmpiricalAnalysis {

    public BubbleSortAnalysis() {
        super("Bubble Sort");
    }

    @Override
    protected void run(int[] testdata) {
        bubbleSort(testdata);
    }

    public void bubbleSort(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
// Added greaterThan() from superclass EmpiricalAnalysis method for analysis
//               if (data[j] > data[j + 1]) {
                int v1 = data[j];
                int v2 = data[j + 1];
                if (super.greaterThan(v1, v2)) {
//  Replaced swapp() with swap() from superclass EmpiricalAnalysis for analysis.
//                    swapp(data, j, j+1);
                    super.swap(data, j, j+1);
                }
            }
        }
    }
    
//Not used
    public void swapp(int[] data, int i1, int i2) {
       int tmp = data[i1];
       data[i1] = data[i2];
       data[i2] = tmp;
    }
}
