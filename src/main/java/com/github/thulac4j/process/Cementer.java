package com.github.thulac4j.process;

import com.github.thulac4j.base.SegItem;
import com.github.thulac4j.dat.Dat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jyzheng
 */
public class Cementer {
  private Dat dat;
  private String pos; // 词性

  public Cementer(String path, String pos) throws FileNotFoundException {
    dat = Dat.loadDat(path);
    this.pos = pos;
  }

  public Cementer(InputStream in, String pos) {
    dat = Dat.loadDat(in);
    this.pos = pos;
  }

  /**
   * 黏结词
   *
   * @param segmented 分词后结果
   */
  public void cement(List<String> segmented) {
    List<String> tmpList = new ArrayList<>();
    for (int i = 0; i < segmented.size(); i++) {
      if (!dat.isPrefixMatched(segmented.get(i))) continue;
      tmpList.clear();
      StringBuilder builder = new StringBuilder(segmented.get(i));
      for (int j = i + 1; j < segmented.size(); j++) {
        if (!dat.isPrefixMatched(segmented.get(j))) break;
        builder.append(segmented.get(j));
        tmpList.add(builder.toString());
      }
      for (int k = tmpList.size() - 1; k >= 0; k--) {
        if (dat.isWordMatched(tmpList.get(k))) {
          for (int j = i + 1; j < i + k + 2; j++)
            segmented.set(i, segmented.get(i) + segmented.get(j));
          for (int j = i + k + 1; j > i; j--)
            segmented.remove(j);
          break;
        }
      }
    }
  }

  // 黏结POS后的分词结果
  public void cementPos(List<SegItem> segmented) {
    List<String> tmpList = new ArrayList<>();
    for (int i = 0; i < segmented.size(); i++) {
      if (!dat.isPrefixMatched(segmented.get(i).word)) continue;
      tmpList.clear();
      StringBuilder builder = new StringBuilder(segmented.get(i).word);
      for (int j = i + 1; j < segmented.size(); j++) {
        if (!dat.isPrefixMatched(segmented.get(j).word)) break;
        builder.append(segmented.get(j).word);
        tmpList.add(builder.toString());
      }
      for (int k = tmpList.size() - 1; k >= 0; k--) {
        if (dat.isWordMatched(tmpList.get(k))) {
          for (int j = i + 1; j < i + k + 2; j++)
            segmented.get(i).word += segmented.get(j).word;
          for (int j = i + k + 1; j > i; j--)
            segmented.remove(j);
          segmented.get(i).pos = pos;
          break;
        }
      }
    }
  }
}
