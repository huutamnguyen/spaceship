package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil
{
  private FileUtil()
  {
    super();
  }
  public static List<List<Integer>> getObjectMatrix(String fileUrl)
  {
    List<List<Integer>> objectMatrix = new ArrayList<>();
    BufferedReader bufferedReader;
    try
    {
      bufferedReader = new BufferedReader(new FileReader(fileUrl));
      String s;
      while((s=bufferedReader.readLine())!=null) {
        List<Integer> objectMatrixRow = new ArrayList<>();
        String[] splitted = s.trim().split(" ");
        for (int i = 0; i < splitted.length; i ++)
        {
          objectMatrixRow.add(Integer.parseInt(splitted[i]));
        }
        objectMatrix.add(objectMatrixRow);
      }
      bufferedReader.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return objectMatrix;
  }
}
