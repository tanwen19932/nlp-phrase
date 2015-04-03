/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/9 16:43</create-date>
 *
 * <copyright file="TestPhrase.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package eshore.cn.it.phrase;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import eshore.cn.it.business.PhraseAction;
import junit.framework.TestCase;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * 测试从静安语料库提取短语
 * @author hankcs
 */
public class TestPhrase extends TestCase
{
    static final String FOLDER = "F:\\广州亿迅科技有限公司工作记录\\沈哥\\腾讯新闻";
    static final PhraseAction action = new PhraseAction();
    public void testExtract() throws Exception
    {	
    }

    public void testSingle() throws Exception
    {
        HanLP.Config.enableDebug();
        action.setFileName(FOLDER + "\\深圳严惩14种非正常上访 新华社发文吁商榷.txt");
        action.phraseAction();
    }

    public void testSeg() throws Exception
    {
        System.out.println(StandardTokenizer.segment(FileUtils.readFileToString(new File(FOLDER + "\\“希望同行”活动志愿者4000米海拔上门家访.txt"), "GBK")));
    }
}
