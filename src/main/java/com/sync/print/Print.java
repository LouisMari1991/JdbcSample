package com.sync.print;

/**
 *
 * 输出M形状
 *
 * Created by Administrator on 2016/11/28 0028.
 */
public class Print {

  public static void main(String[] args) {
    int num = 20;

    int height = num / 4 + 1;
    int width = num;

    int arr[][] = new int[height][width];

    int x = height - 1;
    int y = 0;

    boolean order = false;
    for (int i = 1; i <= num; i++) {
      arr[x][y] = i;
      y++;

      if (!order) {
        x--;
      }
      if (order) {
        x++;
      }

      if (x < 0) {
        order = true;
        x = x + 2;
      }
      if (x > height - 1) {
        order = false;
        x = x - 2;
      }
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == 0) {
          System.out.print("  ");
        } else {
          System.out.print(arr[i][j]);
        }
      }
      System.out.println();
    }
  }
}
