/*
 * @student Aaqib Tariq
 * @class CISC 1170
 * @Description: we will analyze the rise in temperature over the last 200 
    years. This is known as global warming or climate change. The National 
    Weather Service maintains historical data on the average monthly & annual 
    temperatures at Central Park, NYC since 1869. February on the average is 
    the coldest month and August is on the average the warmest month. This 
    program consist on the different methods. first it has main methods 
    which is used to call all other functions. Then use  Mode, Median, 
    Standar derivaties, highestLowest, and sorting methods to perform different
    actions on the file "NYCTemperatureData" which has data from 1869 to 2017.
    then at the end there are STD methods to draw grids and graphs lines for 
    Feb, Aug, and Annual data change over the 150 years. At the end, there is 
    method called label use to print different label on the graph. Overall, 
    after executing this function, you will see beautiful graph and that analyze
    data of 150 years of temperature change.
*/

package analysisoftemperature;

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import edu.princeton.cs.introcs.*;
import static edu.princeton.cs.introcs.StdDraw.point;

public class AnalysisOfTemperature {

    public static PrintStream ps;

    
    
    public static void main(String[] args) throws Exception {

        final int MAXYEARS = 149;
        double count;
        ps = new PrintStream("TempStatistics.txt");

        int[] year = new int[MAXYEARS];

        double[] TempOfFeb = new double[MAXYEARS];
        double[] TempOfAug = new double[MAXYEARS];
        double[] TempAnnual = new double[MAXYEARS];

        double[] TempFebArr = new double[MAXYEARS];
        double[] TempAugArr = new double[MAXYEARS];
        double[] TempAnnualArr = new double[MAXYEARS];

        // print NYCTemperatureData
        count = populateArray(year, TempOfFeb, TempOfAug, TempAnnual);
        print(year, TempOfFeb, TempOfAug, TempAnnual, (int) count);
        
        // print Mean Values of Feb
        double meanOfFeb = Mean(TempOfFeb, (int) count);
        ps.printf("\n\n The Mean Temperature of Feb for period 1869-2017 "
                + " is %.2f\n", meanOfFeb);
        
         // print Mean Values Aug
        double meanOfAug = Mean(TempOfAug, (int) count);
        ps.printf(" The Mean Temperature of Aug for period 1869-2017 "
                + "is %.2f\n", meanOfAug);
        
         // print Mean Values Annual Average
        double meanOfAnnual = Mean(TempAnnual, (int) count);
        ps.printf(" The Mean Temperature of Annual Average for period "
                + "1869-2017 is %.2f\n\n\n", meanOfAnnual);

        // print standard deviation of Feb
        double SDFeb = SD(TempOfFeb, meanOfFeb, (int) count);
        ps.printf(" The standard deviation of Feb in NYC for period "
                + "1869-2017 is %.4f\n", SDFeb);
        
        // print standard deviation of Aug
        double SDAug = SD(TempOfAug, meanOfAug, (int) count);
        ps.printf(" The standard deviation of Aug in NYC for period "
                + "1869-2017 is %.4f\n", SDAug);

        // print standard deviation of Annual Average
        double SDAnnual = SD(TempAnnual, meanOfAnnual, (int) count);
        ps.printf(" The standard deviation of Annual Average for period "
                + "1869-2017 is %.4f\n\n\n", SDAnnual);

        // print NYCTemperatureData
        sort(TempOfFeb, TempOfAug, TempAnnual, TempFebArr, TempAugArr,
                TempAnnualArr);

        // print Median Values of Feb, Aug, and Annual average
        Median(TempOfFeb, TempOfAug, TempAnnual, TempFebArr, TempAugArr, 
                TempAnnualArr, (int) count);
        
        // print Lowest and Highest Values of Feb, Aug, and Annual average
        HighestLowest(TempOfFeb, TempOfAug, TempAnnual, TempFebArr, TempAugArr,
                TempAnnualArr, (int) count);

        // print mode value of Feb
        double modeOfFeb = Mode(TempOfFeb, (int) count);
        ps.printf(" Mode of Feb temperature for period 1869-2017 is " 
                + modeOfFeb + "\n");

        // print mode value of Aug
        double modeOfAug = Mode(TempOfAug, (int) count);
        ps.printf(" Mode of Aug temperature for period 1869-2017 is " 
                + modeOfAug + "\n");
        
        // print mode value of Annual Average
        double modeOfAnnual = Mode(TempAnnual, (int) count);
        ps.printf(" Mode of Annual temperature for period 1869-2017 is "
                + modeOfAnnual  + "\n\n\n");
        
        //  prints the data for each year
        print(year, TempOfFeb, TempOfAug, TempAnnual, (int) count);
        
        /* these draw graph grid and print lines for NYCTemperatureData change
        of Feb, Aug, and annual  average 
        */
        int canvasSize = 800;
        drawGrid(canvasSize);
        drawFeb(year, TempOfFeb, (int) count);
        drawAug(year, TempOfAug, (int) count);
        drawAnnual(year, TempAnnual, (int) count);
        label();

    }

    /*
      Method populateArrary reads a number one at a time, places it into the
      next available slot of the array and returns the number of values that
      were read.
     */
    public static double populateArray(int[] year, double[] TempOfFeb, double[] 
            TempOfAug, double[] TempAnnual) throws Exception {
        Scanner sc = new Scanner(new File("NYCTemperatureData.txt"));

        int count = 0;

        sc.nextLine();
        int i = 0;
        while (sc.hasNext()) {
            year[i] = sc.nextInt();
            TempOfFeb[i] = sc.nextDouble();
            TempOfAug[i] = sc.nextDouble();
            TempAnnual[i] = sc.nextDouble();
            i++;
            count++;
        }
        return count;
    } // end populateArray

    // Print method use to print sorted data in the file.
    public static void print(int[] year, double[] TempOfFeb, double[] TempOfAug,
            double[] TempAnnual, int count) {
        ps.printf("Year\tFeb\tAug\tAnnual\n");

        for (int i = 0; i < count; i++) {

            ps.printf(year[i] + "\t");
            ps.printf(TempOfFeb[i] + "\t");
            ps.printf(TempOfAug[i] + "\t");
            ps.printf(TempAnnual[i] + "\n");
        }
    }
    
    // method that find Mean values
    public static double Mean(double[] arr, int count) {

        int i =0 ;
        double mean, sum = 0;
        for (i = 0; i < count; i++) {
            sum += arr[i];

        }
        mean = (sum / i);
        return mean;

    }
    // Methof that find standard deviation
    public static double SD(double[] arr, double mean, int count) {

        int i = 0;
        double var = 0, SD = 0;

        for (i = 0; i < count; i++) {

            var += (Math.pow(arr[i] - mean, 2));
            SD = Math.sqrt(var / count);

        }
        return SD;
    }
    
    // Method used for sorting
    public static void sort(double TempOfFeb[], double TempOfAug[], 
            double TempAnnual[], double[] TempFebArr, double[] TempAugArr,
            double[] TempAnnualArr) {

        final int MAXYEARS = 149;

        for (int i = 0; i < MAXYEARS; i++) {

            TempFebArr[i] = TempOfFeb[i];
            TempAugArr[i] = TempOfAug[i];
            TempAnnualArr[i] = TempAnnual[i];

        }
        
    }
    
    // MEthod use to find Median Value
    public static void Median(double TempOfFeb[], double TempOfAug[], 
            double TempAnnual[],double TempFebArr[], double TempAugArr[],
            double TempAnnualArr[], int count) {

        if (count % 2 == 0) {
            ps.printf(" The median temp of Feb for period 1869-2017 is " 
                    + TempFebArr[count / 2] + "\n");

            ps.printf(" The median temp of Aug for period 1869-2017 is " 
                    + TempAugArr[count / 2] + "\n");

            ps.printf(" The median temp of Annual for period 1869-2017 is " 
                    + TempAnnualArr[count / 2] + "\n\n\n");

        } else {
            ps.printf(" The median temp of Feb for period 1869-2017 is "
                    + TempFebArr[((count / 2) + ((count / 2) + 1)) / 2] + "\n");

            ps.printf(" The median temp of Aug for period 1869-2017 is "
                    + TempAugArr[((count / 2) + ((count / 2) + 1)) / 2] + "\n");

            ps.printf(" The median temp of Annual for period 1869-2017 is "
                    + TempAnnualArr[((count / 2) + ((count / 2) + 1)) / 2] 
                    + "\n\n\n");

        }
    }
    
    // method use to find lowest and highest temperatue
    public static void HighestLowest(double[] TempOfFeb, double[] TempOfAug, 
            double[] TempAnnual, double[] TempFebArr, double[] TempAugArr, 
            double[] TempAnnualArr, int count) {

        ps.printf(" The Highest temp of Feb in NYC for period 1869-2017 is "
                + TempFebArr[count - 1] + "\n");

        ps.printf(" The Lowest temp of Feb in NYC for period 1869-2017 is "
                + TempFebArr[0] + "\n");

        ps.printf(" The Highest temp of Aug in NYC for period 1869-2017 is "
                + TempAugArr[count - 1] + "\n");

        ps.printf(" The Lowest temp of Aug in NYC for period 1869-2017 is "
                + TempAugArr[0] + "\n");

        ps.printf(" The Highest temp of Annual in NYC for period 1869-2017 is "
                + TempAnnualArr[count - 1] + "\n");

        ps.printf(" The Lowest temp of Annual in NYC for period 1869-2017 is "
                + TempAnnualArr[0] + "\n\n\n");

    }
    // method use to find find Mode of vlaues
    public static double Mode(double[] arr, int count) {

        double modeArr = 0, maxArr = 0;

        for (int i = 0; i < count; i++) {
            int a = 0;
            for (int j = 0; j < count; j++) {
                if (arr[j] == arr[i]) {
                    a++;
                }
                if (a > maxArr) {
                    maxArr = a;
                    modeArr = arr[i];
                }
            }
        }
        return modeArr;
    }
    // method use to graw Red line on the graph for Feb Data
    public static void drawFeb(int[] year, double[] TempOfFeb, int count) {

        double x;
        double x1 = 0, y1 = 0, x1y1 = 0, x1x1 = 0, N = count, XX, XY, m, b, y;

        for (int i = 0; i < count; i++) {
            StdDraw.setPenRadius(0.02);
            point(year[i], TempOfFeb[i]);
            x1 += year[i];
            y1 += TempOfFeb[i];
            x1y1 += (year[i] * TempOfFeb[i]);
            x1x1 += (year[i] * year[i]);

        }

        XX = x1 * y1;
        XY = x1 * y1;
        m = (N * x1y1 - XY) / (N * x1x1 - XX);
        b = (y1 - m * x1) / N;

        for (x = 1840; x <= 2020; x += 0.5) {

            y = m * x + b;;

            StdDraw.line(x, y, x, y);
            StdDraw.setPenColor(250, 8, 8);
        }
    }
    
    // method use to draw sky blue line on the graph for Aug Data
    public static void drawAug(int[] year, double[] TempOfAug, int count) {

        StdDraw.setPenColor(0, 0, 0);
        double x;
        double x1 = 0, y1 = 0, x1y1 = 0, x1x1 = 0, N = count, XX, XY, m, b, y;

        for (int i = 0; i < count; i++) {
            StdDraw.setPenRadius(0.02);
            point(year[i], TempOfAug[i]);
            x1 += year[i];
            y1 += TempOfAug[i];
            x1y1 += (year[i] * TempOfAug[i]);
            x1x1 += (year[i] * year[i]);

        }

        XX = x1 * y1;
        XY = x1 * y1;
        m = (N * x1y1 - XY) / (N * x1x1 - XX);
        b = (y1 - m * x1) / N;

        for (x = 1840; x <= 2020; x += 0.5) {

            y = m * x + b;

            StdDraw.line(x, y, x, y);
            StdDraw.setPenColor(8, 250, 226);
        }
    }

    // method use to draw pink line on the graph for Annual Average of Data
    public static void drawAnnual(int[] year, double[] TempAnnual, int count) {

        StdDraw.setPenColor(0, 0, 0);
        double x;
        double x1 = 0, y1 = 0, x1y1 = 0, x1x1 = 0, N = count, XX, XY, m, b, y;

        for (int i = 0; i < count; i++) {
            StdDraw.setPenRadius(0.02);
            point(year[i], TempAnnual[i]);
            x1 += year[i];
            y1 += TempAnnual[i];
            x1y1 += (year[i] * TempAnnual[i]);
            x1x1 += (year[i] * year[i]);

        }

        XX = x1 * y1;
        XY = x1 * y1;
        m = (N * x1y1 - XY) / (N * x1x1 - XX);
        b = (y1 - m * x1) / N;

        for (x = 1840; x <= 2020; x += 0.5) {

            y = m * x + b;

            StdDraw.line(x, y, x, y);
            StdDraw.setPenColor(250, 8, 234);
        }
    }

    /*
    The Follwing method will create a graph on the drawing window.
    By supplying different values and canvas size, you can control how the 
    grid appears.
     */
    public static void drawGrid(int canvasSize) {
        final int LOW = 1840, HIGH = 2020;
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setXscale(LOW , HIGH);
        StdDraw.setYscale(-10, 90);

        // Draw Grid
        for (int i = -10; i < HIGH; i += 5) {
            StdDraw.line(0, i, HIGH, i);
            StdDraw.line(2 * i, -10, 2 * i, HIGH);
        }

        // Draw Axis
        StdDraw.setPenRadius(0.01);
        StdDraw.line(0, 0, HIGH, 0);
        StdDraw.line(1860, 0, 1860, 90);
    }

    /* method use to draw different labels on the graph. It draw 20, 40, 60, 80,
         Aug, Feb, Annual Average and Temperature vertically. 
        1869, 1950, 2017, NYC Temperature Change Data, AND year Horizontally.
    */
    public static void label() {

        StdDraw.setPenColor(4, 35, 238);

        StdDraw.text(2010, -7, "YEAR");
        StdDraw.text(1880, 87, "Temperature");
        StdDraw.text(1924, -8, " NYC Temperature Change Data");

        StdDraw.text(1855, 20, "20");
        StdDraw.text(1855, 40, "40");
        StdDraw.text(1855, 60, "60");
        StdDraw.text(1855, 80, "80");
        StdDraw.text(1860, -3, "1869");
        StdDraw.text(1950, -3, "1950");
        StdDraw.text(2010, -3, "2017");
        StdDraw.text(2000, 82, "Aug");
        StdDraw.text(2000, 60, "Annual Average");
        StdDraw.text(2000, 43, "Feb");

    }

}
