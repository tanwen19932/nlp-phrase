package eshore.cn.it.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import eshore.cn.it.phrase.ActionConfig;
import eshore.cn.it.phrase.DocumentPhrases;


/**
 * <summary>SolrAction 是将提取的短语以及文本保存到Solr中的操作 </summary>
 * <author>clebeg</author>
 * <email>clebeg@163.com</email>
 * <create-date>2015/04/03 11:34</create-date>
 *
 */
public class SolrAction {
	//初始化solr客服端对象,这个对象全局是唯一的。这个类的所有方法都是静态的。
	private static SolrClient solr = new HttpSolrClient(ActionConfig.baseURL);
	
	/**
	 * 此方法将一系列的文档建立索引，注意对于同一个ID，solr 会建立不同的版本
	 * 另外自行控制DocumentPhrases的个数，此处将会全部提交，一次性。
	 * 考虑存储的格式为 
	 * id:title
	 * title: title(or fileName)
	 * content: content use jieba parse word
	 * phares: phare1=frequent1, phare2=frequent2,...
	 * @param lpds
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public static void  makeIndexes(List<DocumentPhrases> ldps) throws SolrServerException, IOException {
		List<SolrInputDocument> sids = new ArrayList<SolrInputDocument>();
		for (DocumentPhrases dps : ldps) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", dps.getDocumentName());
			doc.addField("title", dps.getDocumentName());
			doc.addField("content", dps.getContent());
			doc.addField("phrases", dps.toString());
			sids.add(doc);
		}
		solr.add(sids);
		solr.commit();
	}
}
