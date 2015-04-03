package eshore.cn.it.business;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.hankcs.hanlp.corpus.occurrence.Occurrence;
import com.hankcs.hanlp.corpus.occurrence.PairFrequency;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

import eshore.cn.it.phrase.ActionConfig;
import eshore.cn.it.phrase.DocumentPhrases;

/**
 * <summary>PhraseAction 是处理文档提取短语的类</summary>
 * <author>clebeg</author>
 * <email>clebeg@163.com</email>
 * <create-date>2015/04/03 11:34</create-date>
 *
 */
public class PhraseAction {
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	private DocumentPhrases documentPhrases;
	public DocumentPhrases getDocumentPhrases() {
		return documentPhrases;
	}
	public void setDocumentPhrases(DocumentPhrases documentPhrases) {
		this.documentPhrases = documentPhrases;
	}
	
	
	public void phraseAction() {
		File tempFile = new File(this.getFileName());
		documentPhrases = new DocumentPhrases();
		
		try {
			documentPhrases.setContent(FileUtils.readFileToString(
								tempFile, 
								ActionConfig.FILE_ENCODING));
			extractPhrase(
					ActionConfig.PHRASES_NUM,
					getDocumentPhrases()
							);
			
			documentPhrases.setDocumentName(tempFile.getName());
		} catch (IOException e1) {
			documentPhrases = null;
			e1.printStackTrace();
		}
        System.out.println(documentPhrases);
	}
	public void extractPhrase(int size, DocumentPhrases dps) {
		
		Map<String, Integer> wn = new LinkedHashMap<String, Integer>();
		
        Occurrence occurrence = new Occurrence();
        Filter[] filterChain = new Filter[] {
            CoreStopWordDictionary.FILTER,
            new Filter() {
                public boolean shouldInclude(Term term) {
                    switch (term.nature) {
                        case t:
                        case nx:
                            return false;
                        default:
                        	return true;
                    } 
                }
            }
        };
        for (List<Term> sentence : NotionalTokenizer.seg2sentence(
        		dps.getContent(), filterChain)) {
            occurrence.addAll(sentence);
        }
        occurrence.compute();
        
        for (PairFrequency phrase : occurrence.getPhraseByScore()) {
            if (wn.size() == size) break;
            wn.put(phrase.first + phrase.second, phrase.getFrequency());
        }
        dps.setKeywordAndFrequent(wn);
	}

}
