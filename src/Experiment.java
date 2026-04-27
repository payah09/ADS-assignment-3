import java.util.Arrays;

public class Experiment {
    private Sorter sorter =  new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type){
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        if(type.equalsIgnoreCase("basic")) sorter.basicSort(copy);
        else sorter.advancedSort(copy);
        return System.nanoTime() - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        return System.nanoTime() - start;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};
        System.out.println("Size\tType\t\tBasic(ns)\tAdvanced(ns)\tSearch(ns)");

        for (int size : sizes) {
            int[] randomArr = sorter.generateRandomArray(size);
            int target = -1;

            long basicTime = measureSortTime(randomArr, "basic");
            long advTime = measureSortTime(randomArr, "advanced");
            long searchTime = measureSearchTime(randomArr, target);
            if(size == 1000) {
                System.out.printf("%d\tRandom\t\t%d\t\t%d\t\t%d\n", size, basicTime, advTime, searchTime);
                Arrays.sort(randomArr);
                basicTime = measureSortTime(randomArr, "basic");
                advTime = measureSortTime(randomArr, "advanced");
                System.out.printf("%d\tSorted\t\t%d\t\t%d\t\t---\n", size, basicTime, advTime);
                continue;
            }
            System.out.printf("%d\t\tRandom\t\t%d\t\t%d\t\t\t%d\n", size, basicTime, advTime, searchTime);

            Arrays.sort(randomArr);
            basicTime = measureSortTime(randomArr, "basic");
            advTime = measureSortTime(randomArr, "advanced");
            System.out.printf("%d\t\tSorted\t\t%d\t\t%d\t\t\t---\n", size, basicTime, advTime);
        }
    }
}
