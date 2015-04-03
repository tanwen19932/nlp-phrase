package eshore.cn.it.phrase;

/**
 * <summary>
 * 	ActionConfig 是程序配置类，以后改为自动从 .properties 文件中读取配置信息，
 *  目前暂时写成静态变量。
 * </summary>
 * <author>clebeg</author>
 * <email>clebeg@163.com</email>
 * <create-date>2015/04/03 11:34</create-date>
 *
 */
public class ActionConfig {
	//需要提取的短语个数
	public static final int PHRASES_NUM = 20;
	
	//需要分析的文本路径，以及文本的编码
	public static final String DOCS_PATH = "F:\\广州亿迅科技有限公司工作记录\\沈哥\\腾讯新闻";
	public static final String FILE_ENCODING = "GBK";
	
	//指定solr核的位置
	public static final String baseURL ="http://183.56.131.87:8983/solr/eshore_it_core";
	
	//指定提交给solr索引的数目，每次提交100条
	public static final int SOLR_COMMIT_NUM = 50;
}
