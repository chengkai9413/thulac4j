package com.github.thulac4j;

import com.github.thulac4j.base.CwsModel;
import com.github.thulac4j.base.SegItem;
import com.github.thulac4j.base.Util;
import com.github.thulac4j.process.Flatter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jyzheng
 */
public class SegPOSer extends AbstractSegger {

  public SegPOSer(String modelPath) throws Exception {
    model = CwsModel.loadModel(modelPath);
    setUp();
  }

  public SegPOSer(InputStream in) throws Exception {
    model = CwsModel.loadModel(in);
    setUp();
  }


  @Override
  public List<SegItem> segment(String sentence) {
    List<SegItem> result = new ArrayList<>();
    int[] labels = sequenceLabel(sentence);
    if (labels == null) return result;
    int len = sentence.length();
    char poc;
    String word, label;
    for (int i = 0, offset = 0; i < len; i++) {
      label = model.labelValues[labels[i]];
      poc = label.charAt(0);
      if (poc == Util.POC_E || poc == Util.POC_S) {
        word = sentence.substring(offset, i + 1);
        label = label.substring(1);
        result.add(new SegItem(word, label));
        offset = i + 1;
      }
    }
    ns.cementPos(result);
    idiom.cementPos(result);
    if (uw != null) uw.cementPos(result);
    Flatter.flatPos(result);
    return result;
  }
}
