import java.io.*;
import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
//finishing importing libraries
//added standard libraries

public class Main {
    // Number of vertices in graph
    static int takenVerticesOfTheGraph = 0;

    //starting time for the programme - run
    static long timingStartAtThisPoint;

    //creating the array for the current programme
    static ArrayList<String> getDetailedInfoOfTheGraph = new ArrayList<>();

    //main method of the programme with exsception
    public static void main(String[] args) throws IOException {

        //importing text file to the system



        // ADD TEXT FILE IN HERE


        // -------------------------------------------------------------------------------------------------------------------------------------

        String importTextFileFromTheFolder = "benchmark/test.txt";

        // -------------------------------------------------------------------------------------------------------------------------------------


        // ADD TEXT FILE IN HERE



        //getting graph - to the programme
        int[][] creatingGraphForTheProgramme = generateGraph(importTextFileFromTheFolder);

       //working with Main class
        Main objForMainClass = new Main();

        // getting source to the program as int
        //value =0
        int gettingSource = 0;

        //gettingSink to the program
        //value =0
        int gettingSink = takenVerticesOfTheGraph-1;
        //printing max flow capacity
        //max




        boolean exitCommandOfMainWindowOfCal=true;
        //welcome massage of the cal
        System.out.println("\nWelcome To The Max Flow Calculation !");

        //importing date and time from your computer
        DateFormat currentDateTimeLive = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date liveDateTaking = new Date();
        //showing date and time
        System.out.println("━━━━━━━ " + currentDateTimeLive.format(liveDateTaking)+" ━━━━━━━━");
        while(exitCommandOfMainWindowOfCal) {

            //user command list - here
            System.out.println("\nINSTRUCTIONS");
            System.out.println("\nPlease select the file before run the program. Default selected file is cw-flow.txt");

            //apress c
            System.out.println("\nPRESS [C] : CALCULATOR");

            //press h
            System.out.println("PRESS [H] : HELP");

            //Exit by "E"
            System.out.println("PRESS [E] : EXIT\n");

            System.out.print("Please Enter Your Command here: ");
            //scanner
            Scanner inputByTheUserCommand = new Scanner(System.in);
            String scanInputOfTheUserTakes = inputByTheUserCommand.nextLine();


            //calling method by user input C
            if (scanInputOfTheUserTakes.equals("C") || scanInputOfTheUserTakes.equals("c")) {
                timingStartAtThisPoint= System.currentTimeMillis();
                StdOut.println("Maximum Flow Capacity Is: " + objForMainClass.fordFulkForTheProgram(creatingGraphForTheProgramme, gettingSource, gettingSink));

                //printing total time taken
                //printing as seconds
                StdOut.println("Total Time Taken Is: "+spentTimeForTheProgram()+" seconds");

                //calling method by user input D
            } else if (scanInputOfTheUserTakes.equals("H") || scanInputOfTheUserTakes.equals("h")) {
                System.out.println("\nPlease select the file before run the program.");
                System.out.println("Eg: bridge_2 ");

                //calling method by user input G
            } else if (scanInputOfTheUserTakes.equals("E") || scanInputOfTheUserTakes.equals("e")) {
                System.exit(0);

        }
    }







    }

    //getting boolean value fr bfs
    private static boolean breadthFirstSearchForThisProgram(int graphDataTaking[][], int s, int t, int varForParent[]) {

        //boolean value checking
        boolean checkVisiting[] = new boolean[takenVerticesOfTheGraph];

        //checking by for loop
        for (int i = 0; i < takenVerticesOfTheGraph; ++i)

            //make checking visit as false
            checkVisiting[i] = false;

        //getting linked list
        LinkedList<Integer> queueListForProgram = new LinkedList<Integer>();

        //adding
        queueListForProgram.add(s);

        //making checkVisiting as true
        checkVisiting[s] = true;

        //assigns -1
        varForParent[s] = -1;

        //while loop
        while (queueListForProgram.size() != 0) {
            int u = queueListForProgram.poll();

            //for loop
            for (int v = 0; v < takenVerticesOfTheGraph; v++) {

                //checking vising - if false
                if (checkVisiting[v] == false
                        && graphDataTaking[u][v] > 0) {

                    //if condition
                    if (v == t) {
                        varForParent[v] = u;

                        //returning true
                        return true;
                    }

                    //adding
                    queueListForProgram.add(v);
                    varForParent[v] = u;

                    //making true
                    checkVisiting[v] = true;
                }
            }
        }

        //returning false
        return false;
    }


    private static int fordFulkForTheProgram(int graphForFord[][], int s, int t) {
        //getting integers
        int u, v;

        //taking graph -dt
        int graphTaking[][] = new int[takenVerticesOfTheGraph][takenVerticesOfTheGraph];

        //for loop
        //u makes 0
        for (u = 0; u < takenVerticesOfTheGraph; u++)

            //for loop  -v++
            for (v = 0; v < takenVerticesOfTheGraph; v++)

                //taking graph for u and v
                graphTaking[u][v] = graphForFord[u][v];


        int parentValuesForFor[] = new int[takenVerticesOfTheGraph];

        //max Flow Taking
        int maxFlowForTheProg = 0;

        // taling flow list
        List<Integer> flowListForProg = new ArrayList<>();

        //getting total flow list
        List<Integer> totalFlowListForProg = new ArrayList<>();

        //while loop
        while (breadthFirstSearchForThisProgram(graphTaking, s, t, parentValuesForFor)) {

            //getting path flow
            int assignPathFlow = Integer.MAX_VALUE;

            //for loop
            for (v = t; v != s; v = parentValuesForFor[v]) {

                //assign u
                u = parentValuesForFor[v];

                //adding to flow list
                flowListForProg.add(u);

                //adding to total flow list
                totalFlowListForProg.add(assignPathFlow);

                //path flow - program
                assignPathFlow = Math.min(assignPathFlow, graphTaking[u][v]);
            }

            //for loop
            for (v = t; v != s; v = parentValuesForFor[v]) {
                //assign u
                u = parentValuesForFor[v];

                graphTaking[u][v] -= assignPathFlow;
                graphTaking[v][u] += assignPathFlow;
            }

            //printing bottle neck capacity
            StdOut.println("\nBottle Neck Capacity Is: "+assignPathFlow);

            //work with max
            maxFlowForTheProg += assignPathFlow;

            //-1 from vertices
            flowListForProg.add(takenVerticesOfTheGraph-1);

            //sorting
            Collections.sort(flowListForProg);

            //printing augmenting path
            StdOut.println("Augmenting Path Is: "+Arrays.toString(flowListForProg.toArray())+"\n");

            //clearing
            flowListForProg.clear();
        }

        //returning maximum flow
        return maxFlowForTheProg;
    }

    //method for time calculation
    public static double spentTimeForTheProgram() {
        //current time in milliseconds
        long timeLive = System.currentTimeMillis();

        //returning time
        return (timeLive - timingStartAtThisPoint) / 1000.0;
    }

    //creating graph
    private static int[][] generateGraph(String dataFileName) throws IOException {

        //reading file
        readFile(dataFileName);

        //getting details of the graph
        int vertexForProg = Integer.parseInt(getDetailedInfoOfTheGraph.get(0));

        //matrix of the program
        int[][] matrixOfTheProgram = new int[vertexForProg][vertexForProg];

        //edge list for program
        ArrayList<Edge> edgeListForProg = new ArrayList<Edge>();

        //for loop - i++
        for (int i = 1; i < getDetailedInfoOfTheGraph.size(); i++) {

            //string line 01
            String line1ForThePRog = getDetailedInfoOfTheGraph.get(i);

            //relation - edge
            //limited to 3
            String[] realtionOfTheEdge = line1ForThePRog.split(" ", 3);

            //adding edges
            edgeListForProg.add(new Edge(

                    //relations - 0
                    Integer.parseInt(realtionOfTheEdge[0]),
                    //relations - 1
                    Integer.parseInt(realtionOfTheEdge[1]),
                    //relations - 2
                    Integer.parseInt(realtionOfTheEdge[2])
            ));
        }

        //for loop
        for (int i = 0; i < edgeListForProg.size(); i++ ) {
            //edge
            Edge currentEdgeOfTheProgr = edgeListForProg.get(i);

            //start -vertex
            int startVertexTaking = currentEdgeOfTheProgr.vertexStartForPRogram;
            //end - vertex
            int endVertexTaking = currentEdgeOfTheProgr.vertexEnfForPRog;
            //weight - vertex
            int weightTaking = currentEdgeOfTheProgr.vertexWeight;

            //matrix
            matrixOfTheProgram[startVertexTaking][endVertexTaking] = weightTaking;
        }

        //returning matrix
        return matrixOfTheProgram;
    }

//reading flie
    private static List<String> readFile(String filename) throws IOException {

        //reading file
        FileReader fileReaderForProg = new FileReader(filename);

        //buf - reader using
        BufferedReader bufferedReaderForProg = new BufferedReader(fileReaderForProg);

        //reading data
        String readDataOfFile = bufferedReaderForProg.readLine();

        //vertices - graph
        takenVerticesOfTheGraph = Integer.parseInt(readDataOfFile);

        getDetailedInfoOfTheGraph.add(readDataOfFile);

        //while loop
        while ((readDataOfFile = bufferedReaderForProg.readLine()) != null) {

            getDetailedInfoOfTheGraph.add(readDataOfFile);
        }

        //retuning
        return getDetailedInfoOfTheGraph;
    }
}

class Edge {
    //vertex - taking -end
    int vertexStartForPRogram;

    //vertex - taking - end
    int vertexEnfForPRog;

    //vertex - taking -weight
    int vertexWeight;

    //edge
    public Edge(int startVertexForMethod, int endVertexForMethod, int weightForMethod) {

        //start vertex
        this.vertexStartForPRogram = startVertexForMethod;

        //end vertex
        this.vertexEnfForPRog = endVertexForMethod;

        //weight vertex
        this.vertexWeight = weightForMethod;
    }
}


//w1790272