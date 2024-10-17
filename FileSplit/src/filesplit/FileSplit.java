package filesplit;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static Queue<String> splitFileContent(String content, int partSize) {
        Queue<String> queue = new LinkedList<>();
        int start = 0;
        
        while (start < content.length()) {
            int end = Math.min(start + partSize, content.length());
            queue.add(content.substring(start, end));
            start = end;
        }
        
        return queue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukkan lokasi file: ");
            String filePath = scanner.nextLine();

            String content = readFile(filePath);
            System.out.println("Konten file telah dibaca.");

            System.out.print("Masukkan ukuran setiap bagian (jumlah karakter): ");
            int partSize = scanner.nextInt();

            Queue<String> partsQueue = splitFileContent(content, partSize);

            int partNumber = 1;
            while (!partsQueue.isEmpty()) {
                System.out.println("Bagian " + partNumber + ":");
                System.out.println(partsQueue.poll());
                partNumber++;
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
