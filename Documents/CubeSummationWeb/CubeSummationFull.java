import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int numberTest = Integer.parseInt(br.readLine());
        int doneTest = 0;
        while(doneTest < numberTest) {
            LinkedList<Point3D> matrix = new LinkedList<>();
            String[] line = br.readLine().split(" ");
            int size = Integer.parseInt(line[0]);
            int operations = Integer.parseInt(line[1]);
            for (int i = 0; i < operations; i++) {
                String[] line_process = br.readLine().split(" ");
                if (line_process[0].equals("UPDATE")) {
                    int x = Integer.parseInt(line_process[1]);
                    int y = Integer.parseInt(line_process[2]);
                    int z = Integer.parseInt(line_process[3]);
                    int value = Integer.parseInt(line_process[4]);
                    boolean find = false;
                    Iterator points = matrix.iterator();
                    while(points.hasNext()){
                        Point3D point = (Point3D)points.next();
                        if(point.x == x && point.y == y && point.z == z){
                            point.value = value;
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        matrix.add(new Point3D(x, y, z, value));
                    } 
                } else {
                    long sum = 0;
                    int x1 = Integer.parseInt(line_process[1]);
                    int y1 = Integer.parseInt(line_process[2]);
                    int z1 = Integer.parseInt(line_process[3]);
                    int x2 = Integer.parseInt(line_process[4]);
                    int y2 = Integer.parseInt(line_process[5]);
                    int z2 = Integer.parseInt(line_process[6]);
                    Iterator points = matrix.iterator();
                    while(points.hasNext()){
                        Point3D point = (Point3D)points.next();
                        if (point.x >= x1 && point.y >= y1 && point.z >= z1
                        && point.x <= x2 && point.y <= y2 && point.z <= z2) {
                            sum += point.value;
                        }
                    }
                    System.out.println(sum);
                }
            }
            doneTest++;
        }
    }
}

class Point3D {
    public int x, y, z;
    public long value;

    public Point3D(int x, int y, int z, long value) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }
}