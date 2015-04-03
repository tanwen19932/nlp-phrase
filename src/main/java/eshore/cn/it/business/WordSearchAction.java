package eshore.cn.it.business;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import eshore.cn.it.phrase.ActionConfig;

public class WordSearchAction {
	//初始化solr客服端对象,这个对象全局是唯一的。这个类的所有方法都是静态的。
	private static SolrClient solr = new HttpSolrClient(ActionConfig.baseURL);
	
	public static void search(String word) {
		
		SolrQuery params = new SolrQuery();
		params.set("q", "phrases:*" + word + "*");
		params.setHighlight(true).setHighlightSnippets(1); //set other params as needed
		params.setParam("hl.fl", "phrases");

		
		BufferedWriter bw = null;
		try {
			//保存查询结果
			bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("data/phrase20.txt")));
			//执行查询
			QueryResponse rsp = solr.query(params);
			
			//获取查询结果并且打印
			Iterator<SolrDocument> iter = rsp.getResults().iterator();

		    while (iter.hasNext()) {
		      SolrDocument resultDoc = iter.next();
		      String id = (String) resultDoc.getFieldValue("id"); //id is the uniqueKey field
		      if (rsp.getHighlighting().get(id) != null) {
		          List<String> highlightSnippets = rsp.getHighlighting().get(id).get("phrases");
		          bw.write(highlightSnippets + "\t" + resultDoc.getFieldValue("title"));
		          bw.newLine();
		      }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				bw = null;
				e.printStackTrace();
			}
		}
	}
}
