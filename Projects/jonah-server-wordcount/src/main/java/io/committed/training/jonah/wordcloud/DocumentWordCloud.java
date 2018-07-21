package io.committed.training.jonah.wordcloud;

import java.util.Collections;
import java.util.List;
import io.committed.invest.extensions.annotations.GraphQLService;
import io.committed.ketos.common.data.BaleenDocument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;

@GraphQLService
public class DocumentWordCloud {

  @GraphQLQuery(name = "words", description = "Count unique words")
  public List<WordCount> getWords(@GraphQLContext final BaleenDocument document) {
    return countWords(document.getContent(), 2);

  }

  private List<WordCount> countWords(String content, int minCount) {
    // TODO: Implementation
    return Collections.emptyList();
  }

}
