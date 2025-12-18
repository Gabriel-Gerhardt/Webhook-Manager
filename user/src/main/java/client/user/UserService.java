package client.user;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class UserService {


    public void notification(String payload) {
        String texto = "Um novo livro foi publicado-> "+ payload;
        String caminhoArquivo = "/home/gabriel-gerhardt/roadmap/spring/webhook/user/notify.txt";
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo,true))) {
            writer.write(texto);
            writer.newLine();
        } catch
        (IOException e) {
            e.printStackTrace();
        }
    }
}
