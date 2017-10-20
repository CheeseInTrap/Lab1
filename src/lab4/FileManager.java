package lab4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {

  /**
   * ��ȡָ���ļ����ֵ�����.
   * @param file ��ʾ�ļ�������
   * @return  String
   */
  public static String readFile(final File file) {

    final StringBuilder strb = new StringBuilder();
    //String text = "";
    try {
      Scanner scan;
      scan = new Scanner(file);
      if (scan != null) {
          while (scan.hasNextLine()) {
            strb.append(scan.nextLine());
          }
          scan.close();
        }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return strb.toString();
  }
  /**
   * ɾ��ָ�����ֵ��ļ�.
   * @param fileName ��ʾҪɾ���ļ�������
   * @return boolean
   */
  
  public static boolean deleteFile(final String fileName) {
    final File file = new File(fileName);
    if (file.exists() && file.isFile() && file.delete()) {
      return true;
    } else {
      return false;
    }
  }
}
