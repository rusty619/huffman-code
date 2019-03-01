
public class huffmanNode {

	int frequency;
	char c;
	huffmanNode left;
	huffmanNode right;
	
	public boolean isLeaf()
	{
		return left == null && right == null;
	}
}
