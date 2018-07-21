package io.committed.training.jonah.wordcloud;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.HashMultiset;
import io.committed.invest.extensions.annotations.GraphQLService;
import io.committed.ketos.common.data.BaleenDocument;
import io.committed.training.jonah.wordcloud.utils.StopwordChecker;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;

@GraphQLService
public class DocumentWordCloud {

  @GraphQLQuery(name = "words", description = "Count unique words")
  public List<WordCount> getWords(@GraphQLContext final BaleenDocument document) {
    return countWords(document.getContent(), 2);

  }

  private List<WordCount> countWords(String content, int minCount) {
    // Poor quality tokenisation
    String[] split = content.split("\\s+");


    // Count up the number of non-stopwords
    HashMultiset<String> multiset = HashMultiset.create();
    for (String w : split) {

      String s = w.toLowerCase();

      if (StopwordChecker.isNotStopWord(s)) {
        multiset.add(s);
      }
    }

    // Convert to a list of word counts
    List<WordCount> list = new ArrayList<>();
    for (String w : multiset.elementSet()) {

      int count = multiset.count(w);
      if (count >= minCount) {
        list.add(new WordCount(w, count));
      }
    }

    return list;

    // Or via streams...
    //
    // return Arrays.stream(split)
    // .filter(StopwordChecker::isNotStopWord)
    // .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
    // .entrySet()
    // .stream()
    // .map(e -> new WordCount(e.getKey(), e.getValue()))
    // .filter(c -> c.getCount() >= minCount)
    // .sorted()
    // .limit(size)
    // .collect(Collectors.toList());
  }

}
