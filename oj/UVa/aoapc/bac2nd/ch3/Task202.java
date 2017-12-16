package algsPractice.oj.UVa.aoapc.bac2nd.ch3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * Created by Scruel on 2017/4/4.  
 * Github : https://github.com/scruel
 * #大整数除法  #string
 */
public class Task202 {
  static BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    String s;
    while ((s = bfr.readLine()) != null && s.length() != 0) {
      String[] rTs = s.split(" ");
      div(Integer.parseInt(rTs[0]), Integer.parseInt(rTs[1]));
    }
    bfr.close();
    bfw.close();
  }


  static void div(int m, int n) throws IOException {
    int tempM = m;
    int tempN = n;
    String result = "";
    //必须对每个符号再确认！！！
    while (m >= n) {
      result += m / n;
      m -= m / n * n;
    }
    if (result.length() == 0) {
      result += "0";
    }
    result += ".";
    int zS = result.length();
    int sum = 0;
    LinkedList<Integer> list = new LinkedList<Integer>();
    //这里的思想是，如果再次出现相同的m，那么接下来一定会有相同序列。
    //做题的时候不能被题目的分类迷惑，也不能随便想分类情况，要多方面考虑可行性及解法。
    while (sum < 99999999) {
      m = (int) (m * 10 + 1e-9);
      int index = list.indexOf(m);
      if (index != -1) {
        String preStr = result.substring(0, zS + index);
        String cycStr = result.substring(zS + index);
        bfw.write(tempM + "/" + tempN + " = " + preStr + "(");
        if (preStr.length() - zS + cycStr.length() > 50) {
          bfw.write(cycStr.substring(0, 50 - index) + "...)\n");
        }
        else {
          bfw.write(cycStr + ")\n");
        }
        bfw.write("   " + cycStr.length() + " = number of digits in repeating cycle\n");
        bfw.write("\n");
        return;
      }
      list.add(m);
      //必须对每个符号再确认！！！
      if (m < n) {
        result += "0";
      }
      else {
        result += m / n + "";
        m = (int) (m - m / n * n + 1e-10);
      }
      sum++;

    }
    String preStr;
    if (result.length() > 50) {
      preStr = result.substring(0, zS + 50) + "...";
    }
    else {
      preStr = result;
    }
    bfw.write(tempM + "/" + tempN + " = " + preStr + "\n");
    bfw.write("   0 = number of digits in repeating cycle\n");
    //输出细节
    bfw.write("\n");
    //                System.out.println(result);
  }
}
