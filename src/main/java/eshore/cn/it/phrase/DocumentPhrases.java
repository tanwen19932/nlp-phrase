package eshore.cn.it.phrase;

import java.util.Map;


/**
 * <summary>DocumentPhrases 是记录文档提取短语之后的结果保存类</summary>
 * <author>clebeg</author>
 * <email>clebeg@163.com</email>
 * <create-date>2015/04/03 11:34</create-date>
 *
 */
public class DocumentPhrases {
	//提取短语的文本的名字，标识符，可以是文章标题
	private String documentName;
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	//记录关健词和词频信息，用一个map对应起来
	private Map<String, Integer> keywordAndFrequent;
	public Map<String, Integer> getKeywordAndFrequent() {
		return keywordAndFrequent;
	}

	public void setKeywordAndFrequent(Map<String, Integer> keywordAndFrequent) {
		this.keywordAndFrequent = keywordAndFrequent;
	}

	@Override
	public String toString() {
		if (this.keywordAndFrequent != null && this.keywordAndFrequent.size() > 0) {
			String result = "";
			for (Map.Entry<String, Integer> en : this.keywordAndFrequent.entrySet()) {
				result += en.getKey() + "=" + en.getValue() + ",";
			}
			return result.substring(0, result.length() - 1);
		}
		return null;
	}
	
}
