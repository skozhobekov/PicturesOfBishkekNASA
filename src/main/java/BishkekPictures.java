import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BishkekPictures {

    private static final String apiKey = "L2cjkOp3FsfwyDcD8LxJ6OxnpCofhSaTzW3pLXZ6";
    private static final double bishkekLAT = 42.8844;
    private static final double bishkekLON = 74.5766;
    private static final double dim = 0.03;
    private static final String folderToSave = "./bishkek_images/";

    public static String getApiKey() { return apiKey;
    }
    public static double getBishkekLAT() { return bishkekLAT;
    }
    public static double getBishkekLON() { return bishkekLON;
    }
    public static double getDim() { return dim;
    }
    public static String getFolderToSave() { return folderToSave;
    }
public static String geturlAddress() {
        return geturlAddress();
}

    static {
        File dir = new File(folderToSave);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
   // https://api.nasa.gov/planetary/earth/imagery?lon=74.5766&lat=42.8844&date=2025-01-01&dim=0.03&api_key=L2cjkOp3FsfwyDcD8LxJ6OxnpCofhSaTzW3pLXZ6

    public static void downloadPicture(String date) throws IOException {
        try {
            String baseUrl = "https://api.nasa.gov/planetary/earth/imagery?";
            String urlAddress = baseUrl + "lon=" + bishkekLON +
                    "&lat=" + bishkekLAT +
                    "&date=" + date +
                    "&dim=" + dim +
                    "&api_key=" + apiKey;


            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream()) {
                    String fileName = folderToSave + " bishkek " + date + ".jpg";

                    try (OutputStream outputStream = new FileOutputStream(fileName)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }

                    System.out.println("Изображение для " + date + " успешно сохранено.");

                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при скачивании изображения для " + date + ": " + e.getMessage());
        }
    }
    //Проверка даты на корректность
    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            System.out.println("Некорректная дата");
            return false;

        }

    }
    public static void getImagesForYears(List<Integer> years) throws IOException {
        for (int year : years) {
            String date = year + "-01-01";
            isValidDate(date);
            downloadPicture(date);

        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> years = List.of(2023, 2024, 2025);
        getImagesForYears(years);
    }
}
