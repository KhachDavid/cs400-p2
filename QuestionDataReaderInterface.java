import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;


public interface QuestionDataReaderInterface {
	List<Question> read(FileReader reader) throws IOException;
}

//public interface QuestionDataReaderInterface {
//	List<Question> read(FileReader reader) throws IOException;
//	int remove(Question question);
//	int add(Question question);
//}
