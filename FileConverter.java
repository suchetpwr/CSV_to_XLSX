package fileconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileConverter {

    public static void main(String[] args) {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheetAddress = workbook.createSheet("Address");
      XSSFRow row;
      String filePath = "C:\\Users\\dell\\Desktop\\Home-Work\\file converter\\addresses.csv";
      String reader = "";
      int temp = 0;
      String comma = ",";
      int counter = 0;
      List<String> lst = new ArrayList<>();
      
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(filePath));
            while((reader = buffer.readLine()) != null){
                String[] tempArr = reader.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                counter = tempArr.length;
                break;
            }
            while((reader = buffer.readLine()) != null){
                String[] arr = reader.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                lst.addAll(Arrays.asList(arr));
            }
            //System.out.println(lst);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        /*row = sheetAddress.createRow(0);
            for(int i=0;i<counter;i++){
                row.createCell(i).setCellValue("Header");
            }*/
        for(int i=0; i<lst.size()/counter;i++){
            row = sheetAddress.createRow(i);
            for(int j=0;j<counter;j++){
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(lst.get(temp));
                if(temp == lst.size()-1){
                    break;
                }
                else{
                    temp++;
                }
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream(new File("addresses.xlsx"));
            workbook.write(fos);
            fos.close();
            System.out.println("The file is converted!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
