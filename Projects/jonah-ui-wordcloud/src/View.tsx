import * as React from 'react'
import ReactWordCloud from 'react-wordcloud'
import { Response } from './DataContainer'

type OwnProps = {
  data?: Response
}

type Props = OwnProps

export default class View extends React.Component<Props> {
  render() {
    const { data } = this.props

    if (!data) {
      return <p>No data</p>
    }

    const words = data.corpus.searchDocuments.hits.words

    if (words == null || words.length === 0) {
      return <p>No words found</p>
    }

    const words2 = words.map(w => ({ word: w.word, value: w.count }))

    return (
      <div style={{ width: '100%', height: 600 }}>
        <ReactWordCloud words={words2} wordCountKey="value" wordKey="word" />
      </div>
    )
  }
}
