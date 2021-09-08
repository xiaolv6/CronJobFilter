import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CronJobFilter {
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("\"([0-9*].*\\s[0-9*]).*/(.*)\\.js");
        File file = new File(args[0]);
        StringBuilder result = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String oneLine = null;
            while((oneLine = br.readLine())!=null){
                if (oneLine.contains("cron") || oneLine.contains("config")) {
                    Matcher regCron = pattern.matcher(oneLine);
                    if (regCron.find() ){
                        result.append(regCron.group(1)+" jd "+regCron.group(2)+"\n");
                    }
                }
            }
            FileWriter fileWriter = new FileWriter(args[1]);
            fileWriter.write(result.toString());
            fileWriter.flush();
            //System.out.println(result);
            fileWriter.close();
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
