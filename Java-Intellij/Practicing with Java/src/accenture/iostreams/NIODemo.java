package accenture.iostreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


public class NIODemo {
    public static void main(String[] args) throws IOException {

        try {
            Path path = Paths.get("src/accenture/Streams/files/StudentInfo.txt");

            List<String> strings = Files.readAllLines(path);
//            for (String string : strings) {
//                System.out.println(string);
//            }

            Iterator<String> iteratorList = strings.iterator();
            while(iteratorList.hasNext()){
                System.out.println(iteratorList.next());
            }

        } catch (IOException e){
            System.err.println("IOException " + e);
        }

    }
}
