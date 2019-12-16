import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        while (true) {
            try (Socket socketClient1 = new Socket(InetAddress.getLocalHost(), 1025)) {
                //socketClient1.connect();
                System.out.println("Я (Клиент 1) подключился к серверу");

                // делаем потоки для ввода вывода в сеть
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketClient1.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketClient1.getOutputStream()));

                // делаем поток для чтения с клавиатуры
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Напечатай что-нибудь и нажми Enter\n");
                String line = null;
                line = keyboard.readLine();
                if (line.equals("quit")) break;
                System.out.println("Отправляем эту строку серверу");
                bufferedWriter.write(line + "\n");
                bufferedWriter.flush();
                String string1 = bufferedReader.readLine();
                System.out.println(string1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
