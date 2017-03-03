package com.github.thulac4j.base;

/**
 * @author jyzheng
 */
public enum ALLowLabels { // 各种组合情况下对应的POS
  B_POS, M_POS, E_POS, S_POS, BS_POS, ES_POS, DEFAULT_POS;

  public int[] labels;

  public static void setLabels(int[][] allowTabular) throws Exception {
    if (allowTabular.length != ALLowLabels.values().length) {
      throw new ExceptionInInitializerError("the size of model allowTabular != 7");
    }
    int i = 0;
    for (ALLowLabels a : ALLowLabels.values()) {
      a.labels = allowTabular[i];
      i++;
    }
  }


}
