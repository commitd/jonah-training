package io.committed.training.jonah.wordcloud;

import com.google.common.base.Objects;

public class WordCount implements Comparable<WordCount> {

  private final long count;
  private final String word;

  public WordCount(String word, long count) {
    this.word = word;
    this.count = count;
  }

  public long getCount() {
    return count;
  }

  public String getWord() {
    return word;
  }

  @Override
  public int compareTo(WordCount o) {
    return Long.compare(getCount(), o.getCount());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(count, word);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    WordCount other = (WordCount) obj;
    if (count != other.count)
      return false;
    if (word == null) {
      if (other.word != null)
        return false;
    } else if (!word.equals(other.word))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s:%s", word, count);
  }

}

