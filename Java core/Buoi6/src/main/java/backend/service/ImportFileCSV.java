package backend.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface ImportFileCSV<T,K> {
    void validation(String line, List<T> entities,List<String> listErrors,K context);
    void saveAll(List<T> entities);
    void exportErrors(String header,List<String> listErrors,String pathErrorFile);
    default String importCSV(String pathFile,K context,String pathErrorFile) {
        File file = new File(pathFile);
        if (!file.exists()) {
            return "File không tồn tại!!";
        }
        List<T> entities=new ArrayList<>();
        List<String> listErrors=new ArrayList<>();
        String header="";
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            header=br.readLine();
            while((line=br.readLine())!=null){
                this.validation(line,entities,listErrors,context);
            }
            this.saveAll(entities);
            if (listErrors.isEmpty()) {
                this.saveAll(entities);
            } else {
                this.exportErrors(header, listErrors, pathErrorFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listErrors.isEmpty() ? "Import thành công" : "Đã xuất ra file lỗi account_errors.csv";
    }
}