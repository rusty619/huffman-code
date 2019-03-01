import java.util.Comparator;

 public class comparator  implements Comparator<huffmanNode> {

	 @Override
	 public int compare(huffmanNode a,huffmanNode b)
	 {
		 return a.frequency - b.frequency;
	 }
}
