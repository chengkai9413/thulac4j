package com.github.thulac4j;

import com.github.thulac4j.base.SegItem;
import org.junit.Test;

import java.util.List;

import static com.github.thulac4j.SegmentTest.bugs;
import static com.github.thulac4j.SegmentTest.sentences;

/**
 * @author jyzheng
 */
public class PosTest {
  private SegPOSer poser = new SegPOSer("models/seg_pos.bin");

  public PosTest() throws Exception {
  }

  @Test
  public void posTest() {
    for (String sentence : sentences) {
      List<SegItem> result = poser.segment(sentence);
      System.out.println(result);
    }
  }


  @Test
  public void bugSentenceTest() {
    for (String sentence : bugs) {
      List<SegItem> result = poser.segment(sentence);
      System.out.println(result);
    }
  }
}
