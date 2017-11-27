package entity;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import def.LabOne;


public class Graph{

  static Map<String,Integer> mapStringToNum = new HashMap<String,Integer>();
  static Map<String,Integer> mapStringToPos = new HashMap<String,Integer>();
  static ArrayList<String> strs = new ArrayList<>();
  static int[][] wordMatrix;
  public static String graphstr;
  

  



  /**
   * randomWalk.
   * @return random walk path
   */
  
  public String randomWalk() {

    String result = "";
    int n = mapStringToNum.size();

    int[][] myMatrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        myMatrix[i][j] = wordMatrix[i][j];
      }
    }
    
    int pointX = (int) Math.ceil(Math.random() * n);
    
    while (judgePath(pointX, myMatrix) == 0 || pointX >= n) {
      pointX = (int) (Math.ceil(Math.random() * n));
    }

    int pointY = 0;
    while (judgePath(pointX, myMatrix) != 0) {
      pointY = (int) (Math.ceil(Math.random() * judgePath(pointX, myMatrix)));
      while (pointY == 0) {
        pointY = (int) (Math.ceil(Math.random() * judgePath(pointX, myMatrix)));
      }
      for (int i = 0; i < n; i++) {
        if (wordMatrix[pointX][i] != 0) {
          pointY = pointY - 1;
        }
        if (pointY == 0) {
          pointY = i;
          i = n;
        }
      }

      myMatrix[pointX][pointY] = 0;
      result = result + strs.get(pointX) + " ";
      pointX = pointY;
    }

    result = result + strs.get(pointX);
    return result;
  }

  private int judgePath(final int x, final int[][] wordMatrix) {
    int path = 0;
    int n = mapStringToNum.size();
    for (int i = 0; i < n; i++) {
      if (wordMatrix[x][i] != 0) {
        path = path + 1;
      }
    }

    return path;
  }

  /**
   * write text to file.
   */


  

  // for dijstra implement 
  private static final int INFINITY = 10000;
  static int[] path;
  static int[] dist;
  
  /**
   * Find the minimum path.
   */
  public int FindMinDist(int []dist,int []collected){
    int MinV = INFINITY;
    int MinDist = INFINITY;
    //收录
    for (int i = 0; i < mapStringToPos.size(); i++) {
      if (collected[i] == 0 && dist[i] < MinDist) {
        MinDist = dist[i];
        MinV = i;
      }
    }
    if (MinDist < INFINITY) {
      return MinV;
    } else {
      return INFINITY;
    }

  }
  
  /**
   * implement of Dijstra.
   */

  public boolean Dijkstra(final String word1) {
    int []collected = new int[mapStringToPos.size()];
    dist = new int[mapStringToPos.size()];
    path = new int[mapStringToPos.size()];
    int num1 = 0, num2 = 0;
    //初始化
    for (int i = 0; i < mapStringToPos.size(); i++) {
      if (wordMatrix[mapStringToPos.get(word1)][i] == 0) {
        dist[i] = INFINITY;
        path[i] = -1;
      } else {
        dist[i] = wordMatrix[mapStringToPos.get(word1)][i];
        path[i] = mapStringToPos.get(word1);
      }
      collected[i] = 0;
    }
    //收入源点
    dist[mapStringToPos.get(word1)] = 0;
    collected[mapStringToPos.get(word1)] = 1;
    while (true) {
      num1 = FindMinDist(dist, collected);
      if (num1 == INFINITY) {
        break;
      }
      collected[num1] = 1;
      for (num2 = 0; num2 < mapStringToPos.size(); num2++) {
        if (collected[num2] == 0 && wordMatrix[num1][num2] != 0) {
          if (wordMatrix[num1][num2] < 0){
            return false;
          }
          if (dist[num1] + wordMatrix[num1][num2] < dist[num2]) {
            dist[num2] = dist[num1] + wordMatrix[num1][num2];
            path[num2] = num1;
          }
        }
      }
    }
    return true;
  }
  
  /**
   * calculate the shortest path.
   */
  public String calcShortestPath(String word1, String word2) {

    if (word2.equals("")) {
      int x = (int) Math.ceil(Math.random() * (strs.size()));
      while (strs.get(x).equals(word1)) {
        x = (int) Math.ceil(Math.random() * (strs.size()));
        //System.out.println(x+"");
      }
      word2 = strs.get(x);
      //System.out.println(word2);
    }

    
    String str1 = "", str2="";
    int num2 = mapStringToPos.get(word2);
    Dijkstra(word1);
    if (path[num2] == -1) {
      System.out.println("不可达");
      return null;
    }
    str1 = word2;
    int num1 = mapStringToPos.get(word1);
    while (path[num2] != num1) {
      str1 = strs.get(path[num2]) + "->" + str1;
      num2 = path[num2];
    }
    str1 = strs.get(path[num2]) + "->" + str1;
    System.out.println(str1);
    num1 = mapStringToPos.get(word1);
    num2 = mapStringToPos.get(word2);
    str2 = word2;
    graphstr = new String(LabOne.DotStr);
    while (path[num2] != num1) {
      str2 = strs.get(path[num2]) + "->" + str2;
      graphstr = new String(graphstr.replace(str2,str2 + "[color=" + "\"red\"]"));
      str2 = new String(strs.get(path[num2]));
      num2 = path[num2];
    }
    str2 = strs.get(path[num2]) + "->" + str2;
    graphstr = new String(graphstr.replace(str2, str2 + "[color=" + "\"red\"]"));
    str2.replace(str2, strs.get(path[num2]));
    System.out.println("red test:" + graphstr);
    return str1;
  }
  public void TextToFile(final String str) throws IOException {
	    final File fileText = new File("randomWalk.txt");
	    FileWriter fileWriter = null;
	    try {
	      fileWriter = new FileWriter(fileText);
	      fileWriter.write(str);
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    fileWriter.close();
	  }

}
