package eshore.cn.it.business;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.hankcs.hanlp.corpus.io.FolderWalker;

import eshore.cn.it.phrase.ActionConfig;
import eshore.cn.it.phrase.DocumentPhrases;

public class IndexAction {
	private static final PhraseAction phraseAction = new PhraseAction();
	public static void  doIndex() {
		List<DocumentPhrases> ldps = new ArrayList<DocumentPhrases>();

		long start = System.currentTimeMillis();
       
		List<File> fileList = FolderWalker.open(ActionConfig.DOCS_PATH);
        
		int i = 0;
        for (File file : fileList) {
            System.out.print(++i + " / " + fileList.size() + " " + file.getName() + " ");
            
            phraseAction.setFileName(file.getAbsolutePath());
            phraseAction.phraseAction();
            
            ldps.add(phraseAction.getDocumentPhrases());
            
            //当索引的数目达到可以提交的量的时候，就提交索引
            if (ldps.size() == ActionConfig.SOLR_COMMIT_NUM) {
            	
            	try {
					SolrAction.makeIndexes(ldps);
				} catch (SolrServerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            	ldps.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(fileList.size() + " 篇文档, 总耗时：" + (end - start) + "ms");
       
	}
}
