import java.util.Arrays;



/**
 *
 * @author evenal
 */
public class QuickSortAnalysis extends EmpiricalAnalysis 
{
    
    public QuickSortAnalysis() {
        super("Quicksort");
    }

    @Override
    protected void run(int[] testdata) {
        qSort(testdata);
    }

    
    /**
     * A straightforward and slightly naive
     * implementation of QuickSort
     */
    public void qSort(int[] data) {
        qSort(data, 0, data.length);
    }

    public static void main(String[]args){
        QuickSortAnalysis test = new QuickSortAnalysis();
        int[] data = {5, 2 ,7 ,8 ,9 ,1, 6, 3, 4 };
        //int[] data = {1,2,3,4,5,6,7,8,9};
        System.out.println("Data to sort:" + Arrays.toString(data));
        test.qSort(data);
        System.out.println("Sorted data:" + Arrays.toString(data));
    }
    public void qSort(int[] data, int lo, int hi) { 
    
        if (lo < hi) {
            int p = partition(data, lo, hi);
            qSort(data, lo, p);
            qSort(data, p + 1, hi);
        }
    }


    public int partition(int[] data, int lo, int hi) {
         System.out.println("..sorting.." + Arrays.toString(data));  
        int ilo = lo;
        int ihi = hi - 1;
        int p = data[ihi];

        for (ilo = lo; ilo < ihi;) {
            while (lessThan(data[ilo], p)) ilo++;
            while (greaterThan(data[ihi], p))
                ihi--;
            swap(data, ilo, ihi);
        }
        return ihi;
    }

    private static void pp(int[] data) {
        for (int i = 0; i < data.length; i++)
            System.out.print(" " + data[i]);
        System.out.println();
        System.out.flush();
    }
}
