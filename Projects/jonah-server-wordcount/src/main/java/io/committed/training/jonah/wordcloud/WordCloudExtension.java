package io.committed.training.jonah.wordcloud;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import io.committed.invest.extensions.InvestGraphQlExtension;

@Configuration
@ComponentScan(basePackageClasses = WordCloudExtension.class)
public class WordCloudExtension implements InvestGraphQlExtension {

}
