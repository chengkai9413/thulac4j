package com.github.thulac4j.base;

import com.github.thulac4j.dat.Dat;
import com.github.thulac4j.dat.DatMaker;

import java.io.IOException;

/**
 * @author jyzheng
 */
public class Serialization {

  public static void main(String[] args) throws IOException {
    DatMaker maker = new DatMaker();
    Dat dat = maker.make("dicts/ns.dict");
    Util.serialize(dat, Util.nsDat);
    DatMaker maker1 = new DatMaker();
    dat = maker.make("dicts/idiom.dict");
    Util.serialize(dat, Util.idiomDat);
  }

}
