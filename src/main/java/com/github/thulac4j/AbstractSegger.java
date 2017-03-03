package com.github.thulac4j;

import com.github.thulac4j.process.Cementer;
import com.github.thulac4j.process.Decoder;
import com.github.thulac4j.base.ALLowLabels;
import com.github.thulac4j.base.CwsModel;
import com.github.thulac4j.base.Util;
import com.github.thulac4j.base.NGramFeature;
import com.github.thulac4j.process.Allower;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author jyzheng
 */
public abstract class AbstractSegger<T> {
  protected CwsModel model;
  protected List<Integer>[] labelTrans;
  protected Cementer ns;
  protected Cementer idiom;
  protected Cementer uw;


  protected void setUp() throws Exception {
    labelTrans = Util.labelPreTransitions(model.labelValues);
    ALLowLabels.setLabels(model.allowTabular);
    ns = new Cementer(this.getClass().getResourceAsStream(Util.nsDat), "ns");
    idiom = new Cementer(this.getClass().getResourceAsStream(Util.idiomDat), "i");
  }

  protected int[] sequenceLabel(String sentence) {
    if (sentence.length() == 0) return null;
    char[] chs = sentence.toCharArray();
    ALLowLabels[] allows = Allower.ruleAllow(chs);
    NGramFeature nGram = new NGramFeature(model.featureDat);
    int[][] values = nGram.putValues(model, chs);
    return Decoder.viterbi(model, chs, allows, values, labelTrans);
  }


  /**
   * 处理序列标注得到分词结果
   *
   * @param sentence 待分词句子
   * @return 词与词性的数组
   */
  public abstract List<T> segment(String sentence);


  public void setUserWordsPath(String path) throws FileNotFoundException {
    uw = new Cementer(path, "uw");
  }
}
