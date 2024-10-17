package filesplit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {
    
    // Fungsi untuk membaca file teks dan memotongnya menjadi bagian-bagian
    public static void splitFile(String filePath, int chunkSize) {
        File file = new File(filePath);
        Queue<String> queue = new LinkedList<>();
        
        try {
            Scanner fileScanner = new Scanner(file);
            StringBuilder chunk = new StringBuilder();
            int lineCount = 0;

            // Baca file baris demi baris
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                chunk.append(line).append("\n");
                lineCount++;

                // Tambahkan chunk ke queue setiap mencapai ukuran yang diinginkan
                if (lineCount == chunkSize) {
                    queue.add(chunk.toString());
                    chunk = new StringBuilder();  // Reset chunk
                    lineCount = 0;
                }
            }

            // Tambahkan chunk terakhir jika masih ada sisa teks
            if (chunk.length() > 0) {
                queue.add(chunk.toString());
            }

            fileScanner.close();
            
            // Tampilkan hasil pemotongan
            int part = 1;
            while (!queue.isEmpty()) {
                System.out.println("== Part " + part + " ==");
                System.out.println(queue.poll());
                part++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + filePath);
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        
        // Input path file dan ukuran chunk dari pengguna
        System.out.print("Masukkan path file: ");
        String filePath = inputScanner.nextLine();
        
        System.out.print("Masukkan ukuran chunk (jumlah baris per bagian): ");
        int chunkSize = inputScanner.nextInt();

        // Memanggil fungsi untuk memotong file
        splitFile(filePath, chunkSize);

        inputScanner.close();
    }
}
