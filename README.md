# thulac4j

thulac4j是[THULAC](http://thulac.thunlp.org/)的Java 8实现，相比于[THULAC-Java](https://github.com/thunlp/THULAC-Java)，我们做了如下工作：

1. 规范化分词词典，并去掉一些无用词；
2. 重写DAT（双数组Trie树）的构造算法，生成的DAT size减少了8%左右，从而节省了内存；
3. 简明化分词流程，便于功能扩展。


## 使用示例

在项目中使用thulac4j，添加依赖（目前正在申请入中央maven仓库）：

```xml
<dependency>
  <groupId>com.github.thulac4j</groupId>
  <artifactId>thulac4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

支持两种分词模式：

1. SegOnly模式，只分词没有词性标注；
2. SegPos模式，分词兼有词性标注。


```java
// segOnly mode
String sentence = "滔滔的流水，向着波士顿湾无声逝去";
Segmenter seg = new Segmenter("models/seg_only.bin");
System.out.println(seg.segment(sentence));
// [滔滔, 的, 流水, ，, 向着, 波士顿湾, 无声, 逝去]

// SegPos mode
SegPOSer pos = new SegPOSer("models/seg_pos.bin");
System.out.println(pos.segment(sentence));
//[滔滔/a, 的/u, 流水/n, ，/w, 向着/p, 波士顿湾/ns, 无声/v, 逝去/v]
```

分词需要下载训练模型`seg_only.bin`与`seg_pos.bin`，可在Github下载源码或在[这里](http://pan.baidu.com/s/1dFvHN4P)下载。SegOnly分词速度更快，但是准确率较SegPos模式要低（还没细致地测试）。SegPos具有更高的准确率，但是内存占用更多、分词速度稍慢。


此外，thulac4j还支持自定义词典：

```java
seg.setUserWordsPath("<user-words-path>");
```

支持繁体转简体：

```java
Simplifier simplifier = new Simplifier();
simplifier.t2s("世界商機大發現")
```


## ToDo

1. 优化分词词典；
2. 增加自定义分词规则；
3. 停用词过滤。

最后感谢THUNLP实验室！没有你们的努力，便没有这么好用的THULAC，也就没有thulac4j。


