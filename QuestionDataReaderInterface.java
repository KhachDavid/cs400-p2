import java.util.List;
import java.io.FileReader;

public interface QuestionDataReaderInterface {
	List<QuestionInterface> read(FileReader reader);
	int remove(FileReader reader, int qid); // qid is the question id;
	int add(FileReader reader, int qid);
}
