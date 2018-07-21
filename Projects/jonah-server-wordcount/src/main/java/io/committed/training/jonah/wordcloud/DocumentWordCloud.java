package io.committed.training.jonah.wordcloud;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.HashMultiset;
import io.committed.invest.extensions.annotations.GraphQLService;
import io.committed.ketos.common.data.BaleenDocument;
import io.committed.ketos.common.graphql.output.Documents;
import io.committed.training.jonah.wordcloud.utils.StopwordChecker;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;

@GraphQLService
public class DocumentWordCloud {

  @GraphQLQuery(name = "words", description = "Count unique words")
  public List<WordCount> getWords(@GraphQLContext final BaleenDocument document,
      @GraphQLArgument(name = "minCount", defaultValue = "2") Integer minCount) {
    return countWords(document.getContent(), minCount);

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


  @GraphQLQuery(name = "words", description = "Count unique words for a collection of documents")
  public List<WordCount> getWordsForAllDocuments(@GraphQLContext final Documents documents,
      @GraphQLArgument(name = "minCount", defaultValue = "2") Integer minCount) {

    // Flux is like stream, we get all the results and then concatenate into a large string
    String all =
        documents.getResults().map(BaleenDocument::getContent).reduce("", String::concat).block();

    return countWords(all, minCount);

  }
}
